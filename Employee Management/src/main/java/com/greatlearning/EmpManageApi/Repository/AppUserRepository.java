package com.greatlearning.EmpManageApi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greatlearning.EmpManageApi.entity.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

	public AppUser findByUsername(String username);

	// finding users by role name
	List<AppUser> findUsernamesByRolesRoleName(String roleName);

}
