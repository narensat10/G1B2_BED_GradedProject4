package com.greatlearning.EmpManageApi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.greatlearning.EmpManageApi.entity.Employee;
import com.greatlearning.EmpManageApi.exceptions.NotFoundException;

@Service
public interface EmployeeService {

	Employee addEmployee(Employee emp);

	Employee updateEmployee(long eid, Employee emp);

	Employee getEmployee(long eid);

	List<Employee> getAllEmployees();

	String deleteEmployee(long eid);

	List<Employee> getEmployeeByName(String ename) throws NotFoundException;

	List<Employee> sortEmployeeByName(String order);

}
