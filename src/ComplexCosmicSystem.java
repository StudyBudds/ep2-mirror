import java.awt.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

//This class represents a double-linked list for objects of class 'CosmicComponent'.
public class ComplexCosmicSystem implements CosmicComponent, CosmicSystemIndex {

    //TODO: Define variables.
    private String name;
    private ComplexNode head, tail;
    private int size;

    public ComplexCosmicSystem(String name, CosmicComponent c1, CosmicComponent c2,
                               CosmicComponent... ci) {
        this.name = name;
        this.head = tail = ComplexNullNode.NIL;
        this.add(c1);
        this.add(c2);
        for(CosmicComponent c : ci) {
            this.add(c);
        }
    }



    public boolean add(CosmicComponent comp) {
        if(head == ComplexNullNode.NIL) {
            this.head = this.tail = new ComplexNonNullNode(comp);
            return true;
        }

        if(head.contains(comp)) {
            return false;
        }
        ComplexNonNullNode newTail = new ComplexNonNullNode(comp);
        tail.add(newTail);
        tail = newTail;
        return true;
    }

    public boolean remove(CosmicComponent comp) {
        if(!head.contains(comp)) {
            return false;
        }
        if(this.tail.getValue().equals(comp)) {
            head.remove(new ComplexNonNullNode(comp));
        }
        this.head = head.remove(new ComplexNonNullNode(comp));

        return true;
    }

    public ComplexCosmicSystem getParent(Body b) {
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
            return node.getNext() != null || currentIterator.hasNext();
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

//    public static class MyNodeRecursiveCosmicComponent {
//        private MyNodeRecursiveCosmicComponent next, prev;
//        private final CosmicComponent val;
//
//        public MyNodeRecursiveCosmicComponent(CosmicComponent body) {
//            this(body, null, null);
//        }
//
//        public MyNodeRecursiveCosmicComponent(CosmicComponent body, MyNodeRecursiveCosmicComponent prev) {
//            this(body, prev, null);
//        }
//
//        public MyNodeRecursiveCosmicComponent(CosmicComponent body, MyNodeRecursiveCosmicComponent prev, MyNodeRecursiveCosmicComponent next) {
//            this.val = body;
//            this.prev = prev;
//            this.next = next;
//        }
//
//        public MyNodeRecursiveCosmicComponent getNext() {
//            return next;
//        }
//
//        public void setNext(MyNodeRecursiveCosmicComponent next) {
//            this.next = next;
//        }
//
//        public void setPrev(MyNodeRecursiveCosmicComponent prev) {
//            this.prev = prev;
//        }
//
//        public boolean hasNext() {
//            return next != null;
//        }
//
//        public CosmicComponent getVal() {
//            return val;
//        }
//
//        public boolean add(CosmicComponent body) {
//            if(body == null) return false;
//            if (val.getName().equals(body.getName())) {
//                return false;
//            } else if (next == null) {
//                next = new MyNodeRecursiveCosmicComponent(body);
//                next.prev = this;
//                return true;
//            } else {
//                return next.add(body);
//            }
//        }
//
//        public CosmicComponent getAt(int i) {
//            if (i == 0) {
//                return val;
//            } else if (!hasNext()) {
//                return null;
//            } else {
//                return next.getAt(i - 1);
//            }
//        }
//
//        public CosmicComponent find(String name) {
//            if (val.getName().equals(name)) {
//                return this.val;
//            }
//            return hasNext() ? next.find(name) : null;
//        }
//
//        public int size() {
//            return hasNext() ? next.size() + 1 : 1;
//        }
//
//        public boolean add(int i, CosmicComponent body) {
//            if (i == 0) {
//                MyNodeRecursiveCosmicComponent node = new MyNodeRecursiveCosmicComponent(body, this.prev, this);
//                this.prev = node;
//                if (node.prev != null) {
//                    node.prev.next = node;
//                }
//                return true;
//            } else if (!hasNext() && i == 1) {
//                return add(body);
//            } else if (!hasNext()) {
//                return false;
//            } else {
//                return next.add(i - 1, body);
//            }
//        }
//
//        public int numberOfBodies() {
//            return this.val.numberOfBodies() + (next == null ? 0 : this.next.numberOfBodies());
//        }
//
//        public boolean remove(int i) {
//            if (i == 0) {
//                if (this.prev != null) {
//                    this.prev.next = this.next;
//                }
//                if (this.next != null) {
//                    this.next.prev = this.prev;
//                }
//                return true;
//            } else if (!hasNext()) {
//                return false;
//            } else {
//                return this.next.remove(i - 1);
//            }
//        }
//
//        public boolean remove(CosmicComponent b) {
//            if (this.val.getName().equals(b.getName())) {
//                return remove(0);
//            } else if (!hasNext()) {
//                return false;
//            } else {
//                return next.remove(b);
//            }
//        }
//
//        public Vector3 getMassCenter() {
//            return val.getMassCenter().times(val.getMass())
//                    .plus(next == null ? new Vector3() : next.getMassCenter());
//        }
//
//        public double getMass() {
//            return val.getMass() + (next == null ? 0 : next.getMass());
//        }
//
//        public String toString() {
//            return this.val.toString() + (next == null ? "" : ", " + next.toString());
//        }
//
//        public int hashCode() {
//            return this.val.hashCode() + (this.next == null ? 0 : this.next.hashCode());
//        }
//
//
//    }


}

//TODO: Define additional class(es) implementing the double-linked list (either here or outside class).