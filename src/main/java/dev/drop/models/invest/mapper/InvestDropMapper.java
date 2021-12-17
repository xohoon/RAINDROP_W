package dev.drop.models.invest.mapper;

import dev.drop.models.invest.dto.SaveListDTO;
import dev.drop.models.invest.dto.SaveResultDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InvestDropMapper {
    // DROPTOP 번호 저장
    void dropListSave(@Param(value="member_id") int member_id,
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
    int dropRoundCheck(@Param(value="round") int round,
                       @Param(value="member_id") int member_id);

    // 회차별 총 게임수
    int dropRoundCount(@Param(value="round") int round, @Param(value="member_id") int member_id);

    // 모의추첨 번호가져오기
    SaveListDTO dropRoundData(@Param(value="i") int i,
                              @Param(value="round") int round,
                              @Param(value="member_id") int member_id);

    // 등수 저장
    void dropRankSave(
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
    int dropRoundIdCount(@Param(value="round") int round, @Param(value="member_id") int member_id);

    // 결과 저장 확인
    int dropResultCount(@Param(value="member_id") int member_id, @Param(value="round") int round);

    // 결과값 가져오기
    SaveResultDTO dropGetResult(@Param(value="round") int round, @Param(value="member_id") int member_id);

    // 저장된 결과값 리스트
    List<SaveResultDTO> dropResultList(@Param(value = "member_id") int member_id);

    // 결과 확인 전 회차 가져오기
    List<Integer> dropBeforeConfirmList(@Param(value="member_id") int member_id);

    // 컨펌
    void dropConfirm(@Param(value = "member_id") int member_id, @Param(value="round") int round);

    // 회차 상세 리스트
    List<SaveListDTO> dropDetailList(@Param(value = "member_id") int member_id, @Param(value = "round") int round);

    // 환전 업데이트
    void dropExchangePoint(@Param(value = "plusPoint") int plusPoint,
                     @Param(value = "member_id") int member_id,
                     @Param(value = "round") int round);

    // 특정 회원 round list
    List<Integer> getRound(@Param(value = "member_id") int member_id);
}
