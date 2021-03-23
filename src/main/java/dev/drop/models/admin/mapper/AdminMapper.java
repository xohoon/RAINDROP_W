package dev.drop.models.admin.mapper;

import org.apache.ibatis.annotations.Mapper;

import dev.drop.models.member.dto.MemberDTO;

@Mapper
public interface AdminMapper {
	
	// 회원정보 GET
	MemberDTO userInfo(String email);
}
