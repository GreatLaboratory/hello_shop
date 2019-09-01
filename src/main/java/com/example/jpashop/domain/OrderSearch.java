package com.example.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specifications;

import static com.example.jpashop.domain.OrderSpec.memberNameLike;
import static com.example.jpashop.domain.OrderSpec.orderStatusEq;
import static org.springframework.data.jpa.domain.Specifications.where;

@Getter
@Setter
public class OrderSearch {

    private String memberName;      //회원 이름
    private OrderStatus orderStatus;//주문 상태

    public Specifications<Order> toSpecification() {
        return where(memberNameLike(memberName))
                .and(orderStatusEq(orderStatus));
    }

}
