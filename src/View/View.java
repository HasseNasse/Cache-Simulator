//******************************************************************
//* Author: Has-san Nazar
//*
//* Date: 2014-04-25
//*
//* Class: Main
//*
//* Description:
//* This is the Main class that will act as the View for the user, Also
//* declarations and calls to the Controller will be done through this
//* main method so that the Controller can thereafter distribute the tasks
//* to the different classes. This is basically a representation of the first SSD
//* that was made in the first Seminar where only the interaction between the user
//* and the System was of interest.
//******************************************************************



package View;

import controller.Controller;
import model.Observer;
import java.util.*;

public class View implements Observer{
    private Controller controller;
    private String currhitmiss;
    private String[][] cachelayout;
    
    /***
     * We start by making a new object of the Controller so that 
     * functions related with controller class can be used.
     * 
     * @param controller 
     */ 
    public View(Controller controller){
    this.controller = controller;
    }
    
    
    /***
     * the main View method
     */
    public void runSimulation()
    {
        Scanner input = new Scanner(System.in);
        input.useLocale(Locale.US);

        System.out.println("Start by typing in Nickname: ");
        String nick = input.next();
        
        String date = controller.startSimulation(nick);
        System.out.println(date);
        insertLine();
        System.out.println("Define Cache:");

        System.out.println("Blocksize: ");
        int bs = input.nextInt();

        System.out.println("Blockcount: ");
        int bc = input.nextInt();

        System.out.println("Associativity: ");
        int assc = input.nextInt();

        insertLine();

        System.out.println("|:| Simulation Launched |:|");
        System.out.println("Nickname is: " + nick);
        System.out.println();
        Object[] datain = controller.defineCache(bs, bc, assc, this);
        System.out.println(datain[1]);
        System.out.println("Adresslayout is:    " + datain[0]);
        insertLine();
        
        while (true) {
            System.out.println("Choose instruction:");
            System.out.print("[Type load/store]:  ");
            String instr = input.next();
            
            boolean loop = true;
            String adress = "";
            Object[] data = new Object[2];
            
            while (loop == true) {
                System.out.print("Define a 32 bit Adress: ");
                adress = input.next();
                
                try {
                    data = controller.defineInstruction(instr, adress);
                    loop = false;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            insertLine();
            System.out.println();
            System.out.println("Choosen Instruction was: " + instr);
            System.out.println("Choosen Adress was: " + adress);
            System.out.println(data[0]);
            System.out.println("Did cache hit?: " + data[1]);
            System.out.println("");
            System.out.println("Current Hitrate:");
            System.out.println(currhitmiss);
            
            if(data[1].toString().contains("false")) System.out.println("|:| Instruction: " + instr + " "
                    + " has been added to a random location in the Cache |:|");
            
            cachelayout = new String[(int)data[2]][(int)data[3]];
            cachelayout = controller.getCacheLayout();
            
            showCacheLayout();
            
           //while (true) {
           //     if (adress.length() == 32) {
           //         break;
           //     }
           //     System.out.println("Not 32 digits, try again: ");
           //     adress = input.next();
           // }
            
            //System.out.println("Did cache hit?: " + data[1]);
            insertLine();
            
            System.out.println("Continue Simulation? [y/n]: ");
            String choice = input.next();
            if (choice.equals("n")) {
                break;
            }
        }
        
        System.out.println("\n--Attempting to exit Simulation--");
        System.out.println("\nFinal Cache Result: ");
        Object[] finaldata = controller.stopSimultion();
        System.out.println("Total Instruktions Simulated were: " +
                           finaldata[1] + " instructions");
        System.out.println(finaldata[0]);
        
        System.out.println();
    }
    
    private void insertLine(){
    System.out.println("--------------------------");
    }
    
    public void notify(int hit, int miss) {
        currhitmiss = ("Hits: " + hit + ", Miss: " + miss);

    }
    
    public void showCacheLayout(){
        
        System.out.println("Current Cache Layout: ");
                
        for(int i=0; i<cachelayout[0].length; i++){
                for(int j=0; j<cachelayout.length; j++){
                System.out.print(cachelayout[j][i]);
                }
        System.out.println(" ");
        }
    }
    
}
