package com.example.jpashop.repository;

import com.example.jpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // save, findById, findAll 이 정도는 따로 구현 안해도 자동으로 쓸 수 있음.

    // 이건 메소드이름으로 만든 쿼리 메소드
    // 아래 메소드는 따로 구현 안해도 알아서 잘 작동함
    List<Member> findByName(String name);

    List<Member> findAllByOrderByIdDesc();



}
