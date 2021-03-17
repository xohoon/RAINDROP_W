package dev.drop.models.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import dev.drop.models.member.dto.Member;
import dev.drop.models.member.dto.MemberDTO;

@Mapper
public interface MemberMapper {
	Integer mailCheck(@Param(value="email") String email);
	
	void signup(@Param(value="type") int type, 
			@Param(value="name") String name, 
			@Param(value="email") String email, 
			@Param(value="password") String password, 
			@Param(value="phone") int phone);
	
	Integer type_check(@Param(value="email") String email);
	
	Integer id_check(@Param(value="email_check") String email_check);
	
	String nick_check(@Param(value="email") String email);
	
	List<MemberDTO> member_list();
}
