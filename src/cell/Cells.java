package cell;

import nano.Canvas;
import nano.Pen;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class Cells {
    public static ArrayList<Cell> initialiseCells(){
        int numberOfCells = 10;
        ArrayList<Cell> cells = new ArrayList<Cell>();
        for(int i = 0; i < numberOfCells; i++) {
            cells.add(new Cell(false, 10, 10));
        }

        return cells;
    }

    public static boolean isOverlapping(ArrayList<Cell> cells, int cellX, int cellY){
        for(Cell cell: cells){
            double distance = Math.sqrt(Math.pow(cell.getX() - cellX, 2) + Math.pow(cell.getY() - cellY, 2));
            if(distance < cell.getCellRadius() + 50){
                return true;
            }
        }
        return false;
    }

    public static void generateCellCoordinates(int screenWidth, int screenHeight, ArrayList<Cell> cells, Random random){
        int borderSize = 10;
        int cellRadius = 50;
        for(Cell cell: cells){
            int x,y;
            boolean isOverlapping;

            do{
                isOverlapping = false;
                x = random.nextInt(screenWidth - borderSize * cellRadius) + cellRadius;
                y = random.nextInt(screenHeight - borderSize * cellRadius) + cellRadius;

                if (isOverlapping(cells, x, y)) {
                    isOverlapping = true;
                }

            } while(isOverlapping);

            cell.setX(x);
            cell.setY(y);
        }
    }

    private static boolean isWithinNucleus(Cell cell, int DNAX, int DNAY){
        double distance = Math.sqrt(Math.pow(DNAX - cell.getX(), 2) + Math.pow(DNAY - cell.getY(), 2));
        return distance + cell.getDNARadius() <= cell.getNucleusRadius();
    }

    public static void generateDNAProteins(ArrayList<Cell> cells, int DNANum, Random random){
        for(Cell cell : cells) {
            for(int i = 0; i < DNANum; i++){
                int DNAX, DNAY;
                boolean isWithinNucleus;

                do{
                    isWithinNucleus = false;
                    DNAX = random.nextInt(cell.getX()) + cell.getNucleusRadius();
                    DNAY = random.nextInt(cell.getY()) + cell.getNucleusRadius();

                    if(isWithinNucleus(cell, DNAX, DNAY)){
                        isWithinNucleus = true;
                    }

                } while(!isWithinNucleus);

                cell.setDNACoordinates(DNAX, DNAY);
            }
        }
    }

    public static void generateRepairProteins(ArrayList<Cell> cells, int repairNum, Random random){
        for(Cell cell : cells){
            for(int i = 0; i < repairNum; i++){
                int repairX, repairY;
                boolean isWithinNucleus;

                do{
                    isWithinNucleus = false;
                    repairX = random.nextInt(cell.getX()) + cell.getNucleusRadius();
                    repairY = random.nextInt(cell.getY()) + cell.getNucleusRadius();

                    if(isWithinNucleus(cell, repairX, repairY)){
                        isWithinNucleus = true;
                    }

                } while(!isWithinNucleus);

                cell.setRepairProteinCoordinates(repairX, repairY);
            }
        }
    }

    public static void drawCells(Canvas screen, Pen pen, ArrayList<Cell> cells){
        for(Cell cell : cells){
            Color cellColor = cell.getIsCancerous() ? Color.RED : Color.WHITE;
            pen.drawCircle(cell.getX(), cell.getY(), cell.getCellRadius(), cellColor, false);
            pen.drawCircle(cell.getX(), cell.getY(), cell.getNucleusRadius(), cellColor, false);
            screen.update();
        }
    }

    public static void drawDNAProteins(Canvas screen, Pen pen, ArrayList<Cell> cells){
        for(Cell cell : cells){
            Color DNAProteinColor = Color.BLUE;
            Iterator DNAIterator = cell.getDNACoordinatesIterator();
            while(DNAIterator.hasNext()){
                Map.Entry coordinates = (Map.Entry)DNAIterator.next();
                int x = (int)coordinates.getKey();
                int y = (int)coordinates.getValue();
                pen.drawCircle(x, y, cell.getDNARadius(), DNAProteinColor, true);
                screen.update();
            }
        }
    }

    public static void drawRepairProteins(Canvas screen, Pen pen, ArrayList<Cell> cells){
        for(Cell cell : cells){
            Color repairProteinColor = Color.GREEN;
            Iterator repairIterator = cell.getRepairProteinCoordinatesIterator();
            while(repairIterator.hasNext()){
                Map.Entry coordinates = (Map.Entry)repairIterator.next();
                int x = (int)coordinates.getKey();
                int y = (int)coordinates.getValue();
                pen.drawCircle(x, y, cell.getRepairRadius(), repairProteinColor, true);
                screen.update();
            }
        }
    }
}
