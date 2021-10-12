package com.hoondragonite.leassemble.domain.orders;

import com.hoondragonite.leassemble.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class UserOrdersDetail extends BaseTimeEntity {
    @Id
    @Column(name = "USER_ORDERS_DETAIL_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 주문한 이벤트상품 ID
    
    // 주문한 이벤트상품 qty

    // 주문한 이벤트상품 price
    
    @ManyToOne
    @JoinColumn(name = "USER_ORDERS_ID")
    private UserOrders userOrders;
}
