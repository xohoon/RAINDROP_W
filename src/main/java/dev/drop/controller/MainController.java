package dev.drop.controller;

import dev.drop.models.invest.mapper.InvestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@Autowired
	private InvestMapper investMapper;
	
	@RequestMapping("/main")
	public String main() {
		return "main";
	}

	@RequestMapping("/intro")
	public String intro() {
		return "intro";
	}

}
