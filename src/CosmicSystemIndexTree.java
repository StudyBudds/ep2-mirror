// A binary search tree implementation of 'CosmicSystemIndex'. This binary tree
// uses a specified comparator for sorting its keys.

import java.util.NoSuchElementException;

public class CosmicSystemIndexTree implements CosmicSystemIndex, BodyIterable {

    private IndexTreeNode root;
    private BodyComparator comparator;
    private int size;

    // Initialises this index with a 'comparator' for sorting
    // the keys of this index.
    public CosmicSystemIndexTree(BodyComparator comparator) {
        // {V}
        assert comparator != null;
        // {S}
        this.comparator = comparator;
        this.root = IndexTreeNullNode.NIL; // NIL in the beginning.

    }

    // Adds a system of bodies to the index.
    // Adding a system adds multiple (key, value) pairs to the
    // index, one for each body of the system, with the same
    // value, i.e., reference to the celestial system.
    // An attempt to add a system with a body that already exists
    // in the index leaves the index unchanged and the returned
    // value would be 'false'.
    // The method returns 'true' if the index was changed as a
    // result of the call and 'false' otherwise.
    public boolean add(ComplexCosmicSystem system) {

        if (system == null || system.numberOfBodies() == 0) {
            return false;
        }

        // {I}
        assert system != null;
        for (Body b : system) {
            // {I}
            assert b != null;
            if (this.contains(b)) {
                return false;
            }
        }

        for (Body b: system) {
            // {V}
            assert b != null;
            // {S}
            root = root.add(new IndexTreeNonNullNode(b, system.getParent(b), comparator));
            // {N}
            assert root != null;
            // {V]
            assert size >= 0;
            // {S}
            size++;
            // {N}
            assert size > 0;
        }

        return true;

    }

    // Returns the system with which a body is
    // associated. If body is not contained as a key, 'null'
    // is returned.
    public ComplexCosmicSystem getParent(Body body) {
        // {V}
        assert body != null && root != null;
        // {S}
        return root.get(body);
    }

    // Returns 'true' if the specified 'body' is listed
    // in the index.
    public boolean contains(Body body) {
        // {V}
        assert body != null;
        // {S}
        return getParent(body) != null;
    }

    // Returns a readable representation with (key, value) pairs sorted by the key.
    public String toString() {
        // {V}
        assert root != null;
        return "{" + root.toString() + "}";
    }

    // Returns the comparator used in this index.
    public BodyComparator getComparator() {
        return comparator;
    }

    @Override
    // Returns an iterator iterating over all celestial bodies of this index.
    public BodyIterator iterator() {

        return root.iterator(new TreeNodeIterator(null, null));
    }

    @Override
    // Returns a 'BodyCollection' view of all bodies of this index.
    public BodyCollection getBodies() {

        return new TreeBodyCollection(this);
    }

    @Override
    public int numberOfBodies() {
        return size;
    }

    public int size() {
        // {V}
        assert root != null;
        return root.size();
    }
}

interface IndexTreeNode {

    // Adds the specified 'node' to the tree of which 'this' is the root
    // node. If the tree already has a node with the same key as that
    // of 'node' the tree remains unchanged.
    // {V}: node != null
    // {N}: added node to this tree if node has not the same key as this.node
    IndexTreeNode add(IndexTreeNode node);

    // Returns the cosmic system with which a body is associated, if 'body' is a key
    // which is contained in this tree (the tree of which 'this' is the root node).
    // If body is not contained as a key, 'null' is returned.
    // {V}: body != null
    // {N}: Tree doesn't change
    ComplexCosmicSystem get(Body body);

    // Returns a readable representation of the tree of which 'this' is the root node.
    // {N}: Tree doesn't change
    String toString();

    // Returns an iterator over all keys of the tree of which 'this' is the root node.
    // 'parent' is the iterator of the parent (path from the root).
    // {V}: parent != null
    // {N}: Tree doesn't change
    TreeNodeIterator iterator(TreeNodeIterator parent);

    // Returns the key of this node.
    // {N}: Tree doesn't change
    Body getKey();

    // Returns the number of entries in the tree of which 'this' is the root
    // node.
    // {N}: Tree doesn't change
    int size();

}

// Implements a terminal node with no content (used instead of 'null').
class IndexTreeNullNode implements IndexTreeNode {
    // Singleton: only one instance is needed, because the
    // state of 'this' can not be changed.
    public static final IndexTreeNullNode NIL = new IndexTreeNullNode();

