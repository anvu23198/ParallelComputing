package Assignment2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;


public class Client {

	public static void main(String[] args) {
		try {
			// Create client socket to connect to certain server (Server IP, Port address)
			//"localhost"
			Socket mySocket = new Socket("localhost", 123);


			// to interact (send data, read incoming data) with the server
			// we need to create the following:
			
			//DataOutputStream variable to send data through the socket
			DataOutputStream outStream = new DataOutputStream(mySocket.getOutputStream());

			// BufferReader variable to read data coming from the server through the socket
			BufferedReader inStream = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));

			
			Scanner scan = new Scanner(System.in);
			String inputString = "";
			while (!inputString.toLowerCase().contains("exit")) {
				try {
					inputString = scan.nextLine();
					
					outStream.writeBytes(inputString + "\n");
					
					// response from the server and replace ||| with new line
					System.out.println(inStream.readLine().replace("|||", "\n"));
					System.out.println();
				} catch (Exception e) {
				}
				
			}
			
			scan.close();

			
			// send data to the server
			outStream.writeBytes("exit\n");

			// receive from the server
			String str1 = inStream.readLine();

			System.out.println(str1);
			

			// close connection.
			outStream.close();
			inStream.close();
			mySocket.close();
		
		} catch (Exception exc) {

		}
	}

}
