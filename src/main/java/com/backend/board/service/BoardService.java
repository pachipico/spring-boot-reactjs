package com.backend.board.service;

import java.util.List;

import com.backend.board.dto.BoardDetailResponseDto;
import com.backend.board.dto.BoardLikeDto;
import com.backend.board.dto.BoardListResponseDto;
import com.backend.board.dto.BoardModifyRequestDto;
import com.backend.board.dto.BoardQuery;
import com.backend.board.dto.BoardRegisterRequestDto;
import com.backend.board.dto.Si;
import com.backend.common.dto.PageableWithEmail;
import com.backend.response.result.ListResult;
import com.backend.response.result.SingleResult;


public interface BoardService {
	void registerBoard(BoardRegisterRequestDto boardRegisterRequestDto);
	List<BoardListResponseDto> findBoardListByQuery(BoardQuery boardQuery);
	int findBoardCntByQuery(BoardQuery boardQuery);
	List<BoardListResponseDto> findUserWroteList(PageableWithEmail pageableWithEmail);
	int findUserWroteListCnt(String email);
	List<BoardListResponseDto> findUserLikedList(PageableWithEmail pageableWithEmail);
	int findUserLikedListCnt(String email);
	List<BoardListResponseDto> findPopularBoardList(String siName, String category);
	BoardDetailResponseDto findBoardByBId(Long bId);
	Long findNextBId(Long bId, String siName);
	Long findPrevBId(Long bId, String siName);
	void modifyBoard(BoardModifyRequestDto boardModifyRequestDto);
	void deleteBoard(Long bId);

	void deleteBoardList(List<Long> list);
	void likeBoard(BoardLikeDto boardLikeDto);
	void unlikeBoard(BoardLikeDto boardLikeDto);
	void unlikeBoardList(String email, List<Long> list);
	List<Si> findAllSi();
}
