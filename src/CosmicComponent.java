public interface CosmicComponent {
    //returns the name of the component
    String getName();

    //returns the number of bodies contained in the component and its children
    int numberOfBodies();

    //returns the overall mass (sum of all contained components)
    double getMass();

    // returns the gravitational center of this component (weighted average of contained components)
    Vector3 getMassCenter();
}
