package com.example.jpa101.board;

import com.example.jpa101.board.domain.BoardEntity;
import com.example.jpa101.board.domain.CommentEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
@Setter
public class BoardDto {
    private String title;
    private List<CommentDto> commentDto;

    public BoardDto() {}
    public BoardDto(BoardEntity entity) {
        title = entity.getTitle();
        List<CommentEntity> comments = entity.getComments();
        if (comments != null) {
            List<CommentDto> collect = comments.stream().map(CommentDto::new).collect(Collectors.toList());
            this.setCommentDto(collect);
        }
    }
}

@Data
class CommentDto {
    private long id;
    private String text1;
    private String text2;

    public CommentDto () {}
    public CommentDto(CommentEntity comment) {
        this.text1 = comment.getText1();
        this.text2 = comment.getText2();
        this.id = comment.getId();
    }
}
