import cell.Cell;
import java.util.ArrayList;
import nano.*;
import java.awt.Color;
import java.util.Random;

public class Main {

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
    public static void generateCellCoordinates(int screenWidth, int screenHeight, ArrayList<Cell> cells){
        Random random = new Random();
        int borderSize = 10;
        int cellRadius = 50;
        for(Cell cell: cells){
            int x,y;
            boolean overlapping;

            do{
                overlapping = false;
                x = random.nextInt(screenWidth - borderSize * cellRadius) + cellRadius;
                y = random.nextInt(screenHeight - borderSize * cellRadius) + cellRadius;

                if (isOverlapping(cells, x, y)) {
                    overlapping = true;
                }

            } while(overlapping);

            cell.setX(x);
            cell.setY(y);
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

    public static void main(String[] args) {
        ArrayList<Cell> cells = initialiseCells();

        int screenWidth = 2000;
        int screenHeight = 1300;

        Canvas screen = new Canvas(screenWidth, screenHeight, 0, 0);
        Pen pen = new Pen(screen);
        generateCellCoordinates(screenWidth, screenHeight, cells);


        drawCells(screen, pen, cells);
    }
}