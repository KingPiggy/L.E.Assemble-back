package com.hoondragonite.leassemble.domain.order;

import com.hoondragonite.leassemble.domain.BaseTimeEntity;
import com.hoondragonite.leassemble.domain.store.StoreEvent;
import com.hoondragonite.leassemble.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Getter
@NoArgsConstructor
@Entity
public class UserOrder extends BaseTimeEntity {
    @Id
    @Column(name = "USER_ORDER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "USER_ORDER_DATE")
    private Date date;

    // 주문번호

    // 주문상태

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
