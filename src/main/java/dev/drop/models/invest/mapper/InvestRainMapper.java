package dev.drop.models.invest.mapper;

import dev.drop.models.invest.dto.SaveListDTO;
import dev.drop.models.invest.dto.SaveResultDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InvestRainMapper {
    // RAINDROP 번호 저장
    void rainListSave(@Param(value="member_id") int member_id,
                      @Param(value="saving1") int saving1,
                      @Param(value="saving2") int saving2,
                      @Param(value="saving3") int saving3,
                      @Param(value="saving4") int saving4,
                      @Param(value="saving5") int saving5,
                      @Param(value="saving6") int saving6,
                      @Param(value="round") int round,
                      @Param(value="ranSum") int ranSum,
                      @Param(value="round_id") int round_id);

    // 회차 중복 추첨 체크
    int rainRoundCheck(@Param(value="round") int round,
                   @Param(value="member_id") int member_id);

    // 회차별 총 게임수
    int rainRoundCount(@Param(value="round") int round, @Param(value="member_id") int member_id);

    // 모의추첨 번호가져오기
    SaveListDTO rainRoundData(@Param(value="i") int i,
                               @Param(value="round") int round,
                               @Param(value="member_id") int member_id);

    // 등수 저장
    void rainRankSave(
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
    int rainRoundIdCount(@Param(value="round") int round, @Param(value="member_id") int member_id);

    // 결과 저장 확인
    int rainResultCount(@Param(value="member_id") int member_id, @Param(value="round") int round);

    // 결과값 가져오기
    SaveResultDTO rainGetResult(@Param(value="round") int round, @Param(value="member_id") int member_id);

    // 결과값 리스트
    List<SaveResultDTO> rainResultList(@Param(value = "member_id") int member_id);

    // 결과 확인 전 리스트
    List<Integer> rainBeforeConfirmList(@Param(value = "member_id") int member_id);

    // 컨펌하기
    void rainConfirm(@Param(value = "member_id") int member_id, @Param(value = "round") int round);

    // 추첨 상세 리스트
    List<SaveListDTO> rainDetailList(@Param(value = "member_id") int member_id, @Param(value = "round") int round);
}
