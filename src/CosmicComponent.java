public interface CosmicComponent {
    //Returns the name of the component.
    String getName();

    //Returns the number of bodies contained in the component and its children.
    int numberOfBodies();

    //Returns the overall mass (sum of all contained components).
    double getMass();

    //Returns the gravitational center of this component (weighted average of contained components).
    Vector3 getMassCenter();
}
