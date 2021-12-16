package dev.rrlabs.hellorrlabs.user.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.rrlabs.hellorrlabs.config.json.ObjectMapperConfig;
import dev.rrlabs.hellorrlabs.user.api.resource.UserResource;
import dev.rrlabs.hellorrlabs.user.domain.document.User;
import dev.rrlabs.hellorrlabs.user.domain.service.UserService;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = {UserController.class})
@ActiveProfiles("test")
public class UserControllerWebMvcTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private UserService userService;

    @Test
    public void testError() {
        Mockito.doThrow(RuntimeException.class).when(this.userService).save(ArgumentMatchers.any());

        Assertions.assertThrows(RuntimeException.class, () -> this.userService.save(ArgumentMatchers.any()));
    }

    @Test
    @SneakyThrows
    public void findUserTest() {
        Mockito.when(this.userService.findByEmail("regisrocha3@gmail.com"))
                .thenReturn(User.builder().id(UUID.randomUUID().toString()).email("regisrocha3@gmail.com")
                        .name("Regis").phoneNumber("mock number").birthdate(LocalDate.now()).build());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/user/regisrocha3@gmail.com"))
                .andExpect(status().isOk()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Regis")));
    }

    @Test
    @SneakyThrows
    public void createTest() {
        /*UserResource regis = UserResource.builder().birthdate(LocalDate.of(2000, Month.JUNE, 3))
                .name("Regis").email("regis@gmail.com").phoneNumber("23423423")
                .build();*/

        UserResource regis = new UserResource();
        regis.setPhoneNumber("23423423");
        regis.setName("Regis");
        regis.setEmail("regis@gmail.com");
        regis.setBirthdate(LocalDate.of(2000, Month.JUNE, 3));

        String json = new ObjectMapperConfig().objectMapper().writeValueAsString(regis);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andExpect(status().isCreated());

    }

}
