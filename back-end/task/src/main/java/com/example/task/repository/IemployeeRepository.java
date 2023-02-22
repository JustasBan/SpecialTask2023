package com.example.task.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.task.models.Employee;

public interface IemployeeRepository extends JpaRepository<Employee, Long>{

}
