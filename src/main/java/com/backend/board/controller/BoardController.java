package com.backend.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.board.dto.BoardDetailResponseDto;
import com.backend.board.dto.BoardLikeDto;
import com.backend.board.dto.BoardListResponseDto;
import com.backend.board.dto.BoardModifyRequestDto;
import com.backend.board.dto.BoardQuery;
import com.backend.board.dto.BoardRegisterRequestDto;
import com.backend.board.dto.Si;
import com.backend.board.service.BoardService;
import com.backend.response.ResponseService;
import com.backend.response.result.CommonResult;
import com.backend.response.result.ListResult;
import com.backend.response.result.SingleResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

	private final ResponseService responseService;
	private final BoardService boardService;

	@GetMapping("/si")
	public List<Si> findAllSi(){
		return boardService.findAllSi();
	}
	
	@PostMapping("/register")
	public CommonResult registerBoard(@RequestBody BoardRegisterRequestDto boardRegisterRequestDto) {
		boardService.registerBoard(boardRegisterRequestDto);
		return responseService.getSuccessfulResult();
	}

	@GetMapping("/list")
	public ListResult<BoardListResponseDto> getBoardList(BoardQuery query) {
		List<BoardListResponseDto> boardList = boardService.findBoardListByQuery(query);
		int totalCnt = boardService.findBoardCntByQuery(query);
		ListResult<BoardListResponseDto> res = responseService.getListResult(boardList);
		res.setTotalCnt(totalCnt);
		return res;
	}
	
	@GetMapping("/list/{query}")
	public ListResult<BoardListResponseDto> getBoardByQuery(@PathVariable("query") String query, String email){
		
		List<BoardListResponseDto> list = new ArrayList<>();
		if(query.equals("wrote")) list = boardService.findUserWroteList(email);
		if(query.equals("liked")) list = boardService.findUserLikedList(email);
		return responseService.getListResult(list);
	}
	
	@GetMapping(value = {"/popular/{siName}","/popular/{siName}/{category}"})
	public ListResult<BoardListResponseDto> getPopular(@PathVariable("siName") String siName, @PathVariable(required = false, name = "category") String category){
		List<BoardListResponseDto> boardList = boardService.findPopularBoardList(siName, category);
		return responseService.getListResult(boardList);
	}

	@GetMapping("/{bId}/{siName}/next")
	public SingleResult<Long> next(@PathVariable("bId") Long bId, @PathVariable("siName") String siName) {
		Long id = boardService.findNextBId(bId, siName);
		log.debug(" next bId >>>>>>>>>>> {}", id);
		return responseService.getSingleResult(id);
	}

	@GetMapping("/{bId}/{siName}/prev")
	public SingleResult<Long> prev(@PathVariable("bId") Long bId, @PathVariable("siName") String siName) {

		return responseService.getSingleResult(boardService.findPrevBId(bId, siName));
	}
	
	@GetMapping("/{bId}")
	public SingleResult<BoardDetailResponseDto> detail(@PathVariable("bId") Long bId){
		BoardDetailResponseDto board = boardService.findBoardByBId(bId);
		return responseService.getSingleResult(board);
	}
	
	
	@PostMapping("/like")
	public CommonResult like(@RequestBody BoardLikeDto boardLikeDto) {
		boardService.likeBoard(boardLikeDto);
		return responseService.getSuccessfulResult();
	}
	
	@PostMapping("/unlike")
	public CommonResult unLike(@RequestBody BoardLikeDto boardLikeDto) {
		boardService.unlikeBoard(boardLikeDto);
		return responseService.getSuccessfulResult();
	}
	
	@DeleteMapping("{bId}")
	public CommonResult delete (@PathVariable("bId") Long bId) {
		boardService.deleteBoard(bId);
		return responseService.getSuccessfulResult();
	}
	
	@PutMapping("{bId}")
	public CommonResult modify (@PathVariable("bId") Long bId, @RequestBody BoardModifyRequestDto boardModifyRequestDto) {
		boardService.modifyBoard(boardModifyRequestDto);
		 
		return responseService.getSuccessfulResult();
	}
	

}
