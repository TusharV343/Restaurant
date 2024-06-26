package com.vaibhav.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.vaibhav.data.controler.Employee;

public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
	

	
}