package com.backend.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.backend.board.domain.Board;
import com.backend.board.dto.BoardRegisterRequestDto;

import lombok.extern.slf4j.Slf4j;

@Mapper
public interface BoardMapper {
	int register(Board board);
	List<Board> findBoardListByQuery(String field, String query, String siName, String category, String orderBy, int start);
	List<Board> findPopularBoardList(String siName, String category);
	List<Board> findBoardListByUser(String email, String siName);
	Board findBoardDetail(Long bId);
	void updateBoardHit(Long bId);
	int updateBoard(Board board);
	void deleteBoard(Long bId);
}
