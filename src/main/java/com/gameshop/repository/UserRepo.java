package com.gameshop.repository;

import com.gameshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    @Query("SELECT u from User u where u.id = :id")
    User findUserById(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update User u set u.personalDiscountOfUser = :personalDiscount where u.id = :id")
    void updateUserPersonalDiscount(@Param("personalDiscount") Double personalDiscount, @Param("id") Long id);

}
