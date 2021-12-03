package dev.rrlabs.hellorrlabs.user.domain.repository;

import dev.rrlabs.hellorrlabs.user.domain.document.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserCustomRepository {

    @Autowired private MongoTemplate mongoTemplate;


    public List<User> search(User filter) {
        Criteria criteria = new Criteria();

        addFilterName(filter, criteria);
        addFilterEmail(filter, criteria);
        addFilterPhoneNumber(filter, criteria);

        return this.mongoTemplate.find(new Query(criteria), User.class);
    }

    private void addFilterPhoneNumber(User filter, Criteria criteria) {
        if (StringUtils.isNotBlank(filter.getPhoneNumber())) {
            criteria.and("phoneNumber").is(filter.getPhoneNumber());
        }
    }

    private void addFilterEmail(User filter, Criteria criteria) {
        if (StringUtils.isNotBlank(filter.getEmail())) {
            criteria.and("email").is(filter.getEmail());
        }
    }

    private void addFilterName(User filter, Criteria criteria) {
        if (StringUtils.isNotBlank(filter.getName())) {
            criteria.and("name").is(filter.getName());
        }
    }

    public void update(User userToUpdate) {
        Criteria criteria = Criteria.where("email").is(userToUpdate.getEmail());

        Update update = new Update();
        update.set("name", userToUpdate.getName());

        this.mongoTemplate.updateFirst(new Query(criteria), update, User.class);
    }

    public void delete(String email) {
        Criteria criteria = Criteria.where("email").is(email);
        this.mongoTemplate.remove(new Query(criteria), User.class);
    }
}
