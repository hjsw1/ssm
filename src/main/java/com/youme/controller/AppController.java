package com.youme.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.youme.pojo.UserInfo;
import com.youme.service.UserService;

@Controller
public class AppController {
	private static Logger logger = LoggerFactory.getLogger(AppController.class);
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String index() {
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam String account, @RequestParam String password, HttpServletRequest request,
			HttpSession session) {
		String message = "";
		UserInfo userInfo = userService.login(account, password);
		if (null == userInfo) {
			message = "用户名密码不匹配，登录失败！";
		} else {
			session.setAttribute("user", userInfo);
			message = "登录成功！";
		}
		logger.info("[" + account + "] 登录系统，" + message);
		request.setAttribute("message", message);
		return "main";
	}
}
