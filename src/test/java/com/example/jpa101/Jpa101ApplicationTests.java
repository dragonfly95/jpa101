package com.example.jpa101;


import com.example.jpa101.board.domain.BoardEntity;
import com.example.jpa101.board.domain.CommentEntity;
import com.example.jpa101.board.repository.BoardRepository;
import com.example.jpa101.board.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class Jpa101ApplicationTests {

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Test
	@Transactional
	void contextLoads() {
	
		BoardEntity board = BoardEntity.builder()
		.title("hello world")
		.build();

		CommentEntity comment = CommentEntity.builder()
				.text1("text1")
				.text2("text2")
				.build();


		BoardEntity board1 = boardRepository.save(board);
		comment.setBoard(board1);
		CommentEntity comment1 = commentRepository.save(comment);
		board.addComment(comment);


/*
		List<BoardEntity> all = boardRepository.findAll();
		List<BoardDto> list = all
				.stream()
				.map(BoardDto::new)
				.collect(Collectors.toList());

		for (BoardDto dto : list) {
			System.out.println("dto = " + dto);
		}
*/
	}

}
