package com.greatlearning.EmpManageApi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.greatlearning.EmpManageApi.entity.Role;

@Service
public interface RoleService {

	Role addRole(Role role);

	Role getRole(String roleName);

	List<Role> getAllRoles();

	boolean deleteRole(String roleName);

}
