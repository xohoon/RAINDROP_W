package dev.drop.controller;

import javax.servlet.http.HttpServletRequest;

import dev.drop.models.invest.mapper.InvestMapper;
import dev.drop.models.member.dto.CoinHistoryDTO;
import dev.drop.models.member.dto.Member;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.drop.models.admin.mapper.AdminMapper;
import dev.drop.models.member.dto.MemberDTO;
import dev.drop.models.member.mapper.MemberMapper;
import dev.drop.models.member.service.MemberService;
import dev.drop.utils.EmailService;
import dev.drop.utils.RandomString;
import lombok.AllArgsConstructor;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/member")
@AllArgsConstructor
public class MemberController {
	private MemberService memberService;
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	JavaMailSender emailSender;
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private InvestMapper investMapper;
	
	@ModelAttribute(value="contextPath")
	public String getContextPath(HttpServletRequest request) {
		System.out.println(request.getContextPath());
		return request.getContextPath();
	}
	
	// 회원 상세정보 페이지
	@RequestMapping("/info")
	public String Info(Model model, Principal principal) {
		int member_id = investMapper.getMemberId(principal.getName());
		Member getMember = memberMapper.getMemberInfo(member_id);
		List<CoinHistoryDTO> historyList = memberMapper.getHistoryList(member_id);
		model.addAttribute("getMember", getMember);
		model.addAttribute("historyList", historyList);

		return "member/info";
	}

	@ResponseBody
	@GetMapping(
			value="/updateInfo",
			produces="application/json; charset=utf-8")
	public Object UpdateInfo(String setName, String setEmail, String setPhone, Principal principal) {
		int member_id = investMapper.getMemberId(principal.getName());
		JSONObject jsonData = new JSONObject();
		try {
			memberMapper.setUserInfo(setName, setEmail, setPhone, member_id);
			jsonData.put("chk", "success");
		}catch (Exception e){
			jsonData.put("chk", "fail");
			e.printStackTrace();
		}
		return jsonData;
	}

	@RequestMapping("/login")
	public String login() {
		return "member/login";
	}

	// 로그인 폼
	@GetMapping(value="/signin")
	public String InForm() {
		return "member/signin";
	}
	
	// 로그인 결과 페이지
	@GetMapping(value="/signin/result")
	public String In() {
		
		return "/main";
	}

	// 회원가입 폼
	@GetMapping(value="/signup")
	public String UpForm() {
		return "member/signup";
	}
	
	// 회원가입 처리
	@PostMapping(value="/signup")
	public String UpPro(MemberDTO memberDTO) {
		memberService.joinUser(memberDTO);
	
		return "redirect:/member/signin";
	}
	
	@GetMapping(value="/modify")
	public String Modify() {
		return "member/modify";
	}
	
	@ResponseBody
	@PostMapping(
			value="/emailSend",
			produces="application/json; charset=utf-8")
	public Object EmailSend(String subject, String email) {
		System.out.println("::: mail test ::: " + email);
		JSONObject jsonData = new JSONObject();
		int mailCheck = memberMapper.mailCheck(email);
		System.out.println("::: mail test ::: 1");
		String random = RandomString.random(5);
		String returnData = "";
		
		// 이메일 중복방지 코드
		/*
		if(mailCheck != 0) {
			System.out.println("이메일 중복");
			returnData = "overlap";
			jsonData.put("check", returnData);
			return jsonData;
		}else {
			System.out.println("메일인증");
			subject = "[RINDROP Code] 인증메일";
			String text = "IEH 회원가입 인증번호 :: " + random;
			EmailService esi = new EmailService();
			esi.setJavaMailSender(emailSender);
			esi.sendSimpleMessage(email, subject, text);
			returnData = random;
			jsonData.put("check", returnData);
			System.out.println("::: mail test success :::");
		}
		 */
		
		// 이메일 중복없이 test version
		subject = "[RINDROP Code] 인증메일";
		String text = "RINDROP 회원가입 인증번호 :: " + random;
		EmailService esi = new EmailService();
		esi.setJavaMailSender(emailSender);
		esi.sendSimpleMessage(email, subject, text);
		returnData = random;
		jsonData.put("check", returnData);
		System.out.println("::: mail test success :::");

		return jsonData;
	}






	/*
	* 체크페이지
	* */
	// 회원정보
	@ResponseBody
	@GetMapping(
			value="/user_info",
			produces="application/json; charset=utf-8")
	public Object UserInfo(@RequestParam String email) {
		System.out.println("TEST:::1?");
		JSONObject jsonData = new JSONObject();
		MemberDTO memberDTO = new MemberDTO();
		memberDTO = adminMapper.userInfo(email);

		jsonData.put("userPoint", memberDTO.getPoint());
		jsonData.put("userCoin", memberDTO.getCoin());
		return jsonData;
	}

	// 로그아웃 결과 페이지
	@GetMapping(value="/logout/result")
	public String Logout() {
		return "/main";
	}

	// 접근 거부 페이지
	@GetMapping(value="/denied")
	public String DispDenied() {
		return "/member/denied";
	}

}
