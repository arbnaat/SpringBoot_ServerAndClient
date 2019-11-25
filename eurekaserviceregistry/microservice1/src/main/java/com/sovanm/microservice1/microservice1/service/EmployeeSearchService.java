package com.sovanm.microservice1.microservice1.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sovanm.microservice1.microservice1.domain.model.Employee;

@Service
public class EmployeeSearchService {
	private static Map<Long, Employee> EmployeeRepsitory = null;
	static {
		EmployeeRepsitory = getEmployeeRepsitory();

		}

	private static Employee createEmployee(Long id, String name, String practiceArea, String designation) {
		Employee emp = new Employee();
		emp.setEmployeeId(id);
		emp.setName(name);
		emp.setPracticeArea(practiceArea);
		emp.setDesignation(designation);
		emp.setCompanyInfo("Photon");
		return emp;
	}

	public Employee findById(Long id) {
		return EmployeeRepsitory.get(id);
	}

	public Collection<Employee> findAll() {
		return EmployeeRepsitory.values();
	}

	private static Map<Long, Employee> getEmployeeRepsitory() {
		EmployeeRepsitory = new HashMap<Long, Employee>();
		Employee emp = null;

		emp = createEmployee((long) 1, "John", "java", "Architect");
		EmployeeRepsitory.put(emp.getEmployeeId(), emp);

		emp = createEmployee((long) 2, "Tunner", "Big Data", "Architect");
		EmployeeRepsitory.put(emp.getEmployeeId(), emp);

		emp = createEmployee((long) 3, "Neil", "scrum", "Manager");
		EmployeeRepsitory.put(emp.getEmployeeId(), emp);

		return EmployeeRepsitory;
	}

}