package com.example.polySpringBootProject.service;

import com.example.polySpringBootProject.RoleType;
import com.example.polySpringBootProject.entity.BoardEntity;
import com.example.polySpringBootProject.entity.MemberEntity;
import com.example.polySpringBootProject.repository.BoardFileRepository;
import com.example.polySpringBootProject.repository.BoardRepository;
import com.example.polySpringBootProject.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardFileRepository boardFileRepository;

//    @Test
//    void memberAdd() {
//        for(int i=100;i<130;i++) {
//            MemberEntity memberEntity = MemberEntity.builder()
//                    .num((long) i)
//                    .id("testuseruser"+i)
//                    .name("테스트유저"+i)
//                    .role(RoleType.ROLE_USER)
//                    .addr("주소"+i)
//                    .post("우편번호"+i)
//                    .addrDetail("상세주소"+i)
//                    .pw("A12345")
//                    .approval("N")
//                    .email("test@test.org")
//                    .boardDatas(null)
//                    .build();
//            memberRepository.save(memberEntity);
//        }
//    }



    @Test
     void write() {
        MemberEntity member = memberRepository.findById(4L).get();
        for(int i=200;i <235;i++){
            BoardEntity board = BoardEntity.builder()
                    .title("제목"+i)
                    .content("내용"+i)
                    .notice("N")
                    .secret("N")
                    .del("N")
                    .noticeTop("N")
                    .member(member)
                    .fileAttached(0)

                    .build();
            boardRepository.save(board);
        }
    }
}