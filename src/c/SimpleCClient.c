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
{
	perror(msg); // print the provided error message
    exit(0);     // exit the program with exit code 0
}

int main(int argc, char *argv[]) 
{
	int clientsock, portno;
	char buffer[256], hostaddr[24];
	struct sockaddr_in host;	
	int n, x;

	if (argc <3) 
	{
		fprintf(stderr,"ERROR, the program should be called with the following parameters: \nProgram_Name hostname port");
		exit(-1); 
	}
	
	if (strcmp(argv[1], "localhost"))
	{
		strcpy(hostaddr, "127.0.0.1");
		
	}
	else
	{
		strcpy(hostaddr, argv[1]);
	}
	
	bzero(buffer, 256);
	printf("Enter in a message to send to the server");
	scanf("%s", buffer);
	
    clientsock = socket(AF_INET, SOCK_STREAM, 0);
    if (clientsock < 0) error("ERROR opening socket");

    bzero((char *) &host, sizeof(host));
    portno = atoi(argv[2]);
	
    host.sin_family = AF_INET;
    host.sin_port = htons(portno);
	
	if (inet_aton(hostaddr, &host.sin_addr.s_addr)==0)
	{
		error("ERROR, destination of the server must be an ip address");
	}
	
    if (connect(clientsock, (struct sockaddr*) &host, sizeof(host)) < 0)
	{
		error("ERROR on connect");
	}
	
	n = write(clientsock, buffer, strlen(buffer));
	if (n < 0) error("ERROR writing to socket");
		
	bzero(buffer, 256);
	n = read(clientsock, buffer, 255);
	if (n < 0) error("ERROR reading from socket");
	
	printf("Here is the message: %s\n",buffer);
	
	close(clientsock);
	
	return 0; 
}
