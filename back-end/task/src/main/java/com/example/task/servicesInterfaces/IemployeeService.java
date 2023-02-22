package com.example.task.servicesInterfaces;
import java.util.List;

import com.example.task.models.Employee;

public interface IemployeeService {
	List<Employee> getAllEmployees();
	void saveEmployee(Employee employee);
}
