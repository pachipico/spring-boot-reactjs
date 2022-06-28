package com.backend.codewars;

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
	private static final int MIN = 60;
	private static final int HOUR = MIN * 60;
	private static final int DAY = HOUR * 24;
	private static final int YEAR = DAY * 365;

	public static String formatDuration(int seconds) {
		
		int year = ((int) Math.floor(seconds / YEAR));
		int day = ((int) Math.floor(seconds / DAY));
		int hour = ((int) Math.floor(seconds / HOUR));
		int min = ((int) Math.floor(seconds / MIN));
		int sec = ((int) Math.floor(seconds));
		sec -= min * 60;
		min -= hour * 60;
		hour -= day * 24;
		day -= year * 365;
		System.out.printf("year: %d,day: %d, hour: %d, min: %d, sec: %d ", year, day, hour, min, sec);
		int[] nums = {year, day, hour, min, sec};
		String[] strs = {" year", " day", " hour", " minute", " second"};
		String res = String.join("",IntStream.range(0, nums.length).mapToObj(i -> {
			String str = "";
			if(nums[i] == 0) return str;
			if(nums[i] != 0) str += nums[i] + strs[i];
			if(nums[i] > 1) str += "s";
			return ", " + str;
		}).filter(v -> v != "").collect(Collectors.toList()));
		
		StringBuilder sb = new StringBuilder(res.replaceFirst(", ", ""));
		
		sb = new StringBuilder(sb.reverse().toString().replaceFirst(",","dna " ));
		return sb.toString().isBlank() ? "now" : sb.reverse().toString();
	}
}
