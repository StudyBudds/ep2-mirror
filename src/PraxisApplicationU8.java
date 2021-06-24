import java.io.IOException;
import java.awt.Color;

public class PraxisApplicationU8 {

    public static void main(String[] args) {

        //we create some bodies (mass is irrelevant here, thus all bodies have zero mass)
        Body sun = new Body("Sol", 0, 696340e3, new Vector3(0, 0, 0), new Vector3(0, 0, 0), Color.YELLOW);
        Body earth = new Body("Earth", 0, 6371e3, new Vector3(0, 0, 0), new Vector3(0, 0, 0), Color.BLUE);
        Body moon = new Body("Moon", 0, 1737e3, new Vector3(0, 0, 0), new Vector3(0, 0, 0), Color.GRAY);
        Body iss = new Body("ISS", 0, 100, new Vector3(0, 0, 0), new Vector3(0, 0, 0), Color.GRAY);
        Body sputnik = new Body("Sputnik", 0, 1, new Vector3(0, 0, 0), new Vector3(0, 0, 0), Color.GRAY);

        DoubleSystem ds = null;
        System.out.println("Test 1: this should throw an exception");
        try {
            ds = new DoubleSystem(null, earth);
        } catch (IOException e) {
            System.out.println("ERROR! " + e.getMessage());
        }

        System.out.println("Test 2: this should also throw an exception");
        try {
            ds = new DoubleSystem(earth, sun);
        } catch (IOException e) {
            System.out.println("ERROR! " + e.getMessage());
        }

        System.out.println("Test 3: this should create a new double system and print \"[Earth + Moon]\" ");

        try {
            ds = new DoubleSystem(earth, moon);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(ds);

        System.out.println("Test 4: this tests the iterator of Body and should print \"Earth\" ");
        for (Body b : earth) {
            System.out.println(b);
        }

        System.out.println("Test 5: this tests the iterator of DoubleSystem and should print:\nEarth\nMoon\n");
        for (Body b : ds) {
            System.out.println(b);
        }

        System.out.println("Test 6: this tests the method numberOfBodies() of DoubleSystem and should give 2:");
        System.out.println(ds.numberOfBodies());

        System.out.println("Test 7: this tests the method add() of Body and should print \"[Sol + Earth]\" twice.");
        System.out.println(earth.add(sun));
        System.out.println(sun.add(earth));

        System.out.println("Test 8: this tests the method add() of DoubleSystem and should print:  \"[Sol + [Earth + [Moon + [ISS + Sputnik]]]]\"");
        ds.add(sputnik);
        ds.add(iss);
        ds.add(sun);
        System.out.println(ds);

        System.out.println("Test 9: this tests again the method numberofBodies() of DoubleSystem and should give 5:");
        System.out.println(ds.numberOfBodies());
        System.out.println("Test 10: this tests again the iterator of DoubleSystem and should print:\nSol\nEarth\nMoon\nISS\nSputnik\n");
        for (Body b : ds) {
            System.out.println(b);
        }

        System.out.println("Test 11: this tests the method getLargest() and should print \"Sol\"");
        System.out.println(ds.getLargest());

    }
}
