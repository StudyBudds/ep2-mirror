public class MyLinkedListIterative implements MyLinkedList {

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
        while(curr.hasNext()) {
            if(curr.getVal().getName().equals(body.getName())) {
                return false;
            }
            curr = curr.getNext();
        }
        if(curr.getVal().getName().equals(body.getName())) {
            return false;
        }
        curr.setNext(new MyNodeIterative(body, curr));
        tail = tail.next;
        size++;
        return true;
    }

    @Override
    public Body get(int i) {
        MyNodeIterative curr = head;
        while(curr != null) {
            if(i == 0) {
                return curr.val;
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
        int size = 0;
        for(MyNodeIterative curr = head; curr != null; curr = curr.getNext(), size++);
        return size;
    }

    @Override
    public boolean add(int i, Body body) {
        if(head == null || find(body.getName()) != null) {
            return false;
        }
        if(i == 0) {
            head.setPrev(new MyNodeIterative(body));
            head = head.prev;
            size++;
            return true;
        }
        MyNodeIterative curr = head;
        while(curr.hasNext()) {
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
            head = head.next;
            if(head != null) {
                head.setPrev(null);
            }
        }
        MyNodeIterative curr = head;
        while(curr.hasNext()) {
            if(i == 0) {
                if(curr.getPrev() != null) {
                    curr.getPrev().setNext(curr.getNext());
                }
                if(curr.getNext() != null) {
                    curr.getNext().setPrev(curr.getPrev());
                }
                return true;
            }
            i--;
            curr = curr.getNext();
        }
        if(i == 0) {
            curr.getPrev().setNext(null);
            tail = tail.getPrev();
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Body b) {
        if(head.getVal().getName().equals(b.getName())) {
            head = head.next;
            if(head != null) {
                head.setPrev(null);
            }
            return true;
        }
        MyNodeIterative curr = head;
        while(curr.hasNext()) {
            if(curr.getVal().getName().equals(b.getName())) {
                if(curr.getPrev() != null) {
                    curr.getPrev().setNext(curr.getNext());
                }
                if(curr.getNext() != null) {
                    curr.getNext().setPrev(curr.getPrev());
                }
                return true;
            }
            curr = curr.getNext();
        }
        if(curr.getVal().getName().equals(b.getName())) {
            curr.getPrev().setNext(null);
            tail = tail.getPrev();
            return true;
        }
        return false;
    }

    @Override
    public MyLinkedList reverse() {
        MyLinkedList newList = new MyLinkedListIterative();
        for(int i = size()-1; i >= 0; i--) {
            newList.add(get(i));
        }
        return newList;
    }

    public static class MyNodeIterative {
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
