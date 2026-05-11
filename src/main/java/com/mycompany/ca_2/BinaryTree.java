/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ca_2;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author wanghuohuo
 */

//Binary tree to store employee hierarchy
public class BinaryTree {
    EmployeeNode root;
    
    public BinaryTree(){
        root = null;
    }
    
    // Insert in level order (fill left first)
    
    public void insertLevelOrder(Employee emp){
        EmployeeNode newNode = new EmployeeNode(emp);
        
        if (root == null){
            root = newNode;
            return;
        }
        
        Queue<EmployeeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()){
            EmployeeNode current = queue.poll();
            
            if (current.left == null){
                current.left = newNode;
                break;
            }else{
                queue.add(current.left);
            }
            
            if (current.right == null){
                current.right = newNode;
                break;
            }else{
                queue.add(current.right);
            }
        }
    }
    
    //Show tree in level order
    
    public void levelOrderTraversal(){
        if (root == null) return;
        
        Queue<EmployeeNode> queue = new LinkedList<>();
        queue.add(root);
        
        System.out.println("=== Employee Hierarchy ===");
        while (!queue.isEmpty()){
            EmployeeNode current = queue.poll();
            System.out.println("Name:" + current.data.getFullName()
                    + "| Department:" + current.data.getDepartment() 
                    + "| Manager:" + current.data.getManagerType());
            
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
    }
    
    //Get tree height
    
    public int getHeight(EmployeeNode node){
        if (node == null) return 0;
        int left = getHeight(node.left);
        int right = getHeight(node.right);
        return Math.max(left, right) + 1;
    }
    
    //Count total nodes
    public int countNodes(EmployeeNode node){
        if (node == null) return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }
}

