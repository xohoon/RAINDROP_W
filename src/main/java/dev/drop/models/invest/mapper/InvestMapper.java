package dev.drop.models.invest.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import dev.drop.models.invest.dto.SaveListDTO;
import dev.drop.models.invest.dto.SaveResultDTO;

import java.util.List;

@Mapper
public interface InvestMapper {
	// ***** RAINDROP  ***** //
	// 회차별 총 게임수
	int rain_roundTotal(@Param(value="round") int round, @Param(value="member_id") int member_id);
	
	// RAINDROP 번호 저장
	void rain_list_saving(@Param(value="member_id") int member_id, 
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
	int rain_Check(@Param(value="round") int round,
			@Param(value="member_id") int member_id);
	
	// 모의추첨 번호가져오기
	SaveListDTO rain_roundData(@Param(value="i") int i,
			@Param(value="round") int round, 
			@Param(value="member_id") int member_id);
	
	// 등수 저장
	void rain_saveRanking(
			@Param(value="member_id") int member_id, 
			@Param(value="rank01") int rank01, 
			@Param(value="rank02") int rank02, 
			@Param(value="rank03") int rank03, 
			@Param(value="rank04") int rank04, 
			@Param(value="rank05") int rank05, 
			@Param(value="round") int round, 
			@Param(value="total") int total, 
			@Param(value="revenue_total") double revenue_total,
			@Param(value="after_tax") double after_tax);

	// 저장된 회차 round_id 갯수 가져오기
	int rain_roundIdGet(@Param(value="round") int round, @Param(value="member_id") int member_id);
	
	// 결과 저장 확인
	int rain_resultCheck(@Param(value="member_id") int member_id, @Param(value="round") int round);
	
	// 결과값 가져오기
	SaveResultDTO rain_getResult(@Param(value="round") int round, @Param(value="member_id") int member_id);

	// 코인 차감
	void useCoin(@Param(value="member_id") int member_id, @Param(value = "numCount") int numCount);

	// 코인 내역
	void history_outCoin(@Param(value="member_id") int member_id, @Param(value = "numCount") int numCount);

	// 결과값 리스트
	List<SaveResultDTO> rain_resultList(@Param(value = "member_id") int member_id);
	// ***** RAINDROP  ***** //



	// ***** DROPTOP  ***** //
	// 회차별 총 게임수
	int top_roundTotal(@Param(value="round") int round, @Param(value="member_id") int member_id);
		
	// DROPTOP 번호 저장
	void top_list_saving(@Param(value="member_id") int member_id, 
			@Param(value="saving1") int saving1, 
			@Param(value="saving2") int saving2, 
			@Param(value="saving3") int saving3, 
			@Param(value="saving4") int saving4, 
			@Param(value="saving5") int saving5, 
			@Param(value="saving6") int saving6, 
			@Param(value="round") int round, 
			@Param(value="ranSum") int ranSum, 
			@Param(value="round_id") int round_id);
	
	// 모의추첨 번호가져오기
	SaveListDTO top_roundData(@Param(value="i") int i, 
			@Param(value="round") int round, 
			@Param(value="member_id") int member_id);
	
	// 중복 추첨 체크
	int top_Check(@Param(value="round") int round, 
			@Param(value="member_id") int member_id);
	
	// 등수 저장
	void top_saveRanking(
			@Param(value="member_id") int member_id, 
			@Param(value="rank01") int rank01, 
			@Param(value="rank02") int rank02, 
			@Param(value="rank03") int rank03, 
			@Param(value="rank04") int rank04, 
			@Param(value="rank05") int rank05, 
			@Param(value="round") int round, 
			@Param(value="total") int total, 
			@Param(value="revenue_total") double revenue_total,
			@Param(value="after_tax") double after_tax);
	
	// 저장된 회차 round_id 갯수 가져오기
	int top_roundIdGet(@Param(value="round") int round, @Param(value="member_id") int member_id);
	
	// 결과 저장 확인
	int top_resultCheck(@Param(value="member_id") int member_id, @Param(value="round") int round);
	
	// 결과값 가져오기
	SaveResultDTO top_getResult(@Param(value="round") int round, @Param(value="member_id") int member_id);

	// 모의투자 회차 가져오기
	List<Integer> getMyList(@Param(value="member_id") int member_id);

	// 확인한 값 1로 변경
	void confirmCheck(@Param(value = "member_id") int member_id, @Param(value="round") int round);

	// 저장된 결과값 리스트
	List<SaveResultDTO> getResultList(@Param(value = "member_id") int member_id);

	// 회차 상세 리스트
	List<SaveListDTO> dropDetailList(@Param(value = "member_id") int member_id, @Param(value = "round") int round);

	// 포인트 충전
	void setPoint(@Param(value = "plusPoint") int plusPoint, @Param(value = "member_id") int member_id);

	// 환전 업데이트
	void setExchange(@Param(value = "plusPoint") int plusPoint,
					 @Param(value = "member_id") int member_id,
					 @Param(value = "round") int round);

	// 포인트 내역
	void setHistory(@Param(value = "plusPoint") int plusPoint, @Param(value = "member_id") int member_id);
	// ***** DROPTOP  ***** //




	// ***** COMMON  ***** //
	// 마지막 회차 저장 위한 id값
	int LastNum();
	
	// 이메일로 회원 고유번호 가져오기
	int get_memberId(@Param(value="user_email") String user_email);
	// ***** COMMON  ***** //
	
}
