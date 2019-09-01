package com.example.jpashop.repository;

import com.example.jpashop.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    // save, findById, findAll 이 정도는 따로 구현 안해도 자동으로 쓸 수 있음.
}
