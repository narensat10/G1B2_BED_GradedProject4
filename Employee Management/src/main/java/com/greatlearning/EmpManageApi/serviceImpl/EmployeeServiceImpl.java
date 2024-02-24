package com.greatlearning.EmpManageApi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.greatlearning.EmpManageApi.Repository.EmployeeRepository;
import com.greatlearning.EmpManageApi.entity.Employee;
import com.greatlearning.EmpManageApi.exceptions.NotFoundException;
import com.greatlearning.EmpManageApi.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository empRepository;

	@Override
	public Employee addEmployee(Employee emp) {

		return empRepository.save(emp);
	}

	@Override
	public Employee updateEmployee(long eid, Employee emp) {
		Employee edit_employee = getEmployee(eid);
		edit_employee.setFirstName(emp.getFirstName());
		edit_employee.setLastName(emp.getLastName());
		edit_employee.setEmail(emp.getEmail());

		return empRepository.save(edit_employee);
	}

	@Override
	public Employee getEmployee(long eid) {

		return empRepository.findById(eid)
				.orElseThrow(() -> new NotFoundException("Employee with ID " + eid + " not found"));
	}

	@Override
	public List<Employee> getAllEmployees() {

		return empRepository.findAll();
	}

	@Override
	public String deleteEmployee(long eid) {
		Employee delete_employee = getEmployee(eid);
		empRepository.delete(delete_employee);
		String deleteMessage = "Employee with\n Id: " + delete_employee.getEmp_id() + "\n Name: "
				+ delete_employee.getFirstName() + " " + delete_employee.getLastName() + "\n email: "
				+ delete_employee.getEmail() + "\n Got Deleted ";
		return deleteMessage;
	}

	@Override
	public List<Employee> getEmployeeByName(String fname) {
		List<Employee> employees = empRepository.findByFirstNameContainingIgnoreCase(fname);

		if (employees.isEmpty())
			throw new NotFoundException("No Employees found with First Name as " + fname);

		return employees;

	}

	@Override
	public List<Employee> sortEmployeeByName(String order) {
		Direction direction = order.equalsIgnoreCase("desc") ? Direction.DESC : Direction.ASC;

		return empRepository.findAll(Sort.by(direction, "firstName"));
	}

}
