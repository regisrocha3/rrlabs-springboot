package dev.rrlabs.hellorrlabs.user.domain.repository;

import dev.rrlabs.hellorrlabs.user.domain.document.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);

    @Query(value = "{email: ?0}")
    User findEmail(String email);
}
