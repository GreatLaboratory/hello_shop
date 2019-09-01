package com.example.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@ApiModel(value = "Member : 회원 정보", description = "회원 정보")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    @ApiModelProperty(value = "회원 고유번호")
    private Long id;

    @ApiModelProperty(value = "회원 이름")
    private String name;

    @Embedded
    @ApiModelProperty(value = "주소 정보")
    private Address address;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    @ApiModelProperty(value = "회원의 주문 정보")
    @JsonBackReference   // json으로 이 객체를 파싱할 때 이렇게 다른 객체를 참조하는 컬럼은 무한재귀호출에러를 뿜는다. 그럴 때 이 어노테이션 사용
    private List<Order> orders = new ArrayList<Order>();

}
