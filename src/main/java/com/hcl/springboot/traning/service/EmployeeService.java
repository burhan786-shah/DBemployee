package com.hcl.springboot.traning.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hcl.springboot.training.dto.EmployeeDto;
import com.hcl.springboot.training.dto.EmployeeReqDto;
import com.hcl.springboot.traning.entity.Employee;
import com.hcl.springboot.traning.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	public List<Employee> getEmployeeForPagination(int pageNumber, int pageSize){
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "employeeName")); 
		return employeeRepository.findAll(pageable).getContent();
	}
	
	
	public List<Employee> searchEmployees(String employeeName){
		return employeeRepository.findByEmployeeNameContainsOrderByEmployeeNameAsc(employeeName);
	}
	
	public List<Employee> searchEmployeesByName(String employeeName){
		return employeeRepository.findByEmployeeName(employeeName);
	}
	
	
	
	public List<EmployeeDto> getAllEmployee(){ 
		List<Employee> employees = employeeRepository.findAll(Sort.by(Sort.Direction.DESC, "employeeName"));
		List<EmployeeDto> employessDtos = new ArrayList<>();
		for(Employee employee : employees){
			EmployeeDto employessDto = new EmployeeDto();
			BeanUtils.copyProperties(employee, employessDto);
			employessDtos.add(employessDto);
		}
		return employessDtos;
	}
	
	
	

	public String deleteEmployee(Integer id) {
		Optional<Employee> employees = employeeRepository.findById(id);
		if (employees.isPresent()) {
			employeeRepository.delete(employees.get());
			return "Data Deleted Successfully";
		}
		return "Employee not found";
	}

	public String updateEmployee(Integer eid, Employee employee) {
		Optional<Employee> employees = employeeRepository.findById(eid);
		if (employees.isPresent()) {
			employee.setEid(eid);
			employeeRepository.save(employee);
			return "Data Updated Successfully";
		} else {
			return "Employee not found";
		}
	}

	public Employee saveEmployee(EmployeeReqDto employeeReqDto) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeReqDto, employee);
		//user.setUserName(userReqDto.getUserName());
		//user.setPassword(userReqDto.getPassword());
		
		return employeeRepository.save(employee);
	}

	public Employee login(String employeeName, String password) {
		return employeeRepository.findByEmployeeNameAndPassword(employeeName, password);
	}
	public EmployeeDto getById(Integer id) {
		Optional<Employee> employees = employeeRepository.findById(id);
		if(employees.isPresent()){
			Employee employee = employees.get();
			EmployeeDto employeeDto = new EmployeeDto();
			BeanUtils.copyProperties(employee, employeeDto);
			return employeeDto;
		}
		return null;
	}
	
}
