package com.example.polySpringBootProject;

import com.example.polySpringBootProject.entity.BoardEntity;
import com.example.polySpringBootProject.entity.MemberEntity;
import com.example.polySpringBootProject.repository.BoardRepository;
import com.example.polySpringBootProject.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class inputT {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BoardRepository boardRepository;

    @Test
    public void inputMem(){
        MemberEntity member = MemberEntity.builder()
                .id("user02")
                .addr("주소")
                .name("이름02")
                .addrDetail("상세주소")
                .post("우편번호")
                .pw("A12345")
                .role(RoleType.ROLE_USER)
                .email("user02@test.org")
                .approval("N")
                .build();

        memberRepository.save(member);
    }

    @Test
    public void inputT() {

        MemberEntity m1 = memberRepository.findById("user01").get();
        MemberEntity m2 = memberRepository.findById("user02").get();

        for (int i = 38; i < 79; i++) {
            BoardEntity board = BoardEntity.builder()
                    .title("제목" + i)
                    .content("내용" + i)
                    .member(m1)
                    .build();
            boardRepository.save(board);
        }
    }
}
