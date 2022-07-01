package com.backend.codewars;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.logging.log4j.util.StringBuilders;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Answer {
	 public static int calculateYears(double principal, double interest,  double tax, double desired) {
		 int years = 0;
		 	while(true) {
		 		years++;
		 		principal += principal*interest - (principal*interest*tax);
		 		System.out.printf("%d years, %d , desired: %d", years, principal, desired);
		 		if(principal > desired) break;
		 	}
		 	return years;
		  }
}
