package com.example.jpashop.controller;

import com.example.jpashop.domain.Member;
import com.example.jpashop.domain.Order;
import com.example.jpashop.domain.OrderSearch;
import com.example.jpashop.domain.item.Item;
import com.example.jpashop.service.ItemService;
import com.example.jpashop.service.MemberService;
import com.example.jpashop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private ItemService itemService;


    // 주문하기_GET
    @GetMapping("/order")
    public ModelAndView createForm() {

        ModelAndView mv = new ModelAndView("order/orderForm");

        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        mv.addObject("members", members);
        mv.addObject("items", items);
        return mv;
    }

    // 주문하기_POST
    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId, @RequestParam("itemId") Long itemId, @RequestParam("count") int count) {

        orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }


    // 주문 내역_GET
    @GetMapping("/orders")
    public ModelAndView orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch) {

        ModelAndView mv = new ModelAndView("order/orderList");

        List<Order> orders = orderService.findOrders(orderSearch);
        mv.addObject("orders", orders);

        return mv;
    }

    // 주문 취소
    @RequestMapping(value = "/orders/{orderId}/cancel")
    public void processCancelBuy(@PathVariable("orderId") Long orderId) {
        orderService.cancelOrder(orderId);
    }
}
