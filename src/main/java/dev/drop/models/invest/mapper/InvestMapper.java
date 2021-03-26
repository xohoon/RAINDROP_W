package dev.drop.models.invest.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import dev.drop.models.invest.dto.DroptopListDTO;
import dev.drop.models.invest.dto.DroptopResultDTO;

@Mapper
public interface InvestMapper {
	
	// ***** 모의투자  ***** //
	
	void droptop_list_saving(@Param(value="member_id") int member_id,
			@Param(value="saving1") int saving1,
			@Param(value="saving2") int saving2,
			@Param(value="saving3") int saving3,
			@Param(value="saving4") int saving4,
			@Param(value="saving5") int saving5,
			@Param(value="saving6") int saving6, 
			@Param(value="round") int round, 
			@Param(value="ranSum") int ranSum, 
			@Param(value="round_id") int round_id);
	
	// 중복 추첨 체크
	int droptopCheck(@Param(value="round") int round, 
			@Param(value="user_email") String user_email);
	int raindropCheck(@Param(value="round") int round,
			@Param(value="user_email") String user_email);
	
	// ***** 모의투자  ***** //
	
	void saveRanking(
			@Param(value="rank01") int rank01, 
			@Param(value="rank02") int rank02, 
			@Param(value="rank03") int rank03, 
			@Param(value="rank04") int rank04, 
			@Param(value="rank05") int rank05, 
			@Param(value="round") int round, 
			@Param(value="total") int total, 
			@Param(value="revenue_total") double revenue_total,
			@Param(value="after_tax") double after_tax);
	
	void testSaving(@Param(value="round") int round, 
			@Param(value="testNum01") int testNum01, 
			@Param(value="testNum02") int testNum02, 
			@Param(value="testNum03") int testNum03, 
			@Param(value="testNum04") int testNum04, 
			@Param(value="testNum05") int testNum05, 
			@Param(value="testNum06") int testNum06, 
			@Param(value="testSum") int testSum, 
			@Param(value="admin") String admin, 
			@Param(value="round_id") int round_id);
	
	int get_memberId(@Param(value="user_email") String user_email);
	
	int gameTotal(@Param(value="round") int round);
	
	DroptopListDTO imiData(@Param(value="i") int i, @Param(value="round") int round);
	
	int testIdGet(@Param(value="round") int round);
	
	
	
	// ***** caseController  ***** //

	void save(@Param(value="num1") int num1,
			@Param(value="num2") int num2,
			@Param(value="num3") int num3,
			@Param(value="num4") int num4,
			@Param(value="num5") int num5,
			@Param(value="num6") int num6,
			@Param(value="num7") int num7,
			@Param(value="sum") int sum);
	
	// list count
	int count_list();
	
	// 최근회사 확인
	int search_last();
	
	DroptopResultDTO dataAll(@Param(value="id") int id);
	
	int LastNum();
	
	int getSum(@Param(value="i") int i);
	
	void caseSum50(@Param(value="case01") int case01, 
			@Param(value="case02") int case02, 
			@Param(value="case03") int case03, 
			@Param(value="case04") int case04, 
			@Param(value="last_round") int last_round, 
			@Param(value="all_round") int all_round);

	void caseSum20(@Param(value="case01") int case01, 
			@Param(value="case02") int case02, 
			@Param(value="case03") int case03, 
			@Param(value="case04") int case04, 
			@Param(value="case05") int case05, 
			@Param(value="case06") int case06, 
			@Param(value="case07") int case07, 
			@Param(value="last_round") int last_round, 
			@Param(value="all_round") int all_round);

	void caseSum10(@Param(value="case01") int case01, 
			@Param(value="case02") int case02, 
			@Param(value="case03") int case03, 
			@Param(value="case04") int case04, 
			@Param(value="case05") int case05, 
			@Param(value="case06") int case06, 
			@Param(value="case07") int case07, 
			@Param(value="case08") int case08, 
			@Param(value="case09") int case09, 
			@Param(value="case10") int case10, 
			@Param(value="case11") int case11, 
			@Param(value="case12") int case12, 
			@Param(value="last_round") int last_round, 
			@Param(value="all_round") int all_round);
	
	DroptopResultDTO decidedRound(@Param(value="round") int round);
	
	// ***** caseController  ***** //
	
	
	// ***** 나의 투자  ***** //
	

	int roundIdGet(@Param(value="round") int round, @Param(value="member_id") int member_id);

	int roundTotal(@Param(value="round") int round, @Param(value="member_id") int member_id);

	DroptopListDTO roundData(@Param(value="i") int i, @Param(value="round") int round, @Param(value="member_id") int member_id);

	
	// ***** 나의 투자  ***** //
	
}
