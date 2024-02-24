package com.greatlearning.EmpManageApi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.EmpManageApi.Repository.RoleRepository;
import com.greatlearning.EmpManageApi.entity.Role;
import com.greatlearning.EmpManageApi.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public Role addRole(Role role) {

		return roleRepository.save(role);
	}

	@Override
	public Role getRole(String roleName) {

		return roleRepository.findByRoleName(roleName);
	}

	@Override
	public List<Role> getAllRoles() {

		return roleRepository.findAll();
	}

	@Override
	public boolean deleteRole(String roleName) {
		Role delete_role = getRole(roleName);
		if (delete_role != null) {
			roleRepository.delete(delete_role);
			return true;
		} else
			return false;
	}

}
