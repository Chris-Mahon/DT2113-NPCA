/*******************************************************************/
/* Mark Deegan                                                     */
/* Simple Client program                                           */
/* Based on examples at:                                           */
/* http://www.cs.rpi.edu/~moorthy/Courses/os98/Pgms/socket.html    */
/* The examples required somo modification to compile on           */
/* MAC OSX, namely addition of some includes and change of a       */
/* data type from int to unsigned int                              */
/*                                                                 */
/*                                                                 */
/*******************************************************************/

#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h> 


#include <stdlib.h>				// required for exit function
#include <strings.h>			// required for bzero function
#include <unistd.h>				// required for read and write functions


/************************************************************************/
/* provide a wrapper that prints an error message and exits with code 0 */
void error(char *msg)
{
	perror(msg); // print the provided error message
    exit(0);     // exit the program with exit code 0
} /* end declaration of error wrapper function                          */


/************************************************************************/
/* declare standard main function , we won't yet be using the args      */
int main(int argc, char *argv[])
{
	// declare the socket identifier, the port and the number of bytes 
	// being read or written 
	int sockfd, portno, n;

	// declare a struct to store the server address
	struct sockaddr_in serv_addr;
	
	// declare a struct to point to the server 
    struct hostent *server;

    // reserve a buffer to write and receive characters
    char buffer[256];
    
    // check the number of arguments
    // in C, there is always at least one, the name of the program being execute
    if (argc < 3) { // if we get too few arguments, then print a usage message
       fprintf(stderr,"usage %s hostname port\n", argv[0]);
       // and exit the program with exit code 0
       exit(0);
    }
    
    // convert the last string on the command-line to an integer
    // and store it as te port number
    portno = atoi(argv[2]);
    
    // create a new socket of family AF-INET
    sockfd = socket(AF_INET, SOCK_STREAM, 0);
    
    // if the socket creation failed, i.e. identifier < 0
    if (sockfd < 0) 
    	// then, print an error message
        error("ERROR opening socket");
        
	// take the first command-line argument, and look up the  host by name
	// storing the results in the server struct       
    server = gethostbyname(argv[1]);
    
    // if that lookup failed, then print an error message
    if (server == NULL) {
        fprintf(stderr,"ERROR, no such host\n");
        // and exit with code 0
        exit(0);
    }
    
    // write a load of zeros (sizeof(serv_addr)) to the serv_addr structure
	bzero((char *) &serv_addr, sizeof(serv_addr));
    
    // set the Address Family of the socket to be AF_INET for IP
    serv_addr.sin_family = AF_INET;
    
    // copy a byte-string, of length server.h_length from the source address
    // server.sin_addr.s_addr to destination server h_addr 
    bcopy((char *)server->h_addr, 
         (char *)&serv_addr.sin_addr.s_addr,
         server->h_length);
    
    // convert the port number (portno) to Host Byte Order (Network Byte order)   
    // and store it in the sin_port field of the serv_addr structure   
    serv_addr.sin_port = htons(portno);
    
    // if the attempt to connect to the specified socket returns a negative value
    // then the connect requerst failed
    if (connect(sockfd,(struct sockaddr *)&serv_addr,sizeof(serv_addr)) < 0) 
        // so, print an error message
        error("ERROR connecting");
    
    // ask the user to type a message on the console
    printf("Please enter the message: ");
    
    // reset the buffer to all zeros 
    bzero(buffer,256);
    
    // read a string, of length, up to 255 characters from the console (stdin)
    // and store it in buffer
    fgets(buffer,255,stdin);

    // write the contents of the buffer, of length strlen(buffer) to the 
    // socket identified by sockfd, and record the number of bytes read in n
    n = write(sockfd,buffer,strlen(buffer));
    
    // if that write attempt failed, i.e. if it returned a count < 0
    if (n < 0) 
    	// then print an error message
         error("ERROR writing to socket");
         
    bzero(buffer,256);
    
    // read data from the socket identified bu sockfd into the buffer up to a
    // maximum of 255, and store the number read in n
   n = read(sockfd,buffer,255);

	// if that read attempt failed, i.e. if it returned a count < 0
	if (n < 0) 
		// then print an error message
         error("ERROR reading from socket");
         
	// print to the console, as a string, followed by a newline, the contents of the buffer
    printf("%s\n",buffer);
    
    // exit the program with code 0
    return 0;
} // end declaration of the main method
