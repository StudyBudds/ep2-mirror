import java.awt.*;

public class Aufgabe2Test {

    public static void main(String[] args) {

        //test CosmicSystem
        CosmicSystem jupiterSystem = new CosmicSystem("Jupiter System");

        testValue(jupiterSystem.size(), 0);

        Body jupiter = new Body("Jupiter", 1.898e27, 69911e3, new Vector3(0,0,0),
                new Vector3(0,0,0),Color.ORANGE);
        Body io = new Body("Io", 8.9e22 ,1822e3, new Vector3(0,0,0),
                new Vector3(0,0,0), Color.YELLOW);
        Body europa = new Body("Europa", 4.8e22 ,1561e3, new Vector3(0,0,0),
                new Vector3(0,0,0), Color.ORANGE);
        Body ganymed = new Body("Ganymed", 1.5e23 ,2631e3, new Vector3(0,0,0),
                new Vector3(0,0,0), Color.GRAY);
        Body kallisto = new Body("Kallisto", 1.1e23 ,2411e3, new Vector3(0,0,0),
                new Vector3(0,0,0), Color.GRAY);

        jupiterSystem.add(jupiter);
        jupiterSystem.add(io);
        jupiterSystem.add(europa);
        jupiterSystem.add(ganymed);
        jupiterSystem.add(kallisto);

        testComparison(io, jupiterSystem.get("Io"), true);
        testComparison(europa, jupiterSystem.get("Europa"), true);
        testComparison(europa, jupiterSystem.get("Moon"), false);
        testComparison(io, jupiterSystem.get(io), true);
        testComparison(io, jupiterSystem.get(new Body("TestBody", 0 ,0,new Vector3(0,0,0),
                new Vector3(0,0,0), Color.BLACK)), false);
        testComparison(io, jupiterSystem.get(new Body("Io", 0 ,0,new Vector3(0,0,0),
                new Vector3(0,0,0), Color.BLACK)), true);

        testComparison(jupiter, jupiterSystem.get(0), true);
        testComparison(io, jupiterSystem.get(1), true);
        testComparison(ganymed, jupiterSystem.get(4), false);

        testValue(jupiterSystem.size(), 5);

        //test CosmicSystemTree
        CosmicSystem earthSystem = new CosmicSystem("Earth System");
        earthSystem.add(new Body("Earth", 5.97237e24, 6371.0084e3, new Vector3(0,0,0),
                new Vector3(0,0,0), Color.BLUE));
        earthSystem.add(new Body("Moon", 7.349e22, 1738e3, new Vector3(0,0,0),
                new Vector3(0,0,0), Color.GRAY));

        CosmicSystem marsSystem = new CosmicSystem("Mars System");
        marsSystem.add(new Body("Mars", 6.419e23, 3396e3, new Vector3(0,0,0),
                new Vector3(0,0,0), Color.RED));
        marsSystem.add(new Body("Phobos", 1.072e16, 11e3, new Vector3(0,0,0),
                new Vector3(0,0,0), Color.GRAY));
        marsSystem.add(new Body("Deimos", 1.8e15, 6e3, new Vector3(0,0,0),
                new Vector3(0,0,0), Color.GRAY));

        CosmicSystem anotherEarth = new CosmicSystem("Another earth system");
        anotherEarth.add(new Body("Earth", 5.97237e24, 6371.0084e3, new Vector3(0,0,0),
                new Vector3(0,0,0), Color.BLUE));

        CosmicSystemTree cst = new CosmicSystemTree();

        testValue(cst.add(jupiterSystem), true);
        testValue(cst.add(marsSystem), true);
        testValue(cst.add(earthSystem), true);
        testValue(cst.add(anotherEarth), false);

        testValue(cst.numberOfBodies(), 10);

        //optional bonus task
        cst.drawTree();

        testComparison(marsSystem, cst.get("Phobos"), true);
        testComparison(jupiterSystem, cst.get("Io"), true);
        testComparison(jupiterSystem, cst.get("Kallisto"), true);
        testComparison(earthSystem, cst.get("Moon"), true);
        testComparison(earthSystem, cst.get("Earth"), true);
        testComparison(earthSystem, cst.get("Ganymed"), false);

        System.out.println(cst);
        /*
        (Deimos,Mars System)
        (Earth,Earth System)
        (Europa,Jupiter System)
        (Ganymed,Jupiter System)
        (Io,Jupiter System)
        (Jupiter,Jupiter System)
        (Kallisto,Jupiter System)
        (Mars,Mars System)
        (Moon,Earth System)
        (Phobos,Mars System)
         */
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
}
