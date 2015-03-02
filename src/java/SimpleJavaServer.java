/** all code should be developed as part of a package to ensure namespace
separation between many developers developing their own versions of utilities, classes and functions */
package ie.dit.student.mahon.christopher;

/** Christopher Mahon
Simple server that would allow a client to connect to it */

import java.net.ServerSocket;
import java.io.*;

public class SimpleJavaServer
{

	/** start declaration of main method */
	public static void main(String[] args) throws Exception 
	{
		ServerSocket ServerSock = new ServerSocket(4444);

	}// end declaration of main method
	
	private int ClientComms()
	{
		System.out.println("Im talking here!");
		return 0;
	}
} // end declaration of utility class