import java.util.stream.StreamSupport;

public class MyBodyList implements BodyCollection {
    private final CosmicSystemIndex collection;

    public MyBodyList(CosmicSystemIndex collection) {
        this.collection = collection;
    }

    @Override
    public boolean add(Body b) {
        return false;
    }

    @Override
    public boolean contains(Body b) {
        return collection.contains(b);
    }

    @Override
    public int size() {
        return collection.numberOfBodies();
    }

    @Override
    public Body[] toArray() {
        return (Body[])StreamSupport.stream(collection.spliterator(), false).toArray();
    }

    @Override
    public BodyIterator iterator() {
        return collection.iterator();
    }
}
