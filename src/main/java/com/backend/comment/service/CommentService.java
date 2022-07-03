package com.backend.comment.service;

import java.util.List;

import com.backend.comment.dto.CommentRegisterRequestDto;
import com.backend.comment.dto.CommentResponseDto;
import com.backend.comment.dto.CommentModifyRequestDto;

import lombok.extern.slf4j.Slf4j;


public interface CommentService {
	int registerComment(CommentRegisterRequestDto commentRegisterRequestDto);
	List<CommentResponseDto> findCommentByBId(Long bId);
	List<CommentResponseDto> findCommentByWriter(String email);
	void modifyComment(CommentModifyRequestDto commentModifyRequestDto);
	void deleteComment(Long cId);
}
