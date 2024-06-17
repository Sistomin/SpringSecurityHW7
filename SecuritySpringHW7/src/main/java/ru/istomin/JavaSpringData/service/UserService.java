package ru.istomin.JavaSpringData.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.istomin.JavaSpringData.model.Issue;
import ru.istomin.JavaSpringData.model.Reader;
import ru.istomin.JavaSpringData.model.Role;
import ru.istomin.JavaSpringData.model.User;
import ru.istomin.JavaSpringData.repository.IssueRepository;
import ru.istomin.JavaSpringData.repository.ReaderRepository;
import ru.istomin.JavaSpringData.repository.UserRepository;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // получить пользователя по id
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    // получить пользователя по логину
    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login).orElseThrow(EntityNotFoundException::new);
    }

    //получить список всех пользователей
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // создание пользователя
    public User addUser(User user){
//        user.getRoles().add(role);
        return userRepository.save(user);
    }

    //обновление пользователя
    @Transactional
    public User updateUsers(Long id, User user) {
        User updateUser = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
        updateUser.setLogin(user.getLogin());
        return userRepository.save(updateUser);
    }

    // удаление пользователя
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


}
