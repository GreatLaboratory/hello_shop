package com.example.jpashop.domain.item;

import com.example.jpashop.domain.Category;
import com.example.jpashop.exception.NotEnoughStockException;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
@Getter
@Setter
@ToString
@ApiModel(value = "Item : 상품", description = "상품")
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    @ApiModelProperty(value = "상품 고유번호")
    private Long id;

    @ApiModelProperty(value = "상품 이름")
    private String name;

    @ApiModelProperty(value = "상품 가격")
    private int price;

    @ApiModelProperty(value = "상품 재고수량")
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    @ApiModelProperty(value = "해당 상품이 속한 카테고리 리스트")
    @JsonBackReference
    private List<Category> categories = new ArrayList<Category>();




    //==Biz Method==//
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }

}
