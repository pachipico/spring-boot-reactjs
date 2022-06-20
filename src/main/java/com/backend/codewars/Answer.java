package com.backend.codewars;

import java.util.Arrays;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Answer {
	public boolean check(String sentence) {
		String str = "abcdefghijklmnopqrstuvwxyz";
		String res = String.join("",Arrays.asList(str.split("")).stream()
				.filter(v -> !Arrays.asList(sentence.split("")).stream().map(String::toLowerCase).collect(Collectors.toList()).contains(v.toLowerCase())).collect(Collectors.toList()));
		
		System.out.println(res);
		return str.length() == 0 ? true : false;
	}

}
