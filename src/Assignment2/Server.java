package Assignment2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Server {
	public static List<Integer> inputValues = new ArrayList<Integer>();
	public static void main(String[] args) {
		
		try {

			// Create server Socket that listens/bonds to port/endpoint address 123
			// The default maximum number of queued incoming connections is 50
			// There is another constructor that can be used to specify the maximum number of connections
			ServerSocket mySocket = new ServerSocket(123);

			System.out.println("Startup the server side ....");

			// use the created ServerSocket and accept() to start listening for incoming client requests
			// accept() blocks the current thread (main thread in this case) until a connection is made.
			// the created connection with a client is represented by the returned Socket object.
			Socket connectedClient = mySocket.accept();

			// reaching this point means that a client established a connection with your
			// server.
			System.out.println("Connection established");

			// to interact (read incoming data, send data) with the connected client
			// we need to create the following:

			// BufferReader variable to read data coming from the client
			BufferedReader br = new BufferedReader(new InputStreamReader(connectedClient.getInputStream()));

			// PrintStream variable to send data to the connected client
			PrintStream ps = new PrintStream(connectedClient.getOutputStream());

			
			// Let's keep reading data from the client, as long as the client does't send "exit".
			String inputData = "";
			while (!(inputData = br.readLine()).contains("exit")) {
				//lower the string data
				
				try {
					inputData = inputData.toLowerCase();
					if (inputData.contains("add number to the list")) {
						
						//add the number to the list
						int number = Integer.valueOf(inputData.split(":")[1].trim());
						inputValues.add(number);
						ps.println("added successfully");
						
					}else if (inputData.contains("remove number from the list")) {
						
						//remove the number from the list
						int number = Integer.valueOf(inputData.split(":")[1].trim());
						removeOccurance(number);
						ps.println("removed successfully");
						
					}else if (inputData.contains("get the summation")) {
						
						//get the sum
						int sum = getSum();
						ps.println("The summation is " + sum);
						
					}else if (inputData.contains("get the minimum")) {
						
						//get the min value
						int min = getMin();
						ps.println("The minimum is " + min);
						
					}else if (inputData.contains("get the maximum")) {
						
						//get the min value
						int max = getMax();
						ps.println("The maximum is " + max);
						
					}else {
						// print error with available command and put ||| as a symbol of new line since PrintStream can't send new line
						ps.println("unsupported command, Please use the command below |||"
								+ "Add number to the list: x -> to add number x to the list. |||"
								+ "Remove number from the list: x -> to remove number x from the list. |||"
								+ "Get the Summation -> will return the sum of the list. |||"
								+ "Get the Minimum -> will return the minimum value in the list. |||"
								+ "Get the Maximum -> will return the maximum value in the list.");
					}
				} catch (Exception e) {
					// print error with available command and put ||| as a symbol of new line since PrintStream can't send new line
					ps.println("unsupported command, Please use the command below |||"
							+ "Add number to the list: x -> to add number x to the list. |||"
							+ "Remove number from the list: x -> to remove number x from the list. |||"
							+ "Get the Summation -> will return the sum of the list. |||"
							+ "Get the Minimum -> will return the minimum value in the list. |||"
							+ "Get the Maximum -> will return the maximum value in the list.");
				}
				
				
				
			}

			// close the input/output streams and the created client/server sockets
			ps.close();
			br.close();
			mySocket.close();
			connectedClient.close();

		} catch (Exception exc) {
			System.out.println("Error :" + exc.toString());
		}
	}
	
	public static int getSum() {
		int sum = 0;
		for (Integer integer : inputValues) {
			sum += integer;
		}
		return sum;
	}
	
	public static int getMin() {
		int minValue = inputValues.get(0);
		for (Integer integer : inputValues) {
			if(minValue > integer)
				minValue = integer;
		}
		return minValue;
	}
	
	public static int getMax() {
		int maxValue = inputValues.get(0);
		for (Integer integer : inputValues) {
			if(maxValue < integer)
			maxValue = integer;
		}
		return maxValue;
	}
	
	public static void removeOccurance(int num) {
		for (Integer integer : inputValues) {
			if(integer == num)
				inputValues.remove(Integer.valueOf(num));
		}
	}

}
