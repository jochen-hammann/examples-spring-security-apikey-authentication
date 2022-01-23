package com.example.spring.security.apikeyauthentication.hello;

import com.example.spring.security.apikeyauthentication.constants.ConstHttpHeaders;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

// TestRestTemplate requires a webEnvironment !!!
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloControllerTestRestTemplateIT
{
    // ============================== [Fields] ==============================

    // -------------------- [Private Fields] --------------------

    @Autowired
    private TestRestTemplate restTemplate;

    // ============================== [Unit Tests] ==============================

    // -------------------- [Test Helper Classes] --------------------

    // -------------------- [Test Helper Methods] --------------------

    private ResponseEntity<String> makeRestCall(String apikey)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.add(ConstHttpHeaders.APIKEY_HEADER, apikey);

        HttpEntity<Void> httpEntity = new HttpEntity<>(headers);

        return restTemplate.exchange("/hello", HttpMethod.GET, httpEntity, String.class);
    }

    // -------------------- [Test Initialization] --------------------

    // -------------------- [Tests] --------------------

    @Test
    void getTest()
    {
        ResponseEntity<String> response = makeRestCall("4712");

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        System.out.println(response.getBody());
    }

    @Test
    void authenticationError()
    {
        ResponseEntity<String> response = makeRestCall("NotExisting");

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());

        System.out.println(response.getBody());
    }

    @Test
    void authorizationError()
    {
        ResponseEntity<String> response = makeRestCall("4713");

        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());

        System.out.println(response.getBody());
    }
}
