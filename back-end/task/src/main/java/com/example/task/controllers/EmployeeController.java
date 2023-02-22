package com.example.task.controllers;

import java.io.IOException;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.task.models.Employee;
import com.example.task.servicesInterfaces.IcsvScannerService;
import com.example.task.servicesInterfaces.IemployeeService;
import com.opencsv.exceptions.CsvValidationException;

@RestController
public class EmployeeController {
	private final IcsvScannerService csv;
	private final IemployeeService employeeService;
	
    public EmployeeController(IcsvScannerService csvScannerService, IemployeeService employeeService) {
        this.csv = csvScannerService;
        this.employeeService = employeeService;
    }
    
    @PostMapping("/upload")
    public ResponseEntity<String> uploadCsvFile(@RequestParam("file") MultipartFile file) throws CsvValidationException {
    	
        try {
            csv.importEmployeesFromCsvFile(file);
            return ResponseEntity.ok("File uploaded successfully.");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error uploading file: " + e.getMessage());
        }
    }
    
    @GetMapping("/getEmployees")
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }
}
