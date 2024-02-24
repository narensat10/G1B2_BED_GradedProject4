package com.greatlearning.EmpManageApi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long emp_id;
	private String firstName;
	private String lastName;
	private String email;

}
