#!/bin/bash

# Mark Deegan
# Tue 10 Feb 2015 09:53:42 GMT
# DT211-3
# Network Programming
# February 2015
# CA-2
#
# script to build HelloWorld.c
# this script should be run from the src/bash directory of the project

# this calles the gcc compiler on the input file HelloWorld.c and 
# generates the output file HelloWorld.o
gcc -o ../../bin/HelloWorld.o ../c/HelloWorld.c
