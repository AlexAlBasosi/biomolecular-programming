package cell;

import java.util.HashMap;
import java.util.Iterator;

public class Nucleus {
    // These are the sizes of the 2-dimensional arrays. So it will be a NXM array.
    private int sizeN;
    private int sizeM;
    private int nucleusRadius;
    private int DNARadius;
    private int repairRadius;

    // Here we declare the 2D arrays but don't initialise them yet.
    private HashMap<Integer, Integer> DNACoordinates;

    private HashMap<Integer, Integer> repairProteinCoordinates;
    private int[][] alphaParticleCoordinates;

    public Nucleus(){
        this.sizeN = 1000;
        this.sizeM = 1000;
        this.nucleusRadius = 40;
        this.DNARadius = 5;
        this.repairRadius = 5;
        this.DNACoordinates = new HashMap<Integer, Integer>();
        this.repairProteinCoordinates = new HashMap<Integer, Integer>();
        this.alphaParticleCoordinates = new int[sizeN][sizeM];
    }

    public Nucleus(int sizeN, int sizeM){
        this.sizeN = sizeN;
        this.sizeM = sizeM;
        this.nucleusRadius = 40;
        this.DNARadius = 5;
        this.repairRadius = 5;
        this.DNACoordinates = new HashMap<Integer, Integer>();
        this.repairProteinCoordinates = new HashMap<Integer, Integer>();
        this.alphaParticleCoordinates = new int[sizeN][sizeM];
    }

    // This method sets the N dimension size of the 2D arrays. For example cell.setSizeN(10);
    public void setSizeN(int sizeN){
        this.sizeN = sizeN;
    }

    // This method sets the M dimension size of the 2D arrays. For example cell.setSizeM(10);
    public void setSizeM(int sizeM){
        this.sizeM = sizeM;
    }

    public void setNucleusRadius(int radius){
        this.nucleusRadius = radius;
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
    public void setDNACoordinates(int x, int y){
        this.DNACoordinates.put(x, y);
    }

    public void setRepairProteinCoordinates(int x, int y){
        this.repairProteinCoordinates.put(x, y);
    }
    public Iterator getDNACoordinatesIterator(){
        return this.DNACoordinates.entrySet().iterator();
    }

    public Iterator getRepairProteinCoordinatesIterator(){
        return this.repairProteinCoordinates.entrySet().iterator();
    }

    // Here we specify the Alpha Particle Coordinates of the  cell.
    // For example cell.setAlphaParticleCoordinates(10, 10, 1); This means we're storing 1 in the 10th row and
    // 10th column of the 2D array.
    public void setAlphaParticleCoordinates(int x, int y, int value){
        this.alphaParticleCoordinates[x][y] = value;
    }
}
