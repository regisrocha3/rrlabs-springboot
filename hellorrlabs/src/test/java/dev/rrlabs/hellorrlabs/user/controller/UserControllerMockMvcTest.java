package dev.rrlabs.hellorrlabs.user.controller;

import dev.rrlabs.hellorrlabs.config.json.ObjectMapperConfig;
import dev.rrlabs.hellorrlabs.user.api.resource.UserResource;
import dev.rrlabs.hellorrlabs.user.domain.document.User;
import dev.rrlabs.hellorrlabs.user.domain.repository.UserRepository;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerMockMvcTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private UserRepository userRepository;

    @Test
    @SneakyThrows
    public void findTest() {
        this.userRepository.save(User.builder().email("regisrocha30@gmail.com").name("Regis")
                .id("43242").birthdate(LocalDate.now()).phoneNumber("23423").build());

        String json = this.mockMvc.perform(MockMvcRequestBuilders.get("/user/regisrocha30@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        UserResource userResource = new ObjectMapperConfig().objectMapper().readValue(json, UserResource.class);

        Assertions.assertEquals("Regis", userResource.getName());
    }

    @Test
    @SneakyThrows
    public void findUsingExpectJsonPathTest() {
        this.userRepository.save(User.builder().email("regisrocha31@gmail.com").name("Regis").build());

        mockMvc.perform(MockMvcRequestBuilders.get("/user/regisrocha31@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.is("Regis")));
    }

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

        Assertions.assertTrue(all.size() >= 1);
        System.out.println(all.get(0));

    }

}
