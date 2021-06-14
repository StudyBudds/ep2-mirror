//TODO: comment.
public class ComplexNonNullNode implements ComplexNode {

    private final CosmicComponent comp;
    private ComplexNode next,prev;

    public ComplexNonNullNode(CosmicComponent c) {
        this.comp = c;
        this.next = ComplexNullNode.NIL;
        this.prev = ComplexNullNode.NIL;
    }

    @Override
    public ComplexNode remove(ComplexNonNullNode c) {
        if(this.comp.equals(c.comp)) {
            c.prev = prev;
            return next;
        }
        next = next.remove(c);
        return this;
    }

    @Override
    public ComplexNode add(ComplexNonNullNode c) {
        this.next = next.add(c);
        next.setPrev(this);
        return this;
    }

    @Override
    public CosmicComponent getValue() {
        return this.comp;
    }

    @Override
    public ComplexNode getNext() {
        return this.next;
    }

    @Override
    public boolean hasNext() {
        return this.next != ComplexNullNode.NIL;
    }

    @Override
    public double getMass() {
        return this.comp.getMass() + next.getMass();
    }

    @Override
    public Vector3 getMassCenter() {
        return this.comp.getMassCenter().times(this.comp.getMass()).plus(next.getMassCenter());
    }

    @Override
    public int numberOfBodies() {
        return this.comp.numberOfBodies() + next.numberOfBodies();
    }

    @Override
    public void setPrev(ComplexNode prev) {
        this.prev = prev;
    }

    @Override
    public boolean contains(CosmicComponent comp) {
        return this.comp.equals(comp) || next.contains(comp);
    }

    @Override
    public String toString() {
        return this.comp + (this.hasNext() ? ", " + this.next : "");
    }

    @Override
    public int hashCode() {
        return this.comp.hashCode() + next.hashCode();
    }

}
