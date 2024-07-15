package cell;

import nano.Canvas;
import nano.Pen;

import java.awt.*;
import java.util.*;

/* This is the Cells class. This class holds all the important methods that are used to generate
* and draw all the components of the application, as well as call methods from the Cell class
* itself. This class also stores the alpha source particles.
* */

public class Cells {

    // Here we declare variables that are used throughout the application. These properties are
    // used by the various methods in the application.

    private int borderSize;
    private int cellRadius;
    private int tumourRegionWidthMin;
    private int tumourRegionWidthMax;
    private int tumourRegionLengthMin;
    private int tumourRegionLengthMax;
    private int numberOfAlphaRows;
    private int[][] alphaSourceParticles;
    private int alphaRegionRadius;
    private int alphaParticleRadius;
    private int alphaRegionX;
    private int alphaRegionY;

    /*  This is the structure of the 2-dimensional array for the alpha source particles:
     *    Row 0: X Coordinates
     *    Row 1: Y Coordinates
     *    Row 2: X Speeds
     *    Row 3: Y Speeds
     *    */

    // We initialise it as new int[numberOfAlphaRows][numberOfAlphaParticles]. This means we want
    // an NXM table, where n is the number of rows (4) and m is the number of alpha particles.
    // That way, we can access the information by iterating through each column of the table.

    // For example, say we have the following table:
    // particle 0 | particle 1 | particle 2 ...
    //     0      |      1     |     2
    //     0      |      1     |     2
    //     3      |      4     |     5
    //     3      |      3     |     3
    //
    // This means that for particle 0, it is stored at point (0,0) on the screen, going at an X
    // speed of 3 and a Y speed of 3. To access information about particle 1, we would do this:
    //
    // alphaX = alphaSourceParticles[0][0]
    // alphaY = alphaSourceParticles[1][0]
    // xSpeed = alphaSourceParticles[2][0]
    // ySpeed = alphaSourceParticles[3][0]
    //
    // So we would loop through the rows while keeping the column consistent, as we're accessing
    // the particle at the 0th column.

    // This constructor is called whenever the class is initialised. Here, we initialise the
    // variables, as well as create a 2-dimensional array to store the alpha particles.
    public Cells(int numberOfAlphaParticles) {
        this.borderSize = 5;
        this.cellRadius = 50;
        this.tumourRegionWidthMin = 350;
        this.tumourRegionWidthMax = 1750;
        this.tumourRegionLengthMin = 300;
        this.tumourRegionLengthMax = 1000;
        this.numberOfAlphaRows = 4;
        this.alphaSourceParticles = new int[this.numberOfAlphaRows][numberOfAlphaParticles];
        this.alphaRegionRadius = 40;
        this.alphaParticleRadius = 5;
    }

    // This method prints information about the alpha source particles for debugging purposes. It isn't actually called in the application.
    public void getAlphaSourceParticles(){
        for(int i = 0; i < this.alphaSourceParticles[0][i]; i++){
            System.out.println("Particle" + i + ":");
            System.out.println("Coordinates: (" + this.alphaSourceParticles + "," + this.alphaSourceParticles[1][i] + ")");
            System.out.println("Speed on the X-axis: " + this.alphaSourceParticles[2][i] + "m/s");
            System.out.println("Speed on the Y-axis: " + this.alphaSourceParticles[3][i] + "m/s\n\n");
        }
    }


    // This is the first method that we call. This takes in a list of the cells from the Main method, and initialises them with the parameters we created in the main method.
    public ArrayList<Cell> initialiseCells(int numberOfCells, int numberOfDNAParticles, int numberOfRepairParticles){
        ArrayList<Cell> cells = new ArrayList<>();
        // It loops through the list, and creates each cell.
        for(int i = 0; i < numberOfCells; i++) {
            cells.add(new Cell(false, numberOfCells, numberOfDNAParticles, numberOfRepairParticles));
        }

        // and then returns the cells.
        return cells;
    }

