package Assignment1;

import java.util.ArrayList;
import java.util.Random;

public class Q1_SeqV {
	public static ArrayList<STUDENT> students = new ArrayList<STUDENT>();
	
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
	
	public static void main(String[] args) {
		//init start time
		long startTime = System.nanoTime();
		
		//create 10000 student and store in a list
		for (int i = 0; i < 10000; i++) {
			float gpa = new Random().nextFloat() * (4.0f - 2.0f) + 2.0f;
			int age = (int)(Math.random() * (30-18)) + 18;
			STUDENT student = new STUDENT(gpa, age);
			students.add(student);
		}

		//get the average gpa
		float aveGpa = getAveGpa(students);
		
		//get the average age
		int aveAge = getAveAge(students);
		
		//calculate the execution time
		long endTime = System.nanoTime();
		long second = (endTime - startTime)/10000000;
		long milisecond = (endTime - startTime)/10000;

		System.out.println("Created a list of 10,000 students in sequential version");
		System.out.println("Average GPA is: " + aveGpa);
		System.out.println("Average Age is: " + aveAge);
		System.out.println("This excution take: " +  + second + "." + milisecond +"s");
	}


	
}
