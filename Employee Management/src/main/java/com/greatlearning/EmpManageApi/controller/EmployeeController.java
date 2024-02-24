package com.greatlearning.EmpManageApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.EmpManageApi.entity.Employee;
import com.greatlearning.EmpManageApi.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService empService;

	@PostMapping("/add")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee emp) {
		emp = empService.addEmployee(emp);
		return ResponseEntity.status(HttpStatus.CREATED).body(emp);
	}

	@PutMapping("/editById/{emp_id}")
	public ResponseEntity<?> editEmployee(@PathVariable("emp_id") long eid, @RequestBody Employee emp)
			throws NotFoundException {
		Employee update_employee = empService.updateEmployee(eid, emp);
		return ResponseEntity.status(HttpStatus.CREATED).body(update_employee);
	}

	@GetMapping("/searchById/{emp_id}")
	public ResponseEntity<?> searchEmployeeById(@PathVariable("emp_id") long eid) throws NotFoundException {
		Employee employee = empService.getEmployee(eid);
		return ResponseEntity.ok(employee);
	}

	@GetMapping("/list")
	public List<Employee> listOfEmployees() {
		return empService.getAllEmployees();
	}

	@GetMapping("/searchByName/{firstName}")
	public List<Employee> searchEmployeeByFirstName(@PathVariable("firstName") String fname) throws NotFoundException {
		return empService.getEmployeeByName(fname);
	}

	@GetMapping("/sort")
	public List<Employee> sortByName(@RequestParam(defaultValue = "asc") String order) {
		return empService.sortEmployeeByName(order);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteById(@PathVariable("id") long eid) throws NotFoundException {
		return empService.deleteEmployee(eid);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
	}

}