    // This method checks if the cells are overlapping. It does that by calculating the euclidean distance between the cells' coordinates.
    public boolean isOverlapping(ArrayList<Cell> cells, int cellX, int cellY){
        for(Cell cell: cells){
            double distance = Math.sqrt(Math.pow(cell.getX() - cellX, 2) + Math.pow(cell.getY() - cellY, 2));

            // If that distance is less than the euclidean distance (+ the radius), it returns true.
            if(distance < cell.getCellRadius() + 50){
                return true;
            }
        }

        // Otherwise it returns false.
        return false;
    }

    // This method generates the coordinates of all the cells that were created, taking in the screen height, width, and list of cells from the Main class.
    public void generateCellCoordinates(int screenWidth, int screenHeight, ArrayList<Cell> cells, Random random){

        // First, it loops through the cells...
        for(Cell cell: cells){
            int x,y;
            boolean isOverlapping;

            do{
                // And sets the isOverlapping flag to false.
                isOverlapping = false;

                // It then generates to random coordinates.
                x = random.nextInt(screenWidth - this.borderSize * this.cellRadius) + this.cellRadius;
                y = random.nextInt(screenHeight - this.borderSize * this.cellRadius) + cellRadius;

                // If those coordinates overlap with any existing cell, isOverlapping is set to
                // true...
                if (isOverlapping(cells, x, y)) {
                    isOverlapping = true;
                }

                // and that triggers the loop, which constantly regenerates new coordinates and
                // checks that it overlaps, until it doesn't.
            } while(isOverlapping);

            // It then stores those coordinates in the cell.
            cell.setX(x);
            cell.setY(y);
        }
    }

    // This method checks that the particles are within the cell's nucleus.
    private boolean isWithinNucleus(Cell cell, int DNAX, int DNAY){
        // It does so by comparing the euclidean distance of the particle and the cell's nucleus.
        double distance = Math.sqrt(Math.pow(DNAX - cell.getX(), 2) + Math.pow(DNAY - cell.getY(), 2));
        // If that distance is less than the nucleus's radius, then it will return true.
        return distance + cell.getDNARadius() <= cell.getNucleusRadius();
    }


    // Here, we generate the coordinates of the DNA particles within the nucleus.
    public void generateDNAProteins(ArrayList<Cell> cells, int numberOfDNAParticles, Random random){
        for(Cell cell : cells) {
            for(int i = 0; i < numberOfDNAParticles; i++){
                int DNAX, DNAY;
                boolean isWithinNucleus;

                do{
                    isWithinNucleus = false;

                    // It generates two random coordinates...
                    DNAX = random.nextInt(cell.getX()) + cell.getNucleusRadius();
                    DNAY = random.nextInt(cell.getY()) + cell.getNucleusRadius();

                    // and checks if they fall within the nucleus.
                    if(isWithinNucleus(cell, DNAX, DNAY)){
                        isWithinNucleus = true;
                    }

                    // If they don't, the loop will trigger and keep generating coordinates until
                    // it does.
                } while(!isWithinNucleus);

                // It then calls a method on the Cell object, passing in those coordinates as well
                // as the column number of the particle, to know where to store it in the 2D array.
                cell.setDNACoordinates(i, DNAX, DNAY);
            }
        }
    }

    // Similar to the DNA particles, the same is done with the repair proteins within the nucleus.
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


    // This method is responsible for drawing the cells onto the canvas.
    public void drawCells(Canvas screen, Pen pen, ArrayList<Cell> cells){

        // For each cell...
        for(Cell cell : cells){
            Color cellColor;

            // We first check if the cell is cancerous or necrotic. If it's cancerous AND necrotic, it will set the color to be grey.
            // If the cell is cancerous, it will be set to red.
            // If the cell is necrotic, it will be set to gray.
            // Otherwise, the cell is healthy and is set to white.
            if(cell.getIsCancerous() && cell.getIsNecrotic()){
                cellColor = Color.GRAY;
            } else if(cell.getIsCancerous()){
                cellColor = Color.RED;
            } else if(cell.getIsNecrotic()){
                cellColor = Color.GRAY;
            } else {
                cellColor = Color.WHITE;
            }

            // It then draws the circle, using the coordinates stored in the cell object, the color that we set, as well as false - meaning that the circle isn't filled.
            pen.drawCircle(cell.getX(), cell.getY(), cell.getCellRadius(), cellColor, false);
            pen.drawCircle(cell.getX(), cell.getY(), cell.getNucleusRadius(), cellColor, false);

            // It then updates the screen.
            screen.update();
        }
    }

