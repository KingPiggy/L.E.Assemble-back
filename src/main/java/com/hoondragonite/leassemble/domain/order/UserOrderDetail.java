package com.hoondragonite.leassemble.domain.order;

import com.hoondragonite.leassemble.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class UserOrderDetail extends BaseTimeEntity {
    @Id
    @Column(name = "USER_ORDER_DETAIL_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 주문한 이벤트상품 ID
    
    // 주문한 이벤트상품 qty

    // 주문한 이벤트상품 price
    
    @ManyToOne
    @JoinColumn(name = "USER_ORDER_ID")
    private UserOrder userOrder;
}
