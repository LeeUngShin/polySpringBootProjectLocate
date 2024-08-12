package com.example.polySpringBootProject.service;

import com.example.polySpringBootProject.dto.CommentDto;
import com.example.polySpringBootProject.entity.BoardEntity;
import com.example.polySpringBootProject.entity.CommentEntity;
import com.example.polySpringBootProject.repository.BoardRepository;
import com.example.polySpringBootProject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    BoardRepository boardRepository;

    public boolean addComment(CommentDto commentDto, Long boardNum){

        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(boardNum);
        if(optionalBoardEntity.isEmpty()) {
            return false;
        }

        BoardEntity board = optionalBoardEntity.get();


        CommentEntity commentEntity = CommentEntity.builder()
                .commentContent(commentDto.getCommentContent())
                .del("N")
                .boardEntity(board)
                .build();

        CommentEntity saveEntity = commentRepository.save(commentEntity);
        if(saveEntity != null){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean delComment(Long commentNum){
        Optional<CommentEntity> optionalCommentEntity= commentRepository.findById(commentNum);
        if(optionalCommentEntity.isEmpty()) {
            return false;
        }
        CommentEntity commentEntity = optionalCommentEntity.get();
        commentEntity.setDel("Y");
        CommentEntity saveCommentEntity = commentRepository.save(commentEntity);
        if(saveCommentEntity != null) return true;
        return  false;
    }

    public List<CommentDto> commentList(Long boardNum){
        List<CommentEntity> commentEntityList = commentRepository.findByDelAndBoardEntityNumOrderByNumDesc("N", boardNum);
        List<CommentDto> commentDtos = new ArrayList<>();
        for(CommentEntity commentEntity : commentEntityList){
            commentDtos.add(CommentDto.EntityToCommentDto(commentEntity));
        }
        return commentDtos;
    }

    public boolean commentMod(CommentDto commentDto, Long commentNum){
        Optional<CommentEntity> optionalCommentEntity = commentRepository.findById(commentNum);
        if(optionalCommentEntity.isEmpty()) return false;
        CommentEntity commentEntity = optionalCommentEntity.get();
        commentEntity.setCommentContent(commentDto.getCommentContent());
        CommentEntity modifyCommentEntity = commentRepository.save(commentEntity);
        if(modifyCommentEntity != null) return true;
        else return false;
    }
}
