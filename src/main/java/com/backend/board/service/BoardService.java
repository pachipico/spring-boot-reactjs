package com.backend.board.service;

import java.util.List;

import com.backend.board.dto.BoardDetailResponseDto;
import com.backend.board.dto.BoardListResponseDto;
import com.backend.board.dto.BoardModifyRequestDto;
import com.backend.board.dto.BoardRegisterRequestDto;
import com.backend.response.result.ListResult;
import com.backend.response.result.SingleResult;


public interface BoardService {
	void registerBoard(BoardRegisterRequestDto boardRegisterRequestDto);
	ListResult<BoardListResponseDto> findBoardListByQuery(String field, String query, String siName, String category, String orderBy, int start);
	ListResult<BoardListResponseDto> findPopularBoardList(String siName, String category);
	SingleResult<BoardDetailResponseDto> findBoardByBId(Long bId);
	void updateBoard(BoardModifyRequestDto boardModifyRequestDto);
	void deleteBoard(Long bId);
	void likeBoard(Long bId, String email);
	void unlikeBoard(Long bId, String email);
	
}
