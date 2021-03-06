package dev.drop.models.invest.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import dev.drop.models.invest.dto.SaveListDTO;
import dev.drop.models.invest.dto.SaveResultDTO;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Mapper
public interface InvestMapper {
	// ***** INVEST COMMON  ***** //
	
	// 이메일로 회원 고유번호 가져오기
	int getMemberId(@Param(value="userEmail") String userEmail);

	// 코인 차감
	void useCoin(@Param(value="member_id") int member_id, @Param(value = "numCount") int numCount);

	// 포인트 충전
	void setPoint(@Param(value = "plusPoint") int plusPoint, @Param(value = "member_id") int member_id);

	// 포인트 내역
	void setHistory(@Param(value = "plusPoint") int plusPoint,
					@Param(value = "member_id") int member_id,
					@Param(value = "in_member") int in_member);

	// 코인 내역
	void setOutCoin(@Param(value="member_id") int member_id, @Param(value = "numCount") int numCount);

	// 코인 충전
	void setExchangeCoin(
			@Param(value = "member_id") int member_id,
			@Param(value = "point") int point,
			@Param(value = "coin") int coin
	);

	// 회원 포인트, 코인 변경
	void setExchangeMember(
			@Param(value = "member_id") int member_id,
			@Param(value = "point") int point,
			@Param(value = "coin") int coin
	);

	// 등수 저장
	void setRank(@Param(value = "whatList") String whatList,
				 @Param(value = "i") int round_id,
				 @Param(value = "round") int round,
				 @Param(value = "rank") int rank,
				 @Param(value = "getRevenue") long getRevenue,
				 @Param(value = "member_id") int member_id
	);

	// 당첨금 적립
	void setCash(@Param(value = "member_id") int member_id, @Param(value = "revenue_total") long revenue_total);
}
