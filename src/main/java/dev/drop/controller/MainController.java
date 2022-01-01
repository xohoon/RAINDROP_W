package dev.drop.controller;

import dev.drop.models.member.dto.MainDTO;
import dev.drop.models.member.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {
	@Autowired
	private MemberMapper memberMapper;
	
	@RequestMapping(value={"/", "/main", "/index"})
	public String main(Model model) {
		List<MainDTO> mainRainList = memberMapper.getRainList();
		List<MainDTO> mainDropList = memberMapper.getDropList();
		model.addAttribute("mainRainList", mainRainList);
		model.addAttribute("mainDropList", mainDropList);

		return "main";
	}

	@RequestMapping("/intro")
	public String intro() {
		return "intro";
	}

}