    // This method draws the DNA proteins within the nucleus.
    public void drawDNAProteins(Canvas screen, Pen pen, ArrayList<Cell> cells){
        for(Cell cell : cells){
            // For each cell...
            Color DNAProteinColor;
            // We get the 2D array for the DNA coordinates from the Cell object.
            int[][] DNACoordinates = cell.getDNACoordinates();
            // Then we loop through the columns of that array.
            for(int i = 0; i < DNACoordinates[0].length; i++){
                // We get the x and y coordinates from 0th and 1st row, respectively.
                int x = DNACoordinates[0][i];
                int y = DNACoordinates[1][i];
                // The third row contains whether that protein is damaged. It's 1 if damaged and 0
                // otherwise.
                // This ternary operator is a shorthand for an if statement. This the format:
                // variable = condition ? then this : otherwise this.
                // So the color will be red if the protein is damaged, otherwise blue.
                DNAProteinColor = DNACoordinates[2][i] == 1 ? Color.RED : Color.BLUE;
                // It is then drawn with the information we retrieved.
                pen.drawCircle(x, y, cell.getDNARadius(), DNAProteinColor, true);
                // And the screen is updated.
                screen.update();
            }
        }
    }

    // This is similar to DNA proteins, but for the repair proteins.
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

    // Here, we're generating a tumour region. All cells within this region will be cancerous. We
    // do that by hardcoding the min and max values for the width and height. And then if both the
    // X value falls between the min and max for the width, and the Y value falls between the min
    // and max for the height, then that cell is cancerous.
    public void generateTumourRegion(ArrayList<Cell> cells){
        for(Cell cell : cells) {
            if((cell.getX() >= this.tumourRegionWidthMin && cell.getX() <= this.tumourRegionWidthMax) && (cell.getY() >= this.tumourRegionLengthMin && cell.getY() <= this.tumourRegionLengthMax)){
                // This is set as a flag on the cell object itself.
                cell.setIsCancerous(true);
            }
        }
    }

    // Here, we generate a region for the alpha source particles to originally sit out of until
    // they are dispersed. We do that in a similar way to how we determined the region of the
    // particles within the nucleus of the cell.
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

    // This checks if the alpha particles sit within the alpha region we generated, using the
    // euclidean distance.
    private boolean isWithinAlphaRegion(int alphaX, int alphaY){
        double distance = Math.sqrt(Math.pow(alphaX - this.alphaRegionX, 2) + Math.pow(alphaY - this.alphaRegionY, 2));
        return distance <= this.alphaRegionRadius;
    }

    // Here we generate the alpha coordinates of the alpha source particles, by checking that they
    // fall within the region, and looping through if it does not.
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

