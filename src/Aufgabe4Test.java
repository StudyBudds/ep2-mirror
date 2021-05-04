import java.awt.*;

public class Aufgabe4Test {

    public static void main(String[] args) {

        System.out.println("\nTesting add() and numberOfBodies()...\n----------");
        ComplexCosmicSystem milkyWay = new ComplexCosmicSystem("Milky Way");
        ComplexCosmicSystem solarSystem = new ComplexCosmicSystem("Solar System");

        testValue(milkyWay.add(solarSystem), true);

        testValue(milkyWay.numberOfBodies(), 0);

        Body sun = new Body("Sol", 1.989e30, 696340e3, new Vector3(0, 0, 0), new Vector3(0, 0, 0), Color.YELLOW);
        testValue(solarSystem.add(sun), true);
        Body voyager = new Body("Voyager", 5, 825, new Vector3(151.82 * 150e9, 0, 0), new Vector3(17e3, 0, 0), Color.CYAN);
        testValue(milkyWay.add(voyager), true);

        testValue(milkyWay.numberOfBodies(), 2);

        System.out.println("\nTesting add(), numberOfBodies() and size()...\n----------");
        ComplexCosmicSystem jupiterSystem = new ComplexCosmicSystem("Jupiter System");

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

        jupiterSystem.add(jupiter);
        jupiterSystem.add(io);
        jupiterSystem.add(europa);
        jupiterSystem.add(ganymed);
        jupiterSystem.add(kallisto);
        solarSystem.add(jupiterSystem);

        Body earth = new Body("Earth", 5.972e24, 6371e3, new Vector3(0, 0, 0), new Vector3(0, 0, 0), Color.BLUE);
        Body moon = new Body("Moon", 7.348e22, 1737e3, new Vector3(0, 0, 0), new Vector3(0, 0, 0), Color.GRAY);

        ComplexCosmicSystem earthSystem = new ComplexCosmicSystem("Earth System");
        earthSystem.add(earth);
        earthSystem.add(moon);

        solarSystem.add(earthSystem);

        testValue(milkyWay.numberOfBodies(), 9);
        testValue(milkyWay.size(), 2);

        System.out.println("\nTesting add(), remove() and toString()...\n----------");
        System.out.println(milkyWay);
        testStringEquals(milkyWay.toString(), "Milky Way{Solar System{Sol, Jupiter System{Jupiter, Io, Europa, Ganymed, Kallisto}, Earth System{Earth, Moon}}, Voyager}");

        testValue(solarSystem.remove(kallisto), false);
        testValue(solarSystem.remove(jupiterSystem), true);
        testValue(milkyWay.remove(voyager), true);
        System.out.println(milkyWay);
        testStringEquals(milkyWay.toString(), "Milky Way{Solar System{Sol, Earth System{Earth, Moon}}}");
        testValue(solarSystem.add(jupiter), true);
        testValue(solarSystem.add(sun), false);
        System.out.println(milkyWay);
        testStringEquals(milkyWay.toString(), "Milky Way{Solar System{Sol, Earth System{Earth, Moon}, Jupiter}}");
//        testValue(solarSystem.get("Earth"), null);
//        testStringEquals(solarSystem.get("Jupiter").toString(), "Jupiter");
//        testStringEquals(solarSystem.get(jupiter).toString(), "Jupiter");

        System.out.println("\nTesting getMass() and getMassCenter()...\n----------");
        ComplexCosmicSystem system = new ComplexCosmicSystem("System");

        testValue(system.getMass(), 0, 0.01);
        testVector3(system.getMassCenter(), new Vector3(0, 0, 0), 0.01);

        ComplexCosmicSystem subSystem1 = new ComplexCosmicSystem("Subsystem 1");
        ComplexCosmicSystem subSystem2 = new ComplexCosmicSystem("Subsystem 2");
        subSystem1.add(new Body("Body 1", 1, 0, new Vector3(1, 2, 3), new Vector3(0, 0, 0), Color.GRAY));
        subSystem1.add(new Body("Body 2", 2, 0, new Vector3(0, 2, 0), new Vector3(0, 0, 0), Color.GRAY));
        subSystem2.add(new Body("Body 3", 3, 0, new Vector3(3, 2, 1), new Vector3(0, 0, 0), Color.GRAY));
        system.add(subSystem1);
        system.add(subSystem2);

        testValue(subSystem1.getMass(), 3.0, 0.01);
        testValue(subSystem2.getMass(), 3.0, 0.01);
        testValue(system.getMass(), 6.0, 0.01);

        testVector3(subSystem1.getMassCenter(), new Vector3(1. / 3., 2.0, 1.0), 0.01);
        testVector3(subSystem2.getMassCenter(), new Vector3(3.0, 2.0, 1.0), 0.01);
        testVector3(system.getMassCenter(), new Vector3(10. / 6., 2.0, 1.0), 0.01);
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
