package com.example.polySpringBootProject.service;

import com.example.polySpringBootProject.dto.BoardDto;
import com.example.polySpringBootProject.entity.BoardEntity;
import com.example.polySpringBootProject.entity.BoardFileEntity;
import com.example.polySpringBootProject.entity.MemberEntity;
import com.example.polySpringBootProject.repository.BoardFileRepository;
import com.example.polySpringBootProject.repository.BoardRepository;
import com.example.polySpringBootProject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
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

    @Autowired
    private BoardFileRepository boardFileRepository;

    public Long write(BoardDto boardDto, String id) throws IOException {

        Optional<MemberEntity> member = memberRepository.findById(id);
        if(!member.isPresent()) return null;

        MemberEntity m = member.get();

        // 파일첨부 여부에 따라
        if(boardDto.getBoardFile().isEmpty()) {  // 파일첨부 안할 경우
            BoardEntity board = BoardEntity.builder()
                    .title(boardDto.getTitle())
                    .content(boardDto.getContent())
                    .member(m)
                    .fileAttached(0)  // 파일 없음 표시
                    .notice(boardDto.getNotice())
                    .build();
            BoardEntity savedBoard = boardRepository.save(board);
            Long savedId = savedBoard.getNum();
            return savedId;


        }else{  // 파일첨부 할 경우
            /*
                1. DTO에 담긴 파일 꺼냄
                2. 파일의 원본 이름 가져옴
                3. 서버 저장용 이름을 만듬(중복x 되도록)
                4. 저장 경로 설정
                5. 행당 경로에 파일을 저장
                6. tbl_board에 해당 데이터 save 처리
                7. board_file_table에 해당 데이터 save 처리
            */
            boolean upload =false;
            MultipartFile boardFile = boardDto.getBoardFile();  // 1
            String originalFileName =  boardFile.getOriginalFilename();  // 2
            String storedFileName = System.currentTimeMillis() + "_" + originalFileName;  // 3
            String saveDir = "C:/springboot_img";
            File saveFolder = new File(saveDir);
            if(!saveFolder.exists()){
                saveFolder.mkdir();
            }
            File saveFile = new File(saveDir + "/" + storedFileName);  // 4
            boardFile.transferTo(saveFile);  // 5
            upload = true;
            System.out.println("업로드 여부 : " + upload);

            BoardEntity board = BoardEntity.builder()
                    .title(boardDto.getTitle())
                    .content(boardDto.getContent())
                    .notice(boardDto.getNotice())
                    .member(m)
                    .fileAttached(1)
                    .build();
            BoardEntity savedBoard = boardRepository.save(board);
            Long savedId = savedBoard.getNum();
            BoardEntity boardEntity = boardRepository.findById(savedId).get();
            BoardFileEntity boardFileEntity = BoardFileEntity.builder()
                    .originalFileName(originalFileName)
                    .storedFileName(storedFileName)
                    .boardEntity(boardEntity)
                    .build();
            boardFileRepository.save(boardFileEntity);
            return savedId;
        }
    }

    @Transactional  // 부모엔티티에서 자식엔티티 접근 시 써줘야 함
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

    @Transactional
    public BoardDto getBoardDto(Long num) {

        Optional<BoardEntity> board = boardRepository.findById(num);
        if(board.isPresent()) {
            BoardEntity b = board.get();
            return BoardDto.entityToDto(b);
        }
        return null;
    }

    public boolean modify(BoardDto boardDto) {

        Optional<BoardEntity> savedBoard = boardRepository.findById(boardDto.getNum());
        if(!savedBoard.isPresent()) {
            return false;
        }
        BoardEntity b = savedBoard.get();

        BoardEntity board = BoardEntity.builder()
                .num(boardDto.getNum())
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .member(b.getMember())
                .build();
        boardRepository.save(board);
        return true;
    }


