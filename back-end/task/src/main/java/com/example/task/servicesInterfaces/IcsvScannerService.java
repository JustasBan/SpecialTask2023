package com.example.task.servicesInterfaces;

import java.io.IOException;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.exceptions.CsvValidationException;

public interface IcsvScannerService {
	void importEmployeesFromCsvFile(MultipartFile file) throws CsvValidationException, IOException, JdbcSQLIntegrityConstraintViolationException;
}
