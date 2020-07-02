package com.hcl.springboot.traning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.springboot.training.dto.EmployeeDto;
import com.hcl.springboot.training.dto.EmployeeReqDto;
import com.hcl.springboot.training.dto.EmployeeResponseDto;
import com.hcl.springboot.traning.entity.Employee;
import com.hcl.springboot.traning.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/pagination")
	public List<Employee> getEmployeeForPagination(@RequestParam int pageNumber, @RequestParam int pageSize){
		return employeeService.getEmployeeForPagination(pageNumber, pageSize);
	}
	
	@GetMapping("/EXsearch")
	public List<Employee> searchEmployeesByName(@RequestParam String employeeName){
		return employeeService.searchEmployeesByName(employeeName);
	}
	
	@GetMapping("/search")
	public List<Employee> searchEmployees(@RequestParam String employeeName){
		return employeeService.searchEmployees(employeeName);
	}
	
		
	@GetMapping("")
	public EmployeeResponseDto getAllEmployees(){ 
		List<EmployeeDto> employeeDtos = employeeService.getAllEmployee();
		EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
		employeeResponseDto.setEmployeeDtos(employeeDtos);
		employeeResponseDto.setStatusCode(200);
		employeeResponseDto.setStatusMessage("Success");
		return employeeResponseDto;
	}

	@PostMapping("")
	public ResponseEntity<String> registration(@RequestBody EmployeeReqDto employeeReqDto){ 
		employeeService.saveEmployee(employeeReqDto);
		return new ResponseEntity<String>("Success", HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public String login(@RequestParam String employeeName, @RequestParam String password) {
		Employee employee = employeeService.login(employeeName, password);
		if (employee == null) {
			return "Fail to Login";
		}
		return "Login Successfully";
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getById(@PathVariable Integer id){ 
		EmployeeDto employeeDto = employeeService.getById(id);
		return new ResponseEntity<EmployeeDto>(employeeDto, HttpStatus.OK);
	}

	@PostMapping("/{eid}")
	public String updateEmployee(@PathVariable Integer eid, @RequestBody Employee employee) {
		return employeeService.updateEmployee(eid, employee);
	}

	@DeleteMapping("/{eid}")
	public String deleteById(@PathVariable Integer eid) {
		return employeeService.deleteEmployee(eid);
	}
}
