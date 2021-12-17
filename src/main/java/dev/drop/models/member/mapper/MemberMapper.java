package dev.drop.models.member.mapper;

import java.util.List;

import dev.drop.models.member.dto.CoinHistoryDTO;
import dev.drop.models.member.dto.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import dev.drop.models.member.dto.MemberDTO;

@Mapper
public interface MemberMapper {
	// 이메일 중복 체크
	Integer mailCheck(@Param(value="email") String email);

	// 회원가입
	void signup(@Param(value="type") int type, 
			@Param(value="name") String name, 
			@Param(value="email") String email, 
			@Param(value="password") String password, 
			@Param(value="phone") int phone);

	// 회원 리스트
	List<MemberDTO> getUserList();

	// 회원 정보
	Member getMemberInfo(@Param(value = "member_id") int member_id);

	// 회원 정보 수정
	void setUserInfo(
			@Param(value="setName") String setName,
			@Param(value="setEmail") String setEmail,
			@Param(value="setPhone") String setPhone,
			@Param(value="member_id") int member_id
	);

	// 코인 내역
	List<CoinHistoryDTO> getHistoryList(@Param(value = "member_id") int member_id);
}
