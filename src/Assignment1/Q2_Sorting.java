package Assignment1;

import java.util.ArrayList;
import java.util.List;

public class Q2_Sorting {
	public static List<EMPLOYEE> Employees = new ArrayList<EMPLOYEE>();
	public static List<EMPLOYEE> Employees1 = new ArrayList<EMPLOYEE>();
	public static List<EMPLOYEE> Employees2 = new ArrayList<EMPLOYEE>();
	
	//bubble sort the employees1
	public static void func1() {
		for (int i = 0; i < Employees1.size(); i++) {
			for (int j = i + 1; j < Employees1.size(); j++) {
				if (Employees1.get(i).getSalary() > Employees1.get(j).getSalary()) {
					EMPLOYEE temp = Employees1.get(i);
					Employees1.set(i, Employees1.get(j));
					Employees1.set(j, temp);
				}
			}
		}
	}
	
	//bubble sort the employees2
	public static void func2() {
		for (int i = 0; i < Employees2.size(); i++) {
			for (int j = i + 1; j < Employees2.size(); j++) {
				if (Employees2.get(i).getSalary() > Employees2.get(j).getSalary()) {
					EMPLOYEE temp = Employees2.get(i);
					Employees2.set(i, Employees2.get(j));
					Employees2.set(j, temp);
				}
			}
		}
	}
	
	public static void main(String[] args) {

		//create 2000 employees
		for (int i = 1; i <= 2000; i++) {
			int salary = (int)(Math.random() * (4000-1500)) + 1500;
			EMPLOYEE employee = new EMPLOYEE(i, salary);
			Employees.add(employee);
		}
		
		//create 2 sublist of equal length
		Employees1 = Employees.subList(0, Employees.size()/2);
		Employees2 = Employees.subList(Employees.size()/2, Employees.size());

		//create thread 1 that call func1
		Thread thread1 = new Thread(
			new Runnable() {
				public void run() {
					func1();
				}
		});

		//create thread 2 that call func2
		Thread thread2 = new Thread(
			new Runnable() {
				public void run() {
					func2();
				}
		});

		//start both thread
		thread1.start();
		thread2.start();
		
		//wait for both thread to finish
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		
		//create pointer for both list for the merge sort
		int pointer1 = 0;
		int pointer2 = 0;

		//the final list that store ascending salary employees 
		List<EMPLOYEE> EmployeesSorted = new ArrayList<EMPLOYEE>();
		
		
		//merge sort
		for(int i = 0; i < Employees.size(); i++) {
			if(pointer1 == Employees1.size()) {
				EmployeesSorted.add(Employees2.get(pointer2));
				pointer2++;
			}else if(pointer2 == Employees2.size()){
				EmployeesSorted.add(Employees1.get(pointer1));
				pointer1++;
			}else if(Employees1.get(pointer1).getSalary() < Employees2.get(pointer2).getSalary()){
				EmployeesSorted.add(Employees1.get(pointer1));
				pointer1++;
			}else {
				EmployeesSorted.add(Employees2.get(pointer2));
				pointer2++;
			}
		}
		

		//print out the sorted employees id and salary
		for (int i = 0; i < EmployeesSorted.size(); i++) {
			System.out.println("Employee ID: " + EmployeesSorted.get(i).getId() + " --- Salary: " + EmployeesSorted.get(i).getSalary() + " ");
		}
	}
	
}
