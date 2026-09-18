package com.andormix.cashcard;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController //This tells Spring that this class is a Component of type RestController and capable of handling HTTP requests.
//Also it tells Spring's component scanner: "Create and manage a bean for this class. " Autowiring
@RequestMapping("/cashcards") // This is a companion to @RestController that indicates which address requests must have to access this Controller.
public class CashCardController {

    private final CashCardRepository cashCardRepository;

    // Some Magic here - Auto Configuration and Construction Injection!  extends CrudRepository<CashCard, Long>
    private CashCardController(CashCardRepository cashCardRepository)
    {
        this.cashCardRepository = cashCardRepository;
    }

    /*@GetMapping()
    private ResponseEntity<Iterable<CashCard>> findAll()
    {
        return ResponseEntity.ok(cashCardRepository.findAll());
    }*/

    @GetMapping
    private ResponseEntity<List<CashCard>> findAll(Pageable pageable, Principal principal) {
        Page<CashCard> page = cashCardRepository.findByOwner(principal.getName(),
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        //pageable.getSort() // SORT TEST ADDITION
                        pageable.getSortOr(Sort.by(Sort.Direction.ASC, "amount"))
                ));
        return ResponseEntity.ok(page.getContent());
    }

    @GetMapping("/{requestedId}") //@GetMapping marks a method as a handler method. GET requests that match cashcards/{requestedID} will be handled by this method.
    private ResponseEntity<CashCard> findById(@PathVariable Long requestedId, Principal principal) {

       CashCard cashCard = findCashCard(requestedId, principal);

        if (cashCard != null )
        {
            return ResponseEntity.ok(cashCard);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    /*
    RFC 9110
    If one or more resources has been created on the origin server as a result of successfully processing a POST request,
    the origin server SHOULD send a 201 (Created) response containing a Location header field that provides an identifier f
    or the primary resource created ...
     */

    // UriComponentsBuilder ucb Injected by IoC
    @PostMapping
    private ResponseEntity<Void> createCashCard(@RequestBody CashCard newCashCardRequest, UriComponentsBuilder ucb, Principal principal)
    {
        CashCard cashCardWithOwner = new CashCard(null, newCashCardRequest.amount(), principal.getName());

        CashCard savedCashCard = cashCardRepository.save(cashCardWithOwner);

        URI locationOfNewCashCard = ucb.path("cashcards/{id}").buildAndExpand(savedCashCard.id()).toUri();

        return ResponseEntity.created(locationOfNewCashCard).build();
    }

    @PutMapping("/{requestedId}")
    private ResponseEntity<Void> updateCashCard(@PathVariable Long requestedId, @RequestBody CashCard newCashCardRequest, Principal principal){

       CashCard cashCard = findCashCard(requestedId, principal);

        if (cashCard != null)
        {
            // Construyo el objeto actualizado usando el ID existente y el owner autenticado (SEGURIDAD)
            CashCard updatedCashCard = new CashCard(cashCard.id(), newCashCardRequest.amount(), principal.getName());

            // 2. Guardo los cambios en la BD (ejecuta un UPDATE al tener ID)
            cashCardRepository.save(updatedCashCard);

            // UPDATE
            return ResponseEntity.noContent().build();
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{requestedId}")
    private ResponseEntity<Void> updateCashCard(@PathVariable Long requestedId, Principal principal)
    {
        CashCard cashCard = findCashCard(requestedId, principal);

        if(cashCard != null)
        {
            // 2. Guardo los cambios en la BD (HARD DELETION WITH NO AUDIT OR BOOL VALUE IN TABLE IS_DELETED)
            cashCardRepository.delete(cashCard);
            return ResponseEntity.noContent().build();

            // Aquí pondría la log de auditoría.

        }
        return ResponseEntity.notFound().build();
    }

    // HELPERS

    private CashCard findCashCard(Long requestedId, Principal principal)
    {
        return cashCardRepository.findByIdAndOwner(requestedId, principal.getName());
    }
}
