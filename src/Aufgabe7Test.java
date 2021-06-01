import java.io.IOException;

public class Aufgabe7Test {

    public static void main(String[] args) {

        Body oumuamua = new Body("Oumuamua",8e6,0.2e3,new Vector3(0,0,0),new Vector3(0,0,0),
                StdDraw.YELLOW);
        Body sun = new Body("Sol",1.989e30,696340e3,new Vector3(0,0,0),new Vector3(0,0,0),StdDraw.YELLOW);
        Body earth = new Body("Earth",5.972e24,6371e3,new Vector3(0,0,0),new Vector3(0,0,0),StdDraw.BLUE);
        Body mercury = new Body("Mercury",3.301e23,2440e3,new Vector3(0,0,0),new Vector3(0,0,0),StdDraw.GRAY);
        Body venus = new Body("Venus",4.86747e24,6052e3,new Vector3(0,0,0),new Vector3(0,0,0),StdDraw.PINK);
        Body mars = new Body("Mars",6.41712e23,3390e3,new Vector3(0,0,0),new Vector3(0,0,0),StdDraw.RED);
        Body moon = new Body("Moon", 7.349e22, 1.737e6, new Vector3(0,0,0), new Vector3(0,0,0),
                StdDraw.GRAY);
        Body deimos = new Body("Deimos", 1.8e20, 6e3, new Vector3(0,0,0), new Vector3(0,0,0),
                StdDraw.GRAY);
        Body phobos = new Body("Phobos", 1.08e20, 5e3, new Vector3(0,0,0), new Vector3(0,0,0),
                StdDraw.GRAY);
        Body vesta = new Body("Vesta", 2.5908e20, 5e5, new Vector3(0,0,0), new Vector3(0,0,0),
                StdDraw.GRAY);
        Body pallas = new Body("Pallas", 2.14e20, 5e5, new Vector3(0,0,0), new Vector3(0,0,0),
                StdDraw.GRAY);
        Body hygiea = new Body("Hygiea", 8.32e19, 5e5, new Vector3(0,0,0), new Vector3(0,0,0),
                StdDraw.GRAY);
        Body ceres = new Body("Ceres", 9.394e20, 5e5, new Vector3(0,0,0), new Vector3(0,0,0),
                StdDraw.GRAY);

        ComplexCosmicSystem earthSystem = new ComplexCosmicSystem("Earth System", earth, moon);
        ComplexCosmicSystem marsSystem = new ComplexCosmicSystem("Mars System", mars, deimos,
                phobos);
        ComplexCosmicSystem solarSystem = new ComplexCosmicSystem("Solar System", sun, earthSystem,
                marsSystem);
        solarSystem.add(mercury);
        solarSystem.add(venus);
        solarSystem.add(vesta);
        solarSystem.add(pallas);
        solarSystem.add(hygiea);
        solarSystem.add(ceres);
        ComplexCosmicSystem milkyWay = new ComplexCosmicSystem("Milky Way",solarSystem, oumuamua);

        CosmicSystemMap map = new CosmicSystemMap(milkyWay);

        System.out.println("\nChecking numberOfBodies...\n----------");
        System.out.println(milkyWay.numberOfBodies());
        System.out.println(milkyWay);

        testValue(milkyWay.numberOfBodies(), 13);

        System.out.println("\nTesting remove of the ComplexCosmicSystem's iterator..." +
                "\n----------");
        BodyIterator iterator = milkyWay.iterator();
        while(iterator.hasNext() && !iterator.next().equals(deimos));
        iterator.remove(); // Mars has lost one moon!
        testValue(milkyWay.numberOfBodies(), 12);
        testValue(milkyWay.contains(deimos), false);
        System.out.println("\nThis is what toString() returns: \n" + milkyWay.toString());

        System.out.println("\nTesting collection view of ComplexCosmicSystem..." +
                "\n----------");

        BodyCollection bc = milkyWay.getBodies();
        iterator = bc.iterator();
        while(iterator.hasNext() && !iterator.next().equals(oumuamua)) {}

        iterator.remove(); // Oumuamua has left the solar system!
        testValue(bc.size(), 11);
        testValue(milkyWay.contains(oumuamua), false);
        System.out.println("\nThis is what toString() returns: \n" + milkyWay.toString());

        System.out.println("\nTesting collection view of ComplexSystemMap..." +
                "\n----------");

        bc = map.getBodies();
        iterator = bc.iterator();

        while(iterator.hasNext() && !iterator.next().equals(oumuamua)) {}

        iterator.remove(); // Oumuamua removed!
        testValue(bc.size(), 12);
        testValue(map.contains(oumuamua), false);
        System.out.println("\nThis is what toString() returns: \n" + map.toString());


        System.out.println("\nTesting readConfiguration..." +
                "\n----------");
        try {
            ReadDataUtil.readConfiguration(earth, "./states/" + earth.getName() + ".txt", "2021-May-28");
        } catch (IOException e) {
            System.out.println("Test NOT successful! " + e.getMessage());
        }
        testVector3(earth.getMassCenter(),new Vector3(-6131.359225348150E+07,
                -1383.789852227691E+08,  2719.682263474911E+04),1e-7);

        System.out.println("\nTesting readConfiguration if date is wrong..." +
                "\n----------");
        try {
            testValue(ReadDataUtil.readConfiguration(earth, "./states/" + earth.getName() + ".txt",
                    "2026-May-28"), false);
        } catch (IOException e) {
            System.out.println("Test NOT successful! " + e.getMessage());
        }

        System.out.println("\nTesting readConfiguration if file does not exist ..." +
                "\n----------");
        try {
            ReadDataUtil.readConfiguration(earth, "./states/blah.txt",
                    "2021-May-28");
            System.out.println("Test NOT successful! (this statement should not be reached.)");
        } catch (IOException e) {
            testValue(e.getClass(), StateFileNotFoundException.class);
        }

    }

    public static void testValue(Object given, Object expected) {
        if (given == expected) {
            System.out.println("Successful test");
        } else {
            System.out.println("Test NOT successful! Expected value: " + expected + " / Given value: " + given);
        }
    }

    public static void testStringEquals(String given, String expected) {
        if (given.equals(expected)) {
            System.out.println("Successful test");
        } else {
            System.out.println("Test NOT successful! Expected String: " + expected + " / Given String: " + given);
        }
    }

    public static void testValue(double given, double expected, double delta) {
        if (given - delta <= expected && given + delta >= expected) {
            System.out.println("Successful test");
        } else {
            System.out.println("Test NOT successful! Expected Value: " + expected + " / Given Value: " + given);
        }
    }

    public static void testVector3(Vector3 given, Vector3 expected, double delta) {
        double distance = given.distanceTo(expected);
        if (distance - delta <= 0.0 && distance + delta >= 0.0) {
            System.out.println("Successful test");
        } else {
            System.out.println("Test NOT successful! Expected Value: " + expected + " / Given Value: " + given);
        }
    }
}