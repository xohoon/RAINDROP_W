package dev.drop.models.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import dev.drop.models.invest.dto.SaveListDTO;
import dev.drop.models.member.dto.MemberDTO;

@Mapper
public interface AdminMapper {
	
	// 회원정보 GET
	MemberDTO userInfo(String email);

	// 회원 포인트 및 코인 내역 추가
	void setBonus(@Param(value = "typeString") String typeString,
				  @Param(value = "setNum") int setNum,
				  @Param(value = "target_id") int target_id,
				  @Param(value = "member_id") int member_id);

	// 회원 포인트 및 코인 추가
	void setBonusMember(@Param(value = "type") String type,
						@Param(value = "setNum") int setNum,
						@Param(value = "target_id") int target_id,
						@Param(value = "member_id") int member_id);


	// 회차별 총 게임 수
	int gameTotal(@Param(value="round") int round);

	// 저장된 회차 round_id 가져오기
	int testIdGet(@Param(value="round") int round);

	// 모의로또 저장
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

	// 추첨번호 가져오기
	SaveListDTO imiData(@Param(value="i") int i, @Param(value="round") int round);

	// 등수 저장
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
}
