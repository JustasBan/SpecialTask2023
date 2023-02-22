package com.example.task.controllers;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.task.models.Employee;
import com.example.task.servicesInterfaces.IcsvScannerService;
import com.example.task.servicesInterfaces.IemployeeService;
import com.opencsv.exceptions.CsvValidationException;

import jakarta.persistence.Column;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;


@RestController
public class EmployeeController {
	private final IcsvScannerService csv;
	private final IemployeeService employeeService;
	
    public EmployeeController(IcsvScannerService csvScannerService, IemployeeService employeeService) {
        this.csv = csvScannerService;
        this.employeeService = employeeService;
    }
    
    @PostMapping("/upload")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> uploadCsvFile(@RequestParam("file") MultipartFile file) throws CsvValidationException {
    	
        try {
            csv.importEmployeesFromCsvFile(file);
            return ResponseEntity.ok("File uploaded successfully.");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error uploading file: " + e.getMessage());
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error uploading file: " + e.getMessage());
        }
        //TODO: can't catch inserts, which violate unique rule and can't inform front-end consequently
        catch (JdbcSQLIntegrityConstraintViolationException e) {
        	return ResponseEntity.badRequest().body("User with such ID(s) or email(s) already exist");
        }
    }
    @GetMapping("/getEmployees")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }
}
