package com.backend.codewars;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ToString
public class TestExtended extends TestClass {
	
	private int score;
	
	public TestExtended(String title, String content, int score) {
		super(title, content);
		this.score = score;
	}

}
