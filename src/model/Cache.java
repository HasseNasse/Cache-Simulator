//******************************************************************
//* Author: Has-san Nazar
//*
//* Date: 2014-04-25
//*
//* Class: Cache
//*
//* Description:
//* The Cache class which we will be using to store, view and edit cachelayouts
//* of the caches that are specied by the user under different Simulations.
//******************************************************************
package model;

public class Cache {
    /**
     * This code is here because we need a object of the typ HitRate to store the
     * different hitts and misses that might occure during the Simulation.
     */
    private HitRate hitmiss;
    /**
     * The actual Cachelayout is a Stringmatrix that will have columns and rows depending
     * on what the data and the calculation dictates.
     */
    private String[][] cachelayout;
    /**
     * Offsetbits and Indexoffsetbits are used to define the AdressLayout later
     */
    private double offsetbits;
    private double indexoffsetbits;
    /**
     * The rowcount(i.e the amount of rows) is used in for example getlayout where
     * we loop through the cachelayout[][] to show it on the console
     */
    private int rowcount;
    /**
     * Istället för att skriva ut adresslayout från Cache så sparar vi denna i en variabel
     */
    private String adresslayout;
    /**
     * And the instruction count(instramunt) is used to count how many instructions were used
     */
    private int instramount = 0;
    
    
    /**
     * This is the constructor that is used when we make a new object of the type
     * Cache. For this we have 3 inputs i.e blocksize, blockcount and associativity.
     * These are all specied by the user in the console and then sent here. 
     * In an attempt to simplify the class with less methods i tried to make the 
     * constructor also be the cachelayout calculator. This is because we need all
     * the inputdata that is given to do this. 
     * 
     * @param blocksize
     * @param blockcount
     * @param associativity 
     */
    public  Cache(int blocksize, int blockcount, int associativity){
        
        double indoffs = Math.round(Math.log(blocksize*blockcount)/Math.log(2));
        this.indexoffsetbits = indoffs;
        
        double offs = Math.round(Math.log(blocksize)/Math.log(2));
        this.offsetbits = offs;
        
        double calc = Math.pow(2, indoffs-offs);
        calc = Math.round(calc);
        int rows = (int)calc;
        this.rowcount = rows;
        
        cachelayout = new String[associativity][rows];
       
        int i = 0;
        while(i < rowcount)
        {
            for(int j=0; j<cachelayout.length; j++)
            {
                cachelayout[j][i]= "-Empty- " + ", " ;
            }
            i++;
        }
        
        hitmiss = new HitRate();
        
    }
    
    /***
     * This public method will be used to get the cache layout information to the
     * user. for this we send back the information to the View so it can be 
     * printed out
     * 
     * @return The info about rows and columns so that the user knows. 
     */
    public String getcacheresult(){
     String cacheresult = "The Cache will have " + cachelayout[1].length + " rows and " + 
                            cachelayout.length + " kolumns.";
     return cacheresult;
    }
    
    /***
     * This public method will give us the information of the adresslayout printed 
     * out on the console so that the User can use this when defining a adress for 
     * instructions.
     * 
     * @return the adress layout information i.e the tag, index and offset bits
     */
    public String setadresslayout()
    {
        double tag = (32-indexoffsetbits);
        double index = (indexoffsetbits - offsetbits);
       
        this.adresslayout = ("|:| tag = "+tag+" bits |:| index = "+index+" bits |:| "
                        + "offset = " + offsetbits + " bits |:|");
        
        return this.adresslayout;
    }
 
    /***
     * This is a very important public method through which we will search and also 
     * add instructions to the empty cache when a instruction failed to be found in
     * the cache. 
     * I decided to use the adress which is specied by the user to only search that 
     * row of the cache which the adress index bits dictate and ignore the rest of the
     * cachelayout rows. Although, if a cache missed we will just randomly put the 
     * instruction into the cache and not look at the indexbits following the recquirement
     * specifikation.
     * 
     * @param instruction defined by the user either load or store
     * @param adress, a 32 bit binary adress defined by the user
     * @return indexbits and HitorMiss in a object array back to the View
     * @throws InvalidInstructionException when the instruction is less than 4 bytes
     *         or is not divisible with 4.
     */
    public Object[] findinstruction(String instruction, String adress) throws InvalidInstructionException{
      
        String lsb = adress.substring(adress.length()-2,adress.length());
        if(adress.length() != 32 || !lsb.equals("00")){
            throw new InvalidInstructionException(adress);
        }
        
        boolean didhitormiss = false;
        Object[] data = new Object[4];
        this.instramount++;
        
        int startindex = 32 - (int)indexoffsetbits;
        int endindex = 32-(int)offsetbits;
        String index = adress.substring(startindex, endindex);
        String indexbits = ("Indexbites are: " + index);
        data[0] = indexbits;
        
        int decimalvalue = Integer.parseInt(index, 2);
        
        int i = decimalvalue;
        while(i == decimalvalue && didhitormiss == false)
        {   for(int j=0; j<cachelayout.length; j++)
            {
                if(cachelayout[j][decimalvalue].toLowerCase().contains(instruction)) didhitormiss = true;
                
            }
            i++;
        }
        hitmiss.setHitMiss(didhitormiss);
        
        if(didhitormiss == false){
        addInCache(instruction);
        }
        data[1] = didhitormiss;
        data[2] = cachelayout.length;
        data[3] = cachelayout[1].length;
        return data;
    }
    
    
    /***
     * This private method will add a instruction to the cache if the didhitormiss boolean
     * ended with the state false.
     * This method is private to hold as much as a good encapsulation as possible
     * 
     * @param instruction 
     */
    private void addInCache(String instruction){
        int n1 = (int)(Math.random() * cachelayout.length); 
        int n2 = (int)(Math.random() * cachelayout[0].length);
        
        if(instruction.contains("store")){
        cachelayout[n1][n2] = "-Store- ";}else
            if(instruction.contains("load")){
            cachelayout[n1][n2] = "-Load- ";}
    }
    
    /***
     * This method is used to get the total hitmiss to be stored in the simulationdata at the
     * end of the simulation.
     * 
     * @return data för hantering i vyn.
     */
    public Object[] getHitMissAndInstrCount(){
        Object[] data = new Object[2];
        data[0] = hitmiss.getHitMiss();
        data[1] = this.instramount;
        return data;
    }
    
    public void addObserver (Observer obj){
    hitmiss.addObserver(obj);
    }
    
    public String[][] getCacheLayout(){
    return cachelayout;
    }
}
