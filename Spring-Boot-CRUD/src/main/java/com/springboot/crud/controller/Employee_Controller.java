package com.springboot.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.crud.entity.Employee;
import com.springboot.crud.repository.EmployeeCrudRepo;

@RestController
@RequestMapping("/crud")
public class Employee_Controller {

	@Autowired
	EmployeeCrudRepo crud;

	// GET ALL
	@GetMapping
	public List<Employee> getAllEmployee() {
		return crud.findAll();
	}

	//CREATE
	@PostMapping
	public void addEmployee(@RequestBody Employee employee) {
		crud.save(employee);
	}

	// READ BY ID
	@GetMapping("/get/id/{id}")
	public Employee getEmployeeById(@PathVariable Long id) {
		return crud.findById(id).get();
	}

	// READ BY NAME
	@GetMapping("/get/name/{name}")
	public Employee getEmployeeByName(@PathVariable String name) {
		return crud.findByName(name);
	}
	
	// UPDATE
	@PutMapping("/update")
	public void updateEmployeeById(@RequestBody Employee employee) {
		crud.save(employee);
	}
	
	// DELETE BY ID
	@DeleteMapping("/{id}")
	public void deleteEmployeeById(@PathVariable Long id) {
		crud.deleteById(id);
	}
}
