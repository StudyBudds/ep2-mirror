public class MyLinkedListIterative implements MyLinkedList {

    private MyNodeIterative head;
    private int size;

    @Override
    public boolean add(Body body) {
        if(head == null) {
            head = new MyNodeIterative(body);
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
        curr.setNext(new MyNodeIterative(body));
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
        MyNodeIterative curr = head;
        while(curr != null) {
            size++;
            curr = curr.getNext();
        }
        return size;
    }

    public class MyNodeIterative {
        private MyNodeIterative next;
        private Body val;
        public MyNodeIterative(Body body) {
            this.val = body;
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

        public boolean hasNext() {
            return next != null;
        }
    }
}
