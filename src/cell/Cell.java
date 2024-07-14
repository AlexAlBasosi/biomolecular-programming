package cell;

import java.util.Iterator;

// Declaring a Cell class to store information and methods related to the cell.
public class Cell {

    // Declaring attributes which store information about the cell.
    private boolean isCancerous;
    private int x;
    private int y;
    private int cellRadius;
    private Nucleus nucleus;

    // This is an overloaded constructor. When you create a Cell object, you can specify whether the cell
    // is cancerous, and the size of the 2D arrays. For example, Cell cell = new Cell(false, 10, 10);
    public Cell(boolean isCancerous, int numberOfCells){
        this.cellRadius = 50;
        this.x = 0;
        this.y = 0;
        this.isCancerous = isCancerous;
        this.nucleus = new Nucleus(numberOfCells);
    }

    public boolean getIsCancerous(){
        return this.isCancerous;
    }

    // After initialising the object, you can specify whether the cell is cancerous. For example, if you already have
    // a cell object, you can call this method by writing cell.setIsCancerous(true);
    public void setIsCancerous(boolean isCancerous){
        this.isCancerous = isCancerous;
    }

    public void setDNACoordinates(int columnNumber, int x, int y){
        this.nucleus.setDNACoordinates(columnNumber, x, y);
    }

    public int[][] getDNACoordinates(){
        return this.nucleus.getDNACoordinates();
    }

    public void setRepairProteinCoordinates(int columnNumber, int x, int y){
        this.nucleus.setRepairProteinCoordinates(columnNumber, x, y);
    }

    public int[][] getRepairProteinCoordinates(){
        return this.nucleus.getRepairProteinCoordinates();
    }

   public void setX(int x){
        this.x = x;
   }

   public void setY(int y){
        this.y = y;
   }

   public int getX(){
        return this.x;
   }

   public int getY(){
        return this.y;
   }

   public int getCellRadius(){
        return this.cellRadius;
   }

   public int getNucleusRadius(){
        return this.nucleus.getNucleusRadius();
   }

   public int getDNARadius(){
        return this.nucleus.getDNARadius();
   }

   public int getRepairRadius(){
        return this.nucleus.getRepairRadius();
   }
}
