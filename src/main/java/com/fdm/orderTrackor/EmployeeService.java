package com.fdm.orderTrackor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class EmployeeService {
	private EntityManagerFactory emf;

	public EmployeeService(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public void persistEmployee(Employee employee) {
		EntityManager em = getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(employee);
		et.commit();
		em.close();
	}
	
	public void removeEmployee(Long id) {
		EntityManager em = getEntityManager();
		EntityTransaction et = em.getTransaction();
		Employee employee = em.find(Employee.class, id);
		try {
			if (employee != null) {
				et.begin();
				em.remove(employee);
				et.commit();
			}
		} finally {
			em.close();
		}
	}
	
	public Employee findEmployeeByID(Long id) {
		Employee employee = new Employee();
		EntityManager em = getEntityManager();
		try {
			employee = em.find(Employee.class, id);
		} finally {
			em.close();
		}
		return employee;
	}


}
