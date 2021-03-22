package dev.drop.models.member.domain.entity;

import java.security.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "member")
public class MemberEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 20, nullable = false)
	private String name;
	
	@Column(length = 20, nullable = false)
	private String email;

	@Column(length = 100, nullable = false)
	private String password;
	
	@Column(length = 30, nullable = false)
	private String phone;
	
	@Column(length = 30, nullable = false)
	private String nick;
	
	@Column(length = 30, nullable = false)
	private int point;
	
	@Column(length = 30, nullable = false)
	private int coin;
	
	@Column(length = 30, nullable = false)
	private String status_1;
	
	@Column(length = 30, nullable = false)
	private String in_date;
	
	@Column(length = 30, nullable = false)
	private String up_date;
	
	@Builder
	public MemberEntity(Long id, String name, String email, String password, String phone, String nick, 
			int point, int coin, String status_1, String in_date, String up_date) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.nick = nick;
		this.point = point;
		this.coin = coin;
		this.status_1 = status_1;
		this.in_date = in_date;
		this.up_date = up_date;
	}
}
