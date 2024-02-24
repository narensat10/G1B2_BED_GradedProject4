package com.greatlearning.EmpManageApi.serviceImpl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greatlearning.EmpManageApi.Repository.AppUserRepository;
import com.greatlearning.EmpManageApi.entity.AppUser;
import com.greatlearning.EmpManageApi.entity.Role;
import com.greatlearning.EmpManageApi.security.MyAppUserDetails;
import com.greatlearning.EmpManageApi.service.AppUserService;

@Service
public class AppUserServiceImpl implements AppUserService, UserDetailsService {

	@Autowired
	AppUserRepository auserRepository;

	@Override
	public AppUser addAppUser(AppUser auser) {

		return auserRepository.save(auser);
	}

	@Override
	public AppUser getAppUser(String username) {

		return auserRepository.findByUsername(username);
	}

	@Override
	public List<AppUser> getAllAppUser() {

		return auserRepository.findAll();
	}

	@Override
	public boolean deleteAppUser(String username) {
		AppUser delete_appUser = getAppUser(username);

		if (delete_appUser != null) {
			auserRepository.delete(delete_appUser);
			return true; // Indicate successful deletion
		} else {
			return false; // Indicate deletion failure
		}
	}

	@Override
	public AppUser editAppUser(String username, AppUser auser) {
		AppUser edit_appUser = getAppUser(username);
		edit_appUser.setUsername(auser.getUsername());
		edit_appUser.setPassword(auser.getPassword());
		edit_appUser.setRoles(auser.getRoles());

		return auserRepository.save(edit_appUser);
	}

	@Override
	public List<Role> getRolesByUsername(String username) {
		AppUser user = auserRepository.findByUsername(username);
		if (user != null)
			return user.getRoles();
		else
			return Collections.emptyList();
	}

	@Override
	public List<AppUser> getUsersByRoleName(String rname) {
		List<AppUser> usernames = auserRepository.findUsernamesByRolesRoleName(rname);

		if (usernames != null && !usernames.isEmpty())
			return usernames;

		else
			return Collections.emptyList();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appUser = auserRepository.findByUsername(username);
		if (appUser == null)
			throw new UsernameNotFoundException("Could not find User");

		return new MyAppUserDetails(appUser);
	}

}
