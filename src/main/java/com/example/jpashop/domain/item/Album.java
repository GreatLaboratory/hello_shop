package com.example.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("A")
@Getter
@Setter
@ToString
public class Album extends Item {

    private String artist;
    private String etc;

}
