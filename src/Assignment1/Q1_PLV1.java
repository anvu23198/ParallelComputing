package Assignment1;

import java.util.ArrayList;
import java.util.Random;

public class Q1_PLV1 {
	public static ArrayList<STUDENT> students1 = new ArrayList<STUDENT>();
	public static ArrayList<STUDENT> students2 = new ArrayList<STUDENT>();
	static float aveGpa1 = 0;
	static int aveAge1 = 0;
	static float aveGpa2 = 0;
	static int aveAge2 = 0;
	
	public static float getAveGpa(ArrayList<STUDENT> studentsParam) {
		float totalGpa = 0;
		for (STUDENT student : studentsParam) {
			totalGpa += student.getGpa();
		}
		return totalGpa/studentsParam.size();
	}
	
	public static int getAveAge(ArrayList<STUDENT> studentsParam) {
		int totalAge = 0;
		for (STUDENT student : studentsParam) {
			totalAge += student.getGpa();
		}
		return totalAge/studentsParam.size();
	}
	
	public static void func1() {
		//create 5000 student and store in a list
		for (int i = 0; i < 5000; i++) {
			float gpa = new Random().nextFloat() * (4.0f - 2.0f) + 2.0f;
			int age = (int)(Math.random() * (30-18)) + 18;
			STUDENT student = new STUDENT(gpa, age);
			students1.add(student);
		}

		//get average gpa and age
		aveGpa1 = getAveGpa(students1);
		aveAge1 = getAveAge(students1);
	}
	
	public static void func2() {
		//create 5000 student and store in a list
		for (int i = 0; i < 5000; i++) {
			float gpa = new Random().nextFloat() * (4.0f - 2.0f) + 2.0f;
			int age = (int)(Math.random() * (30-18)) + 18;
			STUDENT student = new STUDENT(gpa, age);
			students2.add(student);
		}

		//get average gpa and age
		aveGpa2 = getAveGpa(students2);
		aveAge2 = getAveAge(students2);
	}
	
	public static void main(String[] args) {
		Thread thread1 = new Thread(
			new Runnable() {
				public void run() {
					func1();
				}
		});

		Thread thread2 = new Thread(
			new Runnable() {
				public void run() {
					func2();
				}
		});

		long startTime = System.nanoTime();
		
		thread1.start();
		thread2.start();
		
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//get the final average gpa and age
		float aveGpaAll = (aveGpa1 + aveAge2) / 2;
		int aveAgeAll = (aveAge1 + aveAge2) / 2;
		
		
		//calculate execution time
		long endTime = System.nanoTime();
		long second = (endTime - startTime)/10000000;
		long milisecond = (endTime - startTime)/10000;


		System.out.println("Created 2 list of 5,000 students in parallel version 1");
		System.out.println("Average GPA is: " + aveGpaAll);
		System.out.println("Average Age is: " + aveAgeAll);
		System.out.println("This excution take: " +  + second + "." + milisecond +"s");
	}
	
}
