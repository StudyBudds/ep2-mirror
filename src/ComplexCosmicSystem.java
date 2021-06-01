import java.awt.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

//This class represents a double-linked list for objects of class 'CosmicComponent'.
public class ComplexCosmicSystem implements CosmicComponent, CosmicSystemIndex {

    //TODO: Define variables.
    private String name;
    private MyNodeRecursiveCosmicComponent head, tail;
    private int size;
    // Initialises this system as an empty system with a name.
//    public ComplexCosmicSystem(String name) {
//        //TODO: implement constructor.
//        this.name = name;
//    }

    public ComplexCosmicSystem(String name, CosmicComponent c1, CosmicComponent c2,
                               CosmicComponent... ci) {
        this.name = name;
        this.head = tail = new MyNodeRecursiveCosmicComponent(c1);
        this.add(c2);
        for(CosmicComponent c : ci) {
            this.add(c);
        }
    }

    public boolean add(CosmicComponent body) {
        if (head == null) {
            this.tail = this.head = new MyNodeRecursiveCosmicComponent(body);
            size++;
            return true;
        }
        boolean res = this.head.add(body);
        if (res) {
            size++;
            tail = tail.next;
        }

        return res;
    }

    @Override
    public String getName() {
        return this.name;
    }

//    public CosmicComponent get(int i) {
//        return head != null ? head.getAt(i) : null;
//    }
//
//    public CosmicComponent find(String name) {
//        return head != null ? head.find(name) : null;
//    }
//
//    public CosmicComponent find(CosmicComponent body) {
//        return find(body.getName());
//    }

    public int size() {
//        if(head != null) {
//            return head.size();
//        }
//        return 0;
        return size;
    }

    public boolean add(int i, CosmicComponent body) {
        if (this.head == null) {
            if (i == 0) {
                return add(body);
            }
            return false;
        } else if (i == 0) {
            MyNodeRecursiveCosmicComponent newNode = new MyNodeRecursiveCosmicComponent(body, null, head);
            this.head.setPrev(newNode);
            this.head = newNode;
            size++;
            return true;
        } else if (i == size) {
            MyNodeRecursiveCosmicComponent newNode = new MyNodeRecursiveCosmicComponent(body, tail);
            this.tail.setNext(newNode);
            this.tail = newNode;
            size++;
            return true;
        }
        if (this.head.add(i, body)) {
            size++;
            return true;
        }
        return false;
    }

    public boolean remove(int i) {
        if (this.head == null) {
            return false;
        } else if (i == 0) {
            this.head = this.head.next;
            if (this.head != null) {
                this.head.setPrev(null);
            }
            size--;
            return true;
        } else if (i == size - 1) {
            this.tail = this.tail.prev;
            if (this.tail != null) {
                this.tail.setNext(null);
            }
            size--;
            return true;
        } else if (this.head.remove(i)) {
            size--;
            return true;
        }
        return false;
    }

    public boolean remove(CosmicComponent b) {
        if (this.head == null) {
            return false;
        } else if (head.getVal().getName().equals(b.getName())) {
            this.head = this.head.next;
            if (this.head != null) {
                this.head.setPrev(null);
            }
            size--;
            return true;
        } else if (tail.getVal().getName().equals(b.getName())) {
            this.tail = this.tail.prev;
            if (this.tail != null) {
                this.tail.setNext(null);
            }
            size--;
            return true;
        } else if (this.head.remove(b)) {
            size--;
            return true;
        }
        return false;
    }

    @Override
    public BodyCollection getBodies() {
        return new MyBodyList(this);
    }

    public Vector3 getMassCenter() {
        return head == null ? new Vector3() : head.getMassCenter().divide(head.getMass());
    }

    public double getMass() {
        return head == null ? 0 : head.getMass();
    }

    public String toString() {
        return head == null ? "" : head.toString();
    }

    public int numberOfBodies() {
        return head == null ? 0 : head.numberOfBodies();
    }

    public boolean contains(Body b) {
        return this.getParent(b) != null;
    }



