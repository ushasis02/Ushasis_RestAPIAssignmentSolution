package com.gl.EmpMgm.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gl.EmpMgm.model.DomainUserDetails;
import com.gl.EmpMgm.model.User;
import com.gl.EmpMgm.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DomainUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User validDomainUser = this.userRepository.findByName(username)
									.orElseThrow(()-> new UsernameNotFoundException("invalid username password"));
		return new DomainUserDetails(validDomainUser);
	}

}
