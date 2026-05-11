/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ca_2;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author wanghuohuo
 */

//Main class: Bank Employee System
public class CA_2 {
    private static List<Employee> employeeList = new ArrayList<>();

    
    // Bank departments

    private static final String[] DEPTS = {
        "Retail Banking",
        "Corporate Banking",
        "Investment Banking",
        "HR",
        "Finance"
    };
    
    // Manager types
    
    private static final String[] MANAGERS = {
        "Head Manager",
        "Senior Manager",
        "Assistant Manager",
        "Team Lead"
    };
    
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        loadFile("Applicants_Form.txt");

        while (true) {
            System.out.println("\n===== BANK EMPLOYEE SYSTEM =====");
            System.out.println("1. Sort Employees");
            System.out.println("2. Search Employee");
            System.out.println("3. Add Employee");
            System.out.println("4. Build Binary Tree");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: sortEmployees(); break;
                case 2: searchEmployee(); break;
                case 3: addEmployee(sc); break;
                case 4: buildTree(); break;
                case 5: System.out.println("Exited."); return;
                default: System.out.println("Invalid option.");
            }
        }
    }
    
    
    // Load file using BufferedReader
    
private static void loadFile(String fileName) {
    //  Clear the list to avoid duplicate data
    employeeList.clear();

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        String line;
        // Skip the header line(column names)
        br.readLine();

        // Read each line of the file until the end
        while ((line = br.readLine()) != null) {
            // Remove leading/trailing whitespace
            line = line.trim();
            
            //Skip empty lines to avoid errors
            if (line.isEmpty()) continue;

            // Split the line into parts using comma as the separator
            String[] parts = line.split(",");

            // Only process lines that have at least 6 fields
            if (parts.length >= 6) {
                String fname = parts[0].trim();
                String lname = parts[1].trim();
                String dept = parts[5].trim();
                String mgr = (parts.length > 6) ? parts[6].trim() : "Staff";
               // Add the new employee to the list
                employeeList.add(new Employee(fname, lname, dept, mgr));
            }
        }
        System.out.println("Loaded: " + employeeList.size() + " employees");
    } catch (IOException e) {
        System.out.println("Cannot read file");
    }
}

    // Merge Sort (recursive)
   
    private static void sortEmployees() {
        System.out.println("\nSorting using Merge Sort...");
        Employee[] arr = employeeList.toArray(new Employee[0]);
        mergeSort(arr, 0, arr.length - 1);

        System.out.println("\nTop 20 Sorted:");
        for (int i = 0; i < Math.min(20, arr.length); i++) {
            System.out.println((i+1) + ". " + arr[i].getFullName());
        }
        employeeList = new ArrayList<>(Arrays.asList(arr));
    }

    private static void mergeSort(Employee[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid+1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(Employee[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Employee[] L = new Employee[n1];
        Employee[] R = new Employee[n2];

        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, mid+1, R, 0, n2);

        int i=0, j=0, k=left;
        while (i < n1 && j < n2) {
            if (L[i].getFullName().compareToIgnoreCase(R[j].getFullName()) <= 0) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // Binary Search (recursive)
    
    private static void searchEmployee() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = sc.nextLine().trim();

        Employee[] arr = employeeList.toArray(new Employee[0]);
        int index = binarySearch(arr, name, 0, arr.length-1);

        if (index == -1) {
            System.out.println("Not found.");
        } else {
            Employee e = arr[index];
            System.out.println("\nFound:");
            System.out.println("Name: " + e.getFullName());
            System.out.println("Department: " + e.getDepartment());
            System.out.println("Manager: " + e.getManagerType());
        }
    }

    private static int binarySearch(Employee[] arr, String name, int left, int right) {
        if (left > right) return -1;
        int mid = (left + right) / 2;
        String curr = arr[mid].getFullName().toLowerCase();

        if (curr.contains(name.toLowerCase())) return mid;
        else if (curr.compareTo(name.toLowerCase()) > 0)
            return binarySearch(arr, name, left, mid-1);
        else
            return binarySearch(arr, name, mid+1, right);
    }

    // Add employee with validation
    
    private static void addEmployee(Scanner sc) {
        System.out.print("First name: ");
        String fname = sc.nextLine().trim();
        System.out.print("Last name: ");
        String lname = sc.nextLine().trim();

        System.out.println("\nDepartments:");
        for (int i=0; i<DEPTS.length; i++)
            System.out.println((i+1)+". "+DEPTS[i]);
        System.out.print("Choose (1-5): ");
        int d = sc.nextInt()-1;
        if (d<0||d>=DEPTS.length) { System.out.println("Invalid"); return; }

        System.out.println("\nManagers:");
        for (int i=0; i<MANAGERS.length; i++)
            System.out.println((i+1)+". "+MANAGERS[i]);
        System.out.print("Choose (1-4): ");
        int m = sc.nextInt()-1;
        if (m<0||m>=MANAGERS.length) { System.out.println("Invalid"); return; }

        Employee emp = new Employee(fname, lname, DEPTS[d], MANAGERS[m]);
        employeeList.add(emp);
        System.out.println("Added: " + emp.getFullName());
    }

    // Build binary tree with 20 employees
    
    private static void buildTree() {
        if (employeeList.size() <20) {
            System.out.println("Need at least 20 employees.");
            return;
        }

        BinaryTree tree = new BinaryTree();
        for (int i=0; i<20; i++)
            tree.insertLevelOrder(employeeList.get(i));

        tree.levelOrderTraversal();
        System.out.println("\nTree Height: " + tree.getHeight(tree.root));
        System.out.println("Total Nodes: " + tree.countNodes(tree.root));
    }
}
