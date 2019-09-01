package com.example.jpashop.controller;

import com.example.jpashop.domain.Address;
import com.example.jpashop.domain.Member;
import com.example.jpashop.domain.Order;
import com.example.jpashop.domain.item.Book;
import com.example.jpashop.domain.item.Item;
import com.example.jpashop.service.ItemService;
import com.example.jpashop.service.MemberService;
import com.example.jpashop.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
@Api(description = "회원, 상품등록, 주문관리 REST API")
@RequestMapping("/api")
public class RestController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private OrderService orderService;

    // MEMBER
    @ApiOperation(value = "회원 목록 조회")
    @GetMapping("/members")
    public List<Member> getMemberList() {
        return memberService.findMembers();
    }

    @ApiOperation(value = "회원 가입")
    @PostMapping("/members/new")
    public void create(Member member, Address address) {
        member.setAddress(address);
        memberService.join(member);
    }

    // ITEM
    @ApiOperation(value = "상품 목록 조회")
    @GetMapping("/items")
    public List<Item> getItemList() {
        return itemService.findItems();
    }

    @ApiOperation(value = "상품 등록")
    @PostMapping("/items/new")
    public void registerItem(Book item) {
        itemService.saveItem(item);
    }

    @ApiOperation(value = "상품 수정")
    @PostMapping("/items/{itemId}/edit")
    public void updateItem(Book item) {
        itemService.saveItem(item);
    }

    // ORDER
    @ApiOperation(value = "주문 내역 조회")
    @GetMapping("/orders")
    public List<Order> getOrderList() {
        return orderService.findAll();
    }

    @ApiOperation(value = "상품 주문할 때 회원과 상품을 검색목록으로 조회")
    @GetMapping("/order")
    public Map<String, Object> createForm() {

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("members", memberService.findMembers());
        map.put("items", itemService.findItems());

        return map;
    }

    @ApiOperation(value = "주문하기")
    @PostMapping("/order")
    public void order(@RequestParam("memberId") Long memberId, @RequestParam("itemId") Long itemId, @RequestParam("count") int count) {
        orderService.order(memberId, itemId, count);
    }

}
