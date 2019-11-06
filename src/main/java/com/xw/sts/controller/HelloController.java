package com.xw.sts.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	
	@RequestMapping("/hello")
	public String getHello() {
		return "hello myfirst spring_boot";
	}
}
