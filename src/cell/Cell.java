package cell;

// Declaring a Cell class to store information and methods related to the cell.
public class Cell {

    // Declaring attributes which store information about the cell.
    private boolean isCancerous;

    // These are the sizes of the 2-dimensional arrays. So it will be a NXM array.
    private int sizeN;
    private int sizeM;

    // Here we declare the 2D arrays but don't initialise them yet.
    private int[][] DNACoordinates;
    private int[][] alphaParticleCoordinates;

    // This is a default constructor, which assigns default values to the attributes if you don't
    // explicitly set them.
    public Cell(){
        this.isCancerous = false;
        this.sizeN = 1000;
        this.sizeM = 1000;
        this.DNACoordinates = new int[sizeN][sizeM];
        this.alphaParticleCoordinates = new int[sizeN][sizeM];
    }

    // This is an overloaded constructor. When you create a Cell object, you can specify whether the cell
    // is cancerous, and the size of the 2D arrays. For example, Cell cell = new Cell(false, 10, 10);
    public Cell(boolean isCancerous, int sizeN, int sizeM){
        this.isCancerous = isCancerous;
        this.sizeN = sizeN;
        this.sizeM = sizeM;
    }

    // After initialising the object, you can specify whether the cell is cancerous. For example, if you already have
    // a cell object, you can call this method by writing cell.setIsCancerous(true);
    public void setIsCancerous(boolean isCancerous){
        this.isCancerous = isCancerous;
    }

    // This method sets the N dimension size of the 2D arrays. For example cell.setSizeN(10);
    public void setSizeN(int sizeN){
        this.sizeN = sizeN;
    }

    // This method sets the M dimension size of the 2D arrays. For example cell.setSizeM(10);
    public void setSizeM(int sizeM){
        this.sizeM = sizeM;
    }

    // Here we specify the DNA Coordinates of the cell. For example cell.setDNACoordinates(10, 10, 1);
    // This means we're storing 1 in the 10th row and 10th column of the 2D array.
    public void setDNACoordinates(int x, int y, int value){
        this.DNACoordinates[x][y] = value;
        //                          ^^^^^ value
        //                     ^^ column
        //                  ^^ row
    }

    // Here we specify the Alpha Particle Coordinates of the  cell.
    // For example cell.setAlphaParticleCoordinates(10, 10, 1); This means we're storing 1 in the 10th row and
    // 10th column of the 2D array.
    public void setAlphaParticleCoordinates(int x, int y, int value){
        this.alphaParticleCoordinates[x][y] = value;
    }
}
