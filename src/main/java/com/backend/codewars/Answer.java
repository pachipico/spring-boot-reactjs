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

	public static int sortDesc(final int num) {
		List<String> list = Arrays.asList(String.valueOf(num).split(""));
		list.sort((a,b) -> Integer.parseInt(a) - Integer.parseInt(b));
		return Integer.parseInt(String.join("", list));
		
	}

}
