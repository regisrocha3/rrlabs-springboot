package dev.rrlabs.hellorrlabs.user.api;

import dev.rrlabs.hellorrlabs.user.api.resource.UserResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
public interface UserApi {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> create(@RequestBody UserResource resource);

    @GetMapping("/{email}")
    ResponseEntity<UserResource> findById(@PathVariable("email") String email);

    @GetMapping
    ResponseEntity<List<UserResource>> find(UserResource filter);

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> update(@RequestBody UserResource resource);

    @DeleteMapping("/{email}")
    ResponseEntity<UserResource> delete(@PathVariable("email") String email);

}
