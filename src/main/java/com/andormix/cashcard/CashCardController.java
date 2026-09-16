package com.andormix.cashcard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
