package com.backend.board.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardServiceImple implements BoardService {

	private final BoardMapper boardMapper;
	private final ResponseService responseService;

	@Transactional
	@Override
	public void registerBoard(BoardRegisterRequestDto boardRegisterRequestDto) {
		boardMapper.register(boardRegisterRequestDto.toEntity());
	}

	@Transactional
	@Override
	public List<BoardListResponseDto> findBoardListByQuery(BoardQuery boardQuery) {
		
		return boardMapper.findBoardListByQuery(boardQuery).stream()
						.map(v -> new BoardListResponseDto(v)).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public List<BoardListResponseDto> findPopularBoardList(String siName, String category) {

		return boardMapper.findPopularBoardList(siName, category).stream()
				.map(v -> new BoardListResponseDto(v)).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public BoardDetailResponseDto findBoardByBId(Long bId) {
		boardMapper.updateBoardHit(bId);

		return new BoardDetailResponseDto(boardMapper.findBoardDetail(bId));
	}

	@Transactional
	@Override
	public void updateBoard(BoardModifyRequestDto boardModifyRequestDto) {
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
