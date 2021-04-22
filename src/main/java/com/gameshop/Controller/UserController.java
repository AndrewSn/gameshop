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

    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") Long id) {
        return userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable(value = "id") Long id, @Valid @RequestBody User userDetails) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        user.setNameOfUser(userDetails.getNameOfUser());
        user.setPasswordOfUser(userDetails.getPasswordOfUser());
        user.setEmailOfUser(userDetails.getEmailOfUser());
        user.setLastUpdate(userDetails.getLastUpdate());
        user.setPersonalDiscountOfUser(userDetails.getPersonalDiscountOfUser());

        return userRepo.save(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        userRepo.delete(user);
        return ResponseEntity.ok().build();
    }

}
