import cell.Cell;
import java.util.ArrayList;

import cell.Cells;
import nano.*;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int screenWidth = 2000;
        int screenHeight = 1300;
        int DNANum = 10;
        int repairNum = 10;
        int alphaNum = 50;
        int numOfSteps = 1000;

        Cells cells = new Cells(alphaNum);
        ArrayList<Cell> cellsList = cells.initialiseCells();

        Canvas screen = new Canvas(screenWidth, screenHeight, 0, 0);
        Pen pen = new Pen(screen);
        Random random = new Random();

        cells.generateCellCoordinates(screenWidth, screenHeight, cellsList, random);
        cells.generateDNAProteins(cellsList, DNANum, random);
        cells.generateRepairProteins(cellsList, repairNum, random);
        cells.generateTumourRegion(cellsList);
        cells.generateAlphaRegion(cellsList, screenWidth, screenHeight, random);
        cells.generateAlphaCoordinates(alphaNum, random);
        cells.generateAlphaSourceSpeeds(random);

        cells.generateAlphaParticleDiffusion(screen, pen, cellsList, numOfSteps);

        /* TODO: Refactor Hashmaps into 2D Arrays
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