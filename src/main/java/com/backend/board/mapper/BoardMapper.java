package com.backend.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.backend.board.domain.Board;
import com.backend.board.dto.BoardQuery;
import com.backend.board.dto.Si;
import com.backend.common.dto.PageableWithEmail;

@Mapper
public interface BoardMapper {
	int register(Board board);
	List<Board> findBoardListByQuery(BoardQuery boardQuery);
	int findBoardCntByQuery(BoardQuery boardQuery);
	List<Board> findPopularBoardList(String siName, String category);
	List<Board> findUserWroteList(PageableWithEmail pageableWithEmail);
	int findUserWroteListCnt(String email);
	List<Board> findUserLikedList(PageableWithEmail pageableWithEmail);
	int findUserLikedListCnt(String email);
	List<Board> findBoardListByUser(String email);
	Board findBoardDetail(Long bId);
	Long findNextBId(Long bId, String siName);
	Long findPrevBId(Long bId, String siName);
	void updateBoardHit(Long bId);
	void updateBoard(Board board);
	void deleteBoard(Long bId);
	void deleteBoardList(List<Long> list);
	void likeBoard(Long bId, String email);
	int findLikeCntByBId(Long bId);
	void unlikeBoard(Long bId, String email);
	void unlikeBoardList(String email, List<Long> list);
	List<Si> findAllSi();
	List<Board> findMostLikedList();
	List<Board> findMostViewedList();
	List<Board> findMostCommentedList();
	
}
