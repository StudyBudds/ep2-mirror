public class MyLinkedListRecursive implements MyLinkedList {
    private MyNodeRecursive head;
    private int size;

    @Override
    public boolean add(Body body) {
        if(head == null) {
            this.head = new MyNodeRecursive(body);
            size++;
            return true;
        }
        boolean res = this.head.add(body);
        size += res ? 1 : 0;
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
        return size;
    }

    public class MyNodeRecursive {
        private MyNodeRecursive next;
        private Body val;

        public MyNodeRecursive(Body body) {
            this.val = body;
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
            else if(i != 0 && !hasNext()) {
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
    }
}
