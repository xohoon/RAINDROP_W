package dev.drop.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.drop.models.invest.mapper.InvestMapper;

@Controller
@RequestMapping(value="/case")
public class CaseController {
	@Autowired
	private InvestMapper investMapper;

	@ResponseBody
	@GetMapping(
			value="save_last",
			produces="application/json; charset=utf-8")
	public Object save() {
		JSONObject jsonData = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		System.out.println("::data save last::");
		
		/*
		 * 
		 * 마지막 회차 추가
		 * 
		*/
		
		// 최근 회차 가지고오는 코드
		String last_url = "https://dhlottery.co.kr/gameResult.do?method=byWin";
		Document last_doc = null;
		try {
			last_doc = Jsoup.connect(last_url).get();
		}catch(Exception e) {
			e.printStackTrace();
		}
		Elements last1 = last_doc.select("div.win_result");
		Elements last_1 = last1.get(0).select("h4");
		String last_result = last_1.text();
		int last_num = Integer.parseInt(last_result.substring(0, 3));
		// 최근 회차 가지고오는 코드
		
		int last = investMapper.LastNum()+1;
		String url = "https://www.dhlottery.co.kr/gameResult.do?method=byWin&drwNo=" + last;
		Document doc = null;
		String result = "";
		int search_num = investMapper.search_last();
		if(search_num != last_num) {
			try {
				doc = Jsoup.connect(url).get();
			}catch(Exception e) {
				e.printStackTrace();
			}
			Elements test = doc.select("span.ball_645");
			Elements test01 = test.get(0).select("span");
			Elements test02 = test.get(1).select("span");
			Elements test03 = test.get(2).select("span");
			Elements test04 = test.get(3).select("span");
			Elements test05 = test.get(4).select("span");
			Elements test06 = test.get(5).select("span");
			Elements test07 = test.get(6).select("span");
			int num1 = Integer.parseInt(test01.text());
			int num2 = Integer.parseInt(test02.text());
			int num3 = Integer.parseInt(test03.text());
			int num4 = Integer.parseInt(test04.text());
			int num5 = Integer.parseInt(test05.text());
			int num6 = Integer.parseInt(test06.text());
			int num7 = Integer.parseInt(test07.text());
			int sum = num1+num2+num3+num4+num5+num6;
			
			investMapper.save(num1, num2, num3, num4, num5, num6, num7, sum);
			
			System.out.println(last+"회차 앙 성공띄!");
			result = "성공";
			jsonData.put("last", last);
			jsonData.put("result", result);
			jsonArray.add(jsonData);
		}else {
			result = "최신회차가 저장되어있습니다";
			jsonData.put("result", result);
			jsonArray.add(jsonData);
			
		}
		
		return jsonArray;
	}
	
	@ResponseBody
	@GetMapping(
			value="save_all",
			produces="application/json; charset=utf-8")
	public Object save_all() {
		JSONObject jsonData = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		System.out.println("::data save all::");
		
		/*
		 * 
		 * 1회차부터 저장
		 * 
		 */
		
		// 최근 회차 가지고오는 코드
		String last_url = "https://dhlottery.co.kr/gameResult.do?method=byWin";
		Document last_doc = null;
		try {
			last_doc = Jsoup.connect(last_url).get();
		}catch(Exception e) {
			e.printStackTrace();
		}
		Elements last = last_doc.select("div.win_result");
		Elements last_1 = last.get(0).select("h4");
		String last_result = last_1.text();
		int last_num = Integer.parseInt(last_result.substring(0, 3));
		// 최근 회차 가지고오는 코드
		
		int list_count = investMapper.count_list();
		String result_message = "";
		if(list_count > 0) {
			result_message = "최근회차를 저장해주세요";
		}else {
			for(int i = 1; i<=last_num; i++) {
				String url = "https://www.dhlottery.co.kr/gameResult.do?method=byWin&drwNo=" + i;
				Document doc = null;
				
				try {
					doc = Jsoup.connect(url).get();
				}catch(Exception e) {
					e.printStackTrace();
				}
				Elements test = doc.select("span.ball_645");
				Elements test01 = test.get(0).select("span");
				Elements test02 = test.get(1).select("span");
				Elements test03 = test.get(2).select("span");
				Elements test04 = test.get(3).select("span");
				Elements test05 = test.get(4).select("span");
				Elements test06 = test.get(5).select("span");
				Elements test07 = test.get(6).select("span");
				int num1 = Integer.parseInt(test01.text());
				int num2 = Integer.parseInt(test02.text());
				int num3 = Integer.parseInt(test03.text());
				int num4 = Integer.parseInt(test04.text());
				int num5 = Integer.parseInt(test05.text());
				int num6 = Integer.parseInt(test06.text());
				int num7 = Integer.parseInt(test07.text());
				int sum = num1+num2+num3+num4+num5+num6;
				
				investMapper.save(num1, num2, num3, num4, num5, num6, num7, sum);
				
				System.out.println(i+"회차 앙 성공띄!");
			}
			result_message = "success";
		}
		
		jsonData.put("result", result_message);
		jsonArray.add(jsonData);
		
		return jsonArray;
	}
	
