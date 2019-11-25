package com.sovanm.microservice1.microservice1.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sovanm.microservice1.microservice1.domain.model.Employee;
import com.sovanm.microservice1.microservice1.service.EmployeeSearchService;

@RestController
@RequestMapping(path = "/employee")
public class MicroserviceOneController {

	@Autowired
	Environment environment;

	public String test() {
		String port = environment.getProperty("local.server.port");
		return "Microservice 1 running in :" + port;
	}

	@Autowired
	EmployeeSearchService employeeSearchService;

	@RequestMapping("/find/{id}")
	public Employee findById(@PathVariable Long id) {
		return employeeSearchService.findById(id);
	}

	@RequestMapping("/findall")
	public Collection<Employee> findAll() {
		return employeeSearchService.findAll();
	}

}
