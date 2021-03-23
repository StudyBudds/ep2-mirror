public class MyLinkedList {
    private MyNode head;

    public boolean add(Body body) {
        if(head == null) {
            this.head = new MyNode(body);
            return true;
        }
        return this.head.add(body);
    }

    public Body get(int i) {
        return head != null ? head.getAt(i) : null;
    }

    public Body find(String name) {
        return head != null ? head.find(name) : null;
    }

    public int size() {
        return head != null ? head.size() : 0;
    }

    public class MyNode {
        private MyNode next;
        private Body val;

        public MyNode(Body body) {
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
                next = new MyNode(body);
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
