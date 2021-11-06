package com.example.jpa101.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.jpa101.board.domain.BoardEntity;
import com.example.jpa101.board.domain.CommentEntity;
import com.example.jpa101.board.repository.BoardRepository;
import com.example.jpa101.board.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BoardController {


    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping("")
    private ResponseEntity<Map<String, String>> hello() {

        Map<String, String> map = new HashMap<>();
        map.put("hello", "world");


        BoardEntity board = BoardEntity.builder()
                .title("hello world")
                .build();

        CommentEntity comment = CommentEntity.builder()
                .text1("text1")
                .text2("text2")
                .build();

        /*
        BoardEntity board1 = boardRepository.save(board);
        comment.setBoard(board1);
        CommentEntity comment1 = commentRepository.save(comment);
        board.addComment(comment);
        */

        board.addComment(comment);
        BoardEntity board11 = boardRepository.save(board);


        List<BoardEntity> all = boardRepository.findAll();
        List<BoardDto> list = all
                .stream()
                .map(BoardDto::new)
                .collect(Collectors.toList());

        System.out.println("list = " + list);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}