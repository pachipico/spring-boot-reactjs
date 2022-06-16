package com.backend.codewars;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ToString
public class TestClass {
	private String title;
	private String content;

	public TestClass(String title, String content) {
		this.title = title;
		this.content = content;
	}

}
