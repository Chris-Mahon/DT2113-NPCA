/** all code should be developed as part of a package to ensure namespace
separation between many developers developing their own versions of utilities, classes and functions */
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
		String OutMessage = "Heya";
		//Main Function
		try
		{//Trying to connect to the server and pulling any data sent by the server to it
			ClientSocket = new Socket("localhost", 4444);
		}
		catch(IOException e)
		{//If the socket fails, itll print out an error message
			System.out.println("Nope Broken");
			e.printStackTrace();
		} // end of catch to catch any sort of exception
		
		try
		{//Trying to connect to the server and pulling any data sent by the server to it
			PrintWriter output = new PrintWriter(ClientSocket.getOutputStream(), true);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(ClientSocket.getInputStream()));
			output.println(OutMessage);
			
			System.out.println("The response is:" + in.readLine());
		}
		catch(IOException e)
		{//If the socket fails, itll print out an error message
			System.out.println("Nope doesnt work");
			e.printStackTrace();
		} // end of catch to catch any sort of exception


	}// end declaration of main method

} // end declaration of utility class