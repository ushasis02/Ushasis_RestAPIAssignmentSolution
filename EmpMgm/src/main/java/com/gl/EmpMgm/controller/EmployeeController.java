package com.gl.EmpMgm.controller;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gl.EmpMgm.model.Employee;
import com.gl.EmpMgm.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	private final EmployeeService employeeService;
	
	public EmployeeController (EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping
	public Set<Employee> fetchAllEmployees(){
		return this.employeeService.findAll();
	}
	
	@GetMapping("/{id}")
	public Employee fetchEmployeeById(@PathVariable("id") int empId){
		return this.employeeService.findEmployeeById(empId);
	}

	@PostMapping
	public Employee saveEmployee(@RequestBody Employee employee) {
		return this.employeeService.save(employee);
	}
	
	@PutMapping
	public Employee updateEmployee(@RequestBody Employee employee) {
		Employee tempemployee = employeeService.findEmployeeById(employee.getEmpid());
			tempemployee.setEmail(employee.getEmail());
			tempemployee.setLastname(employee.getLastname());
			tempemployee.setFirstname(employee.getFirstname());
		return this.employeeService.save(tempemployee);
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmployeeId(@PathVariable("id") int empId) {
		this.employeeService.deleteEmployeeById(empId);
	}
	
	@GetMapping("/search/{firstname}")
	public List<Employee> fetchEmployeesByFirstname(@PathVariable String firstname){
		return this.employeeService.fetchEmployeesByFirstname(firstname);
	}

    @GetMapping("/sort")
    public List<Employee> sortEmployeesByFirstname(@RequestParam (value="order") String direction) {
    	return this.employeeService.sortEmployeesByFirstname(direction);
    }
}

