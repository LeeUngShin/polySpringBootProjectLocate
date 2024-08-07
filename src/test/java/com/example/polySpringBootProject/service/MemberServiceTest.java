package com.example.polySpringBootProject.service;

import com.example.polySpringBootProject.entity.BoardEntity;
import com.example.polySpringBootProject.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Request;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class MemberServiceTest {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardService service;

    @Test
    void myBoardList() {
        Pageable pageable = PageRequest.of(0,5, Sort.by(Sort.Direction.DESC, "num"));
        Page<BoardEntity> boardEntities = boardRepository.findByMemberId(pageable,"testuser20");
        System.out.println("테스트 내 게시글 : " + boardEntities);
        assertEquals(29, boardEntities.getTotalElements());

    }
}