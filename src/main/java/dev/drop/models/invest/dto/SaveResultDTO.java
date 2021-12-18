package dev.drop.models.invest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SaveResultDTO {
	
	private int id;
	private int member_id;
	private int round;
	private int rank01;
	private int rank02;
	private int rank03;
	private int rank04;
	private int rank05;
	private int rank_count;
	private int game_total;
	private long revenue_total;
	private long revenue_after_tax;
	private String in_date;
	private int exchange_point;
	private int exchange_status;
	private String exchange_date;
	
}
