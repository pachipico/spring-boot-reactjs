package com.backend.comment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.comment.dto.CommentModifyRequestDto;
import com.backend.comment.dto.CommentRegisterRequestDto;
import com.backend.comment.dto.CommentResponseDto;
import com.backend.comment.dto.UserWroteCommentDto;
import com.backend.comment.service.CommentService;
import com.backend.common.dto.PageableWithEmail;
import com.backend.response.ResponseService;
import com.backend.response.result.CommonResult;
import com.backend.response.result.ListResult;
import com.backend.response.result.SingleResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

	private final ResponseService responseService;
	private final CommentService commentService;
	
	
	
	
	
	

	@PostMapping("/register")
	public SingleResult<Integer> registerComment(@RequestBody CommentRegisterRequestDto commentRegisterRequestDto) {
		log.debug("commentController >>> register");
		return responseService.getSingleResult(commentService.registerComment(commentRegisterRequestDto));
	}

	@GetMapping("/id/{bId}")
	public ListResult<CommentResponseDto> findByBId(@PathVariable("bId") Long bId) {
		log.debug("what the fuck");
		List<CommentResponseDto> list = commentService.findCommentByBId(bId);
		return responseService.getListResult(list);
	}

	@GetMapping("/writer/{email}")
	public ListResult<UserWroteCommentDto> findByWriter(@PathVariable("email") String email,
			@RequestParam("page") int page, @RequestParam("size") int size) {
		PageableWithEmail pageableWithEmail = new PageableWithEmail(page, size, email);
		List<UserWroteCommentDto> list = commentService.findCommentByWriter(pageableWithEmail);
		ListResult<UserWroteCommentDto> res = responseService.getListResult(list);
		log.debug("Comment findByWriter >>>>>> {}", list.size());
		int totalCnt = commentService.findCommentByWriterCnt(email);
		res.setTotalCnt(totalCnt);
		res.setCurrPage(page);
		res.setTotalPages((int) Math.ceil(((double) totalCnt) / size));
		
		return res;
	}

	@PutMapping("/modify")
	public CommonResult updateComment(@RequestBody CommentModifyRequestDto commentModifyRequestDto) {
		commentService.modifyComment(commentModifyRequestDto);
		return responseService.getSuccessfulResult();
	}

	@DeleteMapping("/delete/{cId}")
	public CommonResult deleteComment(@PathVariable("cId") Long cId) {
		commentService.deleteComment(cId);
		return responseService.getSuccessfulResult();
	}

	@PostMapping("/delete/list")
	public CommonResult deleteCommentList(@RequestBody List<Long> list){
		commentService.deleteCommentList(list);
		return responseService.getSuccessfulResult();
	}
}
