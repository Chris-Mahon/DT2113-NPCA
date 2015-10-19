
# DT211-3 Network Programming, Semester 2, 2015
### Assignment -3, Due Saturday 28th March 2015, 03:00 (UTC) <br> Protocol Design

### Design and Implement two application protocols to run over TCP and UDP respectively.
The protocols are both intended for the same application, the control and reporting of an electronic game framework.

### The Game: 
- The game hardware consists of a series of tewlve illuminated push-buttons.
- These buttons can be illuminated in any sequence, and all or none may be illuminated at any time. 
- The illumination is controlled by sending messages from a network client to the game.
- In Basic mode, they may be either on or off.
- In advanced mode, they may be dimmed, and may be set to any integer value between 0 (meaning off) and 1023 (meaning on).
- The buttons may be pressed in any sequence, and all or none may be pressed at any time.
- The buttons may only be on or off.
- If one or more buttons is pressed, that fact must be communicated to the connected client.
- The game has a limited functionality test mode which can be controlled from the client.
- In this mode, all buttons are illuminated and one or more buttons can be pressed at any time.

### Design: 
You must submit a complete design document outlining all features of your application protocols and how they are meant to operate. In this you should include, among other things, a full explanation of all significant pieces of code, configuration files and directories etc. that form part of your submission.

### Implementation: 
Your submission must include at least the following:
- Full instructions telling the examiner how to build, deploy and test your protocols.
- A test plan indicating how the app should be tested. This is to enable the examiner to verify all required and any additional features of the app.
- Each student must propose a suitable Uniqe Selling Point, a feature, or selection of features of their own design that will be unique among the class. This Unique Selling Point is open to revision by the lecturer.

### GitHub and WebCourses:
- Your initial project must be based on a forked repo which will be shared with you only upon request.
- Your formal submission for this assignment will be via WebCourses, and should provide links to any materials you wish to have examined.
- To allow for the examination of a GitHub history, this should be shared with Mark Deegan from the outset of the project.
- Once the deadline has passed, no further changes should be made to this repository.
- A copy of your code will be taken by Mark Deegan by cloning this repository.
- Marks will be awarded for the use made of GitHub and for the ability to demonstrate a history of code and other materials committed to GitHub through the project development.

### Note: 
The instructions in this document are not intended as a minimum requirement to attain 100% in this assignment. You need to make contributions to this assignment on your own initiaitve.
