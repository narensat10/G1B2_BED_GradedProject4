package com.greatlearning.EmpManageApi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.greatlearning.EmpManageApi.entity.AppUser;
import com.greatlearning.EmpManageApi.entity.Role;

@Service
public interface AppUserService {

	AppUser addAppUser(AppUser auser);

	AppUser getAppUser(String username);

	AppUser editAppUser(String username, AppUser auser);

	List<Role> getRolesByUsername(String username);

	List<AppUser> getAllAppUser();

	List<AppUser> getUsersByRoleName(String rname);

	boolean deleteAppUser(String username);

}
