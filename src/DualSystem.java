import java.util.NoSuchElementException;

public class DualSystem implements Cluster {

    private Body main;
    private Cluster secondary;
    //the constructor should throw a DualSystemIllegalArgumentException (to be implemented), if one of the arguments is null or the mass of main is smaller than the mass of secondary
    DualSystem(Body main, Cluster secondary) throws DualSystemIllegalArgumentException {
        if(main == null || secondary == null || main.getMainMass() < secondary.getMainMass()) {
            throw new DualSystemIllegalArgumentException("main is smaller than secondary");
        }
        this.main = main;
        this.secondary = secondary;
    }

    @Override
    //adds the Body b to the DualSystem such that the mass of the main body is larger or equal than the 'main' body of 'secondary'
    //returns the changed DualSystem 'this'
    public Cluster add(Body b) {
        if (b.getMainMass() > this.getMainMass()) {
            secondary = secondary.add(main);
            main = b;
        } else {
            secondary = secondary.add(b);
        }
        return this;
    }

    @Override
    //returns the overall number of bodies in the system
    public int numberOfBodies() {
        return 1 + secondary.numberOfBodies();
    }

    @Override
    //returns the mass of the main body
    public double getMainMass() {
        return main.getMainMass();
    }

    @Override
    //returns an iterator over all bodies
    public BodyIterator iterator() {
        return new ClusterIterator(main,secondary);
    }

    //returns a String representation of the dual system in the form of [main body + secondary cluster]
    //e.g. [Sol + [Earth + Moon]]
    public String toString() {
        //TODO
        return "[" + main.getName() + " + " +  secondary + "]";
    }

    public static class ClusterIterator implements BodyIterator {

        private final BodyIterator mainIter, secondaryIter;

        public ClusterIterator(Body main, Cluster secondary) {
            this.mainIter = main.iterator();
            this.secondaryIter = secondary.iterator();
        }

        @Override
        public boolean hasNext() {
            return this.mainIter.hasNext() || this.secondaryIter.hasNext();
        }

        @Override
        public Body next() {
            if(!hasNext()) {
                throw new NoSuchElementException("bruh");
            }
            if(mainIter.hasNext()) {
                return mainIter.next();
            }
            return secondaryIter.next();
        }
    }
}
