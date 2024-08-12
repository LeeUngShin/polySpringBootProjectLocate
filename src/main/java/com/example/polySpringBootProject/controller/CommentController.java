package com.example.polySpringBootProject.controller;

import com.example.polySpringBootProject.dto.CommentDto;
import com.example.polySpringBootProject.service.CommentService;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/commentAdd", method = RequestMethod.POST)
    @ResponseBody
    public String addComment(HttpServletRequest request, HttpSession session, CommentDto commentDto) {
        String boardNumStr = request.getParameter("boardNum");
        Long boardNum = Long.parseLong(boardNumStr);
        System.out.println("댓글 쓸 게시글번호 : " + boardNum);

        boolean addComment = commentService.addComment(commentDto, boardNum);

        if (addComment) return "{\"result\" : \"success\"}";
        else {
            return "{\"result\" : \"fail\"}";
        }
    }

    @RequestMapping(value = "/commentDel", method = RequestMethod.PUT)
    @ResponseBody
    public String commentDel(HttpServletRequest request) {

        String commentNumStr = request.getParameter("commentNum");
        Long commentNum = Long.parseLong(commentNumStr);
        try{
        boolean delComment = commentService.delComment(commentNum);
            if(delComment) {
                System.out.println("삭제된 댓글 : " + commentNum);
                return "{\"result\" : \"success\"}";
            }

            else {
                return "{\"result\" : \"fail\"}";
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "{\"result\" : \"fail\"}";
    }

    @RequestMapping(value="/commentList", method=RequestMethod.GET)
    @ResponseBody
    public String commentList(HttpServletRequest request){
        String boardNumStr = request.getParameter("boardNum");
        Long boardNum = Long.parseLong(boardNumStr);
        try {
            List<CommentDto> commentDtos = commentService.commentList(boardNum);
            String commentDtosjson = new Gson().toJson(commentDtos);
            System.out.println("현재 게시글의 댓글 : " + commentDtosjson);
            return commentDtosjson;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "{\"result\" : \"fail\"}";

    }

    @RequestMapping(value="/commentModify", method=RequestMethod.PUT)
    @ResponseBody
    public String commentDel(HttpServletRequest request, CommentDto commentDto){
        String commentContent = request.getParameter("commentContent");
        String commentNumStr = request.getParameter("commentNum");
        Long commentNum = Long.parseLong(commentNumStr);

        boolean modifyComment = commentService.commentMod(commentDto, commentNum);
        if(modifyComment) return "{\"result\" : \"success\"}";
        else return  "{\"result\" : \"fail\"}";


    }
}