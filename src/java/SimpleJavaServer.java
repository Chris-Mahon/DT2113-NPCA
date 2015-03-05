/** all code should be developed as part of a package to ensure namespace
separation between many developers developing their own versions of utilities, classes and functions */
package ie.dit.student.mahon.christopher;

/** Christopher Mahon
Simple server that would allow a client to connect to it */

import java.net.*;
import java.io.*;

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
		{
			port = Integer.parseInt(args[0]);//Converts the port passed in through the parameters into an int
		}
		catch(NumberFormatException e)
		{
			Error("Error: Occurred while trying to convert the supplied port into an int");
		}
		
		try
		{
			serverSock = new ServerSocket(port);//Creates a socket at the supplied port
		}		
		catch(BindException e)
		{
			Error("Error: Address Already in Use");
		}
		catch(IOException e)
		{
			Error("Error: Creating the socket");
		}

		
		
		while(true)
		{
			try
			{
				System.out.println("\n\nListening on port "+port);
				ClientSock = serverSock.accept();
				err = ClientComms(ClientSock);
				if (err == -1)
				{	
					System.out.println("Error: With connecting to Client Detected. Closing Connections");
					break; //Break will break out of the current loop
				}
			}
			catch(IOException e)
			{
				e.printStackTrace(); 
				Error("Error: Creating a connection");
			}
		}
		
		try
		{
			serverSock.close();
		}
		catch(IOException e)
		{
			e.printStackTrace(); 
			Error("Error: Problem closing the Server Socket");
		}
		
		
	}// end declaration of main method
	
	private static int ClientComms(Socket ClientSock)
	{//Function to communicate with the Client
		int error = 0;
		String InMessage;
		try
		{
			DataInputStream in = new DataInputStream(ClientSock.getInputStream());
            DataOutputStream out = new DataOutputStream(ClientSock.getOutputStream());
            InMessage = in.readUTF();
            System.out.println("The Message recieved was : " + InMessage);
			out.writeUTF("Thank you for connecting\nGoodbye!");
		}
		catch(IOException e)
		{
			e.printStackTrace(); 
			Error("Error Reading and Writing with the Client");
		}
		
		try
		{
			ClientSock.close();
		}
		catch(IOException e)
		{
			e.printStackTrace(); 
			Error("Error closing the socket");
		}
		return error;
	}
	
	private static void Error(String Message)
	{
		System.out.println(Message);
		System.exit(0);
	}
	

} // end declaration of utility class