import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;

public class Simulation {

    // gravitational constant
    public static final double G = 6.6743e-11;

    // one astronomical unit (AU) is the average distance of earth to the sun.
    public static final double AU = 150e9;

    public final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MMM-dd", Locale.ENGLISH);

    // all quantities are based on units of kilogram respectively second and meter.

    // The main simulation method using instances of other classes.
    public static void main(String[] args) {

        if(args.length != 2) {
            System.err.println("Error: wrong number of arguments.");
            System.exit(-1);
        }

        try {
            DATE_FORMAT.parse(args[1]);
        } catch (ParseException e) {
            System.err.println("Error: State not available");
            System.exit(-1);
        }


        File folder = new File(args[0]);
        if(!folder.exists() || !folder.isDirectory()) {
            System.err.println("Error: Folder " + args[0] + "does not exist");
            System.exit(-1);
        }


        // arbitrary initialisation: position opposite to the earth with maximal distance.
        // viewing from z direction movement is counter-clockwise
        Body sun = new Body("Sol",1.989e30,696340e3,new Vector3(0,0,0),new Vector3(0,0,0),StdDraw.YELLOW);
        Body earth = new Body("Earth",5.972e24,6371e3,new Vector3(-1.394555e11,5.103346e10,0),new Vector3(-10308.53,-28169.38,0),StdDraw.BLUE);
        Body mercury = new Body("Mercury",3.301e23,2440e3,new Vector3(-5.439054e10,9.394878e9,0),new Vector3(-17117.83,-46297.48,-1925.57),StdDraw.GRAY);
        Body venus = new Body("Venus",4.86747e24,6052e3,new Vector3(-1.707667e10,1.066132e11,2.450232e9),new Vector3(-34446.02,-5567.47,2181.10),StdDraw.PINK);
        Body mars = new Body("Mars",6.41712e23,3390e3,new Vector3(-1.010178e11,-2.043939e11,-1.591727E9),new Vector3(20651.98,-10186.67,-2302.79),StdDraw.RED);

        StdDraw.setCanvasSize(500, 500);
        StdDraw.setXscale(-2*AU,2*AU);
        StdDraw.setYscale(-2*AU,2*AU);

        // To make Jupiter Visible, uncomment the lines below
        // StdDraw.setXscale(-5*AU,5*AU);
        // StdDraw.setYscale(-5*AU,5*AU);
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.BLACK);



        double seconds = 0;
        ComplexCosmicSystem bodies = new ComplexCosmicSystem("Sonnensystem", sun, earth, mercury, venus, mars);

        BodyIterator iter = bodies.iterator();
        while(iter.hasNext()) {
            Body b = iter.next();
            if(!b.getName().equals("Sol")) {
                try {
                    if (!ReadDataUtil.readConfiguration(b, args[0] + "/" + b.getName() + ".txt", args[1])) {
                        System.err.println("Error: State not available");
                        System.exit(-1);
                    }
                } catch (StateFileNotFoundException | StateFileFormatException io) {
                    System.out.println(io.getMessage());
                    System.out.println("Running simulation without " + b.getName());
                    iter.remove();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(-1);
                }
            }
        }
        System.out.println("Running simulation...");
        // simulation loop
        while(true) {

            seconds++; // each iteration computes the movement of the celestial bodies within one second.
            // for each body (with index i): compute the total force exerted on it.
            for (Body curr : bodies) {
                Vector3 forceOnBody = new Vector3(0, 0, 0); // begin with zero
                for (Body other : bodies) {
                    if (curr.getName().equals(other.getName())) continue;
                    Vector3 forceToAdd = curr.gravitationalForce(other);
                    forceOnBody = forceOnBody.plus(forceToAdd);
                }
                curr.setForce(forceOnBody);
            }
            // now forceOnBody[i] holds the force vector exerted on body with index i.

            // for each body (with index i): move it according to the total force exerted on it.
            for (Body curr : bodies) {
                curr.move();
            }

            // show all movements in StdDraw canvas only every 3 hours (to speed up the simulation)
            if (seconds%(3*3600) == 0) {
                // clear old positions (exclude the following line if you want to draw orbits).
                StdDraw.clear(StdDraw.BLACK);

                // draw new positions
                for (Body curr : bodies) {
                    curr.draw();
                }

                // show new positions
                StdDraw.show();
            }


        }

    }

    // Returns a vector representing the gravitational force exerted by body 'b2' on body 'b1'.
    // The gravitational Force F is calculated by F = G*(m1*m2)/(r*r), with m1 and m2 being the masses of the objects
    // interacting, r being the distance between the centers of the masses and G being the gravitational constant.
    // To calculate the force exerted on b1, simply multiply the normalized vector pointing from b1 to b2 with the
    // calculated force

}

/*
F: Was versteht man unter Datenkapselung? Geben Sie ein Beispiel, wo dieses Konzept in dieser Aufgabenstellung angewendet wird.

A: Die Zusammenfassung von Attributen und Methoden in einem abstraktem Datentypen.
B: Bei der Vektor3 Klasse werden Koordinaten im 3-dimensionalen Raum zusammengefasst in einem abstrakten Datentyp.


F: Was versteht man unter Data Hiding? Geben Sie ein Beispiel, wo dieses Konzept in dieser Aufgabenstellung angewendet wird.
A: Das Ändern der Sichtbarkeiten der Objektvariablen auf private und dadurch das Verhindern des direkten Zugriffs auf diese.
   Der Zugriff auf das Objekt erfolgt nur noch über Schnittstellen die durch Methoden implementiert sind.
   Dadurch wird die interne Logik der Klasse wegabstrahiert und verhindert, das im Code ein Wert eines Objekts gesetzt wird ohne abhänige Objektvariablen zu verändern
B: Zum Beispiel die Body Klasse. Alle Objektvariablen sind private und z.B. position kann nur über die Methode move verändert werden, wodurch garantiert wird das auch currentMovement
   korrekt verändert wird.

 */


