import cell.Cell;
import java.util.ArrayList;

import cell.Cells;
import nano.*;

import java.util.Random;

/* This is the Main class. Here we handle the creation of all the objects that will be
* used in the program.
* */

public class Main {
    public static void main(String[] args){

        // We initialise the screen width and height, as well as the number of
        // cells and other particles depicted in the simulation.
        int screenWidth = 2000;
        int screenHeight = 1300;
        int numberOfCells = 10;
        int numberOfDNAParticles = 20;
        int numberOfRepairParticles = 10;
        int numberOfAlphaParticles = 50;
        int numOfSteps = 1000;

        // First we initialise an object of the Cells class, which contains important methods
        // that contain the bulk of the application's logic. We pass in the
        // numberOfAlphaParticles as a parameter.
        Cells cells = new Cells(numberOfAlphaParticles);

        // Then we initialise an ArrayList of objects of the Cell class, which contain
        // information about the cell.
        ArrayList<Cell> cellsList = cells.initialiseCells(numberOfCells, numberOfDNAParticles, numberOfRepairParticles);

        // Here we initialise the canvas, passing in the screen width and height, create a
        // new pen, and then generate a Random object that we can use to generate a random
        // number.
        Canvas screen = new Canvas(screenWidth, screenHeight);
        Pen pen = new Pen(screen);
        Random random = new Random();


        // Here we call the methods that are stored in the Cells object. These method
        // include generating the cell coordinates, DNA proteins, repair proteins, the
        // tumour region, the region our alpha particles initially sit out of, the alpha
        // particles themselves, and the speeds of each alpha particle.
        cells.generateCellCoordinates(screenWidth, screenHeight, cellsList, random);
        cells.generateDNAProteins(cellsList, numberOfDNAParticles, random);
        cells.generateRepairProteins(cellsList, numberOfRepairParticles, random);
        cells.generateTumourRegion(cellsList);
        cells.generateAlphaRegion(cellsList, screenWidth, screenHeight, random);
        cells.generateAlphaCoordinates(numberOfAlphaParticles, random);
        cells.generateAlphaSourceSpeeds(random);


        // Finally, we run the canvas simulation, which is responsible for drawing all the
        // different components within the class and calling all the methods used in this
        // application.
        cells.runCanvasSimulation(screen, pen, cellsList, numOfSteps);

        // TODO: Update README
    }
}