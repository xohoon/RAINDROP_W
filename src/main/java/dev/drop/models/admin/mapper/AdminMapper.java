package dev.drop.models.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import dev.drop.models.invest.dto.DroptopListDTO;
import dev.drop.models.member.dto.MemberDTO;

@Mapper
public interface AdminMapper {
	
	// 회원정보 GET
	MemberDTO userInfo(String email);
	
	int gameTotal(@Param(value="round") int round);
	
	int testIdGet(@Param(value="round") int round);
	
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
	
	DroptopListDTO imiData(@Param(value="i") int i, @Param(value="round") int round);
	
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
