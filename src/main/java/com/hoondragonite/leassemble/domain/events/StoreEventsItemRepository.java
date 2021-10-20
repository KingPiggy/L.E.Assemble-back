package com.hoondragonite.leassemble.domain.events;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreEventsItemRepository extends JpaRepository<StoreEventsItem, Long> {
    List<StoreEventsItem> findByStoreEvents_Id(@Param(value = "storeEventsId") Long storeEventsId);
}
