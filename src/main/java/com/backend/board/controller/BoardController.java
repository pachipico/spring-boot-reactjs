package com.backend.board.controller;

import java.util.ArrayList;
import java.util.List;

import com.backend.user.handler.ProfileImgHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.board.dto.BoardDetailResponseDto;
import com.backend.board.dto.BoardLikeDto;
import com.backend.board.dto.BoardListResponseDto;
import com.backend.board.dto.BoardModifyRequestDto;
import com.backend.board.dto.BoardQuery;
import com.backend.board.dto.BoardRegisterRequestDto;
import com.backend.board.dto.Si;
import com.backend.board.service.BoardService;
import com.backend.common.dto.PageableWithEmail;
import com.backend.response.ResponseService;
import com.backend.response.result.CommonResult;
import com.backend.response.result.ListResult;
import com.backend.response.result.SingleResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

	private final ResponseService responseService;
	private final BoardService boardService;

	private final ProfileImgHandler imgHandler;

	@GetMapping("/si")
	public List<Si> findAllSi() {
		return boardService.findAllSi();
	}

	@PostMapping("/register")
	public CommonResult registerBoard(@RequestBody BoardRegisterRequestDto boardRegisterRequestDto) {
		boardService.registerBoard(boardRegisterRequestDto);
		return responseService.getSuccessfulResult();
	}
	@PostMapping("/fileRegister")
	public CommonResult fileRegister(@RequestParam("img") MultipartFile img, @RequestParam("title") String title, @RequestParam("siName") String siName, @RequestParam("category") String category, @RequestParam("content") String content, @RequestParam("writer") String writer){
		log.debug("title: {}, writer: {}, content: {}, category: {}, file: {}", title, writer, content, category, img);
		boardService.registerBoard(new BoardRegisterRequestDto(title, writer, content, category, siName, imgHandler.uploadFile(img)));


		return responseService.getSuccessfulResult();
	}

	@GetMapping("/list")
	public ListResult<BoardListResponseDto> getBoardList(@RequestParam("page") int page, @RequestParam("size") int size,
			@RequestParam("siName") String siName,
			@RequestParam(name = "field", defaultValue = "title", required = false) String field,
			@RequestParam(name = "query", defaultValue = "", required = false) String query,
			@RequestParam(name = "orderBy", defaultValue = "desc", required = false) String orderBy,
			@RequestParam(name = "category", defaultValue = "", required = false) String category) {
		BoardQuery boardQuery = new BoardQuery(field, query, siName, category, orderBy, page, size);
		log.debug("<><><<><><><><><><><><><>page, {}", page);
		ListResult<BoardListResponseDto> res = responseService
				.getListResult(boardService.findBoardListByQuery(boardQuery));
		int totalCnt = boardService.findBoardCntByQuery(boardQuery);
		res.setCurrPage(page);
		res.setTotalCnt(totalCnt);

		res.setTotalPages((int) Math.ceil(((double) totalCnt) / size));
		log.debug("getBoardList size: {}", res.getData().size());
		return res;
	}

	@GetMapping("/list/{query}")
	public ListResult<BoardListResponseDto> getBoardByQuery(@PathVariable("query") String query,
			@RequestParam("email") String email, @RequestParam("page") int page, @RequestParam("size") int size) {
		log.debug("getBoardByQuery >>>>>>>> {}", query);
		PageableWithEmail pageableWithEmail = new PageableWithEmail(page, size, email);
		List<BoardListResponseDto> list = new ArrayList<>();
		int totalCnt = 0;
		if (query.equals("wrote")) {
			list = boardService.findUserWroteList(pageableWithEmail);
			totalCnt = boardService.findUserWroteListCnt(email);
		}
		if (query.equals("liked")) {
			list = boardService.findUserLikedList(pageableWithEmail);
			totalCnt = boardService.findUserLikedListCnt(email);
			
		}
		ListResult<BoardListResponseDto> res = responseService.getListResult(list);
		res.setCurrPage(page);
		res.setTotalCnt(totalCnt);
		res.setTotalPages((int) Math.ceil(((double) totalCnt) / size));

		return res;
	}

	@GetMapping(value = { "/popular/{siName}", "/popular/{siName}/{category}" })
	public ListResult<BoardListResponseDto> getPopular(@PathVariable("siName") String siName,
			@PathVariable(required = false, name = "category") String category) {
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
	public SingleResult<BoardDetailResponseDto> detail(@PathVariable("bId") Long bId) {
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

	@PostMapping("/unlike/list/{email}")
	public CommonResult unLikeList(@RequestBody List<Long> list, @PathVariable("email") String email){
		boardService.unlikeBoardList(email,list);
		return responseService.getSuccessfulResult();
	}

	@DeleteMapping("{bId}")
	public CommonResult delete(@PathVariable("bId") Long bId) {
		BoardDetailResponseDto board = boardService.findBoardByBId(bId);
		log.debug(">>>>>>>>>>> delete board : {}", board);
		imgHandler.removeFile(board.getImg());
		boardService.deleteBoard(bId);
		return responseService.getSuccessfulResult();
	}

	@PostMapping("/delete/list")
	public CommonResult deleteList (@RequestBody List<Long> list){
		boardService.deleteBoardList(list);

		return responseService.getSuccessfulResult();
	}

	@PutMapping("{bId}")
	public CommonResult modify(@PathVariable("bId") Long bId,
			@RequestParam("title") String title, @RequestParam("content") String content, @RequestParam("category") String category, @RequestParam(required = false, name="img") String img, @RequestParam(required = false, name = "newImg") MultipartFile newImg) {
		BoardModifyRequestDto boardModifyRequestDto = new BoardModifyRequestDto(bId, title, content, category, img);
		if(img == "removed") {
			imgHandler.removeFile(img);
			boardModifyRequestDto.setImg(null);
		}
		if(newImg != null){
			boardModifyRequestDto.setImg(imgHandler.uploadFile(newImg));
		}
		boardModifyRequestDto.setBId(bId);
		boardService.modifyBoard(boardModifyRequestDto);

		return responseService.getSuccessfulResult();
	}

	@GetMapping("/likedList")
	public ListResult<BoardListResponseDto> likedList(){
		return responseService.getListResult(boardService.findMostLikedList());
	}

	@GetMapping("/viewedList")
	public ListResult<BoardListResponseDto> viewedList(){
		return responseService.getListResult(boardService.findMostViewedList());
	}

	@GetMapping("/commentedList")
	public ListResult<BoardListResponseDto> commentedList() {
		return responseService.getListResult(boardService.findMostCommentedList());
	}
}
