import java.awt.*;

public class Aufgabe5Test{

    public static void main(String[] args) {
        CosmicSystem c = new CosmicSystem("a");
        for(int i = 0; i < c.size(); i++) {
            System.out.println(c.get(i));
        }

        System.out.println("\nIt is expected that add() works...\n----------");
        Body jupiter = new Body("Jupiter", 1.898e27, 69911e3, new Vector3(0, 0, 0),
                new Vector3(0, 0, 0), Color.ORANGE);
        Body io = new Body("Io", 8.9e22, 1822e3, new Vector3(0, 0, 0),
                new Vector3(0, 0, 0), Color.YELLOW);
        Body europa = new Body("Europa", 4.8e22, 1561e3, new Vector3(0, 0, 0),
                new Vector3(0, 0, 0), Color.ORANGE);
        Body ganymed = new Body("Ganymed", 1.5e23, 2631e3, new Vector3(0, 0, 0),
                new Vector3(0, 0, 0), Color.GRAY);
        Body kallisto = new Body("Kallisto", 1.1e23, 2411e3, new Vector3(0, 0, 0),
                new Vector3(0, 0, 0), Color.GRAY);
        Body sun = new Body("Sol", 1.989e30, 696340e3, new Vector3(0, 0, 0),
                new Vector3(0, 0, 0), Color.YELLOW);
        Body voyager = new Body("Voyager", 5, 825, new Vector3(151.82 * 150e9, 0, 0),
                new Vector3(17e3, 0, 0), Color.CYAN);
        Body earth = new Body("Earth", 5.972e24, 6371e3, new Vector3(0, 0, 0), new Vector3(0, 0, 0), Color.BLUE);
        Body moon = new Body("Moon", 7.348e22, 1737e3, new Vector3(0, 0, 0), new Vector3(0, 0, 0), Color.GRAY);
        Body sunCopy = new Body("Sol", 1.989e30+0.000000000001, 696340e3, new Vector3(0, 0, 0),
                new Vector3(0, 0, 0), Color.YELLOW);

        int hash1 = sun.hashCode();
        int hash2 = sunCopy.hashCode();
        System.out.println(hash1 == hash2);
        ComplexCosmicSystem earthSystem = new ComplexCosmicSystem("Earth System", earth, moon);
        ComplexCosmicSystem jupiterSystem = new ComplexCosmicSystem("Jupiter System", jupiter, io
                , europa, ganymed, kallisto);
        ComplexCosmicSystem solarSystem = new ComplexCosmicSystem("Solar System", sun, earthSystem
                , jupiterSystem);
        ComplexCosmicSystem milkyWay = new ComplexCosmicSystem("Milky Way", solarSystem, voyager);




        System.out.println("\nTesting equals() and hashCode() of Body...\n----------");
        testValue(sun.equals(sun), true);
        testValue(sun.equals(moon), false);
        testValue(sun.equals(new Body("Sol", 1.989e30, 696340e3, new Vector3(0, 0, 0),
                new Vector3(0, 0, 0), Color.YELLOW)), true);
        testValue(sun.hashCode() == sun.hashCode(), true);
        testValue(sun.hashCode() == new Body("Sol", 1.989e30, 696340e3,
                new Vector3(0, 0, 0), new Vector3(0, 0, 0), Color.YELLOW).hashCode(), true);

        System.out.println("\nTesting equals() and hashCode() of ComplexCosmicSystem...\n----------");
        ComplexCosmicSystem altEarthSystem = new ComplexCosmicSystem("Earth System", moon, earth);

        testValue(earthSystem.equals(earthSystem), true);
        testValue(earthSystem.equals(solarSystem), false);
        testValue(earthSystem.equals(altEarthSystem), true);
        testValue(earthSystem.hashCode() == altEarthSystem.hashCode(), true);

        System.out.println("\nThis is that toString() returns: \n" + milkyWay.toString());

        System.out.println("\nTesting iterator()...\n----------");
        System.out.println("\n9 bodies should be printed here:");
        for(Body body : milkyWay) {
            System.out.println(body);
        }

        System.out.println("\nTesting numberOfBodies()...\n----------");
        testValue(milkyWay.numberOfBodies(), 9);

        System.out.println("\nTesting getParent() and contains() of ComplexCosmicSystem...\n----------");

        testValue(earthSystem.getParent(earth), earthSystem);
        testValue(solarSystem.getParent(earth), earthSystem);
        testValue(milkyWay.getParent(earth), earthSystem);
        testValue(milkyWay.getParent(new Body("Sol", 1.989e30, 696340e3,
                new Vector3(0, 0, 0), new Vector3(0, 0, 0), Color.YELLOW)), solarSystem);
        testValue(milkyWay.getParent(jupiter), jupiterSystem);
        testValue(milkyWay.getParent(voyager), milkyWay);
        testValue(earthSystem.getParent(voyager), null);
        testValue(earthSystem.getParent(new Body("Sol", 1.989e30, 696340e3,
                new Vector3(0, 0, 0), new Vector3(0, 0, 0), Color.YELLOW)), null);

        testValue(earthSystem.contains(earth), true);
        testValue(solarSystem.contains(new Body("Sol", 1.989e30, 696340e3,
                new Vector3(0, 0, 0), new Vector3(0, 0, 0), Color.YELLOW)), true);
        testValue(earthSystem.contains(voyager), false);
        testValue(milkyWay.contains(earth), true);
        testValue(solarSystem.contains(earth), true);
        testValue(jupiterSystem.contains(earth), false);



        System.out.println("\nTesting CosmicSystemMap() getParent and contains()...\n----------");
        CosmicSystemMap milkyMap = new CosmicSystemMap(milkyWay);
        CosmicSystemMap earthMap = new CosmicSystemMap(earthSystem);
        CosmicSystemMap solarMap = new CosmicSystemMap(solarSystem);
        CosmicSystemMap jupiterMap = new CosmicSystemMap(jupiterSystem);

        testValue(earthMap.getParent(earth), earthSystem);
        testValue(solarMap.getParent(earth), earthSystem);
        testValue(milkyMap.getParent(earth), earthSystem);
        testValue(milkyMap.getParent(new Body("Sol", 1.989e30, 696340e3,
                new Vector3(0, 0, 0), new Vector3(0, 0, 0), Color.YELLOW)), solarSystem);
        testValue(milkyMap.getParent(jupiter), jupiterSystem);
        testValue(milkyMap.getParent(voyager), milkyWay);
        testValue(earthMap.getParent(voyager), null);
        testValue(earthMap.getParent(new Body("Sol", 1.989e30, 696340e3,
                new Vector3(0, 0, 0), new Vector3(0, 0, 0), Color.YELLOW)), null);

        testValue(earthMap.contains(earth), true);
        testValue(solarMap.contains(new Body("Sol", 1.989e30, 696340e3,
                new Vector3(0, 0, 0), new Vector3(0, 0, 0), Color.YELLOW)), true);
        testValue(earthMap.contains(voyager), false);
        testValue(milkyMap.contains(earth), true);
        testValue(solarMap.contains(earth), true);
        testValue(jupiterMap.contains(earth), false);

        System.out.println("\nTesting equals() and hashCode() of CosmicSystemMap...\n----------");
        CosmicSystemMap altEarthMap = new CosmicSystemMap(altEarthSystem);

        testValue(altEarthMap.equals(altEarthMap), true);
        testValue(altEarthMap.equals(earthMap), true);
        testValue(altEarthMap.equals(solarMap), false);
        testValue(altEarthMap.hashCode() == earthMap.hashCode(), true);

        for (Body body : milkyMap) {
            System.out.println(body);
        }
        System.out.println("=====");
        for (Body body : milkyWay) {
            System.out.println(body);
        }
        System.out.println("\nTesting toString() of CosmicSystemMap...\n----------");
        System.out.println("This should print two bodies: \n" + earthMap.toString());
        System.out.println("This should print nine bodies: \n" + milkyMap);
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
