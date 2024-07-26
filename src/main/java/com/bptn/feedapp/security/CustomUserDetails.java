package com.bptn.feedapp.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bptn.feedapp.jpa.User;

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	User user;

	public CustomUserDetails(User user) {
		super();
		this.user = user;
	}

//	 returns a collection of GrantedAuthority objects representing the authorities granted to the user
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return null;
	}

//	returns password used to authenticate the user
	@Override
	public String getPassword() {
		return this.user.getPassword();
	}
	
//	return the username used to authenticate the user
	@Override
	public String getUsername() {
		return this.user.getUsername();
	}

//	checks  whether the user's account has expired
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

//	checks if the user is locked or unlocked
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

//	checks if the users password has expired or not 
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

//	checks if user is enabled or disabled
	@Override
	public boolean isEnabled() {
		
		return true;
	}
}