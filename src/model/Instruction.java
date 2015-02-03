package model;

public class Instruction {
private boolean loadinstruction = false;
private boolean storeinstruction = false;
private int adress;
    
    public Instruction(String instruction, int adress){
        this.adress = adress;
        
        if(instruction.equals("load")) this.loadinstruction = true;
        else if(instruction.equals("store")) this.storeinstruction = true;
    }    
}
