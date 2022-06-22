package com.backend.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.backend.board.dto.BoardLikeDto;
import com.backend.board.dto.BoardModifyRequestDto;
import com.backend.board.dto.BoardQuery;
import com.backend.board.dto.BoardRegisterRequestDto;
import com.backend.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
public class BoardServiceTest {

	@Autowired
	private BoardService boardService;

	@Test
	public void register() {
		String[] arr = new String[] {"기타", "질문"};
		for(int i = 0; i<= 100; i ++) {
			try {
				Thread.sleep(2000);
				boardService.registerBoard(new BoardRegisterRequestDto("test title" + i, "123@123.com", "this is test content number" + i, arr[i%2], "서울"));
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
	}

	@Test
	public void findPopularBoardList() {
		boardService.findPopularBoardList("서울", "");
	}

	@Test
	public void updateBoard() {
		BoardModifyRequestDto boardModifyRequestDto = new BoardModifyRequestDto(10L, "modified title",
				"this is modified content.", "기타");
		boardService.updateBoard(boardModifyRequestDto);
		boardService.findBoardListByQuery(new BoardQuery("title", "", "서울", "", "", 0, 15));
	}

	@Test
	public void deleteBoard() {
		boardService.deleteBoard(8L);
		boardService.findBoardListByQuery(new BoardQuery("title", "", "서울", "", "", 0, 15));
	}
	
	@Test
	public void likeBoard() {
		boardService.likeBoard(new BoardLikeDto(10L, "123@123.com"));
		boardService.findPopularBoardList("서울", "");
	}
	
	@Test 
	public void unlikeBoard() {
		boardService.unlikeBoard(new BoardLikeDto(10L, "123@123.com"));
		boardService.findPopularBoardList("서울","" );
		
	}
}
