public interface Cluster extends BodyIterable {

    //adds the Body to the cluster and returns the changed cluster 'this'
    Cluster add(Body b);

    //returns the body with largest radius in the cluster
    Body getLargest();

    //returns the number of bodies contained in this cluster (and its sub-clusters)
    int numberOfBodies();

    //returns the radius of the main body of the cluster
    double getRadius();

}
