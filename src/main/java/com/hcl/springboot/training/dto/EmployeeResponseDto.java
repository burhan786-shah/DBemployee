package com.hcl.springboot.training.dto;

import java.util.ArrayList;
import java.util.List;

public class EmployeeResponseDto {
	
	List<EmployeeDto> employeeDtos = new ArrayList<>();
	
	private int statusCode;
	
	private String statusMessage;

	public List<EmployeeDto> getEMployeeDtos() {
		return employeeDtos;
	}

	public void setEmployeeDtos(List<EmployeeDto> employeeDtos) {
		this.employeeDtos = employeeDtos;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	

}
