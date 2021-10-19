package com.hoondragonite.leassemble.domain.product;

import com.hoondragonite.leassemble.domain.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByStore_Id(@Param(value = "storeId") Long storeId);
}