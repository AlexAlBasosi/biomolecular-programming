package cell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Nucleus {
    // These are the sizes of the 2-dimensional arrays. So it will be a NXM array.
    private int numberOfRowsDNA;
    private int numberOfRowsRepair;
    private int numberOfCells;
    private int numberOfDNAParticles;
    private int numberOfRepairParticles;
    private int nucleusRadius;
    private int DNARadius;
    private int repairRadius;

    // Here we declare the 2D arrays but don't initialise them yet.
    private int[][] DNACoordinates;

    private int[][] repairProteinCoordinates;

    public Nucleus(int numberOfCells, int numberOfDNAParticles, int numberOfRepairParticles){
        this.numberOfRowsDNA = 3;
        this.numberOfRowsRepair = 5;
        this.numberOfCells = numberOfCells;
        this.numberOfDNAParticles = numberOfDNAParticles;
        this.numberOfRepairParticles = numberOfRepairParticles;
        this.nucleusRadius = 40;
        this.DNARadius = 5;
        this.repairRadius = 5;
        this.DNACoordinates = new int[this.numberOfRowsDNA][this.numberOfDNAParticles];
        this.repairProteinCoordinates = new int[this.numberOfRowsRepair][this.numberOfRepairParticles];
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

    public void inflictDNADamage(){
        for(int i = 0; i < this.DNACoordinates[0].length; i++){
            if(this.DNACoordinates[2][i] == 0) {
                this.DNACoordinates[2][i] = 1;
                break;
            }
        }
    }

    public ArrayList<Integer> getDamagedDNAIndices(){
        ArrayList<Integer> damagedDNAIndices = new ArrayList<>();
        for(int i = 0; i < this.DNACoordinates[0].length; i++){
            if(this.DNACoordinates[2][i] == 1){
                damagedDNAIndices.add(i);
            }
        }

        return damagedDNAIndices;
    }

    public int getDamagedDNACount(){
        int damagedDNACount = 0;
        for(int i = 0; i < this.DNACoordinates[0].length; i++){
            if(this.DNACoordinates[2][i] == 1){
                damagedDNACount++;
            }
        }

        return damagedDNACount;
    }

    public int getRepairParticleCount(){
        int repairParticleCount = 0;
        for(int i = 0; i < this.repairProteinCoordinates[0].length; i++){
            repairParticleCount++;
        }

        return repairParticleCount;
    }

    public void repairDNAParticles(){
        ArrayList<Integer> damagedDNAIndices = this.getDamagedDNAIndices();
        int damagedDNACount = damagedDNAIndices.size();
        int repairParticleCount = this.getRepairParticleCount();

        if(repairParticleCount >= damagedDNACount){
            for(int i = 0; i < damagedDNAIndices.size(); i++){
                this.DNACoordinates[2][i] = 0;
            }
        } else if(repairParticleCount < damagedDNACount){
            List<Integer> slicedDamagedDNAIndices = damagedDNAIndices.subList(0, repairParticleCount);
            for(int i = 0; i < slicedDamagedDNAIndices.size(); i++){
                this.DNACoordinates[2][i] = 0;
                break;
            }
        }
    }

    public void getDNAParticles(){
        for(int i = 0; i < this.DNACoordinates[0].length; i++){
            System.out.println("Protein " + i + ":");
            System.out.println("isDamaged: " + this.DNACoordinates[2][i] + "\n\n");
        }
    }

    public boolean isDamageFatal(){
        int damagedDNACount = this.getDamagedDNACount();
        if(damagedDNACount >= this.numberOfDNAParticles / 2){
            return true;
        }

        return false;
    }
}
