package com.example.polySpringBootProject.controller;

import com.example.polySpringBootProject.dto.BoardDto;
import com.example.polySpringBootProject.entity.BoardEntity;
import com.example.polySpringBootProject.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/board")
public class BoardController {

    BoardService boardService;

    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

//	@RequestMapping(value="/list", method=RequestMethod.GET)
//	public String boardList(HttpServletRequest request) {
//		return "board/boardList";
//	}


    @RequestMapping(value="/write", method=RequestMethod.GET)
    public String boardWrite(HttpServletRequest request) {

        return "board/boardWrite";
    }

    @RequestMapping(value="/write", method=RequestMethod.POST)
    public String processBoardWrite(HttpServletRequest request, HttpSession session, @ModelAttribute BoardDto boardDto) {
//	    String title = request.getParameter("title");
//	    String content = request.getParameter("content");
        String id = (String) session.getAttribute("loginId");  // 세션에 저장된 현재 로그인한 회원 아이디
        if(id==null || id=="") {
            return "member/login";
        }

        Long num = boardService.write(boardDto, id);
        return "redirect:/board/detail/"+ num;
    }

    @RequestMapping(value="/detail/{boardNum}", method=RequestMethod.GET)
    public String boardDetail(HttpServletRequest request, @PathVariable("boardNum") Long boardNum) {

        BoardDto boardDto = boardService.boardDetail(boardNum);

        request.setAttribute("boardDto", boardDto);

        return "board/boardDetail";
    }



    @RequestMapping(value="/delete/{boardNum}", method=RequestMethod.POST)
    public String boardDelete(HttpServletRequest request, @PathVariable("boardNum") Long boardNum) {

        boardService.delete(boardNum);

        return "redirect:/board/list";

    }

    @RequestMapping(value="/modify", method=RequestMethod.GET)
    public String boardModify(HttpServletRequest request) {

        String num = request.getParameter("boardId");
        Long lBoardnum = Long.parseLong(num);

        BoardDto boardDto = boardService.getBoardDto(lBoardnum);
        request.setAttribute("boardDto", boardDto);

        return "board/boardModify";
    }

    @RequestMapping(value="/modify/{boardNum}", method=RequestMethod.POST)
    public String boardModifyProccess(HttpServletRequest request, BoardDto boardDto,
                                      @PathVariable("boardNum")Long boardNum) {
//		String num = request.getParameter("boardNum");
//		Long lBoardNum = Long.parseLong(num);
        log.info("수정 타이틀" +boardDto.getTitle() );
        log.info("수정 내용" +boardDto.getContent() );
        boardService.modify(boardDto, boardNum);
        return "redirect:/board/detail/" + boardNum;
    }

    @RequestMapping(value="/page", method= RequestMethod.GET)
    public String list(HttpServletRequest request, @RequestParam(value = "page", defaultValue = "1") int page) {
        log.info("여기들어옴");
        Page<BoardEntity> paging = boardService.getList(page - 1);
        System.out.println("페이징 : "+paging);
        request.setAttribute("paging", paging);
        System.out.println("현재 페이지" + (paging.getNumber() + 1));  // 1페이지는 getNumber()가 0이 나옴
        int currentPage = paging.getNumber();  // 1페이지의 경우 0
        request.setAttribute("currentPage", currentPage + 1);  // 그래서 1 더함
        int totalPage = paging.getTotalPages();  // 전체 페이지개수
        request.setAttribute("totalPage", totalPage);
        System.out.println("전체페이지 수 : " + totalPage);
        return "board/boardList";
    }

    @RequestMapping(value="/search", method=RequestMethod.GET)
    public String searchList(HttpServletRequest request, @RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        String keyword = request.getParameter("keyword");
        System.out.println("검색 시 받은 page : "+page);
        System.out.println("검색 시 받은 keyword :"+keyword);
        Page<BoardEntity> searchPaging = boardService.searchList(page-1, keyword);
        System.out.println("검색 후페이징 : "+searchPaging.getContent());
        List<BoardEntity> s = searchPaging.getContent();
        for(BoardEntity search : s) {
            System.out.println("검색결과 : "+search.getTitle());
        }
        request.setAttribute("searchPaging", searchPaging);
        System.out.println("검색 후 현재 페이지" + (searchPaging.getNumber() + 1));  // 1페이지는 getNumber()가 0이 나옴
        int currentPage = searchPaging.getNumber();  // 1페이지의 경우 0
        request.setAttribute("currentPage", currentPage + 1);  // 그래서 1 더함
        int totalPage = searchPaging.getTotalPages();  // 전체 페이지개수
        request.setAttribute("totalPage", totalPage);
        System.out.println("검색 후 전체페이지 수 : " + totalPage);
        request.setAttribute("key", keyword);
        return "board/boardPaging";
    }
}