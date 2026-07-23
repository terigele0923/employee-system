package com.example.employee_system.infrastructure.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.employee_system.domain.model.User;
import com.example.employee_system.infrastructure.mapper.UserMapper;

@Service
public class LoginUserDetailsService implements UserDetailsService {
	
	private final UserMapper userMapper;
	
	public LoginUserDetailsService(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		// TODO 自動生成されたメソッド・スタブ
		User user = userMapper.findByLoginId(loginId);
		if (user == null) {
			throw new UsernameNotFoundException("ログインIDまたパスワードが正しくない");
		}
		return new LoginUserDetails(user);
	}

	

}
