# Aufgabenblatt 5

## Allgemeine Anmerkungen
Ihre Lösung für dieses Aufgabenblatt ist bis Donnerstag, 20.5., 12h durch `git commit` und `push` 
abzugeben.
Wenn Sie zusätzlich zu den gefragten Klassen und Interfaces weitere Klassen oder 
Interfaces definieren, achten Sie darauf, dass die Klassennamen mit `My` beginnen, um Konflikte 
mit späteren Aufgabenblättern zu vermeiden.

## Ziel
Ziel der Aufgabe ist das Verständnis und die Anwendung der Konzepte: Gleichheit, Hash-Werte und 
Hash-Tabelle (siehe Skriptum ab Seite 84) und Iterator (ab Seite 91).

## Gegebene Interfaces
Gegeben sind folgende Interface-Definitionen:
````
// A complex cosmic systems regarded as an associative data structure. 
// The key is a single body and the associated value returned
// by 'getParent' is the reference to the smallest complex cosmic system to which 
// the body belongs. For example, searching in the entire solar system for 'Io' returns 
// the jupiter system (Jupiter, Io, Europa, Ganymede, Kallisto, ...).
public interface CosmicSystemIndex {

    // Returns the 'ComplexCosmicSystem' (value) with which a 
    // body (key) is associated. If 'b' is not contained, 'null'
    // is returned.
    ComplexCosmicSystem getParent(Body b);

    // Returns 'true' if the specified 'b' is listed
    // in the index.
    boolean contains(Body b);

}
````
````
public interface BodyIterable extends Iterable<Body> {

    // Returns an iterator over elements of type 'Body'.
    BodyIterator iterator();

}
````
````
// An iterator over elements of type 'Body'.
public interface BodyIterator extends java.util.Iterator<Body> {

    // Returns 'true' if the iteration has more elements.
    boolean hasNext();

    // Returns the next element (i.e. body) in the iteration.
    Body next();

}

````
## Aufgaben
1. Überschreiben Sie in der Klasse `Body` zusätzlich zu `toString`
 auch die Methoden `equals` und `hashCode`. Zwei `Body`-Objekte sind im Sinn von `equals` 
 genau dann gleich, wenn ihr Name und ihre Masse gleich sind. Verwenden Sie anstelle des 
 problematischen Vergleichs von `double`-Werten mit `==` (siehe z.B. `1.1 + 1.1 == 3.3 - 1.1`) 
 einen selbst definierten Toleranzbereich `epsilon > 0` (z.B. `epsilon = 0.0000001`), um
 den `double`-Werte abweichen dürfen und trotzdem als gleich gelten. 
 Achten Sie darauf, dass `equals` und `hashCode` zusammenpassen und die in `Object` beschriebenen 
 Eigenschaften erfüllen.
2. Ändern Sie die Klasse `ComplexCosmicSystem` wie folgt:
 - Überschreiben Sie in der Klasse `ComplexCosmicSystem` zusätzlich zu `toString` auch die 
 Methoden `equals` und `hashCode`. Zwei Objekte vom Typ `ComplexCosmicSystem` sind gleich, 
 wenn sie die gleichen Himmelskörper (unabhängig von der Reihenfolge) und gleichen 
 Untersysteme enthalten.
 - Entfernen Sie die Methoden `get(String)` und `get(CosmicSystem)` aus `ComplexCosmicSystem`,
 diese werden nicht mehr benötigt. 
 - `ComplexCosmicSystem` soll nur folgenden Konstruktor haben, der sicherstellt, dass jedes 
 Objekt der Klasse `ComplexCosmicSystem` mindestens zwei Komponenten hat:
 ````
     // Initialises this system with a name and at least two cosmic components.
     public ComplexCosmicSystem(String name, CosmicComponent c1, CosmicComponent c2,
                                CosmicComponent... ci) {
     
         // implement constructor
     }
 ````
 - Die Klasse soll die bisherige hierarchische Struktur beibehalten, aber die gegebenen Interfaces
 `BodyIterable` und `CosmicSystemIndex` implementieren. Machen Sie die dafür notwendigen
 Ergänzungen in der Klasse `ComplexCosmicSystem`. Definieren Sie eine Klasse, die den Iterator
 implementiert (als weitere Klasse in der Datei `ComplexCosmicSystem.java` oder in einer eigenen
 Datei). Ein Iterator von `ComplexCosmicSystem` soll über alle im dargestellten System (inklusive
 Untersysteme) vorkommenden Himmelskörper (Objekte vom Typ `Body`) iterieren. Die Reihenfolge ist
 nicht festgelegt.
3. Definieren Sie eine Klasse `CosmicSystemMap`, die das Interface `CosmicSystemIndex` mittels
 Hash-Tabelle implementiert. (`BodyIterable` wird von dieser Klasse nicht implementiert).
 Verwenden Sie dabei keine vorgefertigten Klassen aus dem Java-Collection-Framework, sondern
 orientieren Sie sich an den Beispielen aus dem Skriptum. Wählen Sie eine geeignete Form der
 Kollisionsbehandlung. Testen Sie die Implementierung mit eigenen Testfällen. Überschreiben Sie
 auch `toString` so, dass zumindest die enthaltenen `Body`-Objekte dargestellt werden. `equals`
 und `hashCode` werden von dieser Klasse nicht überschrieben.
 Die Klasse `CosmicSystemMap` soll folgenden Konstruktor haben:
 ````
     // Creates a hash map from the specified 'system'.
     // The resulting map has multiple (key, value) pairs, one for each 
     // body of 'system'. The value is the reference
     // to the system (only the direct parent) to which the body belongs.
     public CosmicSystemMap(ComplexCosmicSystem system) { 
         // implement method
     }
 ````

### Denkanstöße (ohne Bewertung)
Folgende Fragen sind als Denkanstöße gedacht und bilden die Grundlage für eine Diskussion in der
Übungseinheit zu diesem Aufgabenblatt.

1. Wie ändert sich das Verhalten von `CosmicSystemMap`, wenn Sie in Ihrer Lösung die
 Implementierung der Methoden `equals` und `hashCode` aus der Klasse `Body` löschen?
2. Was passiert, wenn Sie nur `hashCode` löschen?
3. Gilt in Ihrer Lösung, dass `x.toString().equals(y.toString())` den Wert `true` 
 liefert, wenn `x.equals(y)` den Wert `true` liefert? Welche Probleme können entstehen, wenn 
 diese Bedingung nicht erfüllt ist? (Anmerkung: Es war in diesem Aufgabenblatt nicht 
 verlangt, dass Ihre Lösung die Bedingung erfüllen muss.)
4. Verwenden Sie in Ihrer Implementierung von `ComplexCosmicSystem` eine dynamische Typprüfung? 
 Geht es auch ohne?
 
 #### _Punkteaufteilung_
 
 - Implementierung von `equals` und `hashCode` in `Body`: 0.5 Punkt
 - `ComplexCosmicSystem`: Implementierung von `equals` und `hashCode`: 1 Punkte 
 - `ComplexCosmicSystem`: Konstruktor: 0.5 Punkte
 - `ComplexCosmicSystem`: Implementierung von `CosmicSystemIndex`: 0.5 Punkt
 - `ComplexCosmicSystem`: Implementierung von `Iterable`: 1 Punkt
 - `CosmicSystemMap`: Implementierung von `CosmicSystemIndex`: 1.5 Punkt
 




