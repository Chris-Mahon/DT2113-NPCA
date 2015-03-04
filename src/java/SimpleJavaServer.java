/** all code should be developed as part of a package to ensure namespace
separation between many developers developing their own versions of utilities, classes and functions */
package ie.dit.student.mahon.christopher;

/** Christopher Mahon
Simple server that would allow a client to connect to it */

import java.net.*;
import java.io.*;

public class SimpleJavaServer
{
	private static ServerSocket serverSock = null;
	
	/** start declaration of main method */
	public static void main(String[] args) 
	{
		int err = 0;//Variable used for error reporting
		int port; //Variable used to store the port
		
		Socket ClientSock = null;
		
		if (args.length!=1)
		{
			System.out.println("So... you didnt put enough details in");
			System.exit(-1);
		}
		
		port = Integer.parseInt(args[0]);
		
		try
		{
			serverSock = new ServerSocket(port);
		}
		catch(IOException e)
		{
			e.printStackTrace(); 
		}
		
		
		while(true)
		{
			try
			{
				ClientSock = serverSock.accept();
				err = ClientComms(ClientSock);
				if (err == -1)
				{	
					System.out.println("Error with connecting to Client Detected. Closing Connections");
					break; //Break will break out of the current loop
				}
			}
			catch(IOException e)
			{
				e.printStackTrace(); 
			}
		}
		
		try
		{
			serverSock.close();
		}
		catch(IOException e)
		{
			e.printStackTrace(); 
		}
	}// end declaration of main method
	
	
	
	private static int ClientComms(Socket ClientSock)
	{
		int error = 0;
		System.out.println("Im talking here!");
		
		try
		{
			
			DataInputStream in = new DataInputStream(ClientSock.getInputStream());
            DataOutputStream out = new DataOutputStream(ClientSock.getOutputStream());
            
            System.out.println(in.readUTF());
			out.writeUTF("Thank you for connecting\nGoodbye!");
		}
		catch(IOException e)
		{
			e.printStackTrace(); 
			error = -1;
		}
		
		try
		{
			ClientSock.close();
		}
		catch(IOException e)
		{
			e.printStackTrace(); 
		}
		return error;
	}
} // end declaration of utility class