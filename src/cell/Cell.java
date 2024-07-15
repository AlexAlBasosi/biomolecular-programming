package cell;

/* This is the Cell class, which is responsible for information and methods related to each
* individual cell. It contains an object of the Nucleus class, because each Cell contains a
* Nucleus.
* */

public class Cell {

    // Here we declare variable that are related to the cell, such as whether it's cancerous, its
    // coordinates, the radius of the cell, the Nucleus object, and whether the cell is damaged
    // or necrotic.
    private boolean isCancerous;
    private int x;
    private int y;
    private int cellRadius;
    private Nucleus nucleus;
    private boolean isDamaged;
    private boolean isNecrotic;

    // The Cell constructor is called whenever a cell is initialised. Here, we initialise the
    // variables, and create an instance of the Nucleus class. Notice that several variables are
    // passed into the constructor, such as isCancerous, numberOfCells, etc.
    //
    // isCancerous is passed in from the Cells class when they're initialised and set to false.
    // numberOfCells, numberOfDNAParticles, and numberOfRepairParticles are passed from the Main
    // class to the Cells class to this class, and then through to the Nucleus class. The Cell
    // class is responsible for managing and calling methods on the Nucleus class.

    public Cell(boolean isCancerous, int numberOfCells, int numberOfDNAParticles, int numberOfRepairParticles){
        this.cellRadius = 50;
        this.x = 0;
        this.y = 0;
        this.isCancerous = isCancerous;
        this.nucleus = new Nucleus(numberOfCells, numberOfDNAParticles, numberOfRepairParticles);
        this.isDamaged = false;
        this.isNecrotic = false;
    }

    // This method checks if the current cell is cancerous, and returns it.
    public boolean getIsCancerous(){
        return this.isCancerous;
    }

    // After initialising the object, you can specify whether the cell is cancerous. For example, if you already have
    // a cell object, you can call this method by writing cell.setIsCancerous(true);
    public void setIsCancerous(boolean isCancerous){
        this.isCancerous = isCancerous;
    }

    // This method sets the DNA coordinates by passing in the column number and x and
    // coordinates into the setDNACoordinates method which is called in the Nucleus class.
    public void setDNACoordinates(int columnNumber, int x, int y){
        this.nucleus.setDNACoordinates(columnNumber, x, y);
    }

    // This method returns the 2D array for the DNA particles stored in the Nucleus class.
    public int[][] getDNACoordinates(){
        return this.nucleus.getDNACoordinates();
    }

    // Here we set the x and y coordinates for a given repair particle.
    public void setRepairProteinCoordinates(int columnNumber, int x, int y){
        this.nucleus.setRepairProteinCoordinates(columnNumber, x, y);
    }

    // This method returns the 2D array for the repair proteins stored in the Nucleus class.
    public int[][] getRepairProteinCoordinates(){
        return this.nucleus.getRepairProteinCoordinates();
    }

    // Here, we set the X coordinate of this cell.
   public void setX(int x){
        this.x = x;
   }

   // Here, we set the Y coordinate of this cell.
   public void setY(int y){
        this.y = y;
   }

   // Here, we return the X coordinate of this cell.
   public int getX(){
        return this.x;
   }

   // Here, we return the Y coordinate of this cell.
   public int getY(){
        return this.y;
   }

   // Here, we return the cell's radius.
   public int getCellRadius(){
        return this.cellRadius;
   }

   // Here, we return the radius of the nucleus.
   public int getNucleusRadius(){
        return this.nucleus.getNucleusRadius();
   }

   // Here, we return the radius of the DNA particle.
   public int getDNARadius(){
        return this.nucleus.getDNARadius();
   }

   // Here, we return the radius of the repair particle.
   public int getRepairRadius(){
        return this.nucleus.getRepairRadius();
   }

   // This method calls the inflictDNADamage on the Nucleus class. It then sets the isDamaged
   // flag in this class to true.
   public void inflictDNADamage(){
        this.nucleus.inflictDNADamage();
        this.isDamaged = true;
   }

   // This method returns whether or not this cell is damaged.
   public boolean getIsDamaged(){
        return this.isDamaged;
   }

   // This method calls the repairDNAParticles method on the Nucleus.
   public void repairDNAParticles(){
        this.nucleus.repairDNAParticles();
    }

    // This method checks whether the DNA damage is fatal from the Nucleus class. It then sets
    // the isNecrotic flag on this class to true.
    public void isNectoticCheck(){
        if(this.nucleus.isDamageFatal()){
            this.isNecrotic = true;
        }
    }

    // This method returns whether this cell is necrotic.
    public boolean getIsNecrotic(){
        return this.isNecrotic;
    }
}
