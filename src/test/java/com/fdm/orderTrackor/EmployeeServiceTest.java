package com.fdm.orderTrackor;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EmployeeServiceTest {
	private static EmployeeService employeeService;
	private Employee employee;
	
	@BeforeClass
	public static void setupClass() {
		BasicConfigurator.configure();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("order");
		employeeService = new EmployeeService(emf);
	}
	
	@Before
	public void setup() {
		employee = new Employee();
		employee.setName("alex");
		employee.setUsername("alex001");
		employee.setPassword("123");		
		employeeService.persistEmployee(employee);
				
	}
	
	@Test
	public void persistEmployee_insert_new_employee_in_db() {
		Employee retrievedEmployee = employeeService.findEmployeeByID(employee.getEmployeeId());
		assertEquals(employee, retrievedEmployee);
	}

	@Test
	public void test_removeCustomer() {
		employeeService.removeEmployee(employee.getEmployeeId());
		Assert.assertNull(employeeService.findEmployeeByID(employee.getEmployeeId()));
	}

}
