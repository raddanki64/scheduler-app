package com.apps.schedulerApp.persistance;

import  java.util.List;
 
import  com.apps.schedulerApp.persistance.model.Employee;

import  org.springframework.data.jpa.repository.Query;
import  org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> { // Long: Type of Employee ID.
    List<Employee> findByFirstNameLike(String firstName);
    List<Employee> findByLastNameLike(String lastName);
}