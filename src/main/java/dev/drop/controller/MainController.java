package dev.drop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping("/main")
	public String main() {
		return "main";
	}

	@RequestMapping("/intro")
	public String intro() {
		return "intro";
	}
	
	@RequestMapping("/test")
	public String test() {
		return "/test/test";
	}
	
	// test page
	@GetMapping("/module/header")
	public String Test() {
		return "/module/header";
	}
}
