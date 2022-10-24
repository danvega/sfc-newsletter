package dev.danvega.newsletter.functions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ListSubscribersTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldFindAllSubscribers() {
        final ResponseEntity<List<String>> response = restTemplate.exchange("/", HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {});
        List<String> emails = response.getBody();
        assertNotNull(emails);
        assertEquals(3,emails.size());
    }
}