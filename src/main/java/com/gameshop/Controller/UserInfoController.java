package com.gameshop.Controller;

import com.gameshop.dto.OrderDto;
import com.gameshop.entity.Order;
import com.gameshop.entity.User;
import com.gameshop.entity.UserInfo;
import com.gameshop.exception.ResourceNotFoundException;
import com.gameshop.repository.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/info")
public class UserInfoController {

    @Autowired
    UserInfoRepo userInfoRepo;

    private UserInfo toEntity(OrderDto dto) {
        UserInfo entity = new UserInfo();
        entity.setName(dto.getName());
        entity.setLastname(dto.getSurname());
        entity.setPhoneNumber(dto.getPhone());
        entity.setCity(dto.getCity());
        entity.setBranchNumber(dto.getBranchNumber());
        return entity;
    }


    @GetMapping("/")
    public List<UserInfo> search() {
        return userInfoRepo.findAll();
    }

    @PostMapping("/")
    public UserInfo createUser(@Valid @RequestBody UserInfo userInfo) {
        return userInfoRepo.save(userInfo);
    }

    @PostMapping("/process")
    public UserInfo processOrder(@Valid @RequestBody OrderDto orderDto) {
        return userInfoRepo.save(toEntity(orderDto));
    }


    @GetMapping("/{id}")
    public UserInfo getUserInfoById(@PathVariable(value = "id") Long id) {
        return userInfoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("UserInfo", "id", id));
    }

    @PutMapping("/{id}")
    public UserInfo updateUserInfo(@PathVariable(value = "id") Long id, @Valid @RequestBody UserInfo userDetails) {
        UserInfo userInfo = userInfoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("UserInfo", "id", id));
        userInfo.setName(userDetails.getName());
        userInfo.setLastname(userDetails.getLastname());
        userInfo.setPhoneNumber(userDetails.getPhoneNumber());
        userInfo.setCity(userDetails.getCity());
        userInfo.setBranchNumber(userDetails.getBranchNumber());
        userInfo.setDeliveryMethod(userDetails.getDeliveryMethod());
        userInfo.setUser(userDetails.getUser());
        return userInfoRepo.save(userInfo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserInfo(@PathVariable(value = "id") Long id) {
        UserInfo userInfo = userInfoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("UserInfo", "id", id));
        userInfoRepo.delete(userInfo);
        return ResponseEntity.ok().build();
    }
}
