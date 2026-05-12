package org.example.blogsystem_lab11.Service;

import lombok.RequiredArgsConstructor;
import org.example.blogsystem_lab11.Api.ApiException;
import org.example.blogsystem_lab11.Model.User;
import org.example.blogsystem_lab11.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


        public List<User> getAll() { return userRepository.findAll(); }

        public void addUser(User user) { userRepository.save(user); }

        public void updateUser(Integer id, User user) {
            User old = userRepository.giveMeUserById(id);
            if (old == null) throw new ApiException("User not found");
            old.setUsername(user.getUsername());
            old.setPassword(user.getPassword());
            old.setEmail(user.getEmail());
            userRepository.save(old);
        }

        public void deleteUser(Integer id) {
            User user = userRepository.giveMeUserById(id);
            if (user == null) throw new ApiException("User not found");
            userRepository.delete(user);
        }

        //Extra
        public User getUserByEmail(String email) {
        User user = userRepository.giveMeUserByEmail(email);
        if (user == null) throw new ApiException("User with this email not found");
        return user;
    }

        public User getUserByUsername(String username) {
        User user = userRepository.giveMeUserByUsername(username);
        if (user == null) throw new ApiException("User not found");
        return user;
    }
    }


