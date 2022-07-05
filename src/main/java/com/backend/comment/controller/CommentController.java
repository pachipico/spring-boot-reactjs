package com.backend.comment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.comment.dto.CommentModifyRequestDto;
import com.backend.comment.dto.CommentRegisterRequestDto;
import com.backend.comment.dto.CommentResponseDto;
import com.backend.comment.dto.UserWroteCommentDto;
import com.backend.comment.service.CommentService;
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
		
		return responseService.getSingleResult(commentService.registerComment(commentRegisterRequestDto));
	}

	@GetMapping("/id/{bId}")
	public ListResult<CommentResponseDto> findByBId(@PathVariable("bId") Long bId) {
		List<CommentResponseDto> list = commentService.findCommentByBId(bId);
		return responseService.getListResult(list);
	}

	@GetMapping("/writer/{email}")
	public ListResult<UserWroteCommentDto> findByWriter(@PathVariable("email") String email){
		List<UserWroteCommentDto> list = commentService.findCommentByWriter(email);
		log.debug("comment by {} >>>>>> {}", email, list.size());
		return responseService.getListResult(list);
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
}
