package com.apps.schedulerApp.persistance.model;

import  javax.persistence.Column;
import  javax.persistence.Entity;
import  javax.persistence.Id;
import  javax.persistence.Table;
 
@Entity
@Table(name = "Employees")
public class Employee {
	private static String TOSTRING_COLUMN_SPACER = ", ";
	
    @Id
    private Integer id;
 
    @Column(name = "first_name", length = 30, nullable = false)
    private String firstName;
 
    @Column(name = "last_name", length = 30, nullable = false)
    private String lastName;

    public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }
 
    public String getFirstName() {
        return firstName;
    }
 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
 
    public String getLastName() {
        return lastName;
    }
 
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
 
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append(this.getId())
    	  .append(TOSTRING_COLUMN_SPACER)
    	  .append(this.getFirstName())
    	  .append(TOSTRING_COLUMN_SPACER)
    	  .append(this.getLastName());
    	
        return sb.toString();
    }
}
