package com.example.polySpringBootProject.dto;

import com.example.polySpringBootProject.entity.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;

@ToString  // ToString 메서드 사용가능
@Getter  // Getter 메서드 사용가능
@Builder  // 빌드패턴(클래스) 자동 생성 - 빌더 클래스는 setter 메서드 제공(체이닝 방식)
//  Memo.builder().fiedl1(value1).field(value2).build();
@Setter
@AllArgsConstructor  // 모든 변수를 매개변수로 받는 생성자 자동생성
@NoArgsConstructor  // 매개변수가 없는 생성자 자동생성
public class BoardDto {

    private Long num;
    private String title;
    private String content;
    private LocalDateTime reg_time;
    private String writer;  // 게시물을 쓴 회원의 아이디

    public static BoardDto entityToDto(BoardEntity board) {

        BoardDto boardDto = new BoardDto();

        boardDto.setNum(board.getNum());
        boardDto.setTitle(board.getTitle());
        boardDto.setContent(board.getContent());
        boardDto.setWriter(board.getMember().getId());

        return boardDto;
    }
}