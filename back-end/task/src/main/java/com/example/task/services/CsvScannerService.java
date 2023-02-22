package com.example.task.services;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.task.models.Employee;
import com.example.task.servicesInterfaces.IcsvScannerService;
import com.example.task.servicesInterfaces.IemployeeService;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.Reader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class CsvScannerService implements IcsvScannerService {
	
	private final IemployeeService employeeService;

    @Autowired
    public CsvScannerService(IemployeeService employeeService) {
        this.employeeService = employeeService;
    }

	@Override
	public void importEmployeesFromCsvFile(MultipartFile file) throws CsvValidationException, IOException, JdbcSQLIntegrityConstraintViolationException {

		Reader reader = null;
		try {
			reader = new InputStreamReader(file.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	    CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(0).build();
	    
	    String[] nextRecord;
	    //TODO: get records which violated rules and inform front-end about them
        while ((nextRecord = csvReader.readNext()) != null) {
            Employee employee = new Employee();
            employee.setName(nextRecord[0]);
            employee.setEmail(nextRecord[1]);
            employee.setPhoneNumber(nextRecord[2]);
            
            employeeService.saveEmployee(employee);
        }
        
	}

}
