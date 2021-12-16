package dev.drop.controller;

import dev.drop.utils.Round;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import dev.drop.models.cases.mapper.CaseMapper;
import dev.drop.models.invest.dto.SaveListDTO;
import dev.drop.models.invest.dto.SaveResultDTO;
import dev.drop.models.invest.dto.PrizeListDTO;
import dev.drop.models.invest.mapper.InvestMapper;
import dev.drop.utils.Revenue;

import java.security.Principal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;

@Controller
@RequestMapping(value="/invest")
public class InvestController {
	
	@Autowired
	private InvestMapper investMapper;
	@Autowired
	private CaseMapper caseMapper;
	
	// ***** RAINDROP  ***** //
	// raindrop 접근
	@GetMapping(
			value="/raindrop",
			produces="application/json; charset=utf-8")
	public String Raindrop(Model model, Principal principal) {
		// 최근 회차 가지고오는 코드
		int member_id = investMapper.get_memberId(principal.getName());
		int last_num = Round.lastRound();
		List<SaveResultDTO> rainResultList = investMapper.rain_resultList(member_id);
		model.addAttribute("comming_round", last_num+1);
		model.addAttribute("rainResultList", rainResultList);
		
		return "invest/raindrop";
	}
	// ***** RAINDROP  ***** //

	// ***** DROPTOP  ***** //
	// droptop 접근
	@GetMapping(
			value="/droptop",
			produces="application/json; charset=utf-8")
	public String Dropdop(Model model, Principal principal) {
		// 최근 회차 가지고오는 코드
		int member_id = investMapper.get_memberId(principal.getName());
		int last_num = Round.lastRound();
		List<Integer> roundList = caseMapper.getRoundList();
		List<Integer> myList = investMapper.getMyList(member_id);
		List<SaveResultDTO> dropResultList = investMapper.getResultList(member_id);
		model.addAttribute("comming_round", last_num+1);
		model.addAttribute("roundList", roundList);
		model.addAttribute("myList", myList);
		model.addAttribute("dropResultList", dropResultList);

		return "invest/droptop";
	}
	
	// ***** DROPTOP  ***** //
	
