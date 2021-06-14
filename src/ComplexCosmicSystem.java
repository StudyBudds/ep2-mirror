import java.awt.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

//This class represents a double-linked list for objects of class 'CosmicComponent'.
public class ComplexCosmicSystem implements CosmicComponent, CosmicSystemIndex {

    //TODO: Define variables.
    private final String name;
    private ComplexNode head;
    private int size;

    public ComplexCosmicSystem(String name, CosmicComponent c1, CosmicComponent c2,
                               CosmicComponent... ci) {
        // {V}
        assert c1 != null && c2 != null;
        this.name = name;
        this.head = ComplexNullNode.NIL;
        this.add(c1);
        this.add(c2);
        // {I}
        assert ci != null;
        for(CosmicComponent c : ci) {
            // {I}
            assert c != null;
            this.add(c);
        }
    }


    public boolean add(CosmicComponent comp) {
        // {V}
        assert comp != null;
        if(head.contains(comp)) {
            return false;
        }
        head = head.add(new ComplexNonNullNode(comp));
        return true;
    }

    public boolean remove(CosmicComponent comp) {
        // {V}
        assert comp != null;
        if(!head.contains(comp)) {
            return false;
        }
        this.head = head.remove(new ComplexNonNullNode(comp));

        return true;
    }

    public ComplexCosmicSystem getParent(Body b) {
        // {V}
        assert b != null;
        ComplexNode current = head;
        while(current != null) {
            if(current.getValue() instanceof Body) { // only works for Body
                if(b.equals(current.getValue())) {
                    return this;
                }
            }
            else if(current.getValue() instanceof ComplexCosmicSystem) {
                ComplexCosmicSystem res = ((ComplexCosmicSystem) current.getValue()).getParent(b);
                if(res != null) {
                    return res;
                }
            }
            current = current.getNext();
        }
        return null;
    }

    public boolean contains(Body b) {
        // {V}
        assert b != null;
        return this.getParent(b) != null;
    }

    @Override
    public BodyCollection getBodies() {
        return new MyBodyList(this);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int numberOfBodies() {
        return head.numberOfBodies();
    }

    public Vector3 getMassCenter() {
        return head.getMassCenter().divide(getMass());
    }

    public double getMass() {
        return head.getMass();
    }

    @Override
    public String toString() {
        return head.toString();
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }
        if(o == null || o.getClass() != this.getClass()) {
            return false;
        }
        ComplexCosmicSystem c = (ComplexCosmicSystem) o;
        if(numberOfBodies() != c.numberOfBodies()) return false;

        for(Body b : c) {
            if(!this.contains(b)) { // check if every body o is in this
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return head.hashCode();
    }

    @Override
    public BodyIterator iterator() {
        return new MyRecursiveIterator(this, head);
    }

    public static class MyRecursiveIterator implements BodyIterator {

        private ComplexNode node;
        private BodyIterator currentIterator;
        private final ComplexCosmicSystem system;
        private Body lastElem;


        public MyRecursiveIterator(ComplexCosmicSystem c, ComplexNode head) {
            system = c;
            node = head;
            currentIterator = node.getValue().iterator();
        }

        @Override
        public boolean hasNext() {
            return node.getNext() != ComplexNullNode.NIL || currentIterator.hasNext();
        }

        @Override
        public Body next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            if(!currentIterator.hasNext()) {
                this.node = this.node.getNext();
                this.currentIterator = this.node.getValue().iterator();
            }
            lastElem = currentIterator.next();
            return lastElem;
        }

        @Override
        public void remove() {
            if(lastElem == null) {
                throw new IllegalStateException();
            }
            system.getParent(lastElem).remove(lastElem);
            lastElem = null;
        }
    }
}

//TODO: Define additional class(es) implementing the double-linked list (either here or outside class).