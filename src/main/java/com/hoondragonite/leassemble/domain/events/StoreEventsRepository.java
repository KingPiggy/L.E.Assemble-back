package com.hoondragonite.leassemble.domain.events;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreEventsRepository extends JpaRepository<StoreEvents, Long> {
    List<StoreEvents> findByStore_Id(@Param(value = "storeId") Long storeId);
}