###Angabe

Gegeben ist folgendes Interface:

````
public interface Cluster extends BodyIterable {

    //adds the Body to the cluster and returns the changed cluster 'this'
    Cluster add(Body b);

    //returns the body with largest radius in the cluster
    Body getLargest();

    //returns the number of bodies contained in this cluster (and its sub-clusters)
    int numberOfBodies();

    //returns the radius of the main body of the cluster
    double getRadius();
}
````

Erstellen Sie eine neue Klasse `DoubleSystem`, die dieses Interface implementiert. Ein Objekt vom Typ `DoubleSystem` besteht dabei immer aus genau zwei Komponenten, einem _Main_-Objekt vom Typ `Body` und einem _Secondary_-Objekt vom Typ `Cluster`. Das Interface `Cluster` soll in `DoubleSystem` und `Body` implementiert werden. Somit kann ein `DoubleSystem` aus zwei Bodies oder aus einem `Body` und einem `DoubleSystem` bestehen. Eine Invariante eines Objekts vom Typ `DoubleSystem` ist, dass der Radius von _Main_ immer größer oder gleich dem Radius von _Secondary_ ist (wobei der Radius von _Secondary_ dem Radius seines _Main_-Body entspricht, falls es sich um ein `DoubleSystem` handelt). Hinweis: Durch die Einhaltung dieser Invariante beim Erstellen von `DoubleSystem`-Objekten oder beim Aufrufen von `add()` entsteht automatisch eine nach dem Radius sortierte Hierarchie von dualen Systemen. Ein mögliches duales System könnte zum Beispiel aus der Sonne als _Main_-Objekt und einem weiteren dualen System als _Secondary_ bestehen. Dieses _Secondary_ duale System könnte wiederum aus Erde (_Main_) und Mond (_Secondary_) bestehen (aber nicht umgekehrt). 

Verwenden Sie bei der Implementierung von `DoubleSystem` folgendes Code-Gerüst: 

````
import java.io.IOException;

public class DoubleSystem implements Cluster {

    //TODO: define object variables

    //TODO: implement constructor
    //the constructor should throw a DoubleSystemIllegalArgumentException (to be implemented), if one of the arguments is null or the radius of main is smaller than the radius of secondary
    DoubleSystem(Body main, Cluster rest) throws DoubleSystemIllegalArgumentException {

    }

    @Override
    //adds a Body b to the DoubleSystem such that the radius of the main body is larger or equal than the main body of 'secondary'
    //returns the changed DoubleSystem 'this'
    public Cluster add(Body b) {
        //TODO
        return this;
    }

    @Override
    //returns the body with largest size in the overall system
    public Body getLargest() {
        //TODO
        return null;
    }
    
    //returns the number of bodies contained in this cluster (and its sub-clusters)
    int numberOfBodies() {
        //TODO
        return -1;
    }

    @Override
    //returns the radius of the main body 
    public double getRadius() {
        //TODO
        return -1.0;
    }

    @Override
    //returns an iterator over all bodies
    public BodyIterator iterator() {
        //TODO
        return null;
    }

    //returns a String representation of the double system in the form of [main body + secondary cluster]
    //e.g. [Sol + [Earth + Moon]]
    public String toString() {
        //TODO
        return "";
    }
}
````

(Hinweis: `hashCode()` und `equals()` müssen nicht überschrieben werden)

Verwenden Sie die mitgelieferte Klasse `PraxisApplicationU8` zum Testen Ihrer Lösung.


