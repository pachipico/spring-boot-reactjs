package com.backend.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.backend.board.domain.Board;
import com.backend.board.dto.BoardQuery;
import com.backend.board.dto.Si;

@Mapper
public interface BoardMapper {
	int register(Board board);
	List<Board> findBoardListByQuery(BoardQuery boardQuery);
	int findBoardCntByQuery(BoardQuery boardQuery);
	List<Board> findPopularBoardList(String siName, String category);
	List<Board> findBoardListByUser(String email);
	Board findBoardDetail(Long bId);
	Long findNextBId(Long bId, String siName);
	Long findPrevBId(Long bId, String siName);
	void updateBoardHit(Long bId);
	void updateBoard(Board board);
	void deleteBoard(Long bId);
	void likeBoard(Long bId, String email);
	void unlikeBoard(Long bId, String email);
	List<Si> findAllSi();
	
}
