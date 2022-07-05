package com.backend.response.result;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class ListResult<T> extends CommonResult {
	private List<T> data;
	private int totalCnt;
	private int currPage;
	private int totalPages;
}
