package dev.rrlabs.hellorrlabs.user.controller;

import dev.rrlabs.hellorrlabs.config.exception.NotFoundException;
import dev.rrlabs.hellorrlabs.user.api.UserApi;
import dev.rrlabs.hellorrlabs.user.api.mapper.UserMapper;
import dev.rrlabs.hellorrlabs.user.api.resource.UserResource;
import dev.rrlabs.hellorrlabs.user.domain.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class UserController implements UserApi {

    @Override
    public ResponseEntity<Void> create(UserResource resource) {
        log.info("Recendo informacoes do usuario a ser criado: {}", resource);

        User parse = UserMapper.INSTANCE.parse(resource);
        log.info("Parser do objeto: {}", parse);

        // TODO: Chamar servico que vai persistir esse usuario

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<UserResource> findById(String email) {
        log.info("Recendo informacoes para consulta de usuario {}", email);

        return ResponseEntity.ok(UserResource.builder().phoneNumber("11 239273")
                        .name("Regis").email(email).build());
    }

    @Override
    public ResponseEntity<List<UserResource>> find(UserResource filter) {
        log.info("Recendo informacoes para consulta de usuarios {}", filter);

        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Void> update(UserResource resource) {
        log.info("Recendo informacoes para atualizar o usuario {}", resource);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<UserResource> delete(String email) {
        log.info("Recendo informacoes para deletar um usuario {}", email);

        return ResponseEntity.ok().build();
    }
}
