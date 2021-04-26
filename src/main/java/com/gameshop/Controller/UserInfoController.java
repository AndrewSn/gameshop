package com.gameshop.Controller;

import com.gameshop.dto.OrderDto;
import com.gameshop.entity.UserInfo;
import com.gameshop.exception.ResourceNotFoundException;
import com.gameshop.repository.UserInfoRepo;
import com.gameshop.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user-info")
public class UserInfoController {

    UserInfoRepo userInfoRepo;
    UserInfoService userInfoService;

    @Autowired
    public UserInfoController(UserInfoRepo userInfoRepo, UserInfoService userInfoService) {
        this.userInfoRepo = userInfoRepo;
        this.userInfoService = userInfoService;
    }

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
        return userInfoService.updateUserInfo(userInfoId, userDetails);
    }

    @DeleteMapping("/{userInfoId}")
    public ResponseEntity<?> deleteUserInfo(@PathVariable(value = "userInfoId") Long userInfoId) {
        return userInfoService.deleteUserInfo(userInfoId);
    }
}
