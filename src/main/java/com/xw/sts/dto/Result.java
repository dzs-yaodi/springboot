package com.xw.sts.dto;

import java.util.HashMap;
import java.util.Map;

public class Result extends HashMap<String, Object>{

	public Result() {
		put("code",200);
	}

	public static Result error() {
		return error(500, "未知异常，请联系管理员");
	}
	
	public static Result error(int code,String error) {
		
		Result result = new Result();
		result.put("code", code);
		result.put("error", error);
		
		return result;
	}
	
	public static Result error(Object msg) {
		Result result = new Result();
		result.put("code", 500);
		result.put("error", msg);
		
		return result;
	}
	
	public static Result ok(Object msg) {
		Result r = new Result();
		r.put("msg", msg);
		return r;
	}
	
	public static Result ok(Map<String, Object> map) {
		Result r = new Result();
		r.putAll(map);
		return r;
	}

	@Override
	public Result put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
