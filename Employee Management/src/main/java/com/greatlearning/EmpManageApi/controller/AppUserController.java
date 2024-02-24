package com.greatlearning.EmpManageApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.EmpManageApi.entity.AppUser;
import com.greatlearning.EmpManageApi.entity.Role;
import com.greatlearning.EmpManageApi.service.AppUserService;

@RestController
@RequestMapping("/users")
public class AppUserController {

	@Autowired
	AppUserService auserService;

	@PostMapping("/add")
	public AppUser createAppUser(@RequestBody AppUser auser) {
		return auserService.addAppUser(auser);
	}

	@PutMapping("/edit/{username}")
	public AppUser editAppUser(@PathVariable("username") String username, @RequestBody AppUser auser) {
		return auserService.editAppUser(username, auser);
	}

	@GetMapping("/search/{username}")
	public ResponseEntity<AppUser> searchAppUser(@PathVariable("username") String username) {
		AppUser appUser = auserService.getAppUser(username);

		if (appUser != null) {
			return ResponseEntity.ok(appUser);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/list")
	public List<AppUser> displayAllUsers() {
		return auserService.getAllAppUser();
	}

	@GetMapping("/userRoles/{username}")
	public List<Role> searchUserRoles(@PathVariable("username") String username) {
		return auserService.getRolesByUsername(username);

	}

	@GetMapping("/roleslist/{roleName}/usernames")
	public List<AppUser> displayUsernamesOfRole(@PathVariable("roleName") String roleName) {
		return auserService.getUsersByRoleName(roleName);
	}

	@DeleteMapping("/delete/{username}")
	public ResponseEntity<String> deleteUserByUsername(@PathVariable("username") String username) {
		boolean deletionSuccessful = auserService.deleteAppUser(username);

		if (deletionSuccessful) {
			return ResponseEntity.ok("User deleted successfully");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user");
		}
	}

}
