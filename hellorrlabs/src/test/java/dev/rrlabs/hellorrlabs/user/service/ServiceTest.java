package dev.rrlabs.hellorrlabs.user.service;

import dev.rrlabs.hellorrlabs.user.domain.document.User;
import dev.rrlabs.hellorrlabs.user.domain.repository.UserRepository;
import dev.rrlabs.hellorrlabs.user.domain.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;

@SpringBootTest
@Profile("test")
public class ServiceTest {

    @Autowired private UserService userService;
    @MockBean private UserRepository userRepository;

    @Test
    public void testCreate() {
        Mockito.when(this.userRepository.findByEmail("regisrocha3@gmail.com")).thenReturn(User.builder()
                .name("blala").email("test@test.com").build());

        User byEmail = this.userService.findByEmail("regisrocha3@gmail.com");

        Assertions.assertEquals("blala", byEmail.getName());
    }

}
