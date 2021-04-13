//This class represents a linked list for objects of class 'Body'
public class CosmicSystem {

    //TODO: Define variables.
    private String name;
    private MyLinkedList bodies;

    // Initialises this system as an empty system with a name.
    public CosmicSystem(String name) {
        //TODO: implement constructor.
        this(name, new MyLinkedListIterative());
        //this(name, new MyLinkedListRecursive());
        this.name = name;
        //this.bodies = new MyLinkedListIterative();
        //this.bodies = new MyLinkedListRecursive(); // uncomment this line to switch the LinkedListImplementation to Recursive
    }

    public CosmicSystem(String name, MyLinkedList bodies) {
        this.name = name;
        this.bodies = bodies;
    }

    // Adds 'body' to the end of the list of bodies if the list does not already contain a
    // body with the same name as 'body', otherwise does not change the object state. The method
    // returns 'true' if the list was changed as a result of the call and 'false' otherwise.
    public boolean add(Body body) {
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
        return bodies.size();
    }

    public String getHeadName() {
        Body head = get(0);
        return head != null ? head.getName() : "";
    }

    public String getName() {
        return this.name;
    }

    // Inserts the specified 'body' at the specified position
    // in this list if 'i' is a valid index and there is no body
    // in the list with the same name as that of 'body'.
    // Shifts the element currently at that position (if any) and
    // any subsequent elements to the right (adds 1 to their
    // indices). The first element of the list has the index 0.
    // Returns 'true' if the list was changed as a result of
    // the call, 'false' otherwise.
    public boolean add(int i, Body body) {
        if(get(body) != null)  {
            return false;
        }
        return this.bodies.add(i, body);
    }

    //removes the body at index i from the list, if i is a valid index
    //returns true if removal was done, and false otherwise (invalid index)
    public boolean remove(int i) {

        return this.bodies.remove(i);
    }

    //removes a body from the list, if the list contains a body with the same name as the input body
    //returns true if removal was done, and false otherwise (no body with the same name)
    public boolean remove(Body body) {
        //TODO: implement method.
        return this.bodies.remove(body);
    }

    // Returns a new list that contains the same elements as this list in reverse order. The list 'this'
    // is not changed and only the references to the bodies are copied, not their content (shallow copy).
    public CosmicSystem reverse() {
        return new CosmicSystem(this.name, this.bodies.reverse());
    }

    // Returns a readable representation with the name of the system and all bodies in order of the list.
    // E.g.,
    // Jupiter System:
    // Jupiter, 1.898E27 kg, radius: 6.9911E7 m, position: [0.0,0.0,0.0] m, movement: [0.0,0.0,0.0] m/s.
    // Io, 8.9E22 kg, radius: 1822000.0 m, position: [0.0,0.0,0.0] m, movement: [0.0,0.0,0.0] m/s.
    //
    //Hint: also use toString() in Body.java for this.
    public String toString() {
        StringBuilder s = new StringBuilder(name + ":\n");
        for(int i = 0; i < bodies.size(); i++) {
            s.append(bodies.get(i).toString()).append("\n");
        }
        return s.toString();
    }


}
