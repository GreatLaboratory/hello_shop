package com.example.jpashop.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@ApiModel(value = "Delivery : 배송 정보", description = "배송 정보")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DELIVERY_ID")
    @ApiModelProperty(value = "배송 고유번호")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.EAGER)
    @ApiModelProperty(value = "해당 배송의 주문 정보")
    private Order order;

    @Embedded
    @ApiModelProperty(value = "해당 배송의 주소 정보")
    private Address address;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty(value = "해당 배송 상태")
    private DeliveryStatus status; //ENUM [READY(준비), COMP(배송)]

    public Delivery() {
    }

    public Delivery(Address address) {
        this.address = address;
        this.status = DeliveryStatus.READY;
    }


}
