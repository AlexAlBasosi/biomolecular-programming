package cell;

import nano.Canvas;
import nano.Pen;

import java.awt.*;
import java.util.*;

public class Cells {

    private int borderSize;
    private int cellRadius;
    private int tumourRegionWidthMin;
    private int tumourRegionWidthMax;
    private int tumourRegionLengthMin;
    private int tumourRegionLengthMax;
    private int[][] alphaSourceParticles;
    private int alphaRegionRadius;
    private int alphaParticleRadius;
    private int alphaRegionX;
    private int alphaRegionY;


    public Cells(int alphaNum) {
        this.borderSize = 5;
        this.cellRadius = 50;
        this.tumourRegionWidthMin = 350;
        this.tumourRegionWidthMax = 1750;
        this.tumourRegionLengthMin = 300;
        this.tumourRegionLengthMax = 1000;
        this.alphaSourceParticles = new int[alphaNum][alphaNum];
        this.alphaRegionRadius = 40;
        this.alphaParticleRadius = 5;
    }

    public void getAlphaSourceParticles(){
        for(int i = 0; i < this.alphaSourceParticles[0].length; i++){
            System.out.println("Particle" + i + ":");
            System.out.println("Coordinates: (" + this.alphaSourceParticles[0][i] + "," + this.alphaSourceParticles[1][i] + ")");
            System.out.println("Speed on the X-axis: " + this.alphaSourceParticles[2][i] + "m/s");
            System.out.println("Speed on the Y-axis: " + this.alphaSourceParticles[3][i] + "m/s\n\n");
        }
    }

    public ArrayList<Cell> initialiseCells(int numberOfCells){
        ArrayList<Cell> cells = new ArrayList<>();
        for(int i = 0; i < numberOfCells; i++) {
            cells.add(new Cell(false, numberOfCells));
        }

        return cells;
    }

    public boolean isOverlapping(ArrayList<Cell> cells, int cellX, int cellY){
        for(Cell cell: cells){
            double distance = Math.sqrt(Math.pow(cell.getX() - cellX, 2) + Math.pow(cell.getY() - cellY, 2));
            if(distance < cell.getCellRadius() + 50){
                return true;
            }
        }
        return false;
    }

    public void generateCellCoordinates(int screenWidth, int screenHeight, ArrayList<Cell> cells, Random random){
        for(Cell cell: cells){
            int x,y;
            boolean isOverlapping;

            do{
                isOverlapping = false;
                x = random.nextInt(screenWidth - this.borderSize * this.cellRadius) + this.cellRadius;
                y = random.nextInt(screenHeight - this.borderSize * this.cellRadius) + cellRadius;

                if (isOverlapping(cells, x, y)) {
                    isOverlapping = true;
                }

            } while(isOverlapping);

            cell.setX(x);
            cell.setY(y);
        }
    }

    private boolean isWithinNucleus(Cell cell, int DNAX, int DNAY){
        double distance = Math.sqrt(Math.pow(DNAX - cell.getX(), 2) + Math.pow(DNAY - cell.getY(), 2));
        return distance + cell.getDNARadius() <= cell.getNucleusRadius();
    }

    public void generateDNAProteins(ArrayList<Cell> cells, int numberOfDNAParticles, Random random){
        for(Cell cell : cells) {
            for(int i = 0; i < numberOfDNAParticles; i++){
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

                cell.setDNACoordinates(i, DNAX, DNAY);
            }
        }
    }

    public void generateRepairProteins(ArrayList<Cell> cells, int numberOfRepairParticles, Random random){
        for(Cell cell : cells){
            for(int i = 0; i < numberOfRepairParticles; i++){
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

                cell.setRepairProteinCoordinates(i, repairX, repairY);
            }
        }
    }

    public void drawCells(Canvas screen, Pen pen, ArrayList<Cell> cells){
        for(Cell cell : cells){
            Color cellColor = cell.getIsCancerous() ? Color.RED : Color.WHITE;
            pen.drawCircle(cell.getX(), cell.getY(), cell.getCellRadius(), cellColor, false);
            pen.drawCircle(cell.getX(), cell.getY(), cell.getNucleusRadius(), cellColor, false);
            screen.update();
        }
    }

