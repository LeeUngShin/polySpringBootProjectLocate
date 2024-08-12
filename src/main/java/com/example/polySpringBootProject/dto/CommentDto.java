package com.example.polySpringBootProject.dto;

import com.example.polySpringBootProject.entity.CommentEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private Long num;
    private String commentWriter;
    private Long boardNum;
    private String commentContent;
    private String commentCreateTime;

    public static CommentDto EntityToCommentDto(CommentEntity commentEntity){
        CommentDto commentDto = new CommentDto();
        commentDto.setNum(commentEntity.getNum());
        commentDto.setCommentWriter(commentEntity.getBoardEntity().getMember().getId());
        commentDto.setCommentContent(commentEntity.getCommentContent());
        commentDto.setCommentCreateTime(commentEntity.getCreatedTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return commentDto;
    }
}
