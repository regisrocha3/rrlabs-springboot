package dev.rrlabs.hellorrlabs.user.controller;

import dev.rrlabs.hellorrlabs.config.json.ObjectMapperConfig;
import dev.rrlabs.hellorrlabs.user.api.resource.UserResource;
import dev.rrlabs.hellorrlabs.user.domain.document.User;
import dev.rrlabs.hellorrlabs.user.domain.repository.UserRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserControllerWebEnvironmentTest {

    @Autowired private UserRepository userRepository;
    @Autowired private TestRestTemplate testRestTemplate;

    @Test
    @SneakyThrows
    public void createTest() {
        UserResource regis = UserResource.builder().birthdate(LocalDate.of(2000, Month.JUNE, 3))
                .name("Regis").email("regis@gmail.com").phoneNumber("23423423").build();

        String json = new ObjectMapperConfig().objectMapper().writeValueAsString(regis);

        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);

        ResponseEntity<Void> objectResponseEntity = this.testRestTemplate.postForEntity("/user", entity, Void.class);
        Assertions.assertEquals(201, objectResponseEntity.getStatusCode().value());

        List<User> all = this.userRepository.findAll();
        Assertions.assertEquals(1, all.size());
    }

}
