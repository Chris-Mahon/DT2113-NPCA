#!/src/python
"""
Author: Christopher Mahon
Name: Game Client
Assignmment: Network Programming CA 3
A game client that will communicate with the server through a GUI"""

import socket
from tkinter import *


host="localhost"
port = 4444

def main(): #Main function that the Game Client
	range = 0
	ConnChannel = GameClient() #Initialises the Socket Manager
	i=0
	j=0 
	display(8, ConnChannel)
	
def display(size, Sock): #Displaying the Gui for the client
	BClose = Button(root, text ="Close me!",width= 10, height=4, command = lambda:Sock.Comms("Z"))
	BClose.grid(row=0, column=0) #Creates a Button and places it into the grid
	
	B1 = Button(root, text ="Button 1",width= 10, height=4, command = lambda:Sock.Comms("A"))
	B1.grid(row=1, column=0) #Creates a Button and places it into the grid
	B2 = Button(root, text ="Button 2",width= 10, height=4, command = lambda:Sock.Comms("B"))
	B2.grid(row=1, column=1) #Creates a Button and places it into the grid
	B3 = Button(root, text ="Button 3",width= 10, height=4, command = lambda:Sock.Comms("C"))
	B3.grid(row=1, column=2) #Creates a Button and places it into the grid
	B4 = Button(root, text ="Button 4",width= 10, height=4, command = lambda:Sock.Comms("D"))
	B4.grid(row=1, column=3) #Creates a Button and places it into the grid
	B5 = Button(root, text ="Button 5",width= 10, height=4, command = lambda:Sock.Comms("E"))
	B5.grid(row=2, column=0) #Creates a Button and places it into the grid
	B6 = Button(root, text ="Button 6",width= 10, height=4, command = lambda:Sock.Comms("F"))
	B6.grid(row=2, column=1) #Creates a Button and places it into the grid
	B7 = Button(root, text ="Button 7",width= 10, height=4, command = lambda:Sock.Comms("G"))
	B7.grid(row=2, column=2) #Creates a Button and places it into the grid
	B8 = Button(root, text ="Button 8",width= 10, height=4, command = lambda:Sock.Comms("H"))
	B8.grid(row=2, column=3) #Creates a Button and places it into the grid
	root.mainloop() #Displays the GUI

class GameClient: #Class to manage the game client and the communications
	def __init__(self):#Initialisation for the class Game Client
		try:
			self.Client = socket.socket(socket.AF_INET, socket.SOCK_STREAM) #Creates the socket
		except socket.socket_error:#If it fails, itll display a message and close
			print("Socket Creation failed")
			self.exit(-2)
			
		try: #Tries to get the IP of the host
			remote_ip = socket.gethostbyname(host)
		except socket.gaierror: #If it fails, then it will print out a message
			print('Hostname could not be resolved. Exiting')
			self.exit(-1)

		print('Ip address of ' + host + ' is ' + remote_ip)
		
		try:
			#Connect to remote server
			self.Client.connect((remote_ip , port)) #Connects to the supplied arguments
		except socket.error: 
			print("Error connecting to the server")
			self.exit(-3)
		print('Socket Connected to ' + host + ' on ip ' + remote_ip)
			
	def exit(self, exitcode): #Function to close the program
		root.destroy() #Turns off the GUI
		self.Client.close() #Closes the Socket
		sys.exit(exitcode)
		
	def Comms(self, message):	
		try :
			self.Client.send(message.encode())#Sending a message to the server in bytes
			print(message)
			
			if (message == "Z"):##If the message sent out is Z, close the program
				exit(0)
		except socket.error:#If the sending fails, warn the user
			print("Error Sending the Message, Server has most likely disconnected") #Message sending has failed
		
	
root = Tk()
main()