	@GetMapping(
			value="caseSum",
			produces="application/json; charset=utf-8")
	public String caseSum() {
		
		int last_round = investMapper.LastNum();
		
		/*
		 * 
		 * 50단위 합
		 * 
		 * 
		 * 
		
		// 20-100
		int case01 = 0;
		// 101-150;
		int case02 = 0;
		// 151-200;
		int case03 = 0;
		// 201-255;
		int case04 = 0;
		
		for(int i = 1; i<=last; i++) {
			int sum = investMapper.getSum(i);
			if(20 <= sum && sum <= 100) {
				System.out.println("ADD 20-100");
				case01++;
			}else if(101 <= sum && sum <=150) {
				System.out.println("ADD 101-150");
				case02++;
			}else if(151 <= sum && sum <= 200) {
				System.out.println("ADD 151-200");
				case03++;
			}else if(201 <= sum && sum <= 255) {
				System.out.println("ADD 201-255");
				case04++;
			}
		}
		int all_round = case01 + case02 + case03 + case04;
		investMapper.caseSum50(case01, case02, case03, case04, last_round, all_round);
		*/
		
		/*
		 * 
		 * 20단위 합
		 * 
		 * 
		
		int case01 = 0;
		int case02 = 0;
		int case03 = 0;
		int case04 = 0;
		int case05 = 0;
		int case06 = 0;
		int case07 = 0;
		
		for(int i = 1; i<=last_round; i++) {
			int sum = investMapper.getSum(i);
			if(20 <= sum && sum <= 100) {
				case01++;
			}else if(101 <= sum && sum <=120) {
				case02++;
			}else if(121 <= sum && sum <=140) {
				case03++;
			}else if(141 <= sum && sum <=160) {
				case04++;
			}else if(161 <= sum && sum <= 180) {
				case05++;
			}else if(181 <= sum && sum <=200) {
				case06++;
			}else if(201 <= sum && sum <= 255) {
				case07++;
			}
		}
		int all_round = case01 + case02 + case03 + case04 + case05 + case06 + case07;
		investMapper.caseSum20(case01, case02, case03, case04 , case05 , case06 , case07, last_round, all_round);
		*/
		
		/*
		 * 
		 * 10단위 합
		 * 
		 * */
		
		int case01 = 0;
		int case02 = 0;
		int case03 = 0;
		int case04 = 0;
		int case05 = 0;
		int case06 = 0;
		int case07 = 0;
		int case08 = 0;
		int case09 = 0;
		int case10 = 0;
		int case11 = 0;
		int case12 = 0;
		
		for(int i = 1; i<=last_round; i++) {
			int sum = investMapper.getSum(i);
			if(20 <= sum && sum <= 100) {
				case01++;
			}else if(101 <= sum && sum <=110) {
				case02++;
			}else if(111 <= sum && sum <=120) {
				case03++;
			}else if(121 <= sum && sum <=130) {
				case04++;
			}else if(131 <= sum && sum <=140) {
				case05++;
			}else if(141 <= sum && sum <=150) {
				case06++;
			}else if(151 <= sum && sum <=160) {
				case07++;
			}else if(161 <= sum && sum <=170) {
				case08++;
			}else if(171 <= sum && sum <=180) {
				case09++;
			}else if(181 <= sum && sum <= 190) {
				case10++;
			}else if(191 <= sum && sum <=200) {
				case11++;
			}else if(201 <= sum && sum <= 255) {
				case12++;
			}
		}
		int all_round = case01 + case02 + case03 + case04 + case05 + case06 + case07 + case08 + case09 + case10 + case11 + case12;
		investMapper.caseSum10(case01, case02, case03, case04 , case05 , case06 , case07, case08, case09, case10, case11, case12, last_round, all_round);
		
		return "caseSum success";
	}
}
