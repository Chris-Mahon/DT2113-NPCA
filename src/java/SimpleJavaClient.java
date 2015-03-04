
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
		//Initialisation of variables that will be used
		Socket ClientSocket = null; //Creating a blank Socket
		String hostname, OutMessage;
		int port;
		OutMessage = "Words";
		//Command Line argument checking
		if (args.length==3)
		{//If there are 3 arguments passed in
			OutMessage = args[2];
		}
		else if (args.length!=2)
		{//If there are not 3 or 2 arguments passed in
			System.out.println("So... you didnt put enough details in");//The program will print out an error message
			System.exit(-1);//And it will exit as an error has occurred
		}
		else
		{//Otherwise (IE if 2 arguments have been passed in)
			OutMessage = "This will be read in from the keyboard"; //This string will be replaced with a keyboard read
		}
		hostname = args[0];//Sets a local variable to the host name we will be connecting to
		port = Integer.parseInt(args[1]);//Setting a local instance of the port
		
		try
		{//Trying to connect to the server and pulling any data sent by the server to it
			ClientSocket = new Socket(hostname, port);//Connecting to the server at the passed hostname
		}
		catch(IOException e)
		{//If the socket fails, itll print out an error message
			System.out.println("Cannot connect to server, check the hostname and port and try again");//Error Message for the socket creating failing
			e.printStackTrace();//Prints out an exact error message
		} // end of catch to catch any sort of exception
		
		
		//Somewhere the transmission from the server is sent to the server and recieved 
		//but no response is printed out on this side
		
		try
		{//Sending a message and receiving a confirmation from the server
			//Creates an output stream to  the server to which the program will write out a message
			DataOutputStream output = new DataOutputStream(ClientSocket.getOutputStream());
			
			//Creates an strem for incoming messages
			DataInputStream in = new DataInputStream(ClientSocket.getInputStream());
			
			output.writeUTF(OutMessage); //Printing to the Output stream to the server
			
			String Incoming = in.readUTF(); //Reads in a string from the server
			if (Incoming != OutMessage) //Checks to make sure the message was recieved properly
			{
				System.out.println("The message was not transmitted properly");
				System.out.println("The response was: " + Incoming); //Prints the data from the server
			}
			else
			{
				System.out.println("The response was: " + Incoming); //Prints the data from the server
			}
		}
		catch(IOException e)
		{//If the message transfer fails, itll print out an error message
			System.out.println("Nope doesnt work");
			e.printStackTrace();
		} // end of catch to catch any sort of exception

	}// end declaration of main method

} // end declaration of utility class