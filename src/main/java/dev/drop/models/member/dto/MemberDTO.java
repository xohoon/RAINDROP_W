package dev.drop.models.member.dto;

import java.time.LocalDateTime;

import dev.drop.models.member.domain.entity.MemberEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDTO {
	private Long id;
	private String name;
	private String email;
	private String password;
	private String phone;
	private int type;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;

	public MemberEntity toEntity() {
		return MemberEntity.builder()
				.id(id).name(name).email(email).password(password).phone(phone).type(type)
				.build();
	}

	@Builder
	public MemberDTO(Long id, String name, String email, String password, String phone, int type) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.type = type;
	}
}
