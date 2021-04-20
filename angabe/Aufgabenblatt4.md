# Aufgabenblatt 4

## Allgemeine Anmerkungen
Ihre Lösung für dieses Aufgabenblatt ist bis Dienstag, 27.4. 12h durch `git commit` und `push` abzugeben. Mit der Angabe werden die Dateien `CosmicComponent.java`, `ComplexCosmicSystem.java` und `Aufgabe4Test.java` mitgeliefert. 

 Wenn Sie zusätzlich zu den gefragten Klassen weitere Klassen definieren, achten Sie darauf, dass die Klassennamen mit `My` beginnen, um Konflikte mit späteren Aufgabenblättern zu vermeiden.

## Ziel
Ziel der Aufgabe ist die Anwendung der Konzepte: Interfaces, dynamisches Binden, toString() (siehe Skriptum Seiten 75-84).

## Beschreibung der gegebenen Dateien
- `CosmicComponent` ist ein vorgegebenes Interface, das Sie in den Klassen `Body` und `ComplexCosmicSystem` implementieren sollen. `Body` und `ComplexCosmicSystem` werden somit zu _Untertypen_ von `CosmicComponent`.
-  `ComplexCosmicSystem` repräsentiert eine doppelt verkettete Liste von Objekten des Typs `CosmicComponent`. Das heißt, mithilfe dieser Klasse lassen sich sowohl einzelne Himmelskörper (Objekte vom Typ `Body`) als auch Subsysteme (Objekte vom Typ `ComplexCosmicSystem`), die wiederum aus einzelnen Himmelskörpern und Subsystemen bestehen können, verwalten. Mithilfe dieser Klasse lässt sich somit eine Hierarchie von Systemen und Subsystemen beschreiben. Unser Sonnensystem ist ein Beispiel eines Systems, das mehrere Teilsysteme beinhaltet. Ein solches Teilsystem ist beispielsweise das System Erde und Erdmond. Ein anderes Teilsystem wäre Jupiter mit seinen Monden.
- `Aufgabe4Test` ist wie bisher eine vorgegebene Klasse, die sie zum Testen Ihrer Implementierung verwenden sollten. Bei einer fehlerfreien Implementierung sollten bei der Ausführung dieser Klasse keine Exceptions geworfen werden und alle Tests als erfolgreich ("successful") ausgegeben werden. Sie müssen diese Klasse nicht verändern, können aber eigene Testfälle hinzufügen.

Hinweis: Bitte beachten Sie, dass das Projekt erst kompiliert werden kann, wenn Sie die unten angeführten Änderungen an der Klasse `Body` vornehmen. 

## Aufgaben

Allgemeiner Hinweis: in dieser Aufgabenstellung geht es vorwiegend um die Umsetzung einer hierarchischen Datenstruktur zur Beschreibung von einzelnen Himmelskörpern und deren Zugehörigkeit zu (Sub-)Systemen. Die Verwendung einer solchen Datenstruktur zur Simulation der Planetenbewegungen (wie in der Klasse `Simulation`) wird in dieser Aufgabenstellung nicht behandelt.

Ihre Aufgaben sind folgende:

**1. Implementierung von `CosmicComponent` in `Body`:**

Ändern Sie die Klasse `Body` so um, dass sie das Interface `CosmicComponent` implementiert. Die Methode `getName()` wird von der Klasse bereits implementiert und muss nicht verändert werden. Die Methoden `getMass()` und `getMassCenter()` geben lediglich die Masse bzw. Position des Himmelskörpers zurück. Ändern Sie weiters auch die Methode `toString()` um, sodass nur mehr der Name des Himmelskörpers ohne dessen Eigenschaften zurückgegeben wird.

**2. Implementierung von `CosmicComponent` in `ComplexCosmicSystem`:**

 Vervollständigen Sie die Klasse `ComplexCosmicSystem`, die ebenfalls das Interface `CosmicComponent` implementiert, gemäß den Kommentaren in der Datei. Diese Klasse dient zur Verwaltung von Objekten vom Typ `CosmicComponent` in Form einer doppelt verketteten Liste. Die Methoden zum Einfügen, Löschen und Abfragen von Listenelementen können somit aus der Implementierung von `CosmicSystem` aus dem vorigen Aufgabenblatt 3 übernommen werden. 

Weitere Methoden:

- `toString()`: diese Methode soll eine textuelle Beschreibung der Hierarchie von Himmelskörpern und Subsystemen liefern. Dafür werden die Namen aller Einträge eines ComplexCosmicSystem jeweils in {}-Klammern gesetzt. Wenn unser System namens "Solar System" beispielsweise aus den Einträgen "Sun", "Earth System" (ein System mit den Himmelskörpern "Earth" und "Moon") und "Jupiter System" (ein System mit dem Himmelskörper "Jupiter") besteht, so lautet die Ausgabe

    `"Solar System{Sun, Earth System{Earth, Moon}, Jupiter System{Jupiter}}"`

- `numberOfBodies()`: diese Methode liefert die Gesamtanzahl aller Himmelskörper (nicht Systeme) im System bzw. Himmelskörper, das heißt alle Objekte vom Typ `Body`. Das oben genannte "Solar System" besteht z.B. aus 4 Himmelskörpern, das "Earth System" jedoch nur aus 2.

- `getMass()`: diese Methode liefert die Summe der Massen aller Himmelskörper. Auf "Solar System" angewendet, müsste diese Methode somit die Summe der Massen von "Sun", "Earth", "Moon" und "Jupiter" zurückgeben.

- `getMassCenter()`: diese Methode liefert den Schwerpunkt aller Himmelskörper. Dieser entspricht dem mit den Massen gewichteten Mittelwert aller Positionen, es müssen daher alle Positionen mit der jeweiligen Masse multipliziert und aufsummiert werden und das Resultat durch die Summe aller Massen dividiert werden. Nutzen Sie dafür die bereits implementierten Rechenoperationen in `Vector3`.  


  Hinweis: Nutzen Sie für die Umsetzung dieser 4 Methoden Rekursion sowie das Konzept des _dynamischen Bindens_. Da `Body`und `ComplexCosmicsystem` Untertypen von `CosmicComponent` sind, haben sie jeweils eine eigene Implementierung der in `CosmicComponent` definierten Methoden und es wird zur Laufzeit entschieden, von welchem dynamischen Typ ein Objekt ist und welche Methode somit ausgeführt wird. Sie dürfen hier keine Typumwandlungen (Casts) und auch nicht die Methoden `getClass()` und `instanceOf()` verwenden.

#### _Punkteaufteilung_

- Ìmplementierung von `CosmicComponent` in `Body`: 0.5 Punkte
- Implementierung einer doppelt verketteten Liste in `ComplexCosmicSystem`: 0.5 Punkte 
- `ComplexCosmicSystem`: Methode `toString()`: 1.5 Punkte
- `ComplexCosmicSystem`: Methode `numberOfBodies()`: 1 Punkt
- `ComplexCosmicSystem`: Methode `getMass()`: 0.5 Punkte
- `ComplexCosmicSystem`: Methode `getMassCenter()`: 1 Punkt


 - Gesamt: 5 Punkte 



