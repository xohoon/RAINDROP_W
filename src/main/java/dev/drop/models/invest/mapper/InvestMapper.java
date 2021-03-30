package dev.drop.models.invest.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import dev.drop.models.invest.dto.DroptopListDTO;
import dev.drop.models.invest.dto.DroptopResultDTO;

@Mapper
public interface InvestMapper {
	
	// ***** DROPTOP  ***** //
	// DROPTOP 번호 저장
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
	
	// 회차별 총 게임수
	int roundTotal(@Param(value="round") int round, @Param(value="member_id") int member_id);
	
	// 모의추첨 번호가져오기
	DroptopListDTO roundData(@Param(value="i") int i, @Param(value="round") int round, @Param(value="member_id") int member_id);
	
	// 중복 추첨 체크
	int droptopCheck(@Param(value="round") int round, 
			@Param(value="member_id") int member_id);
	// ***** DROPTOP  ***** //
	
	
	// ***** RAINDROP  ***** //
	// RAINDROP 번호 저장
	void raindrop_list_saving(@Param(value="member_id") int member_id, 
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
	int raindropCheck(@Param(value="round") int round,
			@Param(value="member_id") int member_id);
	// ***** RAINDROP  ***** //
	
	
	// ***** COMMON  ***** //
	// 저장된 회차 round_id 갯수 가져오기
	int roundIdGet(@Param(value="round") int round, @Param(value="member_id") int member_id);

	// 마지막 회차 저장 위한 id값
	int LastNum();
	
	// 저장된 회차별 1등 번호 가져오기
	DroptopResultDTO dataAll(@Param(value="id") int id);
	
	// 이메일로 회원 고유번호 가져오기
	int get_memberId(@Param(value="user_email") String user_email);
	// ***** COMMON  ***** //
	
}
