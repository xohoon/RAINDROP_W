package dev.drop.models.member.domain.entity;

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
	private int type;
	
	@Builder
	public MemberEntity(Long id, String name, String email, String password, String phone, int type) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.type = type;
	}
}
