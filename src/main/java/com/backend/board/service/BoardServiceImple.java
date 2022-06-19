package com.backend.board.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.board.dto.BoardDetailResponseDto;
import com.backend.board.dto.BoardListResponseDto;
import com.backend.board.dto.BoardModifyRequestDto;
import com.backend.board.dto.BoardRegisterRequestDto;
import com.backend.board.mapper.BoardMapper;
import com.backend.response.ResponseService;
import com.backend.response.result.ListResult;
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
	public ListResult<BoardListResponseDto> findBoardListByQuery(String field, String query, String siName,
			String category, String orderBy, int start) {

		return responseService
				.getListResult(boardMapper.findBoardListByQuery(field, query, siName, category, orderBy, start).stream()
						.map(v -> new BoardListResponseDto(v)).collect(Collectors.toList()));
	}

	@Transactional
	@Override
	public ListResult<BoardListResponseDto> findPopularBoardList(String siName, String category) {

		return responseService.getListResult(boardMapper.findPopularBoardList(siName, category).stream()
				.map(v -> new BoardListResponseDto(v)).collect(Collectors.toList()));
	}

	@Transactional
	@Override
	public SingleResult<BoardDetailResponseDto> findBoardByBId(Long bId) {
		boardMapper.updateBoardHit(bId);

		return responseService.getSingleResult(new BoardDetailResponseDto(boardMapper.findBoardDetail(bId)));
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
	public void likeBoard(Long bId, String email) {
		boardMapper.likeBoard(bId, email);
	}

	@Transactional
	@Override
	public void unlikeBoard(Long bId, String email) {
		boardMapper.unlikeBoard(bId, email);
	}

}
