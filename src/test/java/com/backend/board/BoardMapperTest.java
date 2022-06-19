package com.backend.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.backend.board.domain.Board;
import com.backend.board.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
public class BoardMapperTest {

	@Autowired
	private BoardMapper mapper;
	
	@Test
	public void register() {
		Board board = new Board("testTitle",  "123@123.com", "testContent", "질문", "용인");
		mapper.register(board); 
	} 
}
