public class ComplexNullNode implements ComplexNode {

    public static final ComplexNode NIL = new ComplexNullNode();

    private ComplexNullNode() {}

    @Override
    public ComplexNode remove(ComplexNonNullNode c) {
        return this;
    }

    @Override
    public ComplexNode add(ComplexNonNullNode c) {
        return c;
    }

    @Override
    public CosmicComponent getValue() {
        return null;
    }

    @Override
    public ComplexNode getNext() {
        return null;
    }

    @Override
    public Vector3 getMassCenter() {
        return null;
    }

    @Override
    public double getMass() {
        return 0;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public int numberOfBodies() {
        return 0;
    }

    @Override
    public void setPrev(ComplexNode prev) {}

    @Override
    public boolean contains(CosmicComponent comp) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "";
    }

}