    public MyNodeRecursiveCosmicComponent getHead() {
        return this.head;
    }

    public ComplexCosmicSystem getParent(Body b) {
        MyNodeRecursiveCosmicComponent current = head;
        while(current != null) {
            if(current.getVal() instanceof Body) { // only works for Body
                if(b.equals(current.getVal())) {
                    return this;
                }
            }
            else if(current.getVal() instanceof ComplexCosmicSystem) {
                ComplexCosmicSystem res = ((ComplexCosmicSystem) current.getVal()).getParent(b);
                if(res != null) {
                    return res;
                }
            }
            current = current.getNext();
        }
        return null;
    }

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
        return new MyRecursiveIterator(this);
    }

    public static class MyIterator implements BodyIterator {

        private MyNodeRecursiveCosmicComponent current;
        private MyIterator parent;

        public MyIterator(MyNodeRecursiveCosmicComponent current) {
            this.current = current;
        }

        public MyIterator(MyNodeRecursiveCosmicComponent current, MyIterator parent) {
            this.current = current;
            this.parent = parent;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Body next() {
            CosmicComponent c = current.getVal();
            current = current.getNext();
            while(current == null && parent != null) {
                current = parent.current;
                parent = parent.parent;
            }
            if(c.getClass() == Body.class) {
                return (Body) c;
            }
            if(c.getClass() == ComplexCosmicSystem.class) {
                ComplexCosmicSystem system = (ComplexCosmicSystem) c;
                parent = new MyIterator(system.getHead(), parent);
                return next();
            }
            return null;
        }
    }

    public static class MyComplexIterator implements BodyIterator {
        private MyNodeRecursiveCosmicComponent node;
        private MyComplexIterator parent;

        public MyComplexIterator(MyNodeRecursiveCosmicComponent node) {
            this(node, null);
        }

        public MyComplexIterator(MyNodeRecursiveCosmicComponent node, MyComplexIterator parent) {
            this.node = node;
            this.parent = parent;
        }

        @Override
        public boolean hasNext() {
            return node != null || parent != null;
        }

        @Override
        public Body next() {
            if (node == null) {
                if(parent != null) {
                    while(node == null && parent != null) {
                        node = parent.node;
                        parent = parent.parent;
                    }
                    if(node == null) {
                        return null;
                    }
                }
                else{
                    return null;
                }
            }
            CosmicComponent temp = node.getVal();
            if(temp instanceof Body) {
                node = node.next;
                return (Body)temp;
            }
            else if(temp instanceof ComplexCosmicSystem){
                parent = new MyComplexIterator(node.next, parent);
                node = ((ComplexCosmicSystem) temp).getHead();
                return this.next();
            }
            else {
                return null;
            }
        }
    }

    public static class MyStackIterator implements BodyIterator {
        private MyNodeRecursiveCosmicComponent node;
        private MyGenericStack<ComplexCosmicSystem> stack = new MyGenericStack<>();

        public MyStackIterator(MyNodeRecursiveCosmicComponent node) {
            this.node = node;
        }

        @Override
        public boolean hasNext() {
            return node != null || stack.peek() != null;
        }

        @Override
        public Body next() {
            ComplexCosmicSystem top = stack.peek();
            if(node == null) {
                if(top == null) {
                    stack.pop();
                    return null;
                }
                node = top.getHead();
                stack.pop();
            }
            CosmicComponent c = node.getVal();
            if(c instanceof Body) {
                node = node.next;
                return (Body) c;
            }
            if(c instanceof ComplexCosmicSystem) {
                stack.push((ComplexCosmicSystem) c);
                node = node.next;
                return next();
            }
            return null;
        }
    }

    public static class MyRecursiveIterator implements BodyIterator {

        private MyNodeRecursiveCosmicComponent node;
        private BodyIterator currentIterator;
        private ComplexCosmicSystem parent;


        public MyRecursiveIterator(ComplexCosmicSystem c) {
            parent = c;
            node = c.getHead();
            currentIterator = node.val.iterator();
        }

        @Override
        public boolean hasNext() {
            return node.hasNext() || currentIterator.hasNext();
        }

        @Override
        public Body next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            if(!currentIterator.hasNext()) {
                this.node = this.node.next;
                if(node.getVal() instanceof Body) {
                    this.currentIterator = ((Body)(this.node.val)).iterator(this.parent);
                }
                else {
                    this.currentIterator = this.node.val.iterator();
                }
            }
            return currentIterator.next();
        }

        @Override
        public void remove() {
            currentIterator.remove();
        }
    }

    public static class MyNodeRecursiveCosmicComponent {
        private MyNodeRecursiveCosmicComponent next, prev;
        private final CosmicComponent val;

        public MyNodeRecursiveCosmicComponent(CosmicComponent body) {
            this(body, null, null);
        }

        public MyNodeRecursiveCosmicComponent(CosmicComponent body, MyNodeRecursiveCosmicComponent prev) {
            this(body, prev, null);
        }

        public MyNodeRecursiveCosmicComponent(CosmicComponent body, MyNodeRecursiveCosmicComponent prev, MyNodeRecursiveCosmicComponent next) {
            this.val = body;
            this.prev = prev;
            this.next = next;
        }

        public MyNodeRecursiveCosmicComponent getNext() {
            return next;
        }

        public void setNext(MyNodeRecursiveCosmicComponent next) {
            this.next = next;
        }

        public void setPrev(MyNodeRecursiveCosmicComponent prev) {
            this.prev = prev;
        }

        public boolean hasNext() {
            return next != null;
        }

        public CosmicComponent getVal() {
            return val;
        }

        public boolean add(CosmicComponent body) {
            if(body == null) return false;
            if (val.getName().equals(body.getName())) {
                return false;
            } else if (next == null) {
                next = new MyNodeRecursiveCosmicComponent(body);
                next.prev = this;
                return true;
            } else {
                return next.add(body);
            }
        }

        public CosmicComponent getAt(int i) {
            if (i == 0) {
                return val;
            } else if (!hasNext()) {
                return null;
            } else {
                return next.getAt(i - 1);
            }
        }

        public CosmicComponent find(String name) {
            if (val.getName().equals(name)) {
                return this.val;
            }
            return hasNext() ? next.find(name) : null;
        }

        public int size() {
            return hasNext() ? next.size() + 1 : 1;
        }

        public boolean add(int i, CosmicComponent body) {
            if (i == 0) {
                MyNodeRecursiveCosmicComponent node = new MyNodeRecursiveCosmicComponent(body, this.prev, this);
                this.prev = node;
                if (node.prev != null) {
                    node.prev.next = node;
                }
                return true;
            } else if (!hasNext() && i == 1) {
                return add(body);
            } else if (!hasNext()) {
                return false;
            } else {
                return next.add(i - 1, body);
            }
        }

        public int numberOfBodies() {
            return this.val.numberOfBodies() + (next == null ? 0 : this.next.numberOfBodies());
        }

        public boolean remove(int i) {
            if (i == 0) {
                if (this.prev != null) {
                    this.prev.next = this.next;
                }
                if (this.next != null) {
                    this.next.prev = this.prev;
                }
                return true;
            } else if (!hasNext()) {
                return false;
            } else {
                return this.next.remove(i - 1);
            }
        }

        public boolean remove(CosmicComponent b) {
            if (this.val.getName().equals(b.getName())) {
                return remove(0);
            } else if (!hasNext()) {
                return false;
            } else {
                return next.remove(b);
            }
        }

        public Vector3 getMassCenter() {
            return val.getMassCenter().times(val.getMass())
                    .plus(next == null ? new Vector3() : next.getMassCenter());
        }

        public double getMass() {
            return val.getMass() + (next == null ? 0 : next.getMass());
        }

        public String toString() {
            return this.val.toString() + (next == null ? "" : ", " + next.toString());
        }

        public int hashCode() {
            return this.val.hashCode() + (this.next == null ? 0 : this.next.hashCode());
        }


    }


}

//TODO: Define additional class(es) implementing the double-linked list (either here or outside class).