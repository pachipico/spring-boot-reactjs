package com.backend.comment;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.backend.comment.domain.Comment;
import com.backend.comment.mapper.CommentMapper;



@SpringBootTest
public class CommentMapperTest {
	@Autowired
	private CommentMapper mapper;
	
	
	@Test
	public void registerTest() {
		for(int i = 1; i <= 30; i++) {
			mapper.registerComment(new Comment(212L, "123@123.com", "test comment number " + i)); 
		}
		mapper.findCommentByBId(212L);
	}
	
	@Test
	public void findCommentByWriter() {
		mapper.findCommentByWriter("123@123.com");
	}
	
	@Test
	public void update () {
		Comment comment = new Comment(30L, "바뀐내용");
		mapper.modifyComment(comment);
		mapper.findCommentByBId(212L);
	}
	

}
