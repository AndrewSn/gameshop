package com.gameshop.Controller;

import com.gameshop.dto.OrderDto;
import com.gameshop.entity.UserInfo;
import com.gameshop.exception.ResourceNotFoundException;
import com.gameshop.repository.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user-info")
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


    @GetMapping("/{userInfoId}")
    public UserInfo getUserInfoById(@PathVariable(value = "userInfoId") Long userInfoId) {
        return userInfoRepo.findById(userInfoId).orElseThrow(() -> new ResourceNotFoundException("UserInfo", "id", userInfoId));
    }

    @PutMapping("/{userInfoId}")
    public UserInfo updateUserInfo(@PathVariable(value = "userInfoId") Long userInfoId, @Valid @RequestBody UserInfo userDetails) {
        UserInfo userInfo = userInfoRepo.findById(userInfoId).orElseThrow(() -> new ResourceNotFoundException("UserInfo", "id", userInfoId));
        userInfo.setName(userDetails.getName());
        userInfo.setLastname(userDetails.getLastname());
        userInfo.setPhoneNumber(userDetails.getPhoneNumber());
        userInfo.setCity(userDetails.getCity());
        userInfo.setBranchNumber(userDetails.getBranchNumber());
        userInfo.setDeliveryMethod(userDetails.getDeliveryMethod());
        userInfo.setUser(userDetails.getUser());
        return userInfoRepo.save(userInfo);
    }

    @DeleteMapping("/{userInfoId}")
    public ResponseEntity<?> deleteUserInfo(@PathVariable(value = "userInfoId") Long userInfoId) {
        UserInfo userInfo = userInfoRepo.findById(userInfoId).orElseThrow(() -> new ResourceNotFoundException("UserInfo", "id", userInfoId));
        userInfoRepo.delete(userInfo);
        return ResponseEntity.ok().build();
    }
}