    public void drawDNAProteins(Canvas screen, Pen pen, ArrayList<Cell> cells){
        for(Cell cell : cells){
            Color DNAProteinColor = Color.BLUE;
            int[][] DNACoordinates = cell.getDNACoordinates();
            for(int i = 0; i < DNACoordinates[0].length; i++){
                int x = DNACoordinates[0][i];
                int y = DNACoordinates[1][i];
                pen.drawCircle(x, y, cell.getDNARadius(), DNAProteinColor, true);
                screen.update();
            }
        }
    }

    public void drawRepairProteins(Canvas screen, Pen pen, ArrayList<Cell> cells){
        for(Cell cell : cells){
            Color repairProteinColor = Color.GREEN;
            int[][] repairCoordinates = cell.getRepairProteinCoordinates();
            for(int i = 0; i < repairCoordinates[0].length; i++){
                int x = repairCoordinates[0][i];
                int y = repairCoordinates[1][i];
                pen.drawCircle(x, y, cell.getRepairRadius(), repairProteinColor, true);
                screen.update();
            }
        }
    }

    public void generateTumourRegion(ArrayList<Cell> cells){
        for(Cell cell : cells) {
            if((cell.getX() >= this.tumourRegionWidthMin && cell.getX() <= this.tumourRegionWidthMax) && (cell.getY() >= this.tumourRegionLengthMin && cell.getY() <= this.tumourRegionLengthMax)){
                cell.setIsCancerous(true);
            }
        }
    }

    public void generateAlphaRegion(ArrayList<Cell> cells, int screenWidth, int screenHeight, Random random){
        for(Cell cell : cells){
            int alphaX, alphaY;
            boolean isOverlapping;

            do{
                isOverlapping = false;
                alphaX = random.nextInt(screenWidth - this.borderSize * this.cellRadius) + this.cellRadius;
                alphaY = random.nextInt(screenHeight - this.borderSize * this.cellRadius) + cellRadius;

                if (isOverlapping(cells, alphaX, alphaY)) {
                    isOverlapping = true;
                }

            } while(isOverlapping);

            this.alphaRegionX = alphaX;
            this.alphaRegionY = alphaY;
        }
    }

    private boolean isWithinAlphaRegion(int alphaX, int alphaY){
        double distance = Math.sqrt(Math.pow(alphaX - this.alphaRegionX, 2) + Math.pow(alphaY - this.alphaRegionY, 2));
        return distance <= this.alphaRegionRadius;
    }

    public void generateAlphaCoordinates(int numberOfAlphaParticles, Random random){
        for(int i = 0; i < numberOfAlphaParticles; i++){
            int alphaX, alphaY;
            boolean iswithinAlphaRegion;

            do{
                iswithinAlphaRegion = false;
                alphaX = random.nextInt(this.alphaRegionX) + this.alphaRegionRadius;
                alphaY = random.nextInt(this.alphaRegionY) + this.alphaRegionRadius;

                if(isWithinAlphaRegion(alphaX, alphaY)){
                    iswithinAlphaRegion = true;
                }

            } while(!iswithinAlphaRegion);

            this.alphaSourceParticles[0][i] = alphaX;
            this.alphaSourceParticles[1][i] = alphaY;
        }
    }

    public void drawAlphaProteins(Canvas screen, Pen pen){
        Color alphaColor = Color.YELLOW;
        for(int i = 0; i < alphaSourceParticles[0].length; i++){
            int x = alphaSourceParticles[0][i];
            int y = alphaSourceParticles[1][i];
            pen.drawCircle(x, y, this.alphaParticleRadius, alphaColor, true);
            screen.update();
        }
    }

    public void generateAlphaSourceSpeeds(Random random){
        for(int i = 0; i < alphaSourceParticles[0].length; i++){
            this.alphaSourceParticles[2][i] = random.nextInt(11) - 5;
            this.alphaSourceParticles[3][i] = random.nextInt(11) - 5;
        }
    }
    public void generateAlphaParticleDiffusion(Canvas screen, Pen pen, ArrayList<Cell> cells, int numOfSteps){
        for(int i = 0; i < numOfSteps; i++){
            screen.clear();

            this.drawCells(screen, pen, cells);
            this.drawDNAProteins(screen, pen, cells);
            this.drawRepairProteins(screen, pen, cells);
            this.drawAlphaProteins(screen, pen);

            for(int j = 0; j < this.alphaSourceParticles[0].length; j++){
                this.alphaSourceParticles[0][j] += this.alphaSourceParticles[2][j];
                this.alphaSourceParticles[1][j] += this.alphaSourceParticles[3][j];
            }

            screen.update();
            screen.pause(5);
        }
    }
}
