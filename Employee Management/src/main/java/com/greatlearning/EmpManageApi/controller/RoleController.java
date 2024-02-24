package com.greatlearning.EmpManageApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.EmpManageApi.entity.Role;
import com.greatlearning.EmpManageApi.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	RoleService roleService;

	@PostMapping("/add")
	public Role createRole(Role role) {
		return roleService.addRole(role);
	}

	@GetMapping("/search/{roleName}")
	public Role searchRoleByName(@PathVariable("roleName") String rname) {
		return roleService.getRole(rname);
	}

	@GetMapping("/list")
	public List<Role> displayAllRoles() {
		return roleService.getAllRoles();
	}

	@DeleteMapping("/delete/{roleName}")
	public ResponseEntity<String> deleteRoleByName(@PathVariable("roleName") String rname) {
		boolean deletionSuccessful = roleService.deleteRole(rname);
		if (deletionSuccessful)
			return ResponseEntity.ok("Role deleted Successfully");
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete Role");
	}

}
