package dev.drop.models.invest.dto;

public class SaveResultDTO {
	
	private int id;
	private int member_id;
	private int round;
	private int rank01;
	private int rank02;
	private int rank03;
	private int rank04;
	private int rank05;
	private int game_total;
	private Double revenue_total;
	private Double revenue_after_tax;
	private String in_date;
	private String up_date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getRank01() {
		return rank01;
	}
	public void setRank01(int rank01) {
		this.rank01 = rank01;
	}
	public int getRank02() {
		return rank02;
	}
	public void setRank02(int rank02) {
		this.rank02 = rank02;
	}
	public int getRank03() {
		return rank03;
	}
	public void setRank03(int rank03) {
		this.rank03 = rank03;
	}
	public int getRank04() {
		return rank04;
	}
	public void setRank04(int rank04) {
		this.rank04 = rank04;
	}
	public int getRank05() {
		return rank05;
	}
	public void setRank05(int rank05) {
		this.rank05 = rank05;
	}
	public int getGame_total() {
		return game_total;
	}
	public void setGame_total(int game_total) {
		this.game_total = game_total;
	}
	public Double getRevenue_total() {
		return revenue_total;
	}
	public void setRevenue_total(Double revenue_total) {
		this.revenue_total = revenue_total;
	}
	public Double getRevenue_after_tax() {
		return revenue_after_tax;
	}
	public void setRevenue_after_tax(Double revenue_after_tax) {
		this.revenue_after_tax = revenue_after_tax;
	}
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	public String getUp_date() {
		return up_date;
	}
	public void setUp_date(String up_date) {
		this.up_date = up_date;
	}
	
	@Override
	public String toString() {
		return "SvaeResultDTO [id=" + id + ", member_id=" + member_id + ", round=" + round + ", rank01=" + rank01
				+ ", rank02=" + rank02 + ", rank03=" + rank03 + ", rank04=" + rank04 + ", rank05=" + rank05
				+ ", game_total=" + game_total + ", revenue_total=" + revenue_total + ", revenue_after_tax="
				+ revenue_after_tax + ", in_date=" + in_date + ", up_date=" + up_date + "]";
	}
	
	
}
