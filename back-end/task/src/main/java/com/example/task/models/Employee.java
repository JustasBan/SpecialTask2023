package com.example.task.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "employees")
public class Employee {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String Name;
	
	@Column(unique = true)
	private String Email;
	
	@Column(unique = true)
	private String PhoneNumber;
	
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	
	public String getEmail() {
		return Email;
	}
	
	public void setEmail(String email) {
		
		if(isValidEmail(email)) {
			Email = email;
		}
		else {
			throw new IllegalArgumentException("Invalid email address");
		}
	}
	
	private boolean isValidEmail(String email) {
		 String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
		 Pattern pattern = Pattern.compile(regex);
		 Matcher matcher = pattern.matcher(email);
		 return matcher.matches();
	}
	
	
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		if(isValidPhoneNumber(phoneNumber)) {
			PhoneNumber = phoneNumber;
		}
		else {
			throw new IllegalArgumentException("Invalid phone number");
		}
	}
	
	private boolean isValidPhoneNumber(String phoneNumber) {
		 String regex = "^\\d-\\d{3}-\\d{5}$";
		 Pattern pattern = Pattern.compile(regex);
		 Matcher matcher = pattern.matcher(phoneNumber);
		 return matcher.matches();
	}
	
}
