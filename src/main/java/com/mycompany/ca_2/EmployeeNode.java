/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ca_2;

/**
 *
 * @author wanghuohuo
 */

// Node class for binary tree
public class EmployeeNode {
    
    Employee data;
    EmployeeNode left;
    EmployeeNode right;

    // Constructor: create a node with employee data
    
    public EmployeeNode(Employee data) {
        this.data = data;
        left = null;
        right = null;
    }
    
}
