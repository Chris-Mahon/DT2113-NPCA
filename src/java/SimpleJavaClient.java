
package ie.dit.student.mahon.christopher;

import java.net.*;
import java.io.*;

/** Christopher Mahon
Simple Client to connect to a server */
public class SimpleJavaClient
{

	/** start declaration of main method */
	public static void main(String[] args)
	{
		Socket ClientSocket = null; //Creating a blank Socket
		String OutMessage = "Heya";//Message that outputs to the server, will be replaced with user input
		//Main Function
		try
		{//Trying to connect to the server and pulling any data sent by the server to it
			ClientSocket = new Socket("localhost", 4444);
		}
		catch(IOException e)
		{//If the socket fails, itll print out an error message
			System.out.println("Nope Broken");//Error Message for the socket creating failing
			e.printStackTrace();//Prints out an exact error message
		} // end of catch to catch any sort of exception
		
		
		//Somewhere the transmission from the server is sent to the server and recieved 
		//but no response is printed out on this side
		
		try
		{//Sending a message and receiving a confirmation from the server
			//Creates an output stream to  the server to which the program will write out a message
			PrintWriter output = new PrintWriter(ClientSocket.getOutputStream(), true);
			
			//Creates an buffer for incoming messages
			BufferedReader in = new BufferedReader(new InputStreamReader(ClientSocket.getInputStream()));
			output.println(OutMessage); //Printing to the Output stream to the server
			String Incoming = in.readUTF(); //Reads in a string from the server
			System.out.println("The response is:" + Incoming); //Prints the data from the server
		}
		catch(IOException e)
		{//If the message transfer fails, itll print out an error message
			System.out.println("Nope doesnt work");
			e.printStackTrace();
		} // end of catch to catch any sort of exception

	}// end declaration of main method

} // end declaration of utility class