package com.backend.response;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.response.result.CommonResult;
import com.backend.response.result.ListResult;
import com.backend.response.result.SingleResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ResponseService {

	public <T> SingleResult<T> getSingleResult(T data) {
		SingleResult<T> result = new SingleResult<>();
		result.setData(data);
		setSuccessResult(result);
		return result;
	}

	public <T> ListResult<T> getListResult(List<T> data) {
		ListResult<T> result = new ListResult<>();
		result.setData(data);
		setSuccessResult(result);
		return result;
	}

	public CommonResult getSuccessfulResult() {
		CommonResult result = new CommonResult();
		setSuccessResult(result);
		return result;
	}

	public CommonResult getFailResult() {
		CommonResult result = new CommonResult();
		setFailResult(result);
		return result;
	}

	public CommonResult getFailResult(int code, String msg) {
		CommonResult result = new CommonResult();

		result.setCode(code);
		result.setMsg(msg);
		return result;
	}

	public void setSuccessResult(CommonResult result) {
		result.setSuccess(true);
		result.setCode(CommonResponse.SUCCESS.getCode());
		result.setMsg(CommonResponse.SUCCESS.getMsg());
	}

	public void setFailResult(CommonResult result) {
		result.setSuccess(false);
		result.setCode(CommonResponse.FAIL.getCode());
		result.setMsg(CommonResponse.FAIL.getMsg());
	}
}
