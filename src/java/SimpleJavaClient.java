/** all code should be developed as part of a package to ensure namespace
separation between many developers developing their own versions of utilities, classes and functions */
package ie.dit.student.mahon.christopher;

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
			ClientSocket = new Socket("83.212.126.255", 4444);
			
			PrintWriter output = new PrintWriter(ClientSocket.getOutputStream(), true);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(ClientSocket.getInputStream()));
			output.println(OutMessage);
			
			System.println("The response is:" + in.readLine());
		}
		catch(IOException e)
		{//If the NTP server fails, itll inform the user and pull the time from the local machine
			System.out.println("Something went wrong with the connection to the NTP server.");
			System.out.println("Pulling time from the local machine");
			
			
			Calendar now = Calendar.getInstance();//Creating an instance of the object calendar to get the time
			int hour = now.get(Calendar.HOUR_OF_DAY); //Pulls the current hour out of the Calender object
			int minute = now.get(Calendar.MINUTE);//Pulls the current hour out of the Calender object
			
		System.out.println(Integer.toString(hour) + ":" + Integer.toString(minute)); //Converts the current time into our standardised format
			
		} // end of catch to catch any sort of exception
	}// end declaration of main method

} // end declaration of utility class