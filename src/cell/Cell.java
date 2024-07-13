package cell;

// Declaring a Cell class to store information and methods related to the cell.
public class Cell {

    // Declaring attributes which store information about the cell.
    private boolean isCancerous;

    // TODO: Add comments
    private Nucleus nucleus;

    // This is a default constructor, which assigns default values to the attributes if you don't
    // explicitly set them.
    public Cell(){
        this.isCancerous = false;
        this.nucleus = new Nucleus();
    }

    // This is an overloaded constructor. When you create a Cell object, you can specify whether the cell
    // is cancerous, and the size of the 2D arrays. For example, Cell cell = new Cell(false, 10, 10);
    public Cell(boolean isCancerous, int sizeN, int sizeM){
        this.isCancerous = isCancerous;
        this.nucleus = new Nucleus(sizeN, sizeM);
    }

    // After initialising the object, you can specify whether the cell is cancerous. For example, if you already have
    // a cell object, you can call this method by writing cell.setIsCancerous(true);
    public void setIsCancerous(boolean isCancerous){
        this.isCancerous = isCancerous;
    }

    public void setDNACoordinates(int x, int y, int value){
        this.nucleus.setDNACoordinates(x, y, value);
    }

    public void setAlphaParticleCoordinates(int x, int y, int value){
        this.nucleus.setAlphaParticleCoordinates(x, y, value);
    }

}
