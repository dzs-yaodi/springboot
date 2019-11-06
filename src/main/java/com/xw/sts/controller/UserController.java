package com.xw.sts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xw.sts.bean.User;
import com.xw.sts.dto.Result;
import com.xw.sts.repos.UserRepos;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepos userRepos;
	
	@PostMapping("/register")
	public Result register(String username,String password) {
		
		User user = userRepos.findByUsername(username);
		if (user != null) {
			return Result.error("账号已存在");
		}
		
		user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setNickname(username);
		
		userRepos.save(user);
		return Result.ok("注册成功");
	}
	
	
	@PostMapping("/login")
	public Result login(String username,String password) {
		User user = userRepos.findByUsernameAndPassword(username, password);
		
		if (user == null) {
			return Result.error("用户名或密码错误");
		}
		
		return Result.ok("登陆成功").put("user", user);
 	}
}
