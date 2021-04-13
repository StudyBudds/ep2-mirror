public class MyLinkedListRecursive implements MyLinkedList {
    private MyNodeRecursive head, tail;
    private int size;

    @Override
    public boolean add(Body body) {
        if(head == null) {
            this.tail = this.head = new MyNodeRecursive(body);
            size++;
            return true;
        }
        boolean res = this.head.add(body);
        if(res) {
            size++;
            tail = tail.next;
        }

        return res;
    }

    @Override
    public Body get(int i) {
        return head != null ? head.getAt(i) : null;
    }

    @Override
    public Body find(String name) {
        return head != null ? head.find(name) : null;
    }

    public Body find(Body body) {
        return find(body.getName());
    }

    @Override
    public int size() {
//        if(head != null) {
//            return head.size();
//        }
//        return 0;
        return size;
    }

    @Override
    public boolean add(int i, Body body) {
        if(this.head == null) {
            return false;
        }
        if(this.head.add(i, body)) {
            size++;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(int i) {
        if(this.head == null) {
            return false;
        }
        else if(i ==0) {
            this.head = this.head.next;
            if(this.head != null) {
                this.head.prev = null;
            }
            size--;
            return true;
        }
        else if(this.head.remove(i)) {
            size--;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Body b) {
        if(this.head == null) {
            return false;
        }
        else if(this.head.remove(b)) {
            size--;
            return true;
        }
        return false;
    }

    @Override
    public MyLinkedList reverse() {
        MyLinkedList newList = new MyLinkedListRecursive();
        for(int i = size()-1; i >= 0; i--) {
            newList.add(get(i));
        }
        return newList;
    }

    public static class MyNodeRecursive {
        private MyNodeRecursive next, prev;
        private final Body val;

        public MyNodeRecursive(Body body) {
            this(body, null, null);
        }

        public MyNodeRecursive(Body body, MyNodeRecursive prev) {
            this(body, prev, null);
        }

        public MyNodeRecursive(Body body, MyNodeRecursive prev, MyNodeRecursive next) {
            this.val = body;
            this.prev = prev;
            this.next = next;
        }

        public boolean hasNext() {
            return next != null;
        }

        public Body getVal() {
            return val;
        }

        public boolean add(Body body) {
            if(val.getName().equals(body.getName())){
                return false;
            }
            else if(next == null) {
                next = new MyNodeRecursive(body);
                next.prev = this;
                return true;
            }
            else {
                return next.add(body);
            }
        }

        public Body getAt(int i) {
            if(i == 0) {
                return val;
            }
            else if(!hasNext()) {
                return null;
            }
            else {
                return next.getAt(i-1);
            }
        }

        public Body find(String name) {
            if(val.getName().equals(name)){
                return this.val;
            }
            return hasNext() ? next.find(name) : null;
        }

        public int size() {
            return hasNext() ? next.size() + 1 : 1;
        }

        public boolean add(int i, Body body) {
            if(i == 0) {
                MyNodeRecursive node = new MyNodeRecursive(body, this.prev, this);
                this.prev = node;
                node.prev.next = node;
                return true;
            }
            else if(!hasNext() && i == 1) {
                return add(body);
            }
            else if(!hasNext()) {
                return false;
            }
            else {
                return next.add(i-1, body);
            }
        }

        public boolean remove(int i) {
            if(i == 0) {
                if(this.prev != null) {
                    this.prev.next = this.next;
                }
                if(this.next != null) {
                    this.next.prev = this.prev;
                }
                return true;
            }
            else if(!hasNext()) {
                return false;
            }
            else{
                return this.next.remove(i-1);
            }
        }

        public boolean remove(Body b) {
            if(this.val.getName().equals(b.getName())) {
                return remove(0);
            }
            else if(!hasNext()) {
                return false;
            }
            else {
                return next.remove(b);
            }
        }
    }
}
