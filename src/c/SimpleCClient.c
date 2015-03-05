/*******************************************************************/
/* Christopher Mahon                                                     */
/* Simple Server program                                           */
/*******************************************************************/
#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <unistd.h>

void error(char *msg) 
{//Function that will print out an error message and close the program when called
	perror(msg); // print the provided error message
    exit(0);     // exit the program with exit code 0
}

int main(int argc, char *argv[]) 
{//Main function
	//Initialisation
	int clientsock, portno; 
	char buffer[256], hostaddr[24];
	struct sockaddr_in host;	
	int n, x;

	if (argc !=3) 
	{//Checking if there are an invalid number of parameters supplied
		fprintf(stderr,"ERROR, the program should be called with the following parameters: \nProgram_Name hostname port\n");
		exit(-1); 
	}//End of check
	
	if (strcmp(argv[1], "localhost"))
	{//Converts localhost to a ip address relating to the local machine
		strcpy(hostaddr, "127.0.0.1"); //Copies the ip version of localhost into a variable to be used later 
		
	}
	else
	{
		strcpy(hostaddr, argv[1]);
	}
	
	bzero(buffer, 256);
	printf("Enter in a message to send to the server");
	scanf("%s", buffer);//Reads in a message from the terminal
	
    clientsock = socket(AF_INET, SOCK_STREAM, 0);//Creates an instance of the socket
    if (clientsock < 0) error("ERROR opening socket"); //Checks if the socket creation succeeded

    bzero((char *) &host, sizeof(host));//Initialises the variable host to zeros, so as to prevent random data from previous programs to effect the system
    portno = atoi(argv[2]); //creates a local instance of the second parameter which is the port number
	if (portno == 0) //Checks if the conversion failed
	{//if it failed, itll print an error message and exit
		error("Conversion of port failed");
	}//end if
	
    host.sin_family = AF_INET; //Adding the family type as a parameter inside of the sockaddr_in struct
    host.sin_port = htons(portno);//Converts the port supplied into a number
	
	if (inet_aton(hostaddr, &host.sin_addr.s_addr)==0)//Converts hostaddr into a usable ip address
	{//if it returns a 0, it means the conversion failed
		error("ERROR, destination of the server must be an ip address");
	}//end if
	
    if (connect(clientsock, (struct sockaddr*) &host, sizeof(host)) < 0)//Attempts to connect with the server
	{//If the connection fails, itll print an error message and close out
		error("ERROR on connect");
	}//emd if
	
	n = write(clientsock, buffer, strlen(buffer));//Writes to the outbound stream
	if (n < 0) error("ERROR writing to socket");//If it fails, itll return a zero, print an error message and exit
		
	bzero(buffer, 256);//Clears the buffer
	n = read(clientsock, buffer, 255);//Reads in the input from the server
	if (n < 0) error("ERROR reading from socket");//If it fails, itll return a zero, print an error message and exit
	
	printf("Here is the message: %s\n",buffer);
	
	close(clientsock);//Closing the socket
	
	return 0; 
}
