package com.example.polySpringBootProject.dto;

import com.example.polySpringBootProject.entity.BoardEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private String regTime;
    private String modTime;
    private String writer;  // 게시물을 쓴 회원의 아이디
    private String notice = "N";

    private MultipartFile boardFile;  // 파일을 담는 용도
    private String originalFileName;  // 원본 파일 이름
    private String storedFileName;  // 서버 저장용 파일 이름
    private int fileAttached; // 파일 첨부 여부(첨부 1, 미첨부 0)

    public BoardDto(Long num, String title, String content, LocalDateTime regTime, String writer, String notice) {
        this.num = num;
        this.title = title;
        this.content = content;
        this.regTime = regTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.writer = writer;
        this.notice = notice;
    }

    public static BoardDto entityToDto(BoardEntity board) {

        BoardDto boardDto = new BoardDto();

        boardDto.setNum(board.getNum());
        boardDto.setTitle(board.getTitle());
        boardDto.setContent(board.getContent());
        boardDto.setWriter(board.getMember().getId());
        boardDto.setRegTime(board.getCreatedTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        if(board.getFileAttached()==0){  // 첨부파일 없음
            boardDto.setFileAttached(board.getFileAttached());
        }else{  // 파일이 있는 경우
            boardDto.setFileAttached(board.getFileAttached());

            // originalFileName, storedFileName은 BoardFileEntity에 있음
            // 매개변수로 받은 건 BoardEntity
            // join
            // select * from board_table b, board_file_table bf
            // where b.id = bf.board_id and where b.id = ?
            // 단일 파일 첨부이기 때문에 리스트에는 파일객체 1개만 있어서 get(0)으로 가져오면 됨
            // get(0) -> BoardFileEntity 객체 반환
            boardDto.setOriginalFileName(board.getBoardFileEntities().get(0).getOriginalFileName());
            boardDto.setStoredFileName(board.getBoardFileEntities().get(0).getStoredFileName());
        }
        return boardDto;
    }
}