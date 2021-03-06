package dev.drop.models.member.dto;

import java.time.LocalDateTime;

import dev.drop.models.member.domain.entity.MemberEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor
public class MemberDTO {
	private Long id;
	private String name;
	private String email;
	private String password;
	private String phone;
	private long point;
	private int coin;
	private long cash;
	private String status_1;
	private String in_date;
	private String up_date;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;

	public MemberEntity toEntity() {
		return MemberEntity.builder()
				.id(id).name(name).email(email).password(password).phone(phone)
				.point(point).coin(coin).cash(cash).status_1(status_1).in_date(in_date).up_date(up_date)
				.build();
	}

	@Builder
	public MemberDTO(Long id, String name, String email, String password, String phone
			, long point, int coin, long cash, String status_1, String in_date, String up_date) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.point = point;
		this.coin = coin;
		this.cash = cash;
		this.status_1 = status_1;
		this.in_date = in_date;
		this.up_date = up_date;
		
	}
}
