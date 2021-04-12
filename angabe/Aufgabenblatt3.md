# Aufgabenblatt 3

## Allgemeine Anmerkungen
Ihre Lösung für dieses Aufgabenblatt ist bis Dienstag, 20.4. 12h durch `git commit` und `push` abzugeben. Mit der Angabe wird die Datei `Aufgabe3Test.java` mitgeliefert. Diese Klasse dient wie in der vorigen Aufgabe zum Testen Ihrer Lösung. Bitte beachten Sie, dass diese Klasse auch neue Methoden aufruft, die von Ihnen zuerst noch in `CosmicSystem.java` implementiert werden müssen. Das ganze Projekt kann erst wieder kompiliert werden, wenn Sie diese Methoden implementiert haben.

 Wenn Sie zusätzlich zu den gefragten Klassen weitere Klassen definieren, achten Sie darauf, dass die Klassennamen mit `My` beginnen, um Konflikte mit späteren Aufgabenblättern zu vermeiden.

## Ziel
Ziel der Aufgabe ist die Änderung der bestehenden einfach verketteten Liste zu einer doppelt verketteten Liste mit zusätzlicher Funktionalität (siehe Skriptum Seiten 70-73).

## Aufgaben

Ihre Aufgaben sind folgende:

1. Ändern Sie die Klasse `CosmicSystem` so um, dass sie als doppelt verkettete Liste implementiert wird. Ändern Sie die Klasse, ohne dass sich das Verhalten ihrer Objekte ändert.
   
Hinweis: das heißt unter anderem, dass die Tests in `Aufgabe2Test` wie in der vorigen Aufgabe erfolgreich durchgeführt werden können.  

2. Fügen Sie folgende Methoden zur Klasse `CosmicSystem` hinzu:

````
    // Inserts the specified 'body' at the specified position
    // in this list if 'i' is a valid index and there is no body 
    // in the list with the same name as that of 'body'.
    // Shifts the element currently at that position (if any) and 
    // any subsequent elements to the right (adds 1 to their 
    // indices). The first element of the list has the index 0. 
    // Returns 'true' if the list was changed as a result of 
    // the call, 'false' otherwise.
    public boolean add(int i, Body body) {
        //TODO: implement method.
        return false;
    }
    
    //removes the body at index i from the list, if i is a valid index
    //returns true if removal was done, and false otherwise (invalid index)
    public boolean remove(int i) {
        //TODO: implement method.
        return false;
    }

    //removes a body from the list, if the list contains a body with the same name as the input body
    //returns true if removal was done, and false otherwise (no body with the same name)
    public boolean remove(Body body) {
        //TODO: implement method.
        return false;
    }
    
    // Returns a new list that contains the same elements as this list in reverse order. The list 'this'
    // is not changed and only the references to the bodies are copied, not their content (shallow copy).
    public CosmicSystem reverse() {
        //TODO: implement method.
        return null;
    }
    
    // Returns a readable representation with the name of the system and all bodies in order of the list.
    // E.g.,
    // Jupiter System:
    // Jupiter, 1.898E27 kg, radius: 6.9911E7 m, position: [0.0,0.0,0.0] m, movement: [0.0,0.0,0.0] m/s.
    // Io, 8.9E22 kg, radius: 1822000.0 m, position: [0.0,0.0,0.0] m, movement: [0.0,0.0,0.0] m/s.
    //
    //Hint: also use toString() in Body.java for this.
    public String toString() {
        //TODO: implement method.
        return null;
    }
````

3. Schreiben sie die Klasse `Body` so um, sodass sie die aktuelle Kraft, die auf den Himmelskörper wirkt, als Objektvariable speichert. Diese Kraft soll weiterhin in `Simulation` berechnet werden und dann an das Objekt übergeben werden (z.B. `setForce()`). Zum Bewegen des Himmelskörpers implementieren Sie eine überladene Methode `move()` (ohne Parameter), die das Objekt gemäß der gespeicherten Kraft bewegt. Bauen Sie anschließend `Simulation` so um, sodass diese neuen Methoden genutzt werden, und kein Array von Kräften (`Vector3[] forceOnBody`) mehr benötigt wird.


#### _Punkteaufteilung_

- `CosmicSystem`: doppelt verkettete Liste: 1.5 Punkte
- `CosmicSystem`: Methode `add(int i, Body body)`: 1 Punkt
- `CosmicSystem`: Methoden `remove(int i)` und `remove(Body body)` : 1 Punkt
- `CosmicSystem`: Methode `reverse()` : 0.5 Punkte
- `CosmicSystem`: Methode `toString()` : 0.5 Punkte
 - Speichern der Kraft in `Body` und Verwendung in `Simulation`: 0.5 Punkte

 - Gesamt: 5 Punkte 



