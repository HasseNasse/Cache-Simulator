//******************************************************************
//* Author: Has-san Nazar
//*
//* Date: 2014-04-25
//*
//* Class: Controller
//*
//* Description:
//* This is the Controller class that will ditribute the actions from view to
//* the different classes and objects.
//* We start looking at the SSD from the second seminar. Important to know is 
//* that some slight changes were made of the SSD for the Seminar 3 to match the 
//* written code below. Please refer to the SSD shown in the report.
//******************************************************************

package controller;

import model.User;
import model.SimulationData;
import model.Cache;
import model.Observer;


public class Controller {
    //* These are the objects that we will be creating for this program.
    private User user; //* Will hold Nickname
    private SimulationData simulationdata; //* Will store all the Data from the simulation
    private Cache cache; //* Will hold Cachelayout, Adresslayout and also the HitRate object
    /***
     * When the nickname is entered we consider a new simulation started. We begin
     * by making a new object of the type User called user and send the Nickname 
     * that was choosen by the user for this specific Simulation.
     * As a result of the newly started simulation we also make a new object of the
     * type SimulationData called simulationdata to store all the data in the end of 
     * the simulation.
     * And in the end we return the nickname of the User to later be displayed on the
     * console for the user.
     * 
     * @param nickname
     * @return date to the view for the console printout
     */
    public String startSimulation(String nickname){
        user = new User(nickname);
        simulationdata = new SimulationData();
        String date = simulationdata.getDate();
        return date;        
    }
    
    /***
     * At this point blocksize, blockcount and associativity has been specied by the
     * user. We begin by making a new objekt of the type Cache called cache to make the
     * Cachelayout. 
     * After the Cachelayout has been made we ask for the "Empty" Cachelayout to be showed
     * to the user along with the adresslayout (i.e the information of tag,Index and offset
     * bits) that will be useful when searching for instructions in the cache.
     * 
     * @param blocksize
     * @param blockcount
     * @param associativity
     * @return object array with data for the view to print/user
     */
   public Object[] defineCache(int blocksize, int blockcount, int associativity, Observer obj){
       Object[] strarray = new Object[2];
       cache = new Cache(blocksize,blockcount,associativity);
       cache.addObserver(obj);
       Object cacheresult = cache.getcacheresult();
       
       String adrlayout = cache.setadresslayout();
       strarray[0] = adrlayout;
       strarray[1] = cacheresult;
       return strarray;
   }
   
   /***
    * With the Cachelayout and Adresslayout made we are now able to search for and 
    * add instructions to the "Empty" cache. For this we ask the user to Specie the 
    * type of instruction that is needed and also the adress of that instruction.
    * After that we use the newly created cache object to search(in itself) if the needed
    * instruction is contained within the cache. If not then the boolean "didhitormiss" returns
    * as false to the user and the instruction is added at a random place in the Cache.
    * 
    * @param instructionchoice
    * @param adresschoice
    * @return object array with data for the view to print/user
    */
   public Object[] defineInstruction(String instructionchoice, String adresschoice) throws Exception{
       Object[] datain = cache.findinstruction(instructionchoice, adresschoice);
       return datain;
   }
   
   /**
    * Newly defined method for printing out cachelayout.
    */
   public String[][] getCacheLayout(){
       
       String[][] layout = cache.getCacheLayout();
       return layout;
   }
   
   /***
    * When the simulation is fully and the User decides to end we call upon cache to get the 
    * total Hits and misses for the simulation, this will be stored in the simulationdata object
    * we created in the beginning of this Class.
    * Then we send in the User user object, the Cache cache object and the String value of HitRate
    * to be stored in simulationdata.
    * When that is finished we also get the Cachelayout and the total Hit and missrate of the simulation
    * to show the end result of the simulation for the User.
    * 
    * @return object array with data for the view to print/user
    */
   public Object[] stopSimultion(){
       Object[] data = cache.getHitMissAndInstrCount();
       simulationdata.storeData(user, cache, (String)data[0]);
       return data;
   }
}





