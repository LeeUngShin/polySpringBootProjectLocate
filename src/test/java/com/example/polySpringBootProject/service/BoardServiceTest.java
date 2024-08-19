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

import java.lang.reflect.Member;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardFileRepository boardFileRepository;

    @Test
    void memberAdd() {

        MemberEntity memberEntity = MemberEntity.builder()
                .num((long) 1000)
                .id("testuseruseruser")
                .name("테스트유저유저")
                .role(RoleType.ROLE_USER)
                .addr("주소주소")
                .post("우편번호번호")
                .addrDetail("상세주소주소")
                .pw("A12345")
                .approval("N")
                .email("test@test.org")
                .build();
        MemberEntity member = memberRepository.save(memberEntity);
        for(int i=0;i<5;i++) {
            BoardEntity boardEntity = BoardEntity.builder()
                    .title("제목제목")
                    .content("내용내용")
                    .notice("N")
                    .secret("N")
                    .del("N")
                    .noticeTop("N")
                    .member(member)
                    .fileAttached(0)
                    .build();
            member.addBoard(boardEntity);
            boardRepository.save(boardEntity);
        }
        List<BoardEntity> boardEntityList= boardRepository.findByMemberId("testuseruseruser");
        System.out.println("현재 회원의 게시글 : " + member.getBoardDatas());
    }



//    @Test
//     void write() {
//        MemberEntity member = memberRepository.findById(4L).get();
//        for(int i=200;i <235;i++){
//            BoardEntity board = BoardEntity.builder()
//                    .title("제목"+i)
//                    .content("내용"+i)
//                    .notice("N")
//                    .secret("N")
//                    .del("N")
//                    .noticeTop("N")
//                    .member(member)
//                    .fileAttached(0)
//
//                    .build();
//            boardRepository.save(board);
//        }
//    }
}