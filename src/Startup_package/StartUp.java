//******************************************************************
//* Author: Has-san Nazar
//*
//* Date: 2014-04-26
//*
//* Class: StartUp
//*
//* Description:
//* This class will be a implementarion of the SSD diagram of the class 
//* StartUp that was made for the second Seminar. We will be creating 
//* a object of Controll and also of View and sending the object Controll
//* to the object View.
//******************************************************************

package Startup_package;

import controller.Controller;
import View.View;
        
public class StartUp {
    public static void main(String[] args){
        //DataBas databas = new DataBas();
        Controller contr = new Controller();
        View view = new View(contr);
        view.runSimulation();
    }
}
