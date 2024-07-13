import cell.Cell;
import java.util.ArrayList;

import cell.Cells;
import nano.*;
import java.util.Random;

import static cell.Cells.*;

public class Main {
    public static void main(String[] args) {
        Cells cells = new Cells();
        ArrayList<Cell> cellsList = cells.initialiseCells();

        int screenWidth = 2000;
        int screenHeight = 1300;
        int DNANum = 10;
        int repairNum = 10;
        int alphaNum = 10;

        Canvas screen = new Canvas(screenWidth, screenHeight, 0, 0);
        Pen pen = new Pen(screen);
        Random random = new Random();

        cells.generateCellCoordinates(screenWidth, screenHeight, cellsList, random);
        cells.generateDNAProteins(cellsList, DNANum, random);
        cells.generateRepairProteins(cellsList, repairNum, random);
        cells.generateTumourRegion(cellsList);
        cells.generateAlphaRegion(cellsList, screenWidth, screenHeight, random);
        cells.generateAlphaCoordinates(alphaNum, random);

        cells.drawCells(screen, pen, cellsList);
        cells.drawDNAProteins(screen, pen, cellsList);
        cells.drawRepairProteins(screen, pen, cellsList);
        cells.drawAlphaProteins(screen, pen);

        // TODO: Add alpha source
        // TODO: Add diffusion of alpha particles
        // TODO: Add logic to kill DNA particles when alpha particle is close to a cell
        // TODO: Add logic to bind repair protein to damaged DNA particles
        // TODO: Add logic to convert to 'necrotic cell' when DNA particle damage exceeds threshold
        // TODO: Add comments
    }
}