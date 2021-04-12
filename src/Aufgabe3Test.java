import java.awt.*;

public class Aufgabe3Test {

    public static void main(String[] args) {

        //test CosmicSystem
        CosmicSystem jupiterSystem = new CosmicSystem("Jupiter System");

        testValue(jupiterSystem.size(), 0);

        Body jupiter = new Body("Jupiter", 1.898e27, 69911e3, new Vector3(0, 0, 0),
                new Vector3(0, 0, 0), Color.ORANGE);
        Body io = new Body("Io", 8.9e22, 1822e3, new Vector3(0, 0, 0),
                new Vector3(0, 0, 0), Color.YELLOW);
        Body europa = new Body("Europa", 4.8e22, 1561e3, new Vector3(0, 0, 0),
                new Vector3(0, 0, 0), Color.ORANGE);
        Body ganymed = new Body("Ganymed", 1.5e23, 2631e3, new Vector3(0, 0, 0),
                new Vector3(0, 0, 0), Color.GRAY);
        Body kallisto = new Body("Kallisto", 1.1e23, 2411e3,
                new Vector3(0, 0, 0), new Vector3(0, 0, 0), Color.GRAY);

        System.out.println("\nTesting add(Body body)...\n----------");
        testValue(jupiterSystem.add(jupiter), true);
        testValue(jupiterSystem.add(io), true);
        testValue(jupiterSystem.add(europa), true);
        testValue(jupiterSystem.add(ganymed), true);
        testValue(jupiterSystem.add(kallisto), true);

        System.out.println("\nTesting get(String name) and get(Body body)...\n----------");
        testComparison(io, jupiterSystem.get("Io"), true);
        testComparison(europa, jupiterSystem.get("Europa"), true);
        testComparison(europa, jupiterSystem.get("Moon"), false);
        testComparison(io, jupiterSystem.get(io), true);
        testComparison(io, jupiterSystem.get(new Body("TestBody", 0, 0, new Vector3(0, 0, 0),
                new Vector3(0, 0, 0), Color.BLACK)), false);
        testComparison(io, jupiterSystem.get(new Body("Io", 0, 0, new Vector3(0, 0, 0),
                new Vector3(0, 0, 0), Color.BLACK)), true);

        System.out.println("\nTesting get(int i)...\n-----------");
        testComparison(jupiter, jupiterSystem.get(0), true);
        testComparison(io, jupiterSystem.get(1), true);
        testComparison(ganymed, jupiterSystem.get(4), false);

        testValue(jupiterSystem.size(), 5);

        System.out.println("\nTesting add(int i)...\n-----------");
        testValue(jupiterSystem.add(1, new Body("Ganymed", 1.5e23, 2631e3, new Vector3(0, 0, 0),
                new Vector3(0, 0, 0), Color.GRAY)), false);
        testValue(jupiterSystem.add(6, new Body("Elara", 8.7e17, 39e3, new Vector3(0, 0, 0),
                new Vector3(0, 0, 0), Color.GRAY)), false);
        testValue(jupiterSystem.add(-1, new Body("Elara", 8.7e17, 39e3, new Vector3(0, 0, 0),
                new Vector3(0, 0, 0), Color.GRAY)), false);
        testValue(jupiterSystem.add(5, new Body("Elara", 8.7e17, 39e3, new Vector3(0, 0, 0),
                new Vector3(0, 0, 0), Color.GRAY)), true);
        testValue(jupiterSystem.add(5, new Body("Amalthea", 2.1e18, 84e3, new Vector3(0, 0, 0),
                new Vector3(0, 0, 0), Color.GRAY)), true);
        testValue(jupiterSystem.add(6, new Body("Himalia", 6.7e18, 80e3, new Vector3(0, 0, 0),
                new Vector3(0, 0, 0), Color.GRAY)), true);
        testValue(jupiterSystem.add(7, new Body("Himalia", 6.7e18, 80e3, new Vector3(0, 0, 0),
                new Vector3(0, 0, 0), Color.GRAY)), false);
        testStringEquals(jupiterSystem.get(0).getName(), "Jupiter");
        testStringEquals(jupiterSystem.get(5).getName(), "Amalthea");
        testStringEquals(jupiterSystem.get(6).getName(), "Himalia");
        testStringEquals(jupiterSystem.get(7).getName(), "Elara");

        System.out.println(jupiterSystem); //prints all bodies in the system with their properties:
        // Jupiter, 1.898E27 kg, radius: 6.9911E7 m, position: [0.0,0.0,0.0] m, movement: [0.0,0.0,0.0] m/s.
        // Io, 8.9E22 kg, radius: 1822000.0 m, position: [0.0,0.0,0.0] m, movement: [0.0,0.0,0.0] m/s.
        // Europa, 4.8E22 kg, radius: 1561000.0 m, position: [0.0,0.0,0.0] m, movement: [0.0,0.0,0.0] m/s.
        // Ganymed, 1.5E23 kg, radius: 2631000.0 m, position: [0.0,0.0,0.0] m, movement: [0.0,0.0,0.0] m/s.
        // Kallisto, 1.1E23 kg, radius: 2411000.0 m, position: [0.0,0.0,0.0] m, movement: [0.0,0.0,0.0] m/s.
        // Amalthea, 2.1E18 kg, radius: 84000.0 m, position: [0.0,0.0,0.0] m, movement: [0.0,0.0,0.0] m/s.
        // Himalia, 6.7E18 kg, radius: 80000.0 m, position: [0.0,0.0,0.0] m, movement: [0.0,0.0,0.0] m/s.
        // Elara, 8.7E17 kg, radius: 39000.0 m, position: [0.0,0.0,0.0] m, movement: [0.0,0.0,0.0] m/s.

        System.out.println("\nTesting reverse()...\n-----------");
        jupiterSystem = jupiterSystem.reverse();
        System.out.println(jupiterSystem); //all bodies in reversed order:
        // Elara, 8.7E17 kg, radius: 39000.0 m, position: [0.0,0.0,0.0] m, movement: [0.0,0.0,0.0] m/s.
        // Himalia, 6.7E18 kg, radius: 80000.0 m, position: [0.0,0.0,0.0] m, movement: [0.0,0.0,0.0] m/s.
        // Amalthea, 2.1E18 kg, radius: 84000.0 m, position: [0.0,0.0,0.0] m, movement: [0.0,0.0,0.0] m/s.
        // Kallisto, 1.1E23 kg, radius: 2411000.0 m, position: [0.0,0.0,0.0] m, movement: [0.0,0.0,0.0] m/s.
        // Ganymed, 1.5E23 kg, radius: 2631000.0 m, position: [0.0,0.0,0.0] m, movement: [0.0,0.0,0.0] m/s.
        // Europa, 4.8E22 kg, radius: 1561000.0 m, position: [0.0,0.0,0.0] m, movement: [0.0,0.0,0.0] m/s.
        // Io, 8.9E22 kg, radius: 1822000.0 m, position: [0.0,0.0,0.0] m, movement: [0.0,0.0,0.0] m/s.
        // Jupiter, 1.898E27 kg, radius: 6.9911E7 m, position: [0.0,0.0,0.0] m, movement: [0.0,0.0,0.0] m/s.
        testStringEquals(jupiterSystem.get(3).getName(), "Kallisto");
        testStringEquals(jupiterSystem.get(0).getName(), "Elara");
        testStringEquals(jupiterSystem.get(7).getName(), "Jupiter");

        System.out.println("\nTesting remove()...\n-----------");
        testValue(jupiterSystem.remove(0), true);
        testValue(jupiterSystem.remove(io), true);
        testValue(jupiterSystem.remove(5), true);
        testValue(jupiterSystem.remove(5), false);
        testValue(jupiterSystem.remove(new Body("Himalia", 6.7e18, 80e3, new Vector3(0, 0, 0),
                new Vector3(0, 0, 0), Color.GRAY)), true);
        testValue(jupiterSystem.size(), 4);
        System.out.println(jupiterSystem);
        // Jupiter System:
        // Amalthea, 2.1E18 kg, radius: 84000.0 m, position: [0.0,0.0,0.0] m, movement: [0.0,0.0,0.0] m/s.
        // Kallisto, 1.1E23 kg, radius: 2411000.0 m, position: [0.0,0.0,0.0] m, movement: [0.0,0.0,0.0] m/s.
        // Ganymed, 1.5E23 kg, radius: 2631000.0 m, position: [0.0,0.0,0.0] m, movement: [0.0,0.0,0.0] m/s.
        // Europa, 4.8E22 kg, radius: 1561000.0 m, position: [0.0,0.0,0.0] m, movement: [0.0,0.0,0.0] m/s.
    }

    public static void testComparison(Object first, Object second, boolean expected) {
        boolean real = first == second;

        if (real == expected) {
            System.out.println("Successful comparison");
        } else {
            System.out.println("Comparison NOT successful! Expected value: " + expected + " / Given value: " + real);
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

}
