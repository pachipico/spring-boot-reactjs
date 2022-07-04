package com.backend.board.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.board.domain.Board;
import com.backend.board.dto.BoardDetailResponseDto;
import com.backend.board.dto.BoardLikeDto;
import com.backend.board.dto.BoardListResponseDto;
import com.backend.board.dto.BoardModifyRequestDto;
import com.backend.board.dto.BoardQuery;
import com.backend.board.dto.BoardRegisterRequestDto;
import com.backend.board.dto.Si;
import com.backend.board.mapper.BoardMapper;
import com.backend.response.ResponseService;
import com.backend.response.result.SingleResult;
import com.backend.user.domain.User;
import com.backend.user.mapper.UserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardServiceImple implements BoardService {

	private final BoardMapper boardMapper;
	private final UserMapper userMapper;

	@Transactional
	@Override
	public void registerBoard(BoardRegisterRequestDto boardRegisterRequestDto) {
		boardMapper.register(boardRegisterRequestDto.toEntity());
	}

	@Transactional
	@Override
	public List<BoardListResponseDto> findBoardListByQuery(BoardQuery boardQuery) {

		return boardMapper.findBoardListByQuery(boardQuery).stream().map(v -> new BoardListResponseDto(v))
				.collect(Collectors.toList());
	}

	@Transactional
	@Override
	public int findBoardCntByQuery(BoardQuery boardQuery) {

		return boardMapper.findBoardCntByQuery(boardQuery);
	}

	@Override
	public List<BoardListResponseDto> findUserWroteList(String email) {

		return boardMapper.findUserWroteList(email).stream().map(v -> new BoardListResponseDto(v))
				.collect(Collectors.toList());
	}

	@Override
	public List<BoardListResponseDto> findUserLikedList(String email) {

		return boardMapper.findUserLikedList(email).stream().map(v -> {
			BoardListResponseDto boardListResponseDto = new BoardListResponseDto(v);
			boardListResponseDto.setWriter(userMapper.findByEmail(v.getWriter()).getNickName());
			return boardListResponseDto;
		})
				.collect(Collectors.toList());
	}

	@Transactional
	@Override
	public List<BoardListResponseDto> findPopularBoardList(String siName, String category) {

		return boardMapper.findPopularBoardList(siName, category).stream().map(v -> new BoardListResponseDto(v))
				.collect(Collectors.toList());
	}

	@Transactional
	@Override
	public BoardDetailResponseDto findBoardByBId(Long bId) {
		boardMapper.updateBoardHit(bId);
		Board board = boardMapper.findBoardDetail(bId);
		User user = userMapper.findByEmail(board.getWriter());
		return new BoardDetailResponseDto(board, user);
	}

	@Transactional
	@Override
	public Long findNextBId(Long bId, String siName) {

		return boardMapper.findNextBId(bId, siName);
	}

	@Transactional
	@Override
	public Long findPrevBId(Long bId, String siName) {

		return boardMapper.findPrevBId(bId, siName);
	}

	@Transactional
	@Override
	public void modifyBoard(BoardModifyRequestDto boardModifyRequestDto) {
		boardMapper.updateBoard(boardModifyRequestDto.toEntity());
	}

	@Transactional
	@Override
	public void deleteBoard(Long bId) {
		boardMapper.deleteBoard(bId);
	}

	@Transactional
	@Override
	public void likeBoard(BoardLikeDto boardLikeDto) {
		boardMapper.likeBoard(boardLikeDto.getBId(), boardLikeDto.getEmail());
	}

	@Transactional
	@Override
	public void unlikeBoard(BoardLikeDto boardLikeDto) {
		boardMapper.unlikeBoard(boardLikeDto.getBId(), boardLikeDto.getEmail());
	}

	@Override
	public List<Si> findAllSi() {

		return boardMapper.findAllSi();
	}

}
