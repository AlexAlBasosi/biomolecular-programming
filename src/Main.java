import cell.Cell;
import java.util.ArrayList;

import cell.Cells;
import nano.*;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int screenWidth = 2000;
        int screenHeight = 1300;
        int numberOfCells = 10;
        int numberOfDNAParticles = 10;
        int numberOfRepairParticles = 10;
        int numberOfAlphaParticles = 50;
        int numOfSteps = 1000;

        Cells cells = new Cells(numberOfAlphaParticles);
        ArrayList<Cell> cellsList = cells.initialiseCells(numberOfCells);

        Canvas screen = new Canvas(screenWidth, screenHeight);
        Pen pen = new Pen(screen);
        Random random = new Random();

        cells.generateCellCoordinates(screenWidth, screenHeight, cellsList, random);
        cells.generateDNAProteins(cellsList, numberOfDNAParticles, random);
        cells.generateRepairProteins(cellsList, numberOfRepairParticles, random);
        cells.generateTumourRegion(cellsList);
        cells.generateAlphaRegion(cellsList, screenWidth, screenHeight, random);
        cells.generateAlphaCoordinates(numberOfAlphaParticles, random);
        cells.generateAlphaSourceSpeeds(random);

        cells.generateAlphaParticleDiffusion(screen, pen, cellsList, numOfSteps);

        /* TODO: 2D Arrays
            Row 0: Contains X Coordinates
            Row 1: Contains Y Coordinates
            Row 2: Contains X speeds
            Rows 3: Contains Y speeds
         */
        // TODO: Add logic to kill DNA particles when alpha particle is close to a cell
        // TODO: Add logic to bind repair protein to damaged DNA particles
        // TODO: Add logic to convert to 'necrotic cell' when DNA particle damage exceeds threshold
        // TODO: Add comments
    }
}