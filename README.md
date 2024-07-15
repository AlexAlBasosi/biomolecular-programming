# Biomolecular Programming Assignment
## Clone the Repository

- Select `Code` in the top right corner of this repository.
- Click `Download ZIP`. This will download the application's files to your Downloads folder.
- Navigate to your Downloads folder and unzip the file you just downloaded.

## Run the Application
- Open `IntelliJ IDEA` or the IDE (Integrated Development Environment) of your choice.
- Select `Open` and select the file you just unzipped. This should load the project onto IntelliJ.
- Once the project loads all the dependencies, navigate to the project on the left, and expand `src`.
- Right click on `Main`, and then select `Run 'Main.main()'`. This should run the simulation.

## Application Structure
Outlined below are the `.java` files that make up the application. 

### Main.java
This is the **main class** that **initialises all the application's subclasses**, which contain the bulk of the application's logic. This sets up the cells, and calls various methods that are stored in the subclasses. This class is accessed under `src` > `Main.java`.

### Cells.java
This class handles the creation of cells, and any method related to the **simulation as a whole.** This includes storing the alpha source particles' coordinates and any information related to them. This class is accessed under `src` > `cell` > `Cells.java`.

### Cell.java
This class handles all information and methods related to **individual cells**. This includes initialising the nucleus, and exposing the Cells class to its nucleus's methods. This class is accessed under `src` > `cell` > `Cell.java`.

### Nucleus.java
This class handles the creation of the **nucleus**, as well as its D**NA and repair particles** and all their methods. The Nucleus exposes its methods to the Cell class. This class is accessed under `src` > `cell` > `Nucleus.java`.