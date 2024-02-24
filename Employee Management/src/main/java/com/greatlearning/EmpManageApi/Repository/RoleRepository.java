package com.greatlearning.EmpManageApi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.greatlearning.EmpManageApi.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	// fetching role by role name
	@Query("SELECT r FROM Role r WHERE r.roleName = ?1")
	Role findByRoleName(String roleName);

}