    // private to avoid object creation from outside
    private IndexTreeNullNode() {}

    // nicht wie die nachbedingung in IndexTreeNode da sich der subtree nicht ändert, unabhängig vom inhalt von node
    public IndexTreeNode add(IndexTreeNode node) {
        // {V}
        assert node != null;
        return node;
    }

    // {N}: Tree doesn't change
    public ComplexCosmicSystem get(Body body) {
        // {V}
        assert body != null;
        return null;
    }

    // {N}: Tree doesn't change
    public String toString() {
        return "";
    }

    public TreeNodeIterator iterator(TreeNodeIterator parent) {
        // {V}
        assert parent != null;
        return parent;
    }

    // {N}: Tree doesn't change
    public Body getKey() {
        return null;
    }

    // {N}: Tree doesn't change
    public int size() { return 0; }

}

// A node with a content.
class IndexTreeNonNullNode implements IndexTreeNode {
    private IndexTreeNode left;
    private IndexTreeNode right;
    private Body key;
    private ComplexCosmicSystem cs;
    private BodyComparator comparator;

    public IndexTreeNonNullNode(Body key, ComplexCosmicSystem cs,
                                BodyComparator comparator) {
        this.key = key;
        this.cs = cs;
        this.left = IndexTreeNullNode.NIL;
        this.right = IndexTreeNullNode.NIL;
        this.comparator = comparator;

    }



    // {N}: added node to this tree if node has not the same key as this.node
    public IndexTreeNode add(IndexTreeNode node) {
        // {V}: strenger als Supertyp
        assert node != null && left != null && right != null && comparator != null;
        // {V} if (this.key == node.getKey()) comp == 0
        int comp = this.comparator.compare(this.key, node.getKey());
        if (comp > 0) {
            left = left.add(node);
        } else {
            if (comp < 0) {
                right = right.add(node);
            }
        }

        return this;
    }

    public ComplexCosmicSystem get(Body body) {
        // {V}: strenger als Supertyp
        assert body != null && left != null && right != null && comparator != null;
        if (key.equals(body)) {
            return cs;
        }

        if (this.comparator.compare(this.key, body) > 0) {
            return left.get(body);
        } else {
            return right.get(body);
        }

    }

    // {N}: Tree doesn't change
    public String toString() {
        // {V}: strenger als Supertyp
        assert left != null && right != null;
        String result;
        String right = this.right.toString();
        result = this.left.toString();
        result += result.isEmpty() ? "" : ",\n";
        result += this.key + " belongs to " + this.cs;
        result += right.isEmpty() ? "" : ",\n";
        result += right;
        return result;

    }

    // {N}: Tree doesn't change
    public Body getKey() {
        return key;
    }

    public TreeNodeIterator iterator(TreeNodeIterator next) {
        // {V}: left != null
        return left.iterator(new TreeNodeIterator(this, next));
    }

    public TreeNodeIterator nextStep(TreeNodeIterator next) {
        // {V}: right != null
        return right.iterator(next);
    }

    // {N}: Tree doesn't change
    public int size() { return 1 + left.size() + right.size(); }

}

class TreeNodeIterator implements BodyIterator {
    private IndexTreeNonNullNode node;
    private TreeNodeIterator next;

    public TreeNodeIterator(IndexTreeNonNullNode node, TreeNodeIterator next) {
        this.node = node;
        this.next = next;
    }

    @Override
    public boolean hasNext() {
        return node != null;
    }

    @Override
    // {V}: node != null && node.getKey() != null
    public Body next() {
        if (hasNext()) {
            assert node != null && node.getKey() != null;
            Body key = node.getKey();
            next = node.nextStep(next);
            node = next.node;
            next = next.next;
            return key;
        }
        throw new NoSuchElementException("This index has no (more) entries!");
    }

}

class TreeBodyCollection implements BodyCollection {

    private CosmicSystemIndexTree tree;

    public TreeBodyCollection(CosmicSystemIndexTree tree) {
        this.tree = tree;
    }

    public boolean add(Body b) {
        return false;
    }

    public boolean contains(Body b) {
        return tree.contains(b);
    }

    public int size() {
        return tree.size();

    }

    public Body[] toArray() {
        Body[] result = new Body[tree.size()];

        BodyIterator it = tree.iterator();
        assert it != null;
        for (int i = 0; i < result.length; i++) {

            result[i] = it.next();
            // {I}
            assert result[i] != null;
        }

        return result;
    }

    @Override
    public BodyIterator iterator() {
        return tree.iterator();

    }
}


