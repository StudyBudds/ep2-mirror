// An iterator over elements of type 'Body'.
public interface BodyIterator extends java.util.Iterator<Body> {

    // Returns 'true' if the iteration has more elements. (In other
    // words, returns true if 'next()' would return an element rather
    // than throwing an exception.)
    boolean hasNext();

    // Returns the next element (i.e. body) in the iteration.
    Body next();

}