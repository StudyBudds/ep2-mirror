public interface MyLinkedList {
    boolean add(Body body);

    Body get(int i);

    Body find(String name);

    int size();

    boolean add(int i, Body body);

    boolean remove(int i);

    boolean remove(Body b);

    String toString();
}
