//******************************************************************
//* Author: Has-san Nazar
//*
//* Date: 2014-04-26
//*
//* Class: User
//*
//* Description:
//* This class will hold the information about the User. It might seem
//* abit unneeded to have a object of the User when the only thing we do is to 
//* store a name of the user and to then later shove it in the simulationdata.
//* But to use as little variables and as much objects as possible this is what 
//* sounded good to me. 
//******************************************************************
package model;


public class User {
    private String nickname;
    
    /***
     * This is the constructor that will hold the information of the User/Simulator
     * 
     * @param name 
     */
    public User(String name){
        this.nickname = name;
    }
    
    /***
     * A method to get the nickname of the User
     * @return nickname that was stored by the constructor
     */
    public String getName(){
    return this.nickname;
    }
}
