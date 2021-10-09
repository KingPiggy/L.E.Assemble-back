package com.hoondragonite.leassemble.domain.store;

import com.hoondragonite.leassemble.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class StoreEvent extends BaseTimeEntity {
    @Id
    @Column(name = "STORE_EVENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "STORE_EVENT_NM")
    private Long name;

    @Column(name = "STORE_EVENT_INFO")
    private String info;

    @Column(name = "STORE_EVENT_START_DATE")
    private Date startDate;

    @Column(name = "STORE_EVENT_END_DATE")
    private Date endDate;

    @ManyToOne
    @JoinColumn(nullable = false, name = "STORE_ID")
    private Store store;
}
