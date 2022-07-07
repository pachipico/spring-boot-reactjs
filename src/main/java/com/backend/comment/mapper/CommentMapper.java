package com.backend.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.backend.comment.domain.Comment;
import com.backend.common.dto.PageableWithEmail;

@Mapper
public interface CommentMapper {
	void registerComment(Comment comment);
	List<Comment> findCommentByBId(Long bId);
	List<Comment> findCommentByWriter(PageableWithEmail pageableWithEmail);
	int findCommentByWriterCnt(String email);
	void modifyComment(Comment comment);
	void deleteComment(Long cId);
	int findLastSavedCId();
}
