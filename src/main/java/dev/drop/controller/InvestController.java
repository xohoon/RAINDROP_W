package dev.drop.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.drop.models.invest.dto.ImitationDTO;
import dev.drop.models.invest.dto.SaveDTO;
import dev.drop.models.invest.mapper.InvestMapper;
import dev.drop.models.member.mapper.MemberMapper;
import dev.drop.utils.Revenue;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

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

@Controller
@RequestMapping(value="/invest")
public class InvestController {
	
	@Autowired
	private InvestMapper investMapper;
	@Autowired
	private MemberMapper memberMapper;
	
	
	// ***** 리얼투자  ***** //
	
	// mine invest
	@GetMapping(value="/raindrop")
	public String mine() {
		return "invest/raindrop";
	}
	
	// 회원 조합받기
	@ResponseBody
	@GetMapping(
			value="/conn",
			produces="application/json; charset=utf-8")
	public Object Main(int numCount, int numRound, String user_email) {
		
		/*
		 * 
		 * 회원 추출 번호
		 * 
		*/
		JSONObject jsonData = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		SaveDTO saveDTO = new SaveDTO();
		ArrayList<String> ranList = new ArrayList<>();
		
		int member_id = investMapper.get_memberId(user_email);
//			int round = NewRound.newRound();
		int round = numRound;
		int round_id = investMapper.roundIdGet(round, member_id);
		int success = 0;
		
		for(int l = 1; l<=9999; l++) {
			System.out.println(l+"번째 for문:::");
			// 랜덤값 생성
			int[] numList = new int[6];
			for(int i = 0; i < numList.length; i++) {
				numList[i] = (int)(Math.random() * 45 + 1);
				for(int j = 0; j<i; j++) {
					if(numList[j] == numList[i]) {
						i--;
						break;
					}
				}
			}
			Arrays.sort(numList);
			ranList.add(Integer.toString(numList[0]));
			ranList.add(Integer.toString(numList[1]));
			ranList.add(Integer.toString(numList[2]));
			ranList.add(Integer.toString(numList[3]));
			ranList.add(Integer.toString(numList[4]));
			ranList.add(Integer.toString(numList[5]));

			System.out.println(l+"번째 랜덤 조합:::"+ranList.toString());
			
			// 저장된 정보
			ArrayList<String> saveList = new ArrayList<String>();
			int last = investMapper.LastNum();
			for(int i = 1; i <= last; i++) {
				saveDTO = investMapper.dataAll(i);
				saveList.add(Integer.toString(saveDTO.getNum1()));
				saveList.add(Integer.toString(saveDTO.getNum2()));
				saveList.add(Integer.toString(saveDTO.getNum3()));
				saveList.add(Integer.toString(saveDTO.getNum4()));
				saveList.add(Integer.toString(saveDTO.getNum5()));
				saveList.add(Integer.toString(saveDTO.getNum6()));
				
				// 저장된 조합과 랜덤 조합 일치여부 확인
				if(saveList.containsAll(ranList)) {
					System.out.println(i+"회 번호:: " + saveList.toString());
					System.out.println("ran조합 번호:: " + ranList.toString());
					saveList = new ArrayList<>();
					ranList = new ArrayList<>();
					break;
				}
				saveList = new ArrayList<>();
			}
			// 랜덤조합 합
			int ranSum = 0;
			if(ranList.size() != 0) {
				ranSum = Integer.parseInt(ranList.get(0))
						+ Integer.parseInt(ranList.get(1))
						+ Integer.parseInt(ranList.get(2))
						+ Integer.parseInt(ranList.get(3))
						+ Integer.parseInt(ranList.get(4))
						+ Integer.parseInt(ranList.get(5));
				System.out.println("&& 랜덤 조합 합 :: " + ranSum);
			}
			/*
			if(121 > ranSum || ranSum > 150) {
				// 랜덤 조합의 번호가 121보다 작고 150보다 크면 break
				System.out.println("합이 121 - 150 밖");
				ranList = new ArrayList<>();
				break;
			}
			*/
			if(ranSum >= 100 && ranSum <= 170) {
				int saving1 = Integer.parseInt(ranList.get(0));
				int saving2 = Integer.parseInt(ranList.get(1));
				int saving3 = Integer.parseInt(ranList.get(2));
				int saving4 = Integer.parseInt(ranList.get(3));
				int saving5 = Integer.parseInt(ranList.get(4));
				int saving6 = Integer.parseInt(ranList.get(5));
				round_id++;
				System.out.println("저장되는 번호 :: "+saving1+"^"+saving2+"^"+saving3+"^"+saving4+"^"+saving5+"^"+saving6+"^");
				investMapper.saving(member_id, saving1, saving2, saving3, saving4, saving5, saving6, round, ranSum, round_id);
				success++;
				ranList = new ArrayList<>();
			}
			ranList = new ArrayList<>();
			
			if(success == numCount) {
				System.out.println("번호 "+success+"개 추출완료");
				break;
			}
		// for
		}

		jsonData.put("member", member_id);
		jsonData.put("luck", success);
		jsonArray.add(jsonData);
		
		return jsonArray;
	}
	
