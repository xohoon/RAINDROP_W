package dev.drop.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.drop.models.admin.mapper.AdminMapper;
import dev.drop.models.cases.mapper.CaseMapper;
import dev.drop.models.invest.dto.DroptopListDTO;
import dev.drop.models.invest.dto.DroptopResultDTO;
import dev.drop.models.invest.mapper.InvestMapper;
import dev.drop.models.member.dto.MemberDTO;
import dev.drop.models.member.mapper.MemberMapper;
import dev.drop.utils.Revenue;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping(value="/admin")
@AllArgsConstructor
public class AdminController {

	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private InvestMapper investMapper;
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private CaseMapper caseMapper;
	
	// 어드민 페이지
	@GetMapping("/index")
	public String DispAdmin() {
		return "/admin/index";
	}

	// 회원 리스트
	@GetMapping(value="/member_list")
	public String List(Model model) {
		ArrayList<MemberDTO> member = new ArrayList<>();
		member.addAll(memberMapper.member_list());
		model.addAttribute("list", member);

		return "/admin/member_list";
	}
	
	// ***** 관리자  ***** //

	// imitation GET
	@GetMapping(value="/imitation")
	public String imitation() {
		return "admin/imitation";
	}
	
	// 모의 번호 추출
	@ResponseBody
	@GetMapping(
			value="/imi",
			produces="application/json; charset=utf-8")
	public Object Imitation(String admin, int numCount, int numRound) {
		JSONObject jsonData = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		System.out.println(":: TEST DATA SCRIPT ::");
		DroptopResultDTO saveDTO = new DroptopResultDTO();
		ArrayList<String> ranList = new ArrayList<>();
		// round는 일시적으로 원하는 만큼 받고 나중에 자동으로 전환
//			int round = NewRound.newRound();
		int round = numRound;
		int round_id = adminMapper.testIdGet(round);
		int count = 0;
		
		for(int l = 1; l<=999999; l++) {
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
			/*
			 * 테스트용
			ranList.add(Integer.toString(10));
			ranList.add(Integer.toString(23));
			ranList.add(Integer.toString(29));
			ranList.add(Integer.toString(33));
			ranList.add(Integer.toString(37));
			ranList.add(Integer.toString(40));
			 */
			
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
					System.out.println(i+"회 번호와 일치 ");
					saveList = new ArrayList<>();
					ranList = new ArrayList<>();
					break;
				}
				saveList = new ArrayList<>();
			}
			// 랜덤조합 합
			int testSum = 0;
			if(ranList.size() != 0) {
				testSum = Integer.parseInt(ranList.get(0))
						+ Integer.parseInt(ranList.get(1))
						+ Integer.parseInt(ranList.get(2))
						+ Integer.parseInt(ranList.get(3))
						+ Integer.parseInt(ranList.get(4))
						+ Integer.parseInt(ranList.get(5));
			}
			/*
			if(121 > ranSum || ranSum > 150) {
				// 랜덤 조합의 번호가 121보다 작고 150보다 크면 break
				System.out.println("합이 121 - 150 밖");
				ranList = new ArrayList<>();
				break;
			}
			*/
			if(testSum >= 120 && testSum <= 150) {
				int testNum01 = Integer.parseInt(ranList.get(0));
				int testNum02 = Integer.parseInt(ranList.get(1));
				int testNum03 = Integer.parseInt(ranList.get(2));
				int testNum04 = Integer.parseInt(ranList.get(3));
				int testNum05 = Integer.parseInt(ranList.get(4));
				int testNum06 = Integer.parseInt(ranList.get(5));
				round_id++;
				adminMapper.testSaving(round, testNum01, testNum02, testNum03, testNum04, testNum05, testNum06, testSum, admin, round_id);
				System.out.println("저장된 조합 :: " + ranList.toString() + " :: SUM :: " + testSum);
				count++;
				ranList = new ArrayList<>();
			}
			ranList = new ArrayList<>();
			
			if(count == numCount) {
				System.out.println("번호 "+numCount+"개 추출완료");
				break;
			}
		// for
		}
		
		jsonData.put("testData", numRound+"STOP");

		return jsonData;
	}
	
	// 모의번호 등수 뽑기
	@ResponseBody
	@GetMapping(
			value="/rankCount",
			produces="application/json; charset=utf-8")
	public Object RankingCount(int rankRound) {
		JSONObject jsonData = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		DroptopListDTO imiDTO = new DroptopListDTO();
		DroptopResultDTO saveDTO = new DroptopResultDTO();
		ArrayList<String> testGame = new ArrayList<>();
		ArrayList<String> saveList = new ArrayList<>();
//			int round = NewRound.newRound();
		int round = rankRound;
		int total = adminMapper.gameTotal(round);
		System.out.println("::" + total);
		int rankCount = 0;
		int rank01 = 0;
		int rank02 = 0;
		int rank03 = 0;
		int rank04 = 0;
		int rank05 = 0;
		
		/*
		 * 
		 * 저장된 모의번호 round가져오는거 추가하기
		 * for문에 1부터 total 갯수하면 1부터 찾으니까 round 찾아서 확인할수있도록 수정해야함 문제가많네 ㅜㅜ
		 * 
		*/
		
		for(int i = 1; i <= total; i++) {
			imiDTO = adminMapper.imiData(i, round);
			testGame.add(Integer.toString(imiDTO.getNum1()));
			testGame.add(Integer.toString(imiDTO.getNum2()));
			testGame.add(Integer.toString(imiDTO.getNum3()));
			testGame.add(Integer.toString(imiDTO.getNum4()));
			testGame.add(Integer.toString(imiDTO.getNum5()));
			testGame.add(Integer.toString(imiDTO.getNum6()));
			
			saveDTO = caseMapper.decidedRound(round);
			saveList.add(Integer.toString(saveDTO.getNum1()));
			saveList.add(Integer.toString(saveDTO.getNum2()));
			saveList.add(Integer.toString(saveDTO.getNum3()));
			saveList.add(Integer.toString(saveDTO.getNum4()));
			saveList.add(Integer.toString(saveDTO.getNum5()));
			saveList.add(Integer.toString(saveDTO.getNum6()));
			
			for(int a = 0; a < testGame.size(); a++) {
				for(int b = 0; b < saveList.size(); b++) {
					if(testGame.get(a).equals(saveList.get(b))) {
						rankCount++;
					}
				}
			}
			
			// 7번 -> 보너스 번호
			saveList.add(Integer.toString(saveDTO.getNum7()));
			
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
		adminMapper.saveRanking(rank01, rank02, rank03, rank04, rank05, round, total, revenue_total, after_tax);
		
		DecimalFormat formater = new DecimalFormat("###,###");
		
		jsonData.put("rank1", rank01);
		jsonData.put("rank2", rank02);
		jsonData.put("rank3", rank03);
		jsonData.put("rank4", rank04);
		jsonData.put("rank5", rank05);
		jsonData.put("total", formater.format(revenue_total));
		jsonData.put("tax", formater.format(after_tax));
		
		jsonArray.add(jsonData);
		
		return jsonArray;
	}
	
	// ***** 관리자  ***** //

}
