package com.example.jpashop.controller;

import com.example.jpashop.domain.item.Book;
import com.example.jpashop.domain.item.Item;
import com.example.jpashop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    // 상품등록_GET
    @GetMapping("/items/new")
    public String registerForm() {
        return "items/createItemForm";
    }


    // 상품등록_POST
    @PostMapping("/items/new")
    public String register(Book item) {

        itemService.saveItem(item);
        return "redirect:/items";
    }


    // 상품 목록_GET
    @GetMapping("/items")
    public ModelAndView getItemList() {

        ModelAndView mv = new ModelAndView("items/itemList");
        List<Item> items = itemService.findItems();
        mv.addObject("items", items);
        return mv;
    }


    //상품 수정_GET
    @GetMapping("/items/{itemId}/edit")
    public ModelAndView updateItemForm(@PathVariable("itemId") Long itemId) {

        ModelAndView mv = new ModelAndView("items/updateItemForm");
        Item item = itemService.findByID(itemId);
        mv.addObject("item", item);
        return mv;
    }


    //상품 수정_POST
    @PostMapping("/items/{itemId}/edit")
    public String updateItem(Book item) {
        // 파라미터에 Book item 앞에 @ModelAttribute("item") 한거랑 안한거랑 차이가 없다..

        itemService.saveItem(item);
        return "redirect:/items";
    }

}
