package com.backend.comment.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.board.mapper.BoardMapper;
import com.backend.comment.domain.Comment;
import com.backend.comment.dto.CommentModifyRequestDto;
import com.backend.comment.dto.CommentRegisterRequestDto;
import com.backend.comment.dto.CommentResponseDto;
import com.backend.comment.dto.UserWroteCommentDto;
import com.backend.comment.mapper.CommentMapper;
import com.backend.common.dto.PageableWithEmail;
import com.backend.user.mapper.UserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
	private final CommentMapper commentMapper;
	private final UserMapper userMapper;
	private final BoardMapper boardMapper;
	
	
	@Transactional
	@Override
	public int registerComment(CommentRegisterRequestDto commentRegisterRequestDto) {
		commentMapper.registerComment(commentRegisterRequestDto.toEntity());
		log.debug("commentService >>> register {}", commentRegisterRequestDto);
		return commentMapper.findLastSavedCId();
	}

	@Transactional
	@Override
	public List<CommentResponseDto> findCommentByBId(Long bId) {
		List<Comment> list = commentMapper.findCommentByBId(bId);
		log.debug("findCommentByBId >>>>> {}", bId);
		return list.stream().map(v -> {
			return new CommentResponseDto(v, userMapper.findByEmail(v.getWriter()));
		}).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public List<UserWroteCommentDto> findCommentByWriter(PageableWithEmail pageableWithEmail) {
		List<Comment> list = commentMapper.findCommentByWriter(pageableWithEmail);
		return list.stream().map(v -> {
			return new UserWroteCommentDto(v, userMapper.findByEmail(v.getWriter()), boardMapper.findBoardDetail(v.getBId()));
		}).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public int findCommentByWriterCnt(String email) {
		log.debug("CommentService findCommentByWriterCnt email >>>> {}", email);
		return commentMapper.findCommentByWriterCnt(email);
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

	@Override
	@Transactional
	public void deleteCommentList(List<Long> list) {

		commentMapper.deleteCommentList(list.stream().map(String::valueOf).collect(Collectors.joining(",")));
	}

}