	// 나의 회차별 등수 및 당첨금액 확인
	@ResponseBody
	@GetMapping(
			value="/myRank",
			produces="application/json; charset=utf-8")
	public Object myRank(int rankRound, String user_email) {
		JSONObject jsonData = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		ImitationDTO imiDTO = new ImitationDTO();
		SaveDTO saveDTO = new SaveDTO();
		ArrayList<Integer> testGame = new ArrayList<>();
		ArrayList<Integer> saveList = new ArrayList<>();
		
		int member_id = investMapper.get_memberId(user_email);
		
		int round = rankRound;
		int total = investMapper.roundTotal(round, member_id);
		int rankCount = 0;
		int rank01 = 0;
		int rank02 = 0;
		int rank03 = 0;
		int rank04 = 0;
		int rank05 = 0;
		System.out.println("회원아이디 : "+member_id);
		System.out.println("총 : "+total);
		System.out.println("ROUND : "+rankRound);
		for(int i = 1; i <= total; i++) {
			imiDTO = investMapper.roundData(i, round, member_id);
			System.out.println("TEST::"+imiDTO.toString());
			testGame.add(imiDTO.getNum1());
			testGame.add(imiDTO.getNum2());
			testGame.add(imiDTO.getNum3());
			testGame.add(imiDTO.getNum4());
			testGame.add(imiDTO.getNum5());
			testGame.add(imiDTO.getNum6());
			
			saveDTO = investMapper.decidedRound(round);
			saveList.add(saveDTO.getNum1());
			saveList.add(saveDTO.getNum2());
			saveList.add(saveDTO.getNum3());
			saveList.add(saveDTO.getNum4());
			saveList.add(saveDTO.getNum5());
			saveList.add(saveDTO.getNum6());
			
			for(int a = 0; a < testGame.size(); a++) {
				for(int b = 0; b < saveList.size(); b++) {
					if(testGame.get(a).equals(saveList.get(b))) {
						rankCount++;
					}
				}
			}
			//7번은 보너스 번호
			saveList.add(saveDTO.getNum7());
			
			if(rankCount == 6) {
				rank01++;
				rankCount = 0;
				System.out.println("1등"+rank01);
			}else if(rankCount == 5) {
				for(int c = 0; c < testGame.size(); c++) {
					if(testGame.get(c).equals(saveList.get(6))) {
						
						rank02++;
						rankCount = 0;
						System.out.println("2등"+rank02);
					}
				}
				if(rankCount == 5) {
					rank03++;
					rankCount = 0;
					System.out.println("3등"+rank03);
				}
			}else if(rankCount == 4) {
				rank04++;
				rankCount = 0;
				System.out.println("4등"+rank04);
			}else if(rankCount == 3) {
				rank05++;
				rankCount = 0;
				System.out.println("5등"+rank05);
			}
			
			// List reset
			rankCount = 0;
			testGame = new ArrayList<>();
			saveList = new ArrayList<>();
		}
		long revenue_total = Revenue.total(round, rank01, rank02, rank03, rank04, rank05);
		double after_tax = revenue_total*0.7;
		// 저장 제외
//		investMapper.saveRanking(rank01, rank02, rank03, rank04, rank05, round, total, revenue_total, after_tax);
		
		DecimalFormat formater = new DecimalFormat("###,###");
		
		jsonData.put("round", round);
		jsonData.put("rank1", rank01);
		jsonData.put("rank2", rank02);
		jsonData.put("rank3", rank03);
		jsonData.put("rank4", rank04);
		jsonData.put("rank5", rank05);
		jsonData.put("total", total);
		jsonData.put("revenue_total", formater.format(revenue_total));
		jsonData.put("tax", formater.format(after_tax));
		
		jsonArray.add(jsonData);
		
		return jsonArray;
	}
	
	
	// ***** 리얼투자  ***** //
	
	
	// ***** 모의투자  ***** //
	@GetMapping(
			value="/droptop",
			produces="application/json; charset=utf-8")
	public String droptop() {
		
		return "invest/droptop";
	}
	
	
	// ***** 모의투자  ***** //
}
