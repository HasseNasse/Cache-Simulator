package model;

public class InvalidInstructionException extends Exception {
    private String adress;
    
    public InvalidInstructionException(String adress) {
        super("Not a valid adress, Input adress: " + adress
            + " is either not 4 byte long or is not divisible with 4");
        this.adress = adress;
    }
}
