package com.example.jpa101.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.jpa101.board.domain.BoardEntity;
import com.example.jpa101.board.domain.CommentEntity;
import com.example.jpa101.board.domain.User;
import com.example.jpa101.board.dto.BoardDto;
import com.example.jpa101.board.repository.BoardRepository;
import com.example.jpa101.board.repository.CommentRepository;
import com.example.jpa101.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("test")
    public ResponseEntity test() {
        User userA = userRepository.save(new User("UserA"));
        userA.changeName("UserB");
        userRepository.save(userA);
        return ResponseEntity.ok().body(userA);
    }

    @RequestMapping("")
    private ResponseEntity<BoardDto> hello() {

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

        BoardDto boardDto = Optional.of(board11).map(BoardDto::new).get();

        return new ResponseEntity<>(boardDto, HttpStatus.OK);
    }

    // @BatchSize(size = 100)
    @RequestMapping("board_list")
    public ResponseEntity board_list() {

        PageRequest pageRequest = PageRequest.of(0,15);
        Page<BoardEntity> all = boardRepository.findAll(pageRequest);
        List<BoardDto> list = all.getContent()
                .stream()
                .map(BoardDto::new)
                .collect(Collectors.toList());

        System.out.println("list = " + list);

        PageImpl pageImpl = new PageImpl(list, pageRequest, all.getTotalPages());
        return ResponseEntity.ok().body(pageImpl);
    }
}