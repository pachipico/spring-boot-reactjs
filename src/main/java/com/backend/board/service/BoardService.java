package com.backend.board.service;

import java.util.List;

import com.backend.board.dto.BoardDetailResponseDto;
import com.backend.board.dto.BoardLikeDto;
import com.backend.board.dto.BoardListResponseDto;
import com.backend.board.dto.BoardModifyRequestDto;
import com.backend.board.dto.BoardQuery;
import com.backend.board.dto.BoardRegisterRequestDto;
import com.backend.board.dto.Si;
import com.backend.response.result.ListResult;
import com.backend.response.result.SingleResult;


public interface BoardService {
	void registerBoard(BoardRegisterRequestDto boardRegisterRequestDto);
	List<BoardListResponseDto> findBoardListByQuery(BoardQuery boardQuery);
	List<BoardListResponseDto> findPopularBoardList(String siName, String category);
	BoardDetailResponseDto findBoardByBId(Long bId);
	void updateBoard(BoardModifyRequestDto boardModifyRequestDto);
	void deleteBoard(Long bId);
	void likeBoard(BoardLikeDto boardLikeDto);
	void unlikeBoard(BoardLikeDto boardLikeDto);
	List<Si> findAllSi();
}
