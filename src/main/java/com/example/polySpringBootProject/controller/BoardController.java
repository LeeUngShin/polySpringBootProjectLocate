package com.example.polySpringBootProject.controller;

import com.example.polySpringBootProject.dto.BoardDto;
import com.example.polySpringBootProject.entity.BoardEntity;
import com.example.polySpringBootProject.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/board")
public class BoardController {

    BoardService boardService;
    Utils utils;

    public BoardController(BoardService boardService, Utils utils){
        this.boardService = boardService;
        this.utils = utils;
    }

//	@RequestMapping(value="/list", method=RequestMethod.GET)
//	public String boardList(HttpServletRequest request) {
//		return "board/boardList";
//	}


    @RequestMapping(value="/write", method=RequestMethod.GET)
    public String boardWrite(HttpServletRequest request ,Model model,
                HttpSession session) {
        String id = (String) session.getAttribute("loginId");  // 세션에 저장된 현재 로그인한 회원 아이디
        if(id==null || id=="") {
            return utils.showMessageAlert("로그인이 필요합니다.", "/member/login", model);
        }
        return "board/boardWrite";
    }

    @RequestMapping(value="/write", method=RequestMethod.POST)
    public String processBoardWrite(HttpServletRequest request, HttpSession session,
                                    @ModelAttribute BoardDto boardDto) throws IOException {

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
    public String boardDetail(HttpServletRequest request,
                              @PathVariable("boardNum") Long boardNum,
                              @RequestParam(value = "page", defaultValue = "1", required = false) Integer currentPage) {

        log.info("로그1");
        BoardDto boardDto = boardService.boardDetail(boardNum);
        log.info("로그2");
        log.info("디테일 : "+currentPage);

        request.setAttribute("boardDto", boardDto);
        request.setAttribute("currentPage", currentPage);

        return "board/boardDetail";
    }
    @RequestMapping(value="/delete/{boardNum}", method=RequestMethod.POST)
    public String boardDelete(HttpServletRequest request,
                              @PathVariable("boardNum") Long boardNum) {
        String confirmDataStr = request.getParameter("confirmData");
        boolean confirmData = Boolean.parseBoolean(confirmDataStr);
        String currentPageStr = request.getParameter("currentPage");
        int currentPage = Integer.parseInt(currentPageStr);
        System.out.println("삭제할까? " + confirmData);
        System.out.println("삭제할 게시글번호 : " + boardNum);
        if(confirmData){
            boardService.delete(boardNum);
            return "redirect:/board/page";
        }
        else{
            return "redirect:/board/detail/" + boardNum + "/" + currentPage;
        }

    }

    @RequestMapping(value="/modify/{boardNum}", method=RequestMethod.GET)
    public String boardModify(HttpServletRequest request, @PathVariable Long boardNum) {

        String num = request.getParameter("boardId");
        Long lBoardnum = Long.parseLong(num);

        BoardDto boardDto = boardService.getBoardDto(lBoardnum);
        request.setAttribute("boardDto", boardDto);

        return "board/boardModify";
    }

    @RequestMapping(value="/modify", method=RequestMethod.POST)
    public String boardModifyProccess(HttpServletRequest request, BoardDto boardDto) {
//		String num = request.getParameter("boardNum");
//		Long lBoardNum = Long.parseLong(num);
        log.info("수정 타이틀" +boardDto.getTitle() );
        log.info("수정 내용" +boardDto.getContent() );
        boardService.modify(boardDto);
        return "redirect:/board/detail/" + boardDto.getNum();
    }

//    @RequestMapping(value="/page", method= RequestMethod.GET)
//    public String list(HttpServletRequest request, @RequestParam(value = "page", defaultValue = "1") int page) {
//        log.info("여기들어옴");
//    Page<BoardEntity> paging = boardService.getList(page - 1);
//		request.setAttribute("paging", paging);
//		System.out.println("현재 페이지" + (paging.getNumber() + 1));
//    int currentPage = paging.getNumber();
//		request.setAttribute("currentPage", currentPage + 1);
//    int totalPage = paging.getTotalPages();
//		request.setAttribute("totalPage", totalPage);
//		System.out.println("전체페이지 수 : " + totalPage);
//
//    // 첫번쨰, 마지막 페이지 계산 -> 하단 숫자 한번에 5개 1~5 -> 1/5, 6~10 -> 6/10
//    int startPage = ((page-1)/5) * 5 + 1;
//    int endPage = Math.min(startPage + 4, totalPage - 1);
//		request.setAttribute("startPage", startPage);
//		request.setAttribute("endPage", endPage);
//        return "board/boardPaging";
//   }


    /**
     * @PageableDefault 사용시 url?page=1 형식으로 받아야지 pageable 객체 사용 가능
     * @param pageable
     * @param model
     * @return
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String paging(@PageableDefault(page = 1)Pageable pageable, Model model) {
//        String sortStd="";
//        if(sort.contains("asc")){
//            sortStd = "asc";
//        }
//        else if(sort.contains("desc")){
//            sortStd = "desc";
//        }
        System.out.println("현재페이지지지 : " + pageable.getPageNumber());


        //int pageNum = pageable.getPageNumber();
        Page<BoardDto> boardList = boardService.paging(pageable);
        List<BoardDto> noticeBoardList = boardService.noticeList();
        System.out.println("공지글 : " + noticeBoardList);
        int currentPage = boardList.getNumber()+1;  // 파라미터로 받은 현재페이지
        System.out.println("현재페이지 : " + currentPage);

        // 총 Page 개수 20개이고 페이지 선택을 3개씩 보여준다면
        // 3페이지를 보고 있으면 1 2 3 -> startPage = 1, endPage = 3
        // 7페이지를 보고 있으면 7 8 9 -> startPage = 7, endPage = 9
        // 마지막은 19 20 21 이 아닌 19 20만 있어야함 -> 총 Page 개수가 20개라서
        int blockLimit = 3;  // 선택 페이지 개수 3개
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = ((startPage + blockLimit - 1) < boardList.getTotalPages()) ? startPage + blockLimit - 1 : boardList.getTotalPages();  // 3 6 9 12 ~~
        model.addAttribute("boardList", boardList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("noticeBoardList",noticeBoardList);

        //model.addAttribute("sortStd", sortStd);
        return "board/boardPaging";
    }

    @RequestMapping(value="/search", method=RequestMethod.GET)
    public String searchList(HttpServletRequest request,
                             @PageableDefault(page = 1)Pageable pageable,
                             @RequestParam(value = "category") String category,
                             @RequestParam(value = "keyword") String keyword, Model model) {

        Page<BoardDto> searchBoardList = boardService.searchList(pageable, category, keyword);
        List<BoardDto> noticeBoardList = boardService.noticeList();
        int currentPage = searchBoardList.getNumber()+1;
        int blockLimit = 3;  // 선택 페이지 개수 3개
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = ((startPage + blockLimit - 1) < searchBoardList.getTotalPages()) ? startPage + blockLimit - 1 : searchBoardList.getTotalPages();  // 3 6 9 12 ~~
        request.setAttribute("searchBoardList", searchBoardList);
        request.setAttribute("keyword", keyword);
        request.setAttribute("category", category);
        request.setAttribute("currentPage", currentPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("noticeBoardList",noticeBoardList);
        return "board/boardPaging";
    }
}