package com.backend.comment.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.comment.domain.Comment;
import com.backend.comment.dto.CommentModifyRequestDto;
import com.backend.comment.dto.CommentRegisterRequestDto;
import com.backend.comment.dto.CommentResponseDto;
import com.backend.comment.mapper.CommentMapper;
import com.backend.user.mapper.UserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
	private final CommentMapper commentMapper;
	private final UserMapper userMapper;

	@Transactional
	@Override
	public int registerComment(CommentRegisterRequestDto commentRegisterRequestDto) {
		commentMapper.registerComment(commentRegisterRequestDto.toEntity());
		return commentMapper.findLastSavedCId();
	}

	@Transactional
	@Override
	public List<CommentResponseDto> findCommentByBId(Long bId) {
		List<Comment> list = commentMapper.findCommentByBId(bId);
		return list.stream().map(v -> {
			return new CommentResponseDto(v, userMapper.findByEmail(v.getWriter()));
		}).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public List<CommentResponseDto> findCommentByWriter(String email) {
		List<Comment> list = commentMapper.findCommentByWriter(email);
		return list.stream().map(v -> {

			return new CommentResponseDto(v, userMapper.findByEmail(v.getWriter()));
		}).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public void modifyComment(CommentModifyRequestDto commentModifyRequestDto) {
		commentMapper.modifyComment(commentModifyRequestDto.toEntity());
		
	}

	@Transactional
	@Override
	public void deleteComment(Long cId) {
		commentMapper.deleteComment(cId);
	}

}
