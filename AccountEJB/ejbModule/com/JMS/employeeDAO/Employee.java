package com.JMS.employeeDAO;

import java.io.Serializable;

public class Employee implements Serializable{
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	private int id;
    private String name;
    private String designation;
    private double salary;
    public Employee() { }
    public Employee(int id, String name, String designation,
                double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }
    //Getters and Setters
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", designation="
                + designation + ", salary=" + salary + "]";
    }
}