//    public Page<BoardEntity> getList(int page){
//
//        List<Sort.Order> sorts = new ArrayList<>();
//
//        sorts.add(Sort.Order.desc("regDate")); // 첫 번째 정렬 조건: 등록일자 내림차순
//        sorts.add(Sort.Order.asc("title"));    // 두 번째 정렬 조건: 제목 오름차순
//
//        // page : 조회할 페이지번호, 3 : 한 페이지에 보여줄 게시물 개수
//        //Pageable pageable = PageRequest.of(page, 3);
//        Pageable pageable = PageRequest.of(page, 3, Sort.by(sorts));
//
////        Sort sort = Sort.by(Sort.Direction.DESC, "publishedDate");
////        Pageable pageable = PageRequest.of(page, size, sort);
//
//        return boardRepository.findAll(pageable);
//    }

    public Page<BoardDto> paging(Pageable pageable, String keyword, String searchCategory){
        int page = pageable.getPageNumber()-1;  // 0페이지부터 시작하기 때문에 1페이지를 보려면 0페이지를 불러와야함
        int pageLimit = 7;  // 한 페이지에 보여줄 게시글 갯수
        //Page<BoardEntity> boardEntities=  boardRepository.findAll(PageRequest.of(page,pageLimit, Sort.by(Sort.Direction.DESC, "num")));
        Page<BoardEntity> boardEntities = null;
        // PageRequest : Pageable의 구현체
        if(keyword =="" || keyword==null) {
            //if(sort.equals("numAsc")){
            //    boardEntities = boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.ASC, "num")));
            //}else if(sort.equals("numDesc")) {
                boardEntities = boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "num")));
            //} else {
           // }
        }else {
            if(searchCategory.equals("title")) {
                boardEntities = boardRepository.findByTitleContaining(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "num")), keyword);
            }
            else if(searchCategory.equals("content")){
                boardEntities = boardRepository.findByContentContaining(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "num")), keyword);
            }
            else if(searchCategory.equals("writer")) {
                boardEntities = boardRepository.findBySearchId(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "num")), keyword);
            }
        }

        
        System.out.println("boardEntities.getContent() = " + boardEntities.getContent()); // 요청 페이지에 해당하는 글
        System.out.println("boardEntities.getTotalElements() = " + boardEntities.getTotalElements()); // 전체 글갯수
        System.out.println("키워드 = " + keyword);
        System.out.println("boardEntities.getNumber() = " + boardEntities.getNumber()); // DB로 요청한 페이지 번호
        System.out.println("boardEntities.getTotalPages() = " + boardEntities.getTotalPages()); // 전체 페이지 갯수
        System.out.println("boardEntities.getSize() = " + boardEntities.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("boardEntities.hasPrevious() = " + boardEntities.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("boardEntities.isFirst() = " + boardEntities.isFirst()); // 첫 페이지 여부
        System.out.println("boardEntities.isLast() = " + boardEntities.isLast()); // 마지막 페이지 여부

        // map() ; Page<>객체에서 제공해주는 메서드
        // board -> new BoardDto() : Page<BoardEntity>에 있는 BoardEntity 객체를 하나씩 꺼내서
        //                           BoardDto 객체로 바꿔줌(생성자 매개변수를 통해서)
        // 게시글 목록에 보여줄 내용 : num, writer, title, createTime
        // 위의 내용을 담을 수 있는 매개변수 생성자 BoardDto에 만들기
        Page<BoardDto> boardDtos = boardEntities.map
                (board -> new BoardDto(board.getNum(), board.getTitle(), board.getContent(),
                        board.getCreatedTime(), board.getMember().getId(), board.getNotice()));
        return boardDtos;
    }

    public List<BoardDto> noticeList(){
        List<BoardEntity> boardEntities = boardRepository.findByNoticeOrderByNumDesc("Y");
        List<BoardDto> noticeBoardDtos= new ArrayList<>();
        for(BoardEntity boardEntity : boardEntities){
            BoardDto boardDto = new BoardDto();
            boardDto.setNum(boardEntity.getNum());
            boardDto.setTitle(boardEntity.getTitle());
            boardDto.setContent(boardEntity.getContent());
            boardDto.setRegTime(boardEntity.getCreatedTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            boardDto.setNotice(boardEntity.getNotice());
            noticeBoardDtos.add(boardDto);
        }

        return noticeBoardDtos;
    }

//    public Page<BoardEntity> searchList(int page, String keyword){
//
//
//        List<Sort.Order> sorts = new ArrayList<>();
//        sorts.add(Sort.Order.desc("regDate")); // 첫 번째 정렬 조건: 등록일자 내림차순
//        Pageable pageable = PageRequest.of(page, 3, Sort.by(sorts));
//
//        return boardRepository.findByTitleContaining(pageable, keyword);
//    }

//    @Transactional  // db가 바뀌는 경우 중간에 문제 발생 시 롤백
//    public  void updateHits(Long id){
//        boardRepository.updateHits(id);
//    }
}