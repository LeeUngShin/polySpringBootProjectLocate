package com.example.polySpringBootProject.service;

import com.example.polySpringBootProject.dto.BoardDto;
import com.example.polySpringBootProject.entity.BoardEntity;
import com.example.polySpringBootProject.entity.MemberEntity;
import com.example.polySpringBootProject.repository.BoardRepository;
import com.example.polySpringBootProject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

    public Long write(BoardDto boardDto, String id) {

        Optional<MemberEntity> member = memberRepository.findById(id);
        if(!member.isPresent()) return null;

        MemberEntity m = member.get();

        BoardEntity board = BoardEntity.builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .member(m)
                .build();

        BoardEntity savedBoard = boardRepository.save(board);

        return savedBoard.getNum();
    }

    public BoardDto boardDetail(Long num) {

        Optional<BoardEntity> board = boardRepository.findById(num);

        if(!board.isPresent()) return null;

        BoardEntity b = board.get();

        BoardDto boardDto = BoardDto.entityToDto(b);
        return boardDto;
    }


    public void delete(Long num) {

        boardRepository.deleteById(num);

    }

    public BoardDto getBoardDto(Long num) {

        Optional<BoardEntity> board = boardRepository.findById(num);
        if(board.isPresent()) {
            BoardEntity b = board.get();
            return BoardDto.entityToDto(b);
        }
        return null;
    }

    public boolean modify(BoardDto boardDto, Long num) {

        Optional<BoardEntity> savedBoard = boardRepository.findById(num);
        if(!savedBoard.isPresent()) {
            return false;
        }
        BoardEntity b = savedBoard.get();

        BoardEntity board = BoardEntity.builder()
                .num(num)
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .member(b.getMember())
                .build();
        boardRepository.save(board);
        return true;
    }


    public Page<BoardEntity> getList(int page){

        List<Sort.Order> sorts = new ArrayList<>();

        sorts.add(Sort.Order.desc("regDate")); // 첫 번째 정렬 조건: 등록일자 내림차순
        sorts.add(Sort.Order.asc("title"));    // 두 번째 정렬 조건: 제목 오름차순

        // page : 조회할 페이지번호, 3 : 한 페이지에 보여줄 게시물 개수
        //Pageable pageable = PageRequest.of(page, 3);
        Pageable pageable = PageRequest.of(page, 3, Sort.by(sorts));

//        Sort sort = Sort.by(Sort.Direction.DESC, "publishedDate");
//        Pageable pageable = PageRequest.of(page, size, sort);

        return boardRepository.findAll(pageable);
    }

    public Page<BoardEntity> searchList(int page, String keyword){


        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("regDate")); // 첫 번째 정렬 조건: 등록일자 내림차순
        Pageable pageable = PageRequest.of(page, 3, Sort.by(sorts));

        return boardRepository.findByTitleContaining(pageable, keyword);

    }
}