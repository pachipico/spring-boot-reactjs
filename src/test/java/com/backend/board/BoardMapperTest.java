package com.backend.board;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.backend.board.domain.Board;
import com.backend.board.dto.BoardQuery;
import com.backend.board.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
public class BoardMapperTest {

	@Autowired
	private BoardMapper mapper;

	@Test
	public void register() {
		Board board = new Board("testTitle", "123@123.com", "testContent", "질문", "용인");
		mapper.register(board);
	}

	@Test
	public void findList() {
		List<Board> list = mapper.findBoardListByQuery(new BoardQuery("content", "", "용인", "", "", 0, 15));
		for(Board b : list) {
			System.out.println(b);
		}
	}
	@Test
	public void findPopular() {
		List<Board> list = mapper.findPopularBoardList("용인", "");
	}
	
	@Test
	public void findBoardDetail() {
		Board board =  mapper.findBoardDetail(5L);
	}
	@Test
	public void updateBoard() {
		Board board = new Board(5L, "title5", "용인", "123@123.com", "바뀐 내용", "", "", 0, "기타", 0);
		mapper.updateBoard(board);
		mapper.findBoardDetail(5L);
	}
	
	@Test
	public void updateBoardHit() {
		mapper.updateBoardHit(5L);
		mapper.findBoardDetail(5L);
	}
	@Test
	public void deleteBoard() {
		mapper.deleteBoard(9L);
	}
}
