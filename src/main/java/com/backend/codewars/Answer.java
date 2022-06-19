package com.backend.codewars;

import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Answer {
	public static String highAndLow(String numbers) {
		List<String> sorted = Arrays.asList(numbers.split(" "));
		sorted.sort((a, b) -> Integer.parseInt(a) - Integer.parseInt(b));
		
		return sorted.get(0) + " " + sorted.get(sorted.size() -1);
	}

}
