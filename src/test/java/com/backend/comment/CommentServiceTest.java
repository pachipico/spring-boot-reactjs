package com.backend.comment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.backend.comment.dto.CommentRegisterRequestDto;
import com.backend.comment.service.CommentService;

@SpringBootTest
public class CommentServiceTest {
	@Autowired
	private CommentService service;
	
	@Test
	public void register() {
		for(int i = 1; i <= 10; i++) {
			service.registerComment(new CommentRegisterRequestDto("123@123.com", "test comment number " + 1, 213L));
		}
		service.findCommentByBId(213L);
	}
	
	@Test
	public void findCommentByEmail() {
		service.findCommentByWriter("123@123.com");
	}
}
