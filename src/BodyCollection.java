public interface BodyCollection extends BodyIterable {

    // Ensures that this collection contains the specified body. Returns 'true' if this collection
    // changed as a result of the call. (Returns false if this collection does not permit
    // duplicates and already contains the specified body.)
    boolean add(Body b);

    // Returns 'true' if this collection contains the specified body. More formally, returns
    // 'true' if and only if this collection contains at least one body 'c' such
    // that (b==null ? false : b.equals(c)).
    boolean contains(Body b);

    // Returns the number of bodies in this collection.
    int size();

    // Returns an array with all the bodies of this collection.
    Body[] toArray();

}