            // Then we store those coordinates in the 0th and 1st rows of the 2D array for each
            // column the particle's information is stored in.
            this.alphaSourceParticles[0][i] = alphaX;
            this.alphaSourceParticles[1][i] = alphaY;
        }
    }

    // This method draws the alpha proteins onto the screen.
    public void drawAlphaProteins(Canvas screen, Pen pen){
        // It sets the colour to yellow.
        Color alphaColor = Color.YELLOW;
        for(int i = 0; i < alphaSourceParticles[0].length; i++){
            // Gets the x and y coordinates from the 2d array.
            int x = alphaSourceParticles[0][i];
            int y = alphaSourceParticles[1][i];

            // This condition checks if the coordinates of that particle is (0,0). The reason for
            // that is that you cannot remove elements of an array. So if the particle disappears,
            // we set it to (0,0), and it won't be drawn again when the screen is updated.
            if(!(x == 0 && y == 0)){
                pen.drawCircle(x, y, this.alphaParticleRadius, alphaColor, true);
                screen.update();
            }
        }
    }

    // This method generates the alpha speeds for the alpha source particles. Since the screen
    // regularly updates, we can envision a particle moving by changing its X and Y positions. So
    // if a particle is at (0,0), then it will be at (1,1), and so on...
    public void generateAlphaSourceSpeeds(Random random){
        for(int i = 0; i < alphaSourceParticles[0].length; i++){
            // So what we do here is for each particle, we randomly generate X and Y speeds
            // between -5 and 5. That means it can go in any direction. We then store those speeds
            // in the 2nd and 3rd row of the array, respectively.
            this.alphaSourceParticles[2][i] = random.nextInt(11) - 5;
            this.alphaSourceParticles[3][i] = random.nextInt(11) - 5;
        }
    }

    // This method generates the particle diffusion on the screen. So it will increment the X and
    // Y coordinates of the particle by the X and Y speeds. If it's at (0,0) and the X and Y
    // speeds are 1 and 1, then the new position will be (1,1). Similarly if it's at (-1,-1) and
    // the speeds are 1 and 1, we'll add 1 and 1 to -1 and -1 and it will be (0,0), then (1, 1),
    // then (2, 2), etc.
    public void generateAlphaParticleDiffusion(){
        for(int i = 0; i < this.alphaSourceParticles[0].length; i++){
            // x += y is shorthand for x = x + y. So we're adding x to a new value, y, and then
            // storing it in x. Here we're saying the X coordinate = X coordinate + speed, etc...
            this.alphaSourceParticles[0][i] += this.alphaSourceParticles[2][i];
            this.alphaSourceParticles[1][i] += this.alphaSourceParticles[3][i];
        }
    }

    // This method checks if the alpha particle is 10 pixels away from a cell, using the euclidean
    // distance. If it is, it will return true. Otherwise it returns false.
    public boolean isAlphaParticleInProximityToCell(Cell cell, int alphaX, int alphaY){
        double distance = Math.sqrt(Math.pow(alphaX - cell.getX(), 2) + Math.pow(alphaY - cell.getY(), 2));

        return distance >= (cell.getCellRadius() - 10) && distance <= (cell.getCellRadius() + 10);
    }

    // This method removes the alpha particle by setting all its information to 0.
    public void removeStoppedAlphaParticle(int columnNumber){
        // We take in the column number, then for each row, at that column number, set it to 0.
        // This sets the X and Y coordinates, as well as the X and Y speeds, for a particular
        // particle, to 0. Thus removing it.
        for(int i = 0; i < 4; i++){
            this.alphaSourceParticles[i][columnNumber] = 0;
        }
    }

    // This method reduces the particle's speed. There are many conditions that it has to check, but here's the summary:
    // If X > 0 and Y = 0, decrement X by 1.
    // If X = 0 and Y > 0, decrement Y by 1.
    // If X < 0 and Y = 0, increment X by 1.
    // If X = 0 and Y < 0, increment Y by 1.
    // If X > 0 and Y > 0, decrement X and Y by 1.
    // If X < 0 and Y < 0, increment X and Y by 1.
    // If X < 0 and Y > 0, increment X by 1 and decrement Y by 1.
    // If X > 0 and Y < 0, decrement X by 1 and increment Y by 1.
    // Otherwise X and Y are 0, so remove the particle.
    public void reduceAlphaParticleSpeed(int columnNumber){
        if(this.alphaSourceParticles[2][columnNumber] > 0 && this.alphaSourceParticles[3][columnNumber] == 0){
            this.alphaSourceParticles[2][columnNumber]--;
        } else if(this.alphaSourceParticles[2][columnNumber] == 0 && this.alphaSourceParticles[3][columnNumber] > 0){
            this.alphaSourceParticles[3][columnNumber]--;
        } else if(this.alphaSourceParticles[2][columnNumber] < 0 && this.alphaSourceParticles[3][columnNumber] == 0){
            this.alphaSourceParticles[2][columnNumber]++;
        } else if(this.alphaSourceParticles[2][columnNumber] == 0 && this.alphaSourceParticles[3][columnNumber] < 0){
            this.alphaSourceParticles[3][columnNumber]++;
        } else if(this.alphaSourceParticles[2][columnNumber] > 0 && this.alphaSourceParticles[3][columnNumber] > 0){
            this.alphaSourceParticles[2][columnNumber]--;
            this.alphaSourceParticles[3][columnNumber]--;
        } else if(this.alphaSourceParticles[2][columnNumber] < 0 && this.alphaSourceParticles[3][columnNumber] < 0){
            this.alphaSourceParticles[2][columnNumber]++;
            this.alphaSourceParticles[3][columnNumber]++;
        } else if(this.alphaSourceParticles[2][columnNumber] < 0 && this.alphaSourceParticles[3][columnNumber] > 0){
            this.alphaSourceParticles[2][columnNumber]++;
            this.alphaSourceParticles[3][columnNumber]--;
        } else if(this.alphaSourceParticles[2][columnNumber] > 0 && this.alphaSourceParticles[3][columnNumber] < 0){
            this.alphaSourceParticles[2][columnNumber]--;
            this.alphaSourceParticles[3][columnNumber]++;
        } else {
            this.removeStoppedAlphaParticle(columnNumber);
        }
    }

    // This method inflicts damage on the DNA molecules if the alpha particle is in proxmity.
    public void inflictCellDamage(ArrayList<Cell> cells){
        // For each particle...
        for(int i = 0; i < this.alphaSourceParticles[0].length; i++){
            // and for each cell...
            for(Cell cell : cells){
                // Check if that particle is in proximity to the cell.
                if(isAlphaParticleInProximityToCell(cell, this.alphaSourceParticles[0][i], this.alphaSourceParticles[1][i])){
                    // If it is, call the inflictDNADamage method on the Cell object.
                    cell.inflictDNADamage();
                    // And reduce that particle's speed, passing in the column number.
                    this.reduceAlphaParticleSpeed(i);
                }
            }
        }
    }

    // This method repairs the DNA molecule. NOTE: Not functioning properly, so isn't called.
    public void repairDNAParticles(ArrayList<Cell> cells){
        // For each cell...
        for(Cell cell : cells){
            // If the cell is damaged...
            if(cell.getIsDamaged()){
                // call the repairDNAParticles method on the Cell object.
                cell.repairDNAParticles();
            }
        }
    }

    // This method checks if the cell is necrotic.
    public void isNecroticCheck(ArrayList<Cell> cells){
        // For each cell...
        for(Cell cell : cells){
            // call the isNecroticCheck method on the Cell object.
            cell.isNectoticCheck();
        }
    }

    // This method handles drawing the various components onto the canvas. It takes in the Canvas
    // object, the Pen object, the list of cells, and the number of steps the simulation will run.
    public void runCanvasSimulation(Canvas screen, Pen pen, ArrayList<Cell> cells, int numOfSteps){
        for(int i = 0; i < numOfSteps; i++){
            screen.clear();

            // First, we draw the various components. This calls the methods in this class.
            this.drawCells(screen, pen, cells);
            this.drawDNAProteins(screen, pen, cells);
            this.drawRepairProteins(screen, pen, cells);
            this.drawAlphaProteins(screen, pen);

            // Then we generate the alpha particle diffusion, inflict cell damage, and check if
            // the cell is necrotic.
            this.generateAlphaParticleDiffusion();
            this.inflictCellDamage(cells);
            this.isNecroticCheck(cells);

            // Then we repair the damaged DNA particles. NOTE: These lines are uncommented as they
            // are not functioning.
            // this.repairDNAParticles(cells);
            // screen.update();

            // Then we update the screen. It pauses for 50ms between each step so you can see what's happening. That time is configurable.
            screen.update();
            screen.pause(50);
        }
    }
}
