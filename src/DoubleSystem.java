import java.io.IOException;

public class DoubleSystem implements Cluster {

    //TODO: define object variables

    //TODO: implement constructor
    //the constructor should throw a DoubleSystemIllegalArgumentException (to be implemented), if one of the arguments is null or the radius of main is smaller than the radius of secondary
    DoubleSystem(Body main, Cluster rest) throws DoubleSystemIllegalArgumentException {

    }

    @Override
    //adds a Body b to the DoubleSystem such that the radius of the main body is larger or equal than the main body of 'secondary'
    //returns the changed DoubleSystem 'this'
    public Cluster add(Body b) {
        //TODO
        return this;
    }

    @Override
    //returns the body with largest size in the overall system
    public Body getLargest() {
        //TODO
        return null;
    }
    
    //returns the number of bodies contained in this cluster (and its sub-clusters)
    int numberOfBodies() {
        //TODO
        return -1;
    }

    @Override
    //returns the radius of the main body 
    public double getRadius() {
        //TODO
        return -1.0;
    }

    @Override
    //returns an iterator over all bodies
    public BodyIterator iterator() {
        //TODO
        return null;
    }

    //returns a String representation of the double system in the form of [main body + secondary cluster]
    //e.g. [Sol + [Earth + Moon]]
    public String toString() {
        //TODO
        return "";
    }
}