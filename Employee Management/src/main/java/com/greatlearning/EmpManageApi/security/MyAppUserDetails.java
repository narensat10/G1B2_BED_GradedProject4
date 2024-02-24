package com.greatlearning.EmpManageApi.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.greatlearning.EmpManageApi.entity.AppUser;
import com.greatlearning.EmpManageApi.entity.Role;

public class MyAppUserDetails implements UserDetails {

	AppUser appUser;

	public MyAppUserDetails(AppUser appUser) {
		this.appUser = appUser;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<Role> roles = appUser.getRoles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}

		return authorities;
	}

	@Override
	public String getPassword() {

		return appUser.getPassword();
	}

	@Override
	public String getUsername() {

		return appUser.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

}
