package com.gameshop.Controller;

import com.gameshop.entity.User;
import com.gameshop.exception.ResourceNotFoundException;
import com.gameshop.repository.UserRepo;
import com.gameshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    UserRepo userRepo;
    UserService userService;

    @Autowired
    public UserController(UserRepo userRepo, UserService userService) {
        this.userRepo = userRepo;
        this.userService = userService;
    }

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
        return userService.updateUser(userId, userDetails);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "userId") Long userId) {
       return userService.deleteUser(userId);
    }

}
