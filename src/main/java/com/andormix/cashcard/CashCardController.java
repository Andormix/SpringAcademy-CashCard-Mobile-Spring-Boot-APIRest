package com.andormix.cashcard;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
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
    private ResponseEntity<List<CashCard>> findAll(Pageable pageable) {
        Page<CashCard> page = cashCardRepository.findAll(
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        //pageable.getSort() // SORT TEST ADDITION
                        pageable.getSortOr(Sort.by(Sort.Direction.ASC, "amount"))
                ));
        return ResponseEntity.ok(page.getContent());
    }




    @GetMapping("/{requestedId}") //@GetMapping marks a method as a handler method. GET requests that match cashcards/{requestedID} will be handled by this method.
    private ResponseEntity<CashCard> findById(@PathVariable Long requestedId) {


        Optional<CashCard> cashCardCOptional = cashCardRepository.findById(requestedId);

        if (cashCardCOptional.isPresent())
        {
            return ResponseEntity.ok(cashCardCOptional.get());
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
    private ResponseEntity<Void> createCashCard(@RequestBody CashCard newCashCardRequest, UriComponentsBuilder ucb)
    {
        CashCard savedCashCard = cashCardRepository.save(newCashCardRequest);

        URI locationOfNewCashCard = ucb.path("cashcards/{id}").buildAndExpand(savedCashCard.id()).toUri();

        return ResponseEntity.created(locationOfNewCashCard).build();
    }
}
