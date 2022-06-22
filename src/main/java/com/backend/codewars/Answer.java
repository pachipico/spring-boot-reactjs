package com.backend.codewars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Answer {
	public static int duplicateCount(String text) {
		List<String> list = Arrays.asList(text.toLowerCase().split(""));
		List<String> dup = new ArrayList<>();
		for(String s : list) {
			if(list.indexOf(s) != list.lastIndexOf(s)) {
				if(dup.contains(s)) continue;
				dup.add(s);
			}
		}
		return dup.size();
	}

}
