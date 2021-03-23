# Aufgabenblatt 2

## Allgemeine Anmerkungen
Ihre Lösung für dieses Aufgabenblatt ist bis Dienstag, 13.4. 12h durch `git commit` und `push` abzugeben. Mit der Angabe werden folgende Dateien mitgeliefert: `CosmicSystem.java`, `CosmicSystemTree.java` und `Aufgabe2Test.java`.
Diese Klassen dürfen nur an den Stellen verändert werden, die mit TODO markiert sind. Zusätzliche Klassen, Interfaces, Methoden und Variablen dürfen aber eingefügt werden. Wenn Sie zusätzlich zu den gefragten Klassen, weitere Klassen definieren, achten Sie darauf, dass die Klassennamen mit `My` beginnen, um Konflikte mit späteren Aufgabenblättern zu vermeiden.

## Ziel
Ziel der Aufgabe ist die Implementierung einer rekursiven und einer assoziativen Datenstruktur (siehe Skriptum Seiten 50-70).

## Beschreibung der gegebenen Dateien
- `CosmicSystem` repräsentiert eine Implementierung einer einfach verketteten Liste zur Verwaltung von Objekten des Typs `Body`.
-  `CosmicSystemTree` repräsentiert eine Implementierung eines Binärbaums zur Verwaltung und Indexierung von Objekten des Typs `CosmicSystem`.
- `Aufgabe2Test` ist eine vorgegebene Klasse, die sie zum Testen Ihrer Implementierung verwenden sollten. Bei einer fehlerfreien Implementierung sollten bei der Ausführung dieser Klasse keine Exceptions geworfen werden und alle Tests als erfolgreich ("successful") ausgegeben werden. Sie müssen diese Klasse nicht verändern, können aber eigene Testfälle hinzufügen. 

## Aufgaben

Ihre Aufgaben sind folgende:

1. Implementieren Sie in `Body` eine zusätzliche Methode namens `getName()` zum Abfragen des Namens des Himmelskörpers. 

2. Vervollständigen Sie die Klassendefinition in `CosmicSystem.java` gemäß der Kommentare in der Datei. Es dürfen keine vorgefertigten Klassen aus dem Java-Collection-Framework oder Arrays benutzt werden. Stattdessen sollen Sie eine einfach verkettete Liste selbst implementieren.

3. Bauen Sie die bereits bestehende Klasse `Simulation` so um, dass zur Verwaltung der Himmelskörper anstelle des Arrays ein Objekt der Klasse `CosmicSystem` verwendet wird. Das heißt beispielsweise, dass die Zugriffe auf die Himmelskörper der Simulation über Methoden von `CosmicSystem` erfolgen müssen. Testen Sie die Simulation mit den folgenden fünf Himmelskörpern:
    ```
    Body sun = new Body("Sol",1.989e30,696340e3,new Vector3(0,0,0),new Vector3(0,0,0),StdDraw.YELLOW);
    Body earth = new Body("Earth",5.972e24,6371e3,new Vector3(-1.394555e11,5.103346e10,0),new Vector3(-10308.53,-28169.38,0),StdDraw.BLUE);
    Body mercury = new Body("Mercury",3.301e23,2440e3,new Vector3(-5.439054e10,9.394878e9,0),new Vector3(-17117.83,-46297.48,-1925.57),StdDraw.GRAY);
    Body venus = new Body("Venus",4.86747e24,6052e3,new Vector3(-1.707667e10,1.066132e11,2.450232e9),new Vector3(-34446.02,-5567.47,2181.10),StdDraw.PINK);
    Body mars = new Body("Mars",6.41712e23,3390e3,new Vector3(-1.010178e11,-2.043939e11,-1.591727E9),new Vector3(20651.98,-10186.67,-2302.79),StdDraw.RED);
    ```

4. Implementieren Sie die Klasse `CosmicSystemTree`, die einen binären Suchbaum für Objekte der Klasse `CosmicSystem` implementiert. Der Schlüssel für die Suche ist der Name eines Objekts der Klasse `Body` (nicht der eines Objekts der Klasse `CosmicSystem`). Der Wert, der für jeden Schlüssel gespeichert wird, ist eine Referenz auf das  `CosmicSystem`, in dem der Schlüssel vorkommt. Die Klasse bietet somit Methoden zur Suche nach Systemen, in denen bestimmte Himmelskörper (Objekte vom Typ `Body`) vorkommen. Zum Beispiel kann so die Suche mit dem Namen "Io" das Jupiter-System mit den Himmelskörpern Jupiter, Io, Europa, Ganymed und Kallisto liefern. 
   
   Hinweis: zum Schlüsselvergleich können Sie die Methode `compareTo(String anotherString)` der Klasse `String` verwenden.

   Hinweis: Bei dieser letzten Teilaufgabe geht es nicht mehr um die Simulation der Planetenbewegungen, das heißt, die Positionen und Bewegungen der Himmelskörper sind unwichtig und diese Klasse wird auch noch nicht in `Simulation` verwendet.

5. (optionale Bonus-Aufgabe) Implementieren Sie in `CosmicSystemTree` die Methode `drawTree`, die den Binärbaum zeichnet. Visualisieren Sie dafür alle Knoten des Baumes und deren Verbindungen. Beschriften Sie zusätzlich jeden Knoten mit dem Namen des Schlüssels (=Name des `Body`) und dem dazugehörigen Namen des `CosmicSystem`.


#### _Punkteaufteilung_

- `CosmicSystem`: Konstruktor und Methode `size()`: 0.5 Punkte
- `CosmicSystem`: Methode `add()`: 0.5 Punkte
- `CosmicSystem`: `get()`-Methoden: 1 Punkt
- Verwendung von `CosmicSystem` in `Simulation`: 0.5 Punkte
- `CosmicSystemTree`: Methode `add()`: 1 Punkt
- `CosmicSystemTree`: Methode `get()`: 1 Punkt
- `CosmicSystemTree`: Methoden `numberOfBodies()` und `toString`: 0.5 Punkte
 - Gesamt: 5 Punkte 


- (Optional) `CosmicSystemTree`: Methode `drawTree()`: 1 Bonuspunkt

Mit der Bonusaufgabe können Sie bis zu 6 Punkte für dieses Aufgabenblatt erreichen, wobei aber 5 Punkte weiterhin als Bewertungsgrundlage bestehen bleiben (d.h., Sie können durch den Bonuspunkt einen eventuell verlorenen Punkt in einem anderen Aufgabenblatt wieder wettmachen).

Für den Übungstest wird die Bonusaufgabe nicht vorausgesetzt.

