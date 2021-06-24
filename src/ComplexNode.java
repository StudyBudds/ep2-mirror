public interface ComplexNode {

    ComplexNode remove(ComplexNonNullNode c);

    ComplexNode add(ComplexNonNullNode c);

    CosmicComponent getValue();

    ComplexNode getNext();

    Vector3 getMassCenter();

    double getMass();

    boolean hasNext();

    int numberOfBodies();

    void setPrev(ComplexNode prev);

    boolean contains(CosmicComponent comp);
}
