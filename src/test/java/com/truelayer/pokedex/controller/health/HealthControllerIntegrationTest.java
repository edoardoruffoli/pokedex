package com.truelayer.pokedex.controller.health;

import com.truelayer.pokedex.BaseIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HealthControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private Integer port;
    @Test
    @DisplayName("Service health check")
    void getHealthStatus() {
        ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:%d/health".formatted(port), String.class);
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.hasBody()).isTrue();
        assertThat(response.getBody()).isEqualTo("I am alive!");
    }
}
