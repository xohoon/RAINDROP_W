package dev.drop.models.member.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class Member {
	private int id;
	private String email;
	private String name;
	private int phone;
	private long point;
	private int coin;
	private long cash;
	private String status_1;
	private String in_date;
	private String up_date;

}
