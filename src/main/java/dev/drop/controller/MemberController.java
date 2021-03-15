package dev.drop.controller;

import java.security.Principal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.drop.models.member.dto.Member;
import dev.drop.models.member.dto.MemberDTO;
import dev.drop.models.member.mapper.MemberMapper;
import dev.drop.models.member.service.MemberService;
import dev.drop.utils.EmailService;
import dev.drop.utils.RandomString;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/member")
@AllArgsConstructor
public class MemberController {
	private MemberService memberService;
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	JavaMailSender emailSender;
	
	@ModelAttribute(value="contextPath")
	public String getContextPath(HttpServletRequest request) {
		System.out.println(request.getContextPath());
		return request.getContextPath();
	}

	@GetMapping(value="/signin")
	public String InForm() {
		return "member/signin";
	}
	
	// 로그인 결과 페이지
	@GetMapping(value="/signin/result")
	public String In() {
		
		return "/main";
	}

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

	// 내 정보 페이지
	@GetMapping(value="/info")
	public String MyInfo(Principal principal, Model model) {
		String email = principal.getName();
		int type = memberMapper.type_check(email);
		model.addAttribute("type", type);
		return "/member/info";
	}
	
	// 어드민 페이지
	@GetMapping("/admin")
	public String DispAdmin() {
		return "/member/admin";
	}
	
	// 회원 리스트
	@GetMapping("/admin_list")
	public String List(Model model) {
		ArrayList<Member> member = new ArrayList<>();
		member.addAll(memberMapper.member_list());
		model.addAttribute("list", member);
		
		return "/member/admin_list";
	}
}
