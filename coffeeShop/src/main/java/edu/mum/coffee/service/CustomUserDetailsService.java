package edu.mum.coffee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mum.coffee.securitymanagement.UserSecurity;

import edu.mum.coffee.domain.User;
import edu.mum.coffee.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;


	public CustomUserDetailsService() {
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(email);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("User with %s doesn't exist!", email));
		}
		return new UserSecurity(user);
	}

	public User saveUser(User user) {
		return userRepository.save(user);
	}
}
