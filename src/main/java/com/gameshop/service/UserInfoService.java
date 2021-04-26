package com.gameshop.service;

import com.gameshop.entity.UserInfo;
import com.gameshop.exception.ResourceNotFoundException;
import com.gameshop.repository.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    UserInfoRepo userInfoRepo;

    @Autowired
    public UserInfoService(UserInfoRepo userInfoRepo) {
        this.userInfoRepo = userInfoRepo;
    }

    public UserInfo updateUserInfo(Long userInfoId, UserInfo userInfoRequest) {
        UserInfo userInfo = userInfoRepo.findById(userInfoId).orElseThrow(() -> new ResourceNotFoundException("UserInfo", "id", userInfoId));
        userInfo.setName(userInfoRequest.getName());
        userInfo.setLastname(userInfoRequest.getLastname());
        userInfo.setPhoneNumber(userInfoRequest.getPhoneNumber());
        userInfo.setCity(userInfoRequest.getCity());
        userInfo.setBranchNumber(userInfoRequest.getBranchNumber());
        userInfo.setDeliveryMethod(userInfoRequest.getDeliveryMethod());
        userInfo.setUser(userInfoRequest.getUser());
        return userInfoRepo.save(userInfo);
    }

    public ResponseEntity<Object> deleteUserInfo(Long userInfoId) {
        UserInfo userInfo = userInfoRepo.findById(userInfoId).orElseThrow(() -> new ResourceNotFoundException("UserInfo", "id", userInfoId));
        userInfoRepo.delete(userInfo);
        return ResponseEntity.ok().build();
    }
}
