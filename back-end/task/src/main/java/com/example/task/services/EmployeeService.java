package com.example.task.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.task.models.Employee;
import com.example.task.repository.IemployeeRepository;
import com.example.task.servicesInterfaces.IemployeeService;

@Service
public class EmployeeService implements IemployeeService{

	private IemployeeRepository employeeRepository;

	@Autowired
    public EmployeeService(IemployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

}
