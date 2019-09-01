package com.example.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Getter
@ToString
@ApiModel(value = "Order : 주문 정보", description = "주문정보")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    @ApiModelProperty(value = "주문 고유번호")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER) // 오류 해결 위해서 fetch = FetchType.EAGER로 변경함 (프록시 초기화 관련 오류뜸..)
    @JoinColumn(name = "MEMBER_ID")
    @ApiModelProperty(value = "해당 주문을 한 회원")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER) // 오류 해결 위해서 fetch = FetchType.EAGER로 변경함
    @ApiModelProperty(value = "해당 주문에 포함된 상품리스트")
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "DELIVERY_ID")
    @ApiModelProperty(value = "해당 주문의 배송정보")
    private Delivery delivery;

    @ApiModelProperty(value = "해당 주문을 신청한 날짜")
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty(value = "해당 주문의 현재 상태")
    private OrderStatus status;

    //==생성 메서드==//
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {

        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(new Date());  // 여기서 그냥 new Date()이거로 주문 날짜 생성
        return order;
    }

    //==비즈니스 로직==//
    /** 주문 취소 */
    public void cancel() {

        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new RuntimeException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }

        this.setStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    //==조회 로직==//
    /** 전체 주문 가격 조회 */
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }

    //==연관관계 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //==Setter==//

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

}
