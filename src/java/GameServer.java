/** all code should be developed as part of a package to ensure namespace
separation between many developers developing their own versions of utilities, classes and functions */
package ie.dit.student.mahon.christopher;

/** Christopher Mahon
Java Server that will be connecting to a Python game client 
Using CA2 Java Server as a template*/

import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.BindException;


public class GameServer
{
	private static ServerSocket serverSock = null;//Creates an empty instance of the servers socket
	
	/** start declaration of main method */
	public static void main(String[] args) 
	{
		int error = 0;//Variable used for error reporting
		int port = 0; //Variable used to store the port
		
		Socket ClientSock = null;//Creates an empty instance of a  socket
		
		if (args.length!=1)//Checks if exactly 1 argument was passed in
		{//Prints out an error message and closes
			System.out.println("Error: No port has been supplied");
			System.exit(-2);
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

		try
		{//Itll try to create a connection with the Client, transmit messages
			System.out.println("\n\nListening on port "+port);
			ClientSock = serverSock.accept(); //Will accept a connection with the client
			System.out.println("Connection created");
			while(true)
			{//Game Loop
				error = ClientComms(ClientSock);//Calls a function which contacts the client
				
				if (error == -1)
				{//If an error occurs with the communication, itll activate this if statement	
					System.out.println("Error: With connecting to Client Detected. Closing Connections");
					break; //Break will break out of the current loop
				}//end if
				else if (error == 1)
				{ //If the Client tells the server to end communication, itll activate this statement
					error = 0;
					break;
				}
			}//End of the game loop
			
		}
		catch(IOException e)
		{ //Catches and reports any errors that occurs
			e.printStackTrace(); 
			Error("Error: Creating a connection");
		}//end try
		
		try
		{//Attempts to close the Server Socket
			serverSock.close();
		}
		catch(IOException e)
		{//If it fails, itll report the error and exit
			e.printStackTrace(); 
			Error("Error: Problem closing the Server Socket");
		}//end try
		System.exit(error);
	}// end declaration of main method
	
	private static int ClientComms(Socket ClientSock)
	{/**Function to communicate with the Client
		Usage ClientComms(Socket)*/
		int error = 0; //variable for the error message
		int InMessage; //Variable that will hold the Client input
		char Letter;
		
		try
		{	//Trys to communicate with the client
            BufferedReader in = new BufferedReader(new InputStreamReader(ClientSock.getInputStream()));
			
			while (!in.ready()) {} //Doesn't try to read in from the client until it is ready to
			InMessage = in.read(); //Reads in from the client

			Letter = (char)InMessage; //Converts the input from the client into a character

			System.out.println(Letter); //Prints it out onto the server
			if (Letter == (char)'Z')//If the client tells the server to close, it will close
			{
				System.out.println("Closing the Server");
				ClientSock.close(); //Closing the Client Socket
				error = 1; //Tells the loop to close
			}
		}
		catch(IOException e)
		{//If communication fails, itll report the error to the terminal and allow the function to return an error of -1
			e.printStackTrace(); 
			error = -1;
		}//end try
		
		return error; //Returns a -1 when an error occurs
	}//End of Client Communication function
	
	private static void Error(String Message)
	{//Function to print out an error message and exit
		System.out.println(Message);
		System.exit(0);
	}//end of Error Function
	
} // end declaration of the server class