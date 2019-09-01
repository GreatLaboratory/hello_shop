package com.example.jpashop.service;

import com.example.jpashop.domain.item.Item;
import com.example.jpashop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findByID(Long itemId) {

        // findOne은 없어졌고 findById로 대체되었다. findById는 optional로 나온다.
        Optional<Item> optional = itemRepository.findById(itemId);
        if(optional.isPresent()){
            Item item = optional.get();
            return item;
        } else {
            throw new NullPointerException();
        }
    }
}
