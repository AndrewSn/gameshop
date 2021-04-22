package com.gameshop.repository;

import com.gameshop.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsRepo extends JpaRepository<Goods, Long>, JpaSpecificationExecutor<Goods> {
    @Query(value = "select * from goods where price>100" , nativeQuery = true)
    public List<Goods> getAllTopPrice();


}
