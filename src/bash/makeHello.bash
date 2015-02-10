#!/bin/bash

# Mark Deegan
# Tue 10 Feb 2015 09:53:42 GMT
# DT211-3
# Network Programming
# February 2015
# CA-2
#
# script to build Hello.java
# this script should be run from the src/bash directory of the project

# this calls the javac compiler compiler on the input file Hello.java
# genertes the output class in the correct directory srtructure
javac -d ../../bin ../java/Hello.java
