package dev.rrlabs.hellorrlabs.user.service;

import dev.rrlabs.hellorrlabs.user.domain.document.User;
import dev.rrlabs.hellorrlabs.user.domain.repository.UserRepository;
import dev.rrlabs.hellorrlabs.user.domain.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

@SpringBootTest
@Profile("test")
public class UserServiceTest {

    @Autowired private UserService userService;
    @Autowired private UserRepository userRepository;

    @Test
    public void createTest() {
        final User user = new User();
        user.setEmail("regisrocha3@gmail.com");
        user.setName("Regis");
        user.setBirthdate(LocalDate.of(2000, 3,3));
        user.setPhoneNumber("12312321");

        this.userService.save(user);

        Assertions.assertEquals(1, this.userRepository.findAll().size());
    }

}
