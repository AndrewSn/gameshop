package com.gameshop.Controller;

import com.gameshop.entity.User;
import com.gameshop.exception.ResourceNotFoundException;
import com.gameshop.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepo userRepo;

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @PostMapping("/")
    public User createUser(@Valid @RequestBody User user) {
        return userRepo.save(user);
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable(value = "userId") Long userId) {
        return userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable(value = "userId") Long userId, @Valid @RequestBody User userDetails) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
        user.setNameOfUser(userDetails.getNameOfUser());
        user.setPasswordOfUser(userDetails.getPasswordOfUser());
        user.setEmailOfUser(userDetails.getEmailOfUser());
        user.setLastUpdate(userDetails.getLastUpdate());
        user.setPersonalDiscountOfUser(userDetails.getPersonalDiscountOfUser());
        return userRepo.save(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "userId") Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
        userRepo.delete(user);
        return ResponseEntity.ok().build();
    }

}
