/** all code should be developed as part of a package to ensure namespace
separation between many developers developing their own versions of utilities, classes and functions */
package ie.dit.student.mahon.christopher;

/** Christopher Mahon
Simple server that would allow a client to connect to it */

import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.BindException;



public class SimpleJavaServer
{
	private static ServerSocket serverSock = null;//Creates an empty instance of the servers socket
	
	/** start declaration of main method */
	public static void main(String[] args) 
	{
		int err = 0;//Variable used for error reporting
		int port = 0; //Variable used to store the port
		
		Socket ClientSock = null;//Creates an empty instance of a  socket
		
		if (args.length!=1)//Checks if exactly 1 argument was passed in
		{//Prints out an error message and closes
			System.out.println("Error: No port has been supplied");
			System.exit(-1);
		}//End if
		
		try
		{//Converts the port supplied into a number
			port = Integer.parseInt(args[0]);//Converts the port passed in through the parameters into an int
		}
		catch(NumberFormatException e)
		{//If the number cant be converted, it will return an error and exit
			Error("Error: Occurred while trying to convert the supplied port into an int");
		}//End Try
		
		try
		{//Attempts to create a Server Socket
			serverSock = new ServerSocket(port);//Creates a socket at the supplied port
		}		
		catch(BindException e)
		{//If the Address/Port is already in use, itll return an error message and exit
			Error("Error: Address Already in Use");
		}
		catch(IOException e)
		{//If anything else fails, itll tell the user it cant create the socket and exit
			Error("Error: Cannot create the Server socket");
		}//end try

		
		
		while(true)
		{//Will continuously loop until the program is told to break
			try
			{//Itll try to create a connection with the Client, transmit messages
				System.out.println("\n\nListening on port "+port);
				ClientSock = serverSock.accept(); //Will accept a connection with the client
				err = ClientComms(ClientSock);//Calls a function which contacts the client
				if (err == -1)
				{//If an error occurs with the communication, itll activate this if statement	
					System.out.println("Error: With connecting to Client Detected. Closing Connections");
					break; //Break will break out of the current loop
				}//end if
			}
			catch(IOException e)
			{ //Catches and reports any errors that occurs
				e.printStackTrace(); 
				Error("Error: Creating a connection");
			}//end try
		}//end While loop
		
		try
		{//Attempts to close the Server Socket
			serverSock.close();
		}
		catch(IOException e)
		{//If it fails, itll report the error and exit
			e.printStackTrace(); 
			Error("Error: Problem closing the Server Socket");
		}//end try
	}// end declaration of main method
	
	private static int ClientComms(Socket ClientSock)
	{/**Function to communicate with the Client
		Usage ClientComms(Socket)*/
		int error = 0; //variable for the error message
		String InMessage;
		
		try
		{	//Trys to communicate with the client
			DataInputStream in = new DataInputStream(ClientSock.getInputStream()); //Data Stream for input
            DataOutputStream out = new DataOutputStream(ClientSock.getOutputStream()); //Data Stream for output
            InMessage = in.readUTF(); //Reads in a UTF message
            System.out.println("The Message recieved was : " + InMessage); //Displays the message on screen	
			out.writeUTF("Thank you for connecting\nGoodbye!"); //Writes a message to the client
		}
		catch(IOException e)
		{//If communication fails, itll report the error to the terminal and allow the function to return an error of -1
			e.printStackTrace(); 
			error = -1;
		}//end try
		
		try
		{//Attempts to close the client socket
			ClientSock.close();
		}
		catch(IOException e)
		{//If closing the socket fails, itll report the error and return an error code
			e.printStackTrace(); 
			error=-1;
		}//end try
		
		return error; //Returns a -1 when an error occurs
	}//End of Client Communication function
	
	private static void Error(String Message)
	{//Function to print out an error message and exit
		System.out.println(Message);
		System.exit(0);
	}//end of Error Function
	
} // end declaration of the server class