package com.backend.codewars;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
	public static void main(String[] args) {
		Answer answer = new Answer();
		answer.isSquare(4);
		answer.isSquare(3);
	}
}
