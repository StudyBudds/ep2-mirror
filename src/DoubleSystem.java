import java.util.NoSuchElementException;

public class DoubleSystem implements Cluster {

    private Body main;
    private Cluster rest;

    //the constructor should throw a DoubleSystemIllegalArgumentException (to be implemented), if one of the arguments is null or the radius of main is smaller than the radius of secondary
    DoubleSystem(Body main, Cluster rest) throws DoubleSystemIllegalArgumentException {
        if(main == null) {
            throw new DoubleSystemIllegalArgumentException("parameter main cannot be null");
        }
        if(rest == null) {
            throw new DoubleSystemIllegalArgumentException("parameter rest cannot be null");
        }
        if(main.getRadius() < rest.getRadius()) {
            throw new DoubleSystemIllegalArgumentException("radius of main cannot be smaller than radius of secondary");
        }

        this.main = main;
        this.rest = rest;
    }

    @Override
    //adds a Body b to the DoubleSystem such that the radius of the main body is larger or equal than the main body of 'rest'
    //returns the changed DoubleSystem 'this'
    public Cluster add(Body b) {
        if(b.getRadius() >= rest.getRadius()) {
            rest = rest.add(main);
            main = b;
        }
        else {
            rest = rest.add(b);
        }
        return this;
    }

    @Override
    //returns the body with largest size in the overall system
    public Body getLargest() {
        return this.main;
    }
    
    //returns the number of bodies contained in this cluster (and its sub-clusters)
    public int numberOfBodies() {
        return 1 + rest.numberOfBodies();
    }

    @Override
    //returns the radius of the main body 
    public double getRadius() {
        return main.getRadius();
    }

    @Override
    //returns an iterator over all bodies
    public BodyIterator iterator() {
        //TODO
        return new DoubleSysIter(main.iterator(), rest.iterator());
    }

    //returns a String representation of the double system in the form of [main body + secondary cluster]
    //e.g. [Sol + [Earth + Moon]]
    public String toString() {
        //TODO
        return "[" + main + " + " + rest + "]";
    }

    public static class DoubleSysIter implements BodyIterator {

        private final BodyIterator mainIter, restIter;

        public DoubleSysIter(BodyIterator mainIter, BodyIterator restIter) {
            this.mainIter = mainIter;
            this.restIter = restIter;
        }


        @Override
        public boolean hasNext() {
            return mainIter.hasNext() || restIter.hasNext();
        }

        @Override
        public Body next() {
            if(!hasNext()) {
                throw new NoSuchElementException("No more elements");
            }
            if(mainIter.hasNext()) {
                return mainIter.next();
            }
            return restIter.next();
        }
    }
}