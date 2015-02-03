//******************************************************************
//* Author: Has-san Nazar
//*
//* Date: 2014-04-26
//*
//* Class: HitRate
//*
//* Description:
//* This class will monitor and store all the hits and misses that are occured
//* during the simulation. The end result will also be stored in the simulationdata
//* at the end of the simulation.
//******************************************************************
package model;

import java.util.List;
import java.util.ArrayList;

public class HitRate {
private List<Observer> observers = new ArrayList<Observer>();
private int hit;    
private int miss;
   
    /**
     * Regsiters an Observer that shall be notifed about changes in the hit and
     * miss rate
     *
     * @param observer The Observer that shall be registered.
     */ 
    public void addObserver(Observer observer) {
	observers.add(observer);
    }
    
    /***
     * When the object hitmiss of the type HitRate is created we initialize the 
     * variables so that the afterwards can tick.
     */
    public HitRate() {
        this.hit = 0;
        this.miss = 0;
    }

    /***
     * This is the public method that will be called by the Cache class in the
     * method findInstruction() and it will hold a boolean value. We will use 
     * that boolean value to set the hit and miss according to what value the
     * boolean has.
     *
     * @param didhitormiss 
     */
    public void setHitMiss(boolean didhitormiss) {
        if (didhitormiss == true) {
            hit++;
        } else if (didhitormiss == false) {
            miss++;
        }
        notifyObservers();
    }
    
    private void notifyObservers() {
	for (Observer observer : observers) {
	    observer.notify(hit, miss);
	}
    }       
    
    /***
     * This is a method that will be called to get the total Hit and missrate at
     * the end of the simulation to then be stored in the simulationdata object.
     * 
     * @return information for the view 
     */
    public String getHitMiss() {
        String hitmiss = "This Simulation yielded " + hit + " hits & "
                + miss + " misses";
        return hitmiss;
    }
}
