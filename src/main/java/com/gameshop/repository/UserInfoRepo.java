package com.gameshop.repository;

import com.gameshop.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserInfoRepo extends JpaRepository<UserInfo, Long> {
@Query(value = "SELECT u from UserInfo u where u.id = :id ")
 UserInfo findUserInfoById(@Param("id") Long id);
}
