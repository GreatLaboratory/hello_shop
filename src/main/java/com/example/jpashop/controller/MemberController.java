package com.example.jpashop.controller;

import com.example.jpashop.domain.Address;
import com.example.jpashop.domain.Member;
import com.example.jpashop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;


    // 회원가입_GET
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    // 회원가입_POST
    @PostMapping("/members/new")
    public String create(Member member, Address address) {
        // 메소드의 파라미터로 들어오는 Member member, Address address 이 두 인스턴스는 뷰에 input폼을 submit했을 때
        // name파라미터와 각 객체의 필드이름이 같을 때 바인딩되어 넘어온다.?

        member.setAddress(address);
        memberService.join(member);
        return "redirect:/";    // 처음화면으로
    }

    // 회원 목록 조회_GET
    @GetMapping("/members")
    public ModelAndView getMemberList() {

        ModelAndView mv = new ModelAndView("members/memberList");
        List<Member> members = memberService.findMembers();
        mv.addObject("members", members);
        return mv;
    }

}
