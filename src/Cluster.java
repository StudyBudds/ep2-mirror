public interface Cluster extends BodyIterable {

    //adds the Body to the cluster and returns the changed cluster 'this'
    Cluster add(Body b);

    //returns the number of bodies contained in this cluster (and its sub-clusters)
    int numberOfBodies();

    //returns the mass of the main body of this cluster
    double getMainMass();

}
