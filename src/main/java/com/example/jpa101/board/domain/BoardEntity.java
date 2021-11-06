package com.example.jpa101.board.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "board")
public class BoardEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String title;

    @OneToMany(mappedBy = "board")
    @Builder.Default
    private List<CommentEntity> comments = new ArrayList<>();;

    public void addComment(CommentEntity comment) {
        this.comments.add(comment);
        if (comment.getBoard() == this) {
            comment.setBoard(this);
        }
    }
}