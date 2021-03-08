# Aufgabenblatt 1

## Allgemeine Anmerkungen
Ihre Lösung für dieses Aufgabenblatt ist bis 16.3.2021 12:00 Uhr durch `git commit` und `push` abzugeben. Mit der Angabe werden folgende Dateien mitgeliefert, die sie gemäß der Angabe verändern müssen: `Simulation.java`, `Vector3.java`, `Body.java`. Die zusätzliche Datei `StdDraw.java` wird nur zum Zeichnen verwendet und sollte nicht verändert werden. 

Vorgegebene Programmteile dürfen nur an den Stellen verändert werden, die mit TODO markiert sind. Zusätzliche Klassen, Interfaces, Methoden und Variablen dürfen aber eingefügt werden. Wenn Sie zusätzlich zu den gefragten Klassen weitere Klassen definieren, achten Sie darauf, dass die Klassennamen mit `My` beginnen, um Konflikte mit späteren Aufgabenblättern zu vermeiden.

## Verwendung in IntelliJ
Diese Aufgabenstellung ist ein vollständiges IntelliJ-Projekt, das Sie bereits in IntelliJ öffnen können. Sie müssen daher kein eigenes Projekt anlegen. Öffnen Sie nach dem Klonen des Repos in IntelliJ einfach den entsprechenden Ordner. Gegebenenfalls muss noch folgender Schritt ausgeführt werden:

- Einstellen des _Project SDK_: Öffnen Sie dazu in IntelliJ die Projekteinstellungen (_File_ -> _Project Structure..._) und wählen Sie ein JDK unter _Project | Project SDK_ aus.

## Thema 
Das allgemeine Thema dieses und der kommenden Aufgabenblätter ist der Weltraum und die Simulation der physikalischen Gesetze, die für Himmelskörper gelten. Obwohl ein möglichst exaktes physikalisches Modell wünschenswert ist, ist bei der Implementierung die Genauigkeit der physikalischen Modelle sekundär. Konzeptuelle Fehler bei den physikalischen Berechnungen spielen keine Rolle bei der Bewertung. Schwerpunkt sind die Konzepte der Programmiersprache. 

## Ziel
Ziel der Aufgabe ist die Anwendung der Konzepte: Objekt- vs. Klassenmethode, Datensatz, Data Hiding, Konstruktoren (siehe Skriptum Seiten 31-50).

## Aufgaben
1. Lesen Sie sich die Kommentare in den Dateien durch und führen Sie die Klasse `Simulation` aus. 
2. Data hiding: 
    1. Machen Sie in den Klassen `Vector3` und `Body` alle Objektvariablen `private`.
    2. Definieren Sie entsprechende Konstruktoren, um die Objektvariablen zu initialisieren. `Simulation` soll nur noch diese nutzen und nicht mehr direkt auf die Objektvariablen zugreifen dürfen.
3. Datenkapselung: Anstelle der gegebenen statischen Methoden in der Datei `Simulation.java` sollen nur noch entsprechende Objektmethoden der Klassen `Body` und `Vector3` benutzt werden. Implementieren Sie dazu die spezifizierten Methoden und bauen Sie `Simulation` so um, dass anstelle der Aufrufe statischer Methoden Objektmethoden genutzt werden. Sie sollen alle in `Body` und `Vector3` spezifizierten Methoden implementieren, auch wenn nicht alle von `Simulation` genutzt werden. Die in `Simulation.java` gegebenen statischen Methoden können dann entfernt werden (natürlich bis auf `main`). Nutzen Sie die implementierten Methoden auch  in `Simulation.java`, um die Himmelskörper zu bewegen und zu zeichnen.  
4. Freiwillige Zusatzaufgabe: Fügen Sie weitere Himmelskörper (z.B. Planeten, Monde oder Asteroiden) in die Simulation ein. Recherchieren Sie dazu die benötigten Daten (eine mögliche Quelle ist beispielsweise  [www.astronomie.de](https://www.astronomie.de/das-sonnensystem/planeten-und-monde/)). Verwenden Sie die ungefähren Angaben zur mittleren Orbitalgeschwindigkeit oder der Orbitalgeschwindigkeit bei Perihel (sonnennächster Punkt) oder Aphel (sonnenfernster Punkt) der Umlaufbahn. Der Einfachheit halber kann - wie bei den in der Simulation bereits enthaltenen Planeten - Perihel oder Aphel auf eine Achse des Koordinatensystems gelegt werden. Die Daten müssen nicht exakt sein.

## Zusatzfragen
Beantworten Sie folgende Zusatzfragen als Kommentar in `Simulation.java`:

1. Was versteht man unter _Datenkapselung_? Geben Sie ein Beispiel, wo dieses Konzept in dieser Aufgabenstellung angewendet wird. 
2. Was versteht man unter _Data Hiding_? Geben Sie ein Beispiel, wo dieses Konzept in dieser Aufgabenstellung angewendet wird. 

#### _Punkteaufteilung_


- Korrekte Sichtbarkeit von Objektvariablen in `Vector3` und `Body` und Initialisierung mittels Konstruktoren: 1 Punkt
- Korrekte Objektmethoden in `Vector3`: 1 Punkt
- Korrekte Objektmethoden in `Body`: 1.5 Punkte
- Korrekte Verwendung der Objektmethoden in `Simulation`: 1 Punkt
- Korrekte Beantwortung der Zusatzfragen: 0.5 Punkte
 - Gesamt: 5 Punkte


[https://www.astronomie.de/das-sonnensystem/planeten-und-monde/]: www.astronomie.de