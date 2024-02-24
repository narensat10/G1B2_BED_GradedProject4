package com.greatlearning.EmpManageApi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.greatlearning.EmpManageApi.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	// fetching employee using first name by ignoring case
	@Query("SELECT e FROM Employee e WHERE LOWER(e.firstName) LIKE LOWER(concat('%', ?1, '%'))")
	List<Employee> findByFirstNameContainingIgnoreCase(String fname);

}
