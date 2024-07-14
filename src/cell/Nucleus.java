package cell;

import java.util.HashMap;
import java.util.Iterator;

public class Nucleus {
    // These are the sizes of the 2-dimensional arrays. So it will be a NXM array.
    private int numberOfRows;
    private int numberOfCells;
    private int nucleusRadius;
    private int DNARadius;
    private int repairRadius;

    // Here we declare the 2D arrays but don't initialise them yet.
    private int[][] DNACoordinates;

    private int[][] repairProteinCoordinates;

    public Nucleus(int numberOfCells){
        this.numberOfRows = 2;
        this.numberOfCells = numberOfCells;
        this.nucleusRadius = 40;
        this.DNARadius = 5;
        this.repairRadius = 5;
        this.DNACoordinates = new int[this.numberOfRows][this.numberOfCells];
        this.repairProteinCoordinates = new int[this.numberOfRows][this.numberOfCells];
    }

    public int getNucleusRadius(){
        return this.nucleusRadius;
    }

    public int getDNARadius(){
        return this.DNARadius;
    }

    public int getRepairRadius(){
        return this.repairRadius;
    }


    // Here we specify the DNA Coordinates of the cell. For example cell.setDNACoordinates(10, 10, 1);
    // This means we're storing 1 in the 10th row and 10th column of the 2D array.
    public void setDNACoordinates(int columnNumber, int x, int y){
        this.DNACoordinates[0][columnNumber] = x;
        this.DNACoordinates[1][columnNumber] = y;
    }

    public int[][] getDNACoordinates(){
        return this.DNACoordinates;
    }

    public void setRepairProteinCoordinates(int columnNumber, int x, int y){
        this.repairProteinCoordinates[0][columnNumber] = x;
        this.repairProteinCoordinates[1][columnNumber] = y;
    }

    public int[][] getRepairProteinCoordinates(){
        return this.repairProteinCoordinates;
    }
}
