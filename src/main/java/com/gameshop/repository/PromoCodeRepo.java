package com.gameshop.repository;

import com.gameshop.Enum.PromoStatus;
import com.gameshop.entity.Order;
import com.gameshop.entity.PromoCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PromoCodeRepo extends JpaRepository<PromoCode, Long> {
    @Query("SELECT o from PromoCode o where  o.promoCode = :promoCode")
    PromoCode getPromoCodeIfExist(@Param("promoCode") String promoCode);

    @Transactional
    @Modifying
    @Query("update PromoCode p set p.promoStatus = :promoStatus where p.promoId = :id")
    void updatePromoCodeStatus(@Param("promoStatus") PromoStatus promoStatus, @Param("id") Long id);

    @Query("SELECT p from PromoCode p where  p.promoId = :id")
    PromoCode getPromoByPromoId(@Param("id") Long id);
}
