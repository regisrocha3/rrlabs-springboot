package dev.rrlabs.hellorrlabs.user.controller;

import dev.rrlabs.hellorrlabs.user.api.UserApi;
import dev.rrlabs.hellorrlabs.user.api.mapper.UserMapper;
import dev.rrlabs.hellorrlabs.user.api.resource.UserResource;
import dev.rrlabs.hellorrlabs.user.domain.document.User;
import dev.rrlabs.hellorrlabs.user.domain.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class UserController implements UserApi {

    @Autowired private UserService userService;

    @Override
    public ResponseEntity<Void> create(UserResource resource) {
        log.info("Recendo informacoes do usuario a ser criado: {}", resource);

        final User user = UserMapper.INSTANCE.parse(resource);
        log.info("Parser do objeto: {}", user);

        this.userService.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<UserResource> findByEmail(String email) {
        log.info("Recendo informacoes para consulta de usuario {}", email);

        User user = this.userService.findByEmail(email);

        return ResponseEntity.ok(UserMapper.INSTANCE.parse(user));
    }

    @Override
    public ResponseEntity<List<UserResource>> find(UserResource filter) {
        log.info("Recendo informacoes para consulta de usuarios {}", filter);

        final List<User> users = this.userService.search(UserMapper.INSTANCE.parse(filter));

        return ResponseEntity.ok(UserMapper.INSTANCE.parse(users));
    }

    @Override
    public ResponseEntity<Void> update(UserResource resource) {
        log.info("Recendo informacoes para atualizar o usuario {}", resource);

        this.userService.update(UserMapper.INSTANCE.parse(resource));

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<UserResource> delete(String email) {
        log.info("Recendo informacoes para deletar um usuario {}", email);

        this.userService.delete(email);

        return ResponseEntity.ok().build();
    }
}
