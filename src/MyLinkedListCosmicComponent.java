public class MyLinkedListCosmicComponent {
    private MyNodeRecursiveCosmicComponent head, tail;
    private int size;

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

    public CosmicComponent get(int i) {
        return head != null ? head.getAt(i) : null;
    }

    public CosmicComponent find(String name) {
        return head != null ? head.find(name) : null;
    }

    public CosmicComponent find(CosmicComponent body) {
        return find(body.getName());
    }

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
    }
}

