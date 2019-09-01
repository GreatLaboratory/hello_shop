package com.example.jpashop.domain;

import com.example.jpashop.domain.item.Item;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_ITEM")
@Getter
@Setter
@ToString
@ApiModel(value = "OrderItem : 주문상품 정보", description = "주문상품 정보")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ITEM_ID")
    @ApiModelProperty(value = "주문상품 고유번호")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ITEM_ID")
    @ApiModelProperty(value = "해당 주문상품의 소속 상품")
    private Item item;      //주문 상품

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ORDER_ID")
    @ApiModelProperty(value = "해당 주문상품의 소속 주문")
    private Order order;    //주문

    @ApiModelProperty(value = "해당 주문상품의 가격")
    private int orderPrice; //주문 가격

    @ApiModelProperty(value = "해당 주문상품을 주문한 수량")
    private int count;      //주문 수량

    //==생성 메서드==//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {

        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }

    //==비즈니스 로직==//
    /** 주문 취소 */
    public void cancel() {
        getItem().addStock(count);
    }

    //==조회 로직==//
    /** 주문상품 전체 가격 조회 */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }

}
