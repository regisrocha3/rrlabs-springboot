package dev.rrlabs.hellorrlabs.user.domain.service;

import dev.rrlabs.hellorrlabs.user.domain.document.User;
import dev.rrlabs.hellorrlabs.user.domain.repository.UserCustomRepository;
import dev.rrlabs.hellorrlabs.user.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired private UserRepository userRepository;
    @Autowired private UserCustomRepository userCustomRepository;

    public void save(User user) {
        this.userRepository.save(user);
    }

    public User findByEmail(String email) {
        //return this.userRepository.findByEmail(email);
        return this.userRepository.findEmail(email);
    }

    public List<User> search(User filter) {
        return this.userCustomRepository.search(filter);
    }

    public void update(User userToUpdate) {
        User byEmail = this.userRepository.findByEmail(userToUpdate.getEmail());
        userToUpdate.setId(byEmail.getId());
        this.userRepository.save(userToUpdate);
    }

    public void delete(String email) {
        User byEmail = this.userRepository.findByEmail(email);
        this.userRepository.delete(byEmail);
    }
}
