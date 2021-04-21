import java.awt.*;

// This class represents celestial bodies like stars, planets, asteroids, etc..
public class Body implements CosmicComponent {

    private String name;
    private double mass;
    private double radius;
    private Vector3 position, currentMovement, force;
    private Color color; // for drawing the body.

    public Body(String name, double mass, double radius, Vector3 position, Vector3 currentMovement, Color color) {
        this.name = name;
        this.mass = mass;
        this.radius = radius;
        this.position = position;
        this.currentMovement = currentMovement;
        this.color = color;
    }

    // Returns the distance between this body and the specified 'body'.
    public double distanceTo(Body body) {
        return this.position.distanceTo(body.position);
    }

    //Returns a vector representing the gravitational force exerted by 'body' on this body.
    //The gravitational Force F is calculated by F = G*(m1*m2)/(r*r), with m1 and m2 being the masses of the objects
    //interacting, r being the distance between the centers of the masses and G being the gravitational constant.
    //To calculate the force exerted on b1, simply multiply the normalized vector pointing from b1 to b2 with the
    //calculated force
    public Vector3 gravitationalForce(Body body) {
        Vector3 direction = body.position.minus(this.position);

        double distance = this.distanceTo(body);
        direction.normalize();

        double force = Simulation.G * body.mass * this.mass / (distance * distance);

        return direction.times(force);
    }

    // Moves this body to a new position, according to the specified force vector 'force' exerted
    // on it, and updates the current movement accordingly.
    // (Movement depends on the mass of this body, its current movement and the exerted force)
    // Hint: see simulation loop in Simulation.java to find out how this is done
    public void move(Vector3 force) {
        Vector3 newPosition = this.position
                .plus(force.times(1/this.mass))
                .plus(this.currentMovement);
        //Vector3 newMovement = this.position.minus(newPosition);
        this.currentMovement = newPosition.minus(this.position); // new minus old position.
        this.position = newPosition;
    }

    public void move() {
        this.move(force);
    }

    // Returns a string with the information about this body including
    // name, mass, radius, position and current movement. Example:
    // "Earth, 5.972E24 kg, radius: 6371000.0 m, position: [1.48E11,0.0,0.0] m, movement: [0.0,29290.0,0.0] m/s."
    public String toString() {

        return this.name;
    }

    // Draws the body to the current StdDraw canvas as a dot using 'color' of this body.
    // The radius of the dot is in relation to the radius of the celestial body
    // (use a conversion based on the logarithm as in 'Simulation.java').
    // Hint: use the method drawAsDot implemented in Vector3 for this
    public void draw() {
        this.position.drawAsDot(1e9*Math.log10(this.radius), this.color);
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int numberOfBodies() {
        return 1;
    }

    @Override
    public double getMass() {
        return mass;
    }

    @Override
    public Vector3 getMassCenter() {
        return this.position;
    }

    public Color getColor() {
        return this.color;
    }

    public void setForce(Vector3 force) {
        this.force = force;
    }

    public void switchMassAndRadius(Body body) {
        double tempMass = this.mass;
        double tempRadius = this.radius;

        this.radius = body.radius;
        this.mass = body.mass;

        body.mass = tempMass;
        body.radius = tempRadius;
    }

}

