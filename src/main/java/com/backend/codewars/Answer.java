package com.backend.codewars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Answer {

	public int GetSum(int a, int b) {
		int res = 0;
		for(int i = Math.min(a, b); i <= Math.max(a, b); i++) {
			res += i;
		}
		return res;
	} 
}
