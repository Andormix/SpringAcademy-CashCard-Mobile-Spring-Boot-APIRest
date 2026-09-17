package com.andormix.cashcard;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
class AndormixCashCardAppProjectApplicationTests {

    @Autowired
    // cliente HTTP diseñado específicamente para pruebas de integración en Spring Boot.
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnACashCardWhenDataIsSaved() {

        // getForEntity Petición HTTP real (local): Envía un GET http://localhost:<puerto>/cashcards/99 al servidor Spring Boot interno que se levantó para los tests.
        ResponseEntity<String> response = restTemplate.getForEntity("/cashcards/99", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());

        Number id = documentContext.read("$.id");
        assertThat(id).isEqualTo(99);

        Double amount = documentContext.read("$.amount");
        assertThat(amount).isEqualTo(123.45);
    }

    @Test
    void shouldNotReturnACashCardWithAnUnknownId() {
        ResponseEntity<String> response = restTemplate.getForEntity("/cashcards/1000", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isBlank();
    }

    @Test
    void shouldCreateANewCashCard()
    {

        //Supplying an id to cashCardRepository.save is supported when an update is performed on an existing resource.
        CashCard newCashCard = new CashCard(null, 250.00);
        ResponseEntity<Void> createResponse = restTemplate.postForEntity("/cashcards", newCashCard, Void.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        //Check that exists
        URI locationOfNewCashCard = createResponse.getHeaders().getLocation();
        ResponseEntity<String> getResponse = restTemplate.getForEntity(locationOfNewCashCard, String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        //Check contents
        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
        Number id = documentContext.read("$.id");
        Double amount = documentContext.read("$.amount");
        assertThat(id).isNotNull();
        assertThat(amount).isNotNull();
        assertThat(amount).isEqualTo(250.00);
    }

    /*

    * [1. Test] restTemplate.getForEntity("/cashcards/99", String.class)
    │
    ▼  (Envía petición HTTP GET simulada)
    [2. Spring Server] Mapea la URL a CashCardController
    │
    ▼  (Ejecuta findById(99L))
    [3. Controller] Devuelve ResponseEntity.ok(new CashCard(99L, 123.45))
    │
    ▼  (Jackson serializa CashCard a JSON: {"id":99,"amount":123.45})
    [4. Test] Recibe la respuesta HTTP y la guarda en la variable 'response'
    *
    */
}