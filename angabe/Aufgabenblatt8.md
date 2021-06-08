# Aufgabenblatt 8

## Allgemeine Anmerkungen
Ihre Lösung für dieses Aufgabenblatt ist bis Dienstag, 15.6., 12h durch `git commit` und `push` 
abzugeben.
Wenn Sie zusätzlich zu den gefragten Klassen und Interfaces weitere Klassen oder 
Interfaces definieren, achten Sie darauf, dass die Klassennamen mit `My` beginnen, um Konflikte 
mit späteren Aufgabenblättern zu vermeiden.

Zusammen mit dieser Angabe werden die Dateien `CosmicSystemIndexTree.java`, `BodyComparator.java`,
`ComplexNode.java`, `ComplexNullNode.java` und `ComplexNonNullNode.java` mitgeliefert. Es wird 
keine Testklasse mitgeliefert.

## Ziel
Ziel der Aufgabe ist das Verständnis und die Anwendung der Konzepte: Schleifeninvarianten vs. 
Invarianten, Zusicherungen unterscheiden, Code-Review (siehe Skriptum Seiten 137–153).

## Aufgaben

1. Statisches Programmverstehen:
    - Fügen Sie die Vorbedingungen (V) und Nachbedingungen (N) für die Methoden im Interface 
    `IndexTreeNode` in den Kommentaren hinzu. 
    - Fügen Sie Zusicherungen als Hoare-Tripel `{V}S{N}` an den passenden Stellen in 
    der Klasse `CosmicSystemIndexTree` ein. `S` soll dabei eine Anweisung sein, die 
    einen Methodenaufruf von `IndexTreeNode` enthält.
    Sie können die entsprechenden Zusicherungen als Kommentare einfügen, oder - sofern sich die 
    Zusicherungen in Java ausdrücken lassen - entsprechende `assert`-Anweisungen.
    - Bestimmen Sie jeweils eine Schleifeninvariante für die Schleifen in der Methode `add` der 
    Klasse `CosmicSystemIndexTree`. Fügen Sie an den passenden Stellen Kommentare oder 
    `assert`-Anweisung ein.
2. Zusicherungen und Untertypen: 
    - Fügen Sie die Vorbedingungen und Nachbedingungen für die Methoden in den Klassen 
    `IndexTreeNullNode` und `IndexTreeNonNullNode` als Kommentare bei den Methoden hinzu.
    - Überprüfen Sie, ob die im Skriptum auf Seite 142 beschriebenen Bedingungen für die Bildung
    von Untertypen erfüllt sind und weisen Sie ggfs. in den Kommentaren auf Fehler hin.
3. Ändern Sie ihre Klasse `ComplexCosmicSystem` wie folgt: Definieren Sie ein Interface 
   `ComplexNode` als gemeinsamen Obertyp für `ComplexNullNode` und `ComplexNonNullNode` 
   und implementieren Sie es in diesen Klassen. Überlegen Sie sich entsprechende 
   Zusicherungen und nutzen Sie diese, um den Code von `ComplexCosmicSystem` zu
   vereinfachen. Verwenden Sie die Klasse `CosmicSystemIndexTree` als Vorlage. 
   (Dort ist das gegebene Interface `IndexTreeNode` ein Obertyp für `IndexTreeNullNode` und 
   `IndexTreeNonNullNode`). 

### Denkanstöße (ohne Bewertung)

1. Überlegen Sie sich, wie man durch ein Interface `Body` (anstelle der Klasse) und private 
Konstruktoren in den Klassen, die `Body` implementieren, sicher stellen könnte, dass jeder Planet 
nur einmal vorkommen kann. Was würde das für die Implementierung von `equals` und `hashCode` 
bedeuten?

#### _Punkteaufteilung_
 
 - Vor-/Nachbedingungen in `IndexTreeNode`: 1 Punkt
 - Hoare-Tripel in  `CosmicSystemIndexTree`: 1 Punkt
 - Schleifeninvarianten in `CosmicSystemIndexTree`: 0.5 Punkt
 - Vorbedingungen und Nachbedingungen in `IndexTreeNullNode` und `IndexTreeNonNullNode` hinzufügen
   und prüfen: 1 Punkt
 - `ComplexCosmicSystem` ändern: 1.5 Punkte
   
 