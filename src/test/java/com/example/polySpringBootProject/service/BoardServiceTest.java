package com.example.polySpringBootProject.service;

import com.example.polySpringBootProject.enumClass.BoardType;
import com.example.polySpringBootProject.enumClass.RoleType;
import com.example.polySpringBootProject.entity.BoardEntity;
import com.example.polySpringBootProject.entity.MemberEntity;
import com.example.polySpringBootProject.repository.BoardFileRepository;
import com.example.polySpringBootProject.repository.BoardRepository;
import com.example.polySpringBootProject.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardFileRepository boardFileRepository;

    @Autowired
    private EntityManager entityManager;

/*    @Test
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

 */



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

    @Test
    void entityTest(){
        MemberEntity memberEntity1 = MemberEntity.builder()
                .id("test05")
                .pw("12345")
                .name("사용자05")
                .post("00000")
                .addr("주소05")
                .addrDetail("상세주소05")
                .email("test02@test.org")
                .approval("Y")
                .role(RoleType.ROLE_USER)
                .build();
        MemberEntity saveMember = memberRepository.save(memberEntity1);

        BoardEntity boardEntity = BoardEntity.builder()
                .title("제목제목05")
                .content("내용내용05")
                .notice("N")
                .secret("N")
                .del("N")
                .noticeTop("N")
                .member(saveMember)
                .boardType(BoardType.FREE_BOARD)
                .fileAttached(0)
                .build();
        BoardEntity saveBoard = boardRepository.save(boardEntity);

        entityManager.flush();
        entityManager.clear();

        MemberEntity m = memberRepository.findById(saveMember.getNum()).get();
        System.out.println("******************");
        System.out.println(m.getBoardDatas());
    }

//    @Test
//    @Transactional
//    void a(){
//        MemberEntity m = memberRepository.findById(20L).get();
//        System.out.println("******************");
//        System.out.println(m.getBoardDatas());
//    }

    @Test
    //@Transactional
    void memberDelete(){
        MemberEntity member = memberRepository.findById(23L).get();
        memberRepository.delete(member);
    }
}