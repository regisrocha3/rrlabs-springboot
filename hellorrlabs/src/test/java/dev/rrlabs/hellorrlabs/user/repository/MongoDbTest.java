package dev.rrlabs.hellorrlabs.user.repository;

import dev.rrlabs.hellorrlabs.user.domain.document.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

@DataMongoTest
public class MongoDbTest {

    @Autowired private MongoTemplate mongoTemplate;

    @Test
    public void testMongoDb() {
        this.mongoTemplate.insert(User.builder().name("Theo Rocha").build());

        List<User> all = this.mongoTemplate.findAll(User.class);

        Assertions.assertEquals(1, all.size());
    }

}
