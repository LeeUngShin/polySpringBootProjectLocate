package com.example.polySpringBootProject.service;

import com.example.polySpringBootProject.dto.JoinDto;
import com.example.polySpringBootProject.entity.MemberEntity;
import com.example.polySpringBootProject.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import org.hibernate.mapping.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    MemberRepository memberRepository;

    public boolean logout(HttpSession session) {

        String id = (String) session.getAttribute("loginId");
        if(id != null && !id.isEmpty()) {
            System.out.println("로그아웃 할려고 했더닌 세션이 널이 아니다");
            session.invalidate();
            return true;
        } else {
            System.out.println("로그아웃 할려고 했더닌 세션이 널이당");
            return false;
        }
    }

    public Page<JoinDto> getNotApprovalMember(){

        Page<MemberEntity>

    }
}
