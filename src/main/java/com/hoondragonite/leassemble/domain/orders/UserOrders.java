package com.hoondragonite.leassemble.domain.orders;

import com.hoondragonite.leassemble.domain.BaseTimeEntity;
import com.hoondragonite.leassemble.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Getter
@NoArgsConstructor
@Entity
public class UserOrders extends BaseTimeEntity {
    @Id
    @Column(name = "USER_ORDERS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "USER_ORDERS_DATE")
    private Date date;

    // 주문번호

    // 주문상태

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
