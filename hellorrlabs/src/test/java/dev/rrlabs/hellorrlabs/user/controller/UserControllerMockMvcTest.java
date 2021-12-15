package dev.rrlabs.hellorrlabs.user.controller;

import dev.rrlabs.hellorrlabs.config.json.ObjectMapperConfig;
import dev.rrlabs.hellorrlabs.user.api.resource.UserResource;
import dev.rrlabs.hellorrlabs.user.domain.document.User;
import dev.rrlabs.hellorrlabs.user.domain.repository.UserRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerMockMvcTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private UserRepository userRepository;

    @Test
    @SneakyThrows
    public void createTest() {
        UserResource regis = UserResource.builder().birthdate(LocalDate.of(2000, Month.JUNE, 3))
                .name("Regis").email("regis@gmail.com").phoneNumber("23423423")
                .build();

        String json = new ObjectMapperConfig().objectMapper().writeValueAsString(regis);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andExpect(status().isCreated());

        List<User> all = this.userRepository.findAll();

        Assertions.assertEquals(1, all.size());
        System.out.println(all.get(0));

    }

}
