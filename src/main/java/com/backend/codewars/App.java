package com.backend.codewars;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
	public static void main(String[] args) {
		TestExtended testExtended = new TestExtended("title", "content", 3);
		System.out.println(testExtended.getTitle());
	}
}
