/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ca_2;

/**
 *
 * @author wanghuohuo
 */

// Employee class: stores all employee details
public class Employee {
    
    private String firstName;
    private String lastName;
    private String department;
    private String managerType;

    // Constructor: create a new employee
    
    public Employee(String firstName, String lastName, String department, String managerType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.managerType = managerType;
    }

    // Get full name
    
    public String getFullName() {
        return firstName + " " + lastName;
    }

    // Get department
    
    public String getDepartment() {
        return department;
    }

    // Get manager type
   
    public String getManagerType() {
        return managerType;
    }
    
}
