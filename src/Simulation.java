public class Simulation {

    // gravitational constant
    public static final double G = 6.6743e-11;

    // one astronomical unit (AU) is the average distance of earth to the sun.
    public static final double AU = 150e9;

    // all quantities are based on units of kilogram respectively second and meter.

    // The main simulation method using instances of other classes.
    public static void main(String[] args) {

        //TODO: change implementation of this method according to 'Aufgabe1.md'.

        Body sun = new Body();
        sun.name = "Sol";
        sun.mass = 1.989e30; // kg
        sun.radius = 696340e3; // meters
        sun.position = new Vector3();
        sun.currentMovement = new Vector3();
        sun.position.x = 0; // meters
        sun.position.y = 0;
        sun.position.z = 0;
        // sun is the reference point and assumed not to move.
        sun.currentMovement.x = 0;
        sun.currentMovement.y = 0;
        sun.currentMovement.z = 0;
        sun.color = StdDraw.YELLOW;

        Body earth = new Body();
        earth.name = "Earth";
        earth.mass = 5.972e24; // kg
        earth.radius = 6371e3; // meters
        earth.position = new Vector3();
        earth.currentMovement = new Vector3();
        earth.position.x = 148e9; // minimal distance to sun in meters.
        earth.position.y = 0;
        earth.position.z = 0;
        // viewing from z direction movement is counter-clockwise
        earth.currentMovement.x = 0;
        earth.currentMovement.y = 29.29e3; // orbital speed in meters per second (at minimal distance).
        earth.currentMovement.z = 0;
        earth.color = StdDraw.BLUE;

        Body mercury = new Body();
        mercury.name = "Mercury";
        mercury.mass = 3.301e23;
        mercury.radius = 2.4397e3;
        mercury.position = new Vector3();
        mercury.currentMovement = new Vector3();
        // arbitrary initialisation: position opposite to the earth with maximal distance.
        mercury.position.x = -46.0e9; // meters
        mercury.position.y = 0;
        mercury.position.z = 0;
        // viewing from z direction movement is counter-clockwise
        mercury.currentMovement.x = 0;
        mercury.currentMovement.y = -47.87e3; // meters per second
        mercury.currentMovement.z = 0;
        mercury.color = StdDraw.RED;

        Body[] bodies = new Body[] {earth, sun, mercury};
        Vector3[] forceOnBody = new Vector3[bodies.length];

        StdDraw.setCanvasSize(500, 500);
        StdDraw.setXscale(-2*AU,2*AU);
        StdDraw.setYscale(-2*AU,2*AU);
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.BLACK);

        double seconds = 0;

        // simulation loop
        while(true) {

            seconds++; // each iteration computes the movement of the celestial bodies within one second.

            // for each body (with index i): compute the total force exerted on it.
            for (int i = 0; i < bodies.length; i++) {
                forceOnBody[i] = new Vector3(); // begin with zero
                for (int j = 0; j < bodies.length; j++) {
                    if (i == j) continue;
                    Vector3 forceToAdd = gravitationalForce(bodies[i], bodies[j]);
                    forceOnBody[i] = plus(forceOnBody[i],forceToAdd);
                }
            }
            // now forceOnBody[i] holds the force vector exerted on body with index i.

            // for each body (with index i): move it according to the total force exerted on it.
            for (int i = 0; i < bodies.length; i++) {
                Vector3 newPosition = plus(
                                            plus(bodies[i].position,
                                                 times(forceOnBody[i], 1/bodies[i].mass)
                                                 // F = m*a -> a = F/m
                                            ),
                                            bodies[i].currentMovement
                                      );

                Vector3 newMovement = minus(newPosition,bodies[i].position); // new minus old position.

                bodies[i].position = newPosition;
                bodies[i].currentMovement = newMovement;

            }

            // show all movements in StdDraw canvas only every 3 hours (to speed up the simulation)
            if (seconds%(3*3600) == 0) {
                // clear old positions (exclude the following line if you want to draw orbits).
                StdDraw.clear(StdDraw.BLACK);

                // draw new positions
                for (int i = 0; i < bodies.length; i++) {
                    StdDraw.setPenColor(bodies[i].color);
                    StdDraw.filledCircle(bodies[i].position.x, bodies[i].position.y,
                            1e9*Math.log10(bodies[i].radius));
                            // use log10 because of large variation of radii.
                }

                // show new positions
                StdDraw.show();
            }

        }

    }

    //TODO: remove static methods below.

    // Returns a vector representing the gravitational force exerted by body 'b2' on body 'b1'.
    // The gravitational Force F is calculated by F = G*(m1*m2)/(r*r), with m1 and m2 being the masses of the objects
    // interacting, r being the distance between the centers of the masses and G being the gravitational constant.
    // To calculate the force exerted on b1, simply multiply the normalized vector pointing from b1 to b2 with the
    // calculated force
    public static Vector3 gravitationalForce(Body b1, Body b2) {
        Vector3 direction = minus(b2.position,b1.position);
        double distance = length(direction);
        normalize(direction);
        double force = G*b1.mass*b2.mass/(distance * distance);
        return times(direction,force);
    }

    // Returns the norm of v1-v2.
    public static double distance(Vector3 v1, Vector3 v2) {
        double dX = v1.x-v2.x;
        double dY = v1.y-v2.y;
        double dZ = v1.z-v2.z;

        return Math.sqrt(dX*dX+dY*dY+dZ*dZ);
    }

    // Returns v1+v2.
    public static Vector3 plus(Vector3 v1, Vector3 v2) {

        Vector3 result = new Vector3();
        result.x = v1.x+v2.x;
        result.y = v1.y+v2.y;
        result.z = v1.z+v2.z;

        return result;
    }

    // Returns v1-v2.
    public static Vector3 minus(Vector3 v1, Vector3 v2) {

        Vector3 result = new Vector3();
        result.x = v1.x-v2.x;
        result.y = v1.y-v2.y;
        result.z = v1.z-v2.z;

        return result;
    }

    // Returns v*d.
    public static Vector3 times(Vector3 v, double d) {

        Vector3 result = new Vector3();
        result.x = v.x*d;
        result.y = v.y*d;
        result.z = v.z*d;

        return result;
    }

    // Returns the norm of 'v'.
    public static double length(Vector3 v) {

        return distance(v,new Vector3()); // distance to origin.
    }

    // Normalizes the specified vector 'v': changes the length of the vector such that its length
    // becomes one. The direction and orientation of the vector is not affected.
    public static void normalize(Vector3 v) {

        double length = length(v);
        v.x/=length;
        v.y/=length;
        v.z/=length;
    }

}

//TODO: answer additional questions of 'Aufgabe1'.


