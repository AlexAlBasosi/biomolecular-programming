import cell.Cell;
import nano.*;

public class Main {
    public static void main(String[] args) {
        // An example of initialising a Cell object based on a Cell class.
        Cell cell = new Cell(false, 10, 10);
        //          ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ overloaded constructor is called here.

        cell.setIsCancerous(true);
        //   ^^^^^^^^^^^^^^^^^^^^^ this method sets the value of isCancerous to true.

        // At the 0th row and 0th column, the value 1 is stored.
        cell.setDNACoordinates(0, 0, 1);

//        Canvas canvas = new Canvas();

    }
}