package cell;

import java.util.ArrayList;
import java.util.List;

/* This is the Nucleus class. All the logic related to the DNA and repair particles are contained
* in this class. It also stores information about those particles.
* */

public class Nucleus {
    // Here we declare variables related to the nucleus, such as the number of rows in the DNA
    // and repair particle 2D arrays, the number of cells, the number of molecules within the
    // nucleus, as well as the radius of the nucleus and its particles.
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

    /* This is the structure of the 2-dimensional array for the DNA particles:
     * Row 0: X Coordinates
     * Row 1: Y Coordinates
     * Row 2: isDamaged (1 or 0)
     * */

    /* This is the structure of the 2-dimensional array for the repair particles:
     * row 0: X Coordinate
     * row 1: Y Coordinates
     * */

    // This constructor is called by the Cell constructor whenever a cell is initialised, as each
    // cell contains a corresponding nucleus. The information passed into the constructor via the
    // parameters is used to initialise some of the class's attributes, whereas some are set to
    // default values.
    //
    // The numberOfRowsDNA and numberOfDNAParticles are used to configure the size of the DNA 2D
    // array. Similarly, the numberOfRowsRepair and numberOfRepairParticles are used to configure
    // the size of the repair DNA array.
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

    // This method returns the nucleus's radius.
    public int getNucleusRadius(){
        return this.nucleusRadius;
    }

    // This method returns the DNA particle's radius.
    public int getDNARadius(){
        return this.DNARadius;
    }

    // This method returns the repair particle's radius.
    public int getRepairRadius(){
        return this.repairRadius;
    }

    // Here, we set the DNA particle at a particular column number's X and Y coordinates, which are stored in the 0th and 1st row, respectively.
    public void setDNACoordinates(int columnNumber, int x, int y){
        this.DNACoordinates[0][columnNumber] = x;
        this.DNACoordinates[1][columnNumber] = y;
    }

    // This method returns the DNA particle's 2D array.
    public int[][] getDNACoordinates(){
        return this.DNACoordinates;
    }

    // Similarly, this method sets the repair protein's coordinates.
    public void setRepairProteinCoordinates(int columnNumber, int x, int y){
        this.repairProteinCoordinates[0][columnNumber] = x;
        this.repairProteinCoordinates[1][columnNumber] = y;
    }

    // And this method returns the repair protein's 2D array.
    public int[][] getRepairProteinCoordinates(){
        return this.repairProteinCoordinates;
    }

    // This method inflicts damage on the first DNA particle it can find that's not damaged.
    public void inflictDNADamage(){
        // It loops through the DNA particles...
        for(int i = 0; i < this.DNACoordinates[0].length; i++){
            // and if the particle's isDamaged field is set to 0...
            if(this.DNACoordinates[2][i] == 0) {
                // set it to 1.
                this.DNACoordinates[2][i] = 1;
                // It then breaks out of the loop.
                break;
            }
        }
    }

    // This method returns the indices of all the DNA particles that aren't damaged.
    public ArrayList<Integer> getDamagedDNAIndices(){
        // It initialises a new ArrayList.
        ArrayList<Integer> damagedDNAIndices = new ArrayList<>();
        // It then loops through the DNA particles...
        for(int i = 0; i < this.DNACoordinates[0].length; i++){
            // and for each particle whose isDamaged field is set to 1...
            if(this.DNACoordinates[2][i] == 1){
                // it adds the index of that particle to the ArrayList.
                damagedDNAIndices.add(i);
            }
        }

        // It then returns that ArrayList.
        return damagedDNAIndices;
    }

    // This method returns the number of DNA particles that are damaged.
    public int getDamagedDNACount(){
        // First, it sets the count to 0.
        int damagedDNACount = 0;

        // It then loops through the DNA coordinates...
        for(int i = 0; i < this.DNACoordinates[0].length; i++){
            // and if the isDamaged field is set to 1...
            if(this.DNACoordinates[2][i] == 1){
                // it increments the count by 1.
                damagedDNACount++;
            }
        }

        // It then returns the count.
        return damagedDNACount;
    }

    // This method repairs the DNA particles. NOTE: It's not currently functioning.
    public void repairDNAParticles(){
        // First, an ArrayList of the damaged DNA indices is initialised, which it retrieves by
        // calling the getDamagedDNAIndices method.
        ArrayList<Integer> damagedDNAIndices = this.getDamagedDNAIndices();
        // It then determines the count using the size method on that list. The size method is
        // equivalent to how many items are in that list.
        int damagedDNACount = damagedDNAIndices.size();
        // It retrieves the number of repair particles from the class's attribute.
        int repairParticleCount = this.numberOfRepairParticles;

        // If the number of repair particles is greater than the number of damaged DNA particles
        // ...
        if(repairParticleCount >= damagedDNACount){
            // Then for each of its indices...
            for(int i = 0; i < damagedDNAIndices.size(); i++){
                // set the isDamaged field back to 0.
                this.DNACoordinates[2][i] = 0;
            }
            // Otherwise, if the number of repair particles is less than the number of damaged
            // DNA particles...
        } else if(repairParticleCount < damagedDNACount){
            // Create a subset of the damaged DNA particle list, the size of which is determined
            // by the number of repair particles.
            //
            // For example, if the indices are [3, 5, 7, 8] and there are two repair particles,
            // the new list will be [3, 5].
            List<Integer> slicedDamagedDNAIndices = damagedDNAIndices.subList(0, repairParticleCount);
            // For each DNA particle in that sublist...
            for(int i = 0; i < slicedDamagedDNAIndices.size(); i++){
                // Set the isDamaged field on that particle to 0.
                this.DNACoordinates[2][i] = 0;
            }
        }
    }

    // This method prints all the DNA particles of that cell, and whether they are damaged or not. It's for debugging purposes.
    public void getDNAParticles(){
        for(int i = 0; i < this.DNACoordinates[0].length; i++){
            System.out.println("Protein " + i + ":");
            System.out.println("isDamaged: " + this.DNACoordinates[2][i] + "\n\n");
        }
    }

    // This method checks whether the DNA damage for this cell is fatal - that is, if more than
    // half its DNA particles are damaged.
    public boolean isDamageFatal(){
        // First, we get the count of damaged DNA particles by calling the getDamagedDNACount
        // method on this class.
        int damagedDNACount = this.getDamagedDNACount();
        // If that count is greater than half the total number of DNA particles...
        if(damagedDNACount >= this.numberOfDNAParticles / 2){
            // we return true.
            return true;
        }

        // Otherwise, we return false.
        return false;
    }
}
