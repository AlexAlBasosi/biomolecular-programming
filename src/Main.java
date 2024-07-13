import cell.Cell;
import java.util.ArrayList;
import nano.*;
import java.util.Random;

import static cell.Cells.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Cell> cells = initialiseCells();

        int screenWidth = 2000;
        int screenHeight = 1300;
        int DNANum = 10;
        int repairNum = 10;

        Canvas screen = new Canvas(screenWidth, screenHeight, 0, 0);
        Pen pen = new Pen(screen);
        Random random = new Random();

        generateCellCoordinates(screenWidth, screenHeight, cells, random);
        generateDNAProteins(cells, DNANum, random);
        generateRepairProteins(cells, repairNum, random);
        generateTumourRegion(cells);

        drawCells(screen, pen, cells);
        drawDNAProteins(screen, pen, cells);
        drawRepairProteins(screen, pen, cells);
    }
}