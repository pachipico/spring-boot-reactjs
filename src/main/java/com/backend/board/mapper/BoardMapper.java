package com.backend.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.backend.board.domain.Board;

@Mapper
public interface BoardMapper {
	int register(Board board);
	List<Board> findBoardListByQuery(String field, String query, String siName, String category, String orderBy, int start);
	List<Board> findPopularBoardList(String siName, String category);
	List<Board> findBoardListByUser(String email);
	Board findBoardDetail(Long bId);
	void updateBoardHit(Long bId);
	void updateBoard(Board board);
	void deleteBoard(Long bId);
	void likeBoard(Long bId, String email);
	void unlikeBoard(Long bId, String email);
}
