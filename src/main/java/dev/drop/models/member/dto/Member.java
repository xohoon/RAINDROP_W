package dev.drop.models.member.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class Member {
	private int id;
	private String name;
	private String email;
	private String password;
	private String password2;
	private int phone;
	private int type;
	private Date join_date;

}
