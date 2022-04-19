package com.gl.EmpMgm.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gl.EmpMgm.model.Employee;
import com.gl.EmpMgm.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;
	
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public Employee save (Employee employee) {
		return this.employeeRepository.save(employee);
	}

	public Set<Employee> findAll(){
		Iterable<Employee> employeeIterable = this.employeeRepository.findAll();
		Set<Employee> employees = new HashSet<>();
		employeeIterable.forEach(employees::add);
		return employees;
	}
	
	public Employee findEmployeeById(int employeeId) {
		Optional<Employee> optionalEmployee = this.employeeRepository.findById(employeeId);
		if(optionalEmployee.isPresent()) {
			return optionalEmployee.get();
		} else {
			throw new IllegalArgumentException("Emoployee with the given employee id does not exist");
		}
	}

	public void deleteEmployeeById(int employeeId) {
		this.employeeRepository.deleteById(employeeId);
	}

	public List<Employee> fetchEmployeesByFirstname(String firstname){
		return this.employeeRepository.findByFirstnameLike(firstname);
	}
	
	public List<Employee> sortEmployeesByFirstname(String direction){
		List<Employee> sortemployees = new ArrayList<>();
		switch(direction) {
			case "asc":
				sortemployees = this.employeeRepository.findAll(Sort.by(Direction.ASC, "firstname"));
				break;
			case "desc": 
				sortemployees = this.employeeRepository.findAll(Sort.by(Direction.DESC, "firstname"));
				break;
			default:
				sortemployees = this.employeeRepository.findAll(Sort.by(Direction.ASC, "firstname"));
		}
		return sortemployees;
	}
	
}