	// ***** COMMON  ***** //
	// 번호 저장 공통
	@ResponseBody
	@GetMapping(
			value="/list_saving",
			produces="application/json; charset=utf-8")
	public Object Main(int numCount, int numRound, String whatDrop, Principal principal) {
		
		/*
		 * 
		 * 회원 추출 번호
		 * 
		*/
		
		JSONObject jsonData = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		PrizeListDTO saveDTO = new PrizeListDTO();
		ArrayList<String> ranList = new ArrayList<>();

		// 이미 회차 모의 투자를 진행한 경우
		int member_id = investMapper.get_memberId(principal.getName());
		int droptop_check = investMapper.top_Check(numRound, member_id);
		int raindrop_check = investMapper.rain_Check(numRound, member_id);
		if(whatDrop == "droptop" && droptop_check > 0) {
			jsonData.put("member", member_id);
			jsonData.put("luck", 0);
			jsonArray.add(jsonData);
			System.out.println("droptop = " + droptop_check);
			return jsonArray;
		} else if (whatDrop == "raindrop" && raindrop_check > 0) {
			jsonData.put("member", member_id);
			jsonData.put("luck", 0);
			jsonArray.add(jsonData);
			System.out.println("raindrop? = " + raindrop_check);
			return jsonArray;
		}

		int round = numRound;
		int round_id = 0;
		if(whatDrop.equals("raindrop")) {
			round_id = investMapper.rain_roundIdGet(round, member_id);
		}else if(whatDrop.equals("droptop")) {
			round_id = investMapper.top_roundIdGet(round, member_id);
		}
		int success = 0;
		
		for(int l = 1; l <= 999; l++) {
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
			// 최근 회차
			int last = Round.lastRound();
			for(int i = 1; i <= last; i++) {
				saveDTO = caseMapper.roundResult(i);
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
				if(whatDrop.equals("droptop")) {
					investMapper.top_list_saving(member_id, saving1, saving2, saving3, saving4, saving5, saving6, round, ranSum, round_id);
				}else if(whatDrop.equals("raindrop")) {
					investMapper.rain_list_saving(member_id, saving1, saving2, saving3, saving4, saving5, saving6, round, ranSum, round_id);
				}
				success++;
				ranList = new ArrayList<>();
			}
			ranList = new ArrayList<>();
			
			if(success == numCount) {
				System.out.println("WHAT?? = " + member_id + numCount);
				// 코인 차감
				investMapper.useCoin(member_id, numCount);
				// 내역 추가
				investMapper.history_outCoin(member_id, numCount);

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
	
	// 회차별 등수 및 당첨금액 확인
	@ResponseBody
	@GetMapping(
			value="/myRank",
			produces="application/json; charset=utf-8")
	public Object myRank(int rankRound, String whatDrop, Principal principal) {
		JSONObject jsonData = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		SaveListDTO imiDTO = new SaveListDTO();
		PrizeListDTO saveDTO = new PrizeListDTO();
		ArrayList<Integer> testGame = new ArrayList<>();
		ArrayList<Integer> saveList = new ArrayList<>();
		DecimalFormat formater = new DecimalFormat("###,###");
		SaveResultDTO resultDTO = new SaveResultDTO();
		
		int member_id = investMapper.get_memberId(principal.getName());
		int round = rankRound;
		int total = 0;
		int result = 0;
		// 결과값 확인 및 갯수 확인
		if(whatDrop.equals("raindrop")) {
			result = investMapper.rain_resultCheck(member_id, round);
			total = investMapper.rain_roundTotal(round, member_id);
		}else if(whatDrop.equals("droptop")) {
			result = investMapper.top_resultCheck(member_id, round);
			total = investMapper.top_roundTotal(round, member_id);
		}
		int rankCount = 0;
		int rank01 = 0;
		int rank02 = 0;
		int rank03 = 0;
		int rank04 = 0;
		int rank05 = 0;
		
		if(total < 1) { // 회차 게임 없을때
			jsonData.put("gameResult", "false");
			jsonData.put("round", round);
		}else if(result == 0) { // 회차 저장 없을때
			System.out.println("RESULT TEST2::"+result);
			System.out.println("회원아이디 : "+member_id);
			System.out.println("총 : "+total);
			System.out.println("ROUND : "+rankRound);
			for(int i = 1; i <= total; i++) {
				if(whatDrop.equals("raindrop")) {
					imiDTO = investMapper.rain_roundData(i, round, member_id);
				}else if(whatDrop.equals("droptop")) {
					imiDTO = investMapper.top_roundData(i, round, member_id);
				}
				testGame.add(imiDTO.getNum1());
				testGame.add(imiDTO.getNum2());
				testGame.add(imiDTO.getNum3());
				testGame.add(imiDTO.getNum4());
				testGame.add(imiDTO.getNum5());
				testGame.add(imiDTO.getNum6());
				
				saveDTO = caseMapper.roundResult(round);
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
				// 등수 로직
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
			// 회차별 등수 당첨금액 계산
			long revenue_total = Revenue.total(round, rank01, rank02, rank03, rank04, rank05);
			double after_tax = revenue_total*0.7;
			// 저장
			if(whatDrop.equals("raindrop")) {
				investMapper.rain_saveRanking(member_id, rank01, rank02, rank03, rank04, rank05, round, total, revenue_total, after_tax);

			}else if(whatDrop.equals("droptop")) {
				investMapper.top_saveRanking(member_id, rank01, rank02, rank03, rank04, rank05, round, total, revenue_total, after_tax);
				investMapper.confirmCheck(member_id, round);
			}
			jsonData.put("gameResult", "true");
			jsonData.put("round", round);
			jsonData.put("rank1", rank01);
			jsonData.put("rank2", rank02);
			jsonData.put("rank3", rank03);
			jsonData.put("rank4", rank04);
			jsonData.put("rank5", rank05);
			jsonData.put("total", total);
			jsonData.put("revenue_total", formater.format(revenue_total));
			//result = 0 end
		}else if(result >= 1){ // 회차 저장 있을때
			if(whatDrop.equals("raindrop")) {
				resultDTO = investMapper.rain_getResult(round, member_id);
			}else if(whatDrop.equals("droptop")) {
				resultDTO = investMapper.top_getResult(round, member_id);
			}
			jsonData.put("gameResult", "true");
			jsonData.put("round", resultDTO.getRound());
			jsonData.put("rank1", resultDTO.getRank01());
			jsonData.put("rank2", resultDTO.getRank02());
			jsonData.put("rank3", resultDTO.getRank03());
			jsonData.put("rank4", resultDTO.getRank04());
			jsonData.put("rank5", resultDTO.getRank05());
			jsonData.put("total", resultDTO.getGame_total());
			jsonData.put("revenue_total", resultDTO.getRevenue_total());
		}
		
		
		jsonArray.add(jsonData);
		
		return jsonArray;
	}
	
	// 최근 추첨 여부 확인
	@ResponseBody
	@GetMapping(value="/dropCheck")
	public Object DropCheck(String whatDrop, int round, Principal principal) {
		JSONObject jsonData = new JSONObject();
		int investCheck = 0;
		int member_id = investMapper.get_memberId(principal.getName());
		if(whatDrop.equals("droptop")) {
			investCheck = investMapper.top_Check(round, member_id);
		}else if(whatDrop.equals("raindrop")) {
			investCheck = investMapper.rain_Check(round, member_id);
		}
		if(investCheck == 0) {
			jsonData.put("chk", "pass");
		}else if(investCheck > 0){
			jsonData.put("chk", "block");
		}
		return jsonData;
	}
		
	// 최신회차 가져오기
	@ResponseBody
	@GetMapping(
			value="/getRound",
			produces="application/json; charset=utf-8")
	public Object GetRound(Model model) {
		// 최근 회차 가지고오는 코드
		int last_num = Round.lastRound();

		JSONObject jsonData = new JSONObject();
		jsonData.put("getRound", last_num);
		return jsonData;
	}

	@ResponseBody
	@GetMapping(
			value="/modalData",
			produces="application/json; charset=utf-8")
	public List<SaveListDTO> modalData(int round, Principal principal) {
		int member_id = investMapper.get_memberId(principal.getName());
		List<SaveListDTO> dropDetailList = investMapper.dropDetailList(member_id, round);

		return dropDetailList;
	}

	@ResponseBody
	@GetMapping(
			value="/exchangePoint",
			produces="application/json; charset=utf-8")
	public Object exchangePoint(@RequestParam int round, @RequestParam int point, Principal principal) {
		System.out.println("round = " + round);
		JSONObject jsonData = new JSONObject();
		int member_id = investMapper.get_memberId(principal.getName());
		int plusPoint = (int)(point * 0.01);
		try {
			// 당첨금 1% 포인트 충전
			investMapper.setPoint(plusPoint, member_id);
			// 포인트 충전 후 status update
			investMapper.setExchange(plusPoint, member_id, round);
			investMapper.setHistory(plusPoint, member_id);
			jsonData.put("chk", "success");
		}catch (Exception e){
			jsonData.put("chk", "fail");
			e.printStackTrace();
		}
		return jsonData;
	}
	
	// ***** COMMON  ***** //
}
