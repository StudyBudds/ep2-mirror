public class Simulation {

    // gravitational constant
    public static final double G = 6.6743e-11;

    // one astronomical unit (AU) is the average distance of earth to the sun.
    public static final double AU = 150e9;

    // all quantities are based on units of kilogram respectively second and meter.

    // The main simulation method using instances of other classes.
    public static void main(String[] args) {


        Body sun = new Body("Sol",
                1.989e30,
                696340e3,
                new Vector3(),
                new Vector3(),
                StdDraw.YELLOW);

        Body mercury = new Body("Mercury",
                3.301e23,
                2.4397e3,
                new Vector3(-46.0e9, 0, 0),
                new Vector3(0, -47.87e3, 0),
                StdDraw.RED);

        Body earth = new Body("Earth",
                5.972e24,
                6371e3,
                new Vector3(148e9, 0, 0),
                new Vector3(0, 29.29e3, 0),
                StdDraw.BLUE);

        Body jupiter = new Body("Jupiter",
                1898.19e24,
                71492e3,
                new Vector3(740.522e9, 0, 0),
                new Vector3(0, 13.72e3, 0),
                StdDraw.ORANGE);
        // moon does not really work as planned :(
        Body moon = new Body("Moon",
                7.342e22,
                1738.1e3,
                new Vector3(148e9 + 6371e3 + 405.4e8, 0.4055e9, 0),
                new Vector3(0, 29.29e3 + 1.022e3, 0),
                StdDraw.LIGHT_GRAY);
        // arbitrary initialisation: position opposite to the earth with maximal distance.
        // viewing from z direction movement is counter-clockwise

        Body[] bodies = new Body[] {earth, sun, mercury, jupiter};
        Vector3[] forceOnBody = new Vector3[bodies.length];

        StdDraw.setCanvasSize(1000, 1000);
        StdDraw.setXscale(-2*AU,2*AU);
        StdDraw.setYscale(-2*AU,2*AU);

        // To make Jupiter Visible, uncomment the lines below
        // StdDraw.setXscale(-5*AU,5*AU);
        // StdDraw.setYscale(-5*AU,5*AU);
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.BLACK);

        double seconds = 0;

        // simulation loop
        while(true) {

            seconds++; // each iteration computes the movement of the celestial bodies within one second.

            // for each body (with index i): compute the total force exerted on it.
            for (int i = 0; i < bodies.length; i++) {
                forceOnBody[i] = new Vector3(0, 0, 0); // begin with zero
                for (int j = 0; j < bodies.length; j++) {
                    if (i == j) continue;
                    Vector3 forceToAdd = bodies[i].gravitationalForce(bodies[j]);
                    forceOnBody[i] = forceOnBody[i].plus(forceToAdd);
                }
            }
            // now forceOnBody[i] holds the force vector exerted on body with index i.

            // for each body (with index i): move it according to the total force exerted on it.
            for (int i = 0; i < bodies.length; i++) {
               bodies[i].move(forceOnBody[i]);
            }

            // show all movements in StdDraw canvas only every 3 hours (to speed up the simulation)
            if (seconds%(3*3600) == 0) {
                // clear old positions (exclude the following line if you want to draw orbits).
                StdDraw.clear(StdDraw.BLACK);

                // draw new positions
                for (int i = 0; i < bodies.length; i++) {
                    bodies[i].draw();
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

//TODO: answer additional questions of 'Aufgabe1'.


