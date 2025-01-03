package com.AliGrp.Journal.service;

import com.AliGrp.Journal.entity.User;
import com.AliGrp.Journal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User saveUser(User user, String role) {
        user.setCreated(LocalDate.now());
        user.setRoles(role);
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }
    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
    }
    public User updateUser(User user,String id) {
        User userToUpdate = userRepository.findById(id).orElse(null);
        if (userToUpdate != null) {
            userToUpdate.setId(id);
            userToUpdate.setRoles("STUDENT");
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setPassword(encoder.encode(user.getPassword()));
            return userRepository.save(userToUpdate);
        }
        return null;
    }
    public User AupdateUser(User user,String id) {
        User userToUpdate = userRepository.findById(id).orElse(null);
        if (userToUpdate != null) {
            userToUpdate.setId(id);
            userToUpdate.setRoles(user.getRoles());
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setPassword(encoder.encode(user.getPassword()));
            return userRepository.save(userToUpdate);
        }
        return null;
    }
    public String loginUser(User user) {
        User userFound = userRepository.findByUsername(user.getUsername());
        if (userFound != null && encoder.matches(user.getPassword(), userFound.getPassword())) {
            if(userFound.getRoles().equals("ADMIN")) {
                return "ADMIN";
            }
            else if(userFound.getRoles().equals("PARENT")) {
                return "PARENT";
            }
            else{
                return "STUDENT";
            }
            
        }
        return "";
    }
}
