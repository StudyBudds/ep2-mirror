public class MyLinkedListIterative implements MyLinkedList, BodyIterable {

    private MyNodeIterative head, tail;
    private int size;

    @Override
    public boolean add(Body body) {
        if(head == null) {
            tail = head = new MyNodeIterative(body);
            size++;
            return true;
        }
        MyNodeIterative curr = head;
        while(curr != tail) {
            if(curr.getVal().getName().equals(body.getName())) {
                return false;
            }
            curr = curr.getNext();
        }
        if(curr.getVal().getName().equals(body.getName())) {
            return false;
        }
        curr.setNext(new MyNodeIterative(body, curr));
        tail = tail.getNext();
        size++;
        return true;
    }

    @Override
    public Body get(int i) {
        MyNodeIterative curr = head;
        while(curr != null) {
            if(i == 0) {
                return curr.getVal();
            }
            curr = curr.getNext();
            i--;
        }
        return null;
    }

    @Override
    public Body find(String name) {
        MyNodeIterative curr = head;
        while(curr != null) {
            if(curr.getVal().getName().equals(name)) {
                return curr.val;
            }
            curr = curr.getNext();
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(int i, Body body) {
        if(head == null) {
            return i == 0 && add(body);
        }
        else if(find(body.getName()) != null) {
            return false;
        }
        else if(i == 0) {
            head.setPrev(new MyNodeIterative(body));
            head = head.getPrev();
            size++;
            return true;
        }
        else if (i == size) {
            tail.setNext(new MyNodeIterative(body, tail));
            tail = tail.getNext();
            size++;
            return true;
        }
        MyNodeIterative curr = head;
        while(curr != tail) {
            if(i == 0) {
                MyNodeIterative temp = new MyNodeIterative(body, curr.getPrev(), curr.getNext());
                if(curr.getPrev() != null) {
                    curr.getPrev().setNext(temp);
                }
                curr.setPrev(temp);
                size++;
                return true;
            }
            i--;
            curr = curr.getNext();
        }
        if(i == 0) {
            MyNodeIterative temp = new MyNodeIterative(body, curr.getPrev(), curr);
            if(curr.getPrev() != null) {
                curr.getPrev().setNext(temp);
            }
            curr.setPrev(temp);
            size++;
            return true;
        }
        else if(i == 1) {
            curr.setNext(new MyNodeIterative(body, curr));
            tail = tail.getNext();
            size++;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(int i) {
        if(i == 0) {
            head = head.getNext();
            if(head != null) {
                head.setPrev(null);
            }
            size--;
            return true;
        }
        else if(i == size-1) {
            tail = tail.getPrev();
            if(tail != null) {
                tail.setPrev(null);
            }
            size--;
            return true;
        }
        MyNodeIterative curr = head;
        while(curr != tail) {
            if(i == 0) {
                if(curr.getPrev() != null) {
                    curr.getPrev().setNext(curr.getNext());
                }
                if(curr.getNext() != null) {
                    curr.getNext().setPrev(curr.getPrev());
                }
                size--;
                return true;
            }
            i--;
            curr = curr.getNext();
        }
        return false;
    }

    private MyNodeIterative getNode(int i) {
        MyNodeIterative curr = head;
        while(curr != null) {
            if(i == 0) {
                return curr;
            }
            curr = curr.getNext();
            i--;
        }
        return null;
    }

    @Override
    public boolean remove(Body b) {
        if(head.getVal().getName().equals(b.getName())) {
            head = head.next;
            if(head != null) {
                head.setPrev(null);
            }
            size--;
            return true;
        }
        MyNodeIterative curr = head;
        while(curr != tail) {
            if(curr.getVal().getName().equals(b.getName())) {
                if(curr.getPrev() != null) {
                    curr.getPrev().setNext(curr.getNext());
                }
                if(curr.getNext() != null) {
                    curr.getNext().setPrev(curr.getPrev());
                }
                size--;
                return true;
            }
            curr = curr.getNext();
        }
        if(curr.getVal().getName().equals(b.getName())) {
            curr.getPrev().setNext(null);
            tail = tail.getPrev();
            size--;
            return true;
        }
        return false;
    }

    public MyLinkedNode getHead() {
        return this.head;
    }

    @Override
    public BodyIterator iterator() {
        return null;
    }

    public static class Iter implements BodyIterator {

        private MyNodeIterative current;

        public Iter(MyNodeIterative head) {
            this.current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Body next() {
            Body b = current.getVal();
            current = current.getNext();
            return b;
        }
    }

    public static class MyNodeIterative implements MyLinkedNode {
        private MyNodeIterative next, prev;
        private final Body val;
        public MyNodeIterative(Body body) {
            this(body, null, null);
        }
        public MyNodeIterative(Body body, MyNodeIterative prev) {
            this(body, prev, null);
        }
        public MyNodeIterative(Body body, MyNodeIterative prev, MyNodeIterative next) {
            this.val = body;
            this.next = next;
            this.prev = prev;
        }

        public Body getVal() {
            return this.val;
        }

        public MyNodeIterative getNext() {
            return this.next;
        }

        public void setNext(MyNodeIterative node) {
            this.next = node;
        }

        public MyNodeIterative getPrev() {
            return this.prev;
        }

        public void setPrev(MyNodeIterative node) {
            this.prev = node;
        }

        public boolean hasNext() {
            return next != null;
        }
    }
}
