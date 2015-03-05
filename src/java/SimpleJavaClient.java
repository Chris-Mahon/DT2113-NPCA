
package ie.dit.student.mahon.christopher;

import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

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
		Scanner keyboard = new Scanner(System.in);
		
		//Command Line argument checking
		if (args.length!=2)
		{//If there are not 3 or 2 arguments passed in
			System.out.println("So... you didnt put enough details in");//The program will print out an error message
			System.exit(-1);//And it will exit as an error has occurred
		}

		System.out.println("Enter in your message");
		OutMessage = keyboard.nextLine();
		
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
		} // end of catch to catch IOexception
		
		
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
			System.out.println("The response was: " + Incoming); //Prints the data from the server
		}
		catch(IOException e)
		{//If the message transfer fails, itll print out an error message
			System.out.println("Nope doesnt work");
			e.printStackTrace();
		} // end of catch to catch IOexception

	}// end declaration of main method

} // end declaration of utility class