package dev.drop.models.member.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dev.drop.models.member.domain.entity.MemberEntity;
import dev.drop.models.member.domain.repository.MemberRepository;
import dev.drop.models.member.dto.MemberDTO;
import dev.drop.models.member.dto.Role;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {
	private MemberRepository memberRepository;

	@Transactional
	public Long joinUser(MemberDTO memberDTO) {
		// 비밀번호 암호화
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));

		return memberRepository.save(memberDTO.toEntity()).getId();
	}

	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		Optional<MemberEntity> userEntityWrapper = memberRepository.findByEmail(userEmail);
		MemberEntity userEntity = userEntityWrapper.get();

		List<GrantedAuthority> authorities = new ArrayList<>();
		// admin@naver.com 계정만 ADMIN ROLE 부여
		if (("admin@naver.com").equals(userEmail)) {
			authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
		} else {
			authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
		}

		return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
	}
}
