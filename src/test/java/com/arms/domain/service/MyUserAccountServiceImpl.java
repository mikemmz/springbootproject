package com.arms.domain.service;

import com.arms.domain.entity.Role;
import com.arms.domain.entity.User;
import com.arms.domain.repository.UserRepository;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserAccountServiceImpl implements UserAccountService, UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User findOneByEmail(String username) {
		return userRepository.findOneByEmail(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findOneByEmail(username);
		return new MyUserDetailsImpl(user);

	}

}

