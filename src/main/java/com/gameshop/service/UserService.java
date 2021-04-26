package com.gameshop.service;

import com.gameshop.entity.User;
import com.gameshop.exception.ResourceNotFoundException;
import com.gameshop.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User updateUser(Long userId, User userRequest){
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
        user.setNameOfUser(userRequest.getNameOfUser());
        user.setPasswordOfUser(userRequest.getPasswordOfUser());
        user.setEmailOfUser(userRequest.getEmailOfUser());
        user.setLastUpdate(userRequest.getLastUpdate());
        user.setPersonalDiscountOfUser(userRequest.getPersonalDiscountOfUser());
        return userRepo.save(user);
    }

    public ResponseEntity<Object> deleteUser(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
        userRepo.delete(user);
        return ResponseEntity.ok().build();
    }
}
