package com.backend.response.result;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class SingleResult<T> extends CommonResult {
	private T data;
}
