package com.example.polySpringBootProject.service;

import com.example.polySpringBootProject.dto.MemberDto;
import com.example.polySpringBootProject.entity.MemberEntity;
import com.example.polySpringBootProject.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Page<MemberDto> getNotApprovalMember(Pageable pageable){
        
        int currentPage = pageable.getPageNumber() - 1;
        int pageLimit = 10;  // 한페이지에 보여줄 회원 수
        Page<MemberEntity> memberEntityPage = memberRepository.findByApproval(PageRequest.of(currentPage, pageLimit, Sort.by(Sort.Direction.DESC, "num")), "N");
        Page<MemberDto> memberDtoPage = memberEntityPage.map
                (member -> new MemberDto(member.getNum(), member.getId()));
        return memberDtoPage;
    }

    public boolean memberApproval(String id){
        Optional<MemberEntity> memberEntity = memberRepository.findById(id);
        if(memberEntity.isEmpty()){
            return false;
        }
        MemberEntity member = memberEntity.get();
        member.setApproval("Y");
        memberRepository.save(member);
        return true;
    }

    public Page<MemberDto> getMemberList(Pageable pageable) {
        int currentPage = pageable.getPageNumber() - 1;
        int pageLimit = 10;  // 한페이지에 보여줄 회원 수
        Page<MemberEntity> memberEntityPage = memberRepository.findAll(PageRequest.of(currentPage, pageLimit, Sort.by(Sort.Direction.DESC, "num")));
        Page<MemberDto> memberDtoPage = memberEntityPage.map
                (member -> new MemberDto(member.getNum(), member.getId(), member.getPost(), member.getAddr(), member.getAddrDetail(), member.getEmail(), member.getApproval(), member.getCreatedTime()));
        return memberDtoPage;
    }
}