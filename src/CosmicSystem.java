//This class represents a linked list for objects of class 'Body'
public class CosmicSystem {

    //TODO: Define variables.
    private String name;
    private MyLinkedList bodies;

    // Initialises this system as an empty system with a name.
    public CosmicSystem(String name) {
        //TODO: implement constructor.
        this.name = name;
        this.bodies = new MyLinkedList();
    }

    // Adds 'body' to the end of the list of bodies if the list does not already contain a
    // body with the same name as 'body', otherwise does not change the object state. The method
    // returns 'true' if the list was changed as a result of the call and 'false' otherwise.
    public boolean add(Body body) {
        //TODO: implement method.
        return bodies.add(body);
    }

    // Returns the 'body' with the index 'i'. The body that was first added to the list has the
    // index 0, the body that was most recently added to the list has the largest index (size()-1).
    // Precondition: 'i' is a valid index.
    public Body get(int i) {
        return bodies.get(i);
    }

    // Returns the body with the specified name or 'null' if no such body exits in the list.
    public Body get(String name) {
        return bodies.find(name);
    }

    // Returns the body with the same name as the input body or 'null' if no such body exits in the list.
    public Body get(Body body) {
        return bodies.find(body.getName());
    }

    // returns the number of entries of the list.
    public int size() {
        //TODO: implement method.
        return bodies.size();
    }

    //TODO: Define additional class(es) implementing the linked list (either here or outside class).
}
