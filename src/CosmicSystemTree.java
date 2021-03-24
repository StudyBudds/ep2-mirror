//This class represents a binary search tree for objects of class 'CosmicSystem'
public class CosmicSystemTree {
    public static final int WIDTH = 1600;
    public static final int HEIGHT = 1000;


    //TODO: Define variables.
    private MyTreeNode root;

    // Adds a system of bodies to the tree. Since the keys of the tree are the names of bodies,
    // adding a system adds multiple (key, value) pairs to the tree, one for each body of the
    // system, with the same value, i.e., reference to the cosmic system.
    // An attempt to add a system with a body that already exists in the tree
    // leaves the tree unchanged and the returned value would be 'false'.
    // For example, it is not allowed to have a system with the bodies "Earth" and "Moon" and another
    // system with the bodies "Jupiter" and "Moon" indexed by the tree, since "Moon" is not unique.
    // The method returns 'true' if the tree was changed as a result of the call and
    // 'false' otherwise.
    public boolean add(CosmicSystem system) {
        int size = system.size();
        if(root == null) {
            this.root = new MyTreeNode(system, system.getHeadName());
            Body curr;
            for(int i = 1; i < size; i++) {
                curr = system.get(i);
                root.insert(system, curr.getName());
            }
            return true;
        }
        else {
            boolean returnVal = false;
            for (int i = 0; i < size; i++) {
                returnVal = get(system.get(i).getName()) != null;
                if(returnVal) return false;
            }
            Body curr;
            for(int i = 0; i < size; i++) {
                curr = system.get(i);
                root.insert(system, curr.getName());
            }
            return true;
        }
    }

    // Returns the cosmic system in which a body with the specified name exists.
    // For example, if the specified name is "Europa", the system of Jupiter (Jupiter, Io,
    // Europa, Ganymed, Kallisto) will be returned.
    // If no such system is found, 'null' is returned.
    public CosmicSystem get(String name) {
        return root != null ? root.get(name) : null;
    }

    // Returns the overall number of bodies indexed by the tree.
    public int numberOfBodies() {
        return root != null ? root.size() : -1;
    }

    // Returns a readable representation with (key, value) pairs, sorted alphabetically by the key.
    //E.g.,
    //    (Deimos,Mars System)
    //    (Earth,Earth System)
    //
    //Hint: for this you will also need a method in CosmicSystem.java to access the name of a CosmicSystem object.
    public String toString() {
        return root != null ? root.toString() : null;
    }

    //BONUS TASK: sets a new canvas and draws the tree using StdDraw
    public void drawTree() {
        if(root == null) return;
        StdDraw.setCanvasSize(WIDTH, HEIGHT);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(-HEIGHT, 0);
        StdDraw.setPenRadius(0.005);
        root.draw(WIDTH / 2.0, -50, WIDTH / 4.0);
        StdDraw.show();
    }

    //TODO: Define additional class(es) implementing the binary tree (either here or outside class).
    public class MyTreeNode {
        private String key;
        private CosmicSystem value;
        private MyTreeNode left;
        private MyTreeNode right;

        public MyTreeNode(CosmicSystem system, String key) {
            this.key = key;
            this.value = system;
        }

        public boolean insert(CosmicSystem system, String newKey) {
            int comparison = newKey.compareTo(this.key);
            if(comparison > 0) {
                if(right == null) {
                    this.right = new MyTreeNode(system, newKey);
                    return true;
                }
                return this.right.insert(system, newKey);
            }
            else if(comparison < 0) {
                if(left == null) {
                    this.left = new MyTreeNode(system, newKey);
                    return true;
                }
                return this.left.insert(system, newKey);
            }
            return false;
        }

        public CosmicSystem get(String name) {
            int comparison = name.compareTo(key);
            if(comparison == 0) {
                return this.value;
            }
            else if(comparison < 0) {
                if(left != null) {
                    return left.get(name);
                }
                else {
                    return null;
                }
            }
            else {
                if(right != null) {
                    return right.get(name);
                }
                else {
                    return null;
                }
            }
        }

        public int size() {
            int size = 1;
            if(left != null) {
                size += left.size();
            }
            if(right != null) {
                size += right.size();
            }
            return size;
        }

        public String toString() {
            String temp = "";
            if(left != null) {
                temp = this.left.toString();
            }
            temp = temp + "\n(" + this.key + "," + this.value.getName() + ")";
            if(right != null) {
                temp = temp + this.right.toString();
            }
            return temp;
        }

        public void draw(double middleX, double middleY, double size) {
            if(left != null) {
                StdDraw.line(middleX, middleY, middleX - size, middleY - 150);
                left.draw(middleX - size, middleY - 150, size/2);
            }
            if(right != null){
                StdDraw.line(middleX, middleY, middleX + size, middleY - 150);
                right.draw(middleX + size, middleY - 150, size/2);
            }
            StdDraw.filledCircle(middleX, middleY, 20);
            int textOffset = middleX > CosmicSystemTree.WIDTH / 2.0 ? -80 : 80;
            StdDraw.text(middleX + textOffset, middleY, this.key);
            StdDraw.text(middleX + textOffset, middleY-20, this.value.getName());
        }
    }
}


