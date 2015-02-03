//******************************************************************
//* Author: Has-san Nazar
//*
//* Date: 2014-04-26
//*
//* Class: SimulationData
//*
//* Description:
//* This class will hold all the information at the end of the simulation.
//* Every object and important information will be stored in this class at the end
//******************************************************************
package model;
import java.util.*;

public class SimulationData {
    private User user;
    private Cache cache;
    private String hitmiss;
    private java.util.Date curdate;
    
    /***
     * When the object simulationdata of the type SimulationData is created we set 
     * the date and store that in the cache, we also show the date to the user to 
     * indicate a newly launched simulation
     */
    public SimulationData(){
        Date currentdate = new Date();
        this.curdate = currentdate;
    }
    
    /***
     * So that the date will be printed out at the start of the simulation for 
     * the user.
     * 
     * @return java.util.Date in stringform to the view
     */
    public String getDate(){
        String date = this.curdate.toString();
        return date;
    }
    
    /***
     * The most important method in this class is this public method that stores
     * all the data from the simulation in the class. 
     * 
     * @param user
     * @param cache
     * @param hitmiss 
     */
    public void storeData(User user, Cache cache,String hitmiss){
        this.user = user;
        this.cache = cache;
        this.hitmiss = hitmiss;
    }
    
}
