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

int main(int argc, char *argv[]) {
	int sockfd, newsockfd, portno;
	unsigned int clilen;
	char buffer[256];
	struct sockaddr_in serv_addr, cli_addr;	
	int n, x;

	if (argc < 2) 
	{
		fprintf(stderr,"ERROR, no port provided\n");
		exit(1); 
	}
    sockfd = socket(AF_INET, SOCK_STREAM, 0);
    if (sockfd < 0) error("ERROR opening socket");

    bzero((char *) &serv_addr, sizeof(serv_addr));
    portno = atoi(argv[1]);
	
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_addr.s_addr = INADDR_ANY;
	
    serv_addr.sin_port = htons(portno);	
	if (portno == 0) //Checks if the conversion failed
	{//if it failed, itll print an error message and exit
		error("Conversion of port failed");
	}//end if
	
    if (bind(sockfd, (struct sockaddr *) &serv_addr, sizeof(serv_addr)) < 0)
     	error("ERROR on binding");

	listen(sockfd,5);
	for (x=0; x<5; x++)
	{
		clilen = sizeof(cli_addr);
		newsockfd = accept(sockfd, (struct sockaddr *) &cli_addr, &clilen);

		if (newsockfd < 0) error("ERROR on accept");

		bzero(buffer, 256);
		n = read(newsockfd, buffer, 255);

		if (n < 0) error("ERROR reading from socket");
		printf("Here is the message: %s\n",buffer);

		n = write(newsockfd, buffer, strlen(buffer));

		if (n < 0) error("ERROR writing to socket");
		
		close(newsockfd);
	}
	
	return 0; 
}
