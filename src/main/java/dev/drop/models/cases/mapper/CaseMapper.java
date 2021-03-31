package dev.drop.models.cases.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import dev.drop.models.invest.dto.PrizeListDTO;

@Mapper
public interface CaseMapper {

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
	
	PrizeListDTO roundResult(@Param(value="round") int round);
	
}
