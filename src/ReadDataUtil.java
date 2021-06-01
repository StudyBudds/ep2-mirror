import java.io.*;

public class ReadDataUtil {

    /* some variables that might be helpful in 'Simulation.java':
    Body sun = new Body("Sol",1.989e30,696340e3,new Vector3(0,0,0),new Vector3(0,0,0),StdDraw.YELLOW);
    Body earth = new Body("Earth",5.972e24,6371e3,new Vector3(0,0,0),new Vector3(0,0,0),StdDraw.BLUE);
    Body mercury = new Body("Mercury",3.301e23,2440e3,new Vector3(0,0,0),new Vector3(0,0,0),StdDraw.GRAY);
    Body venus = new Body("Venus",4.86747e24,6052e3,new Vector3(0,0,0),new Vector3(0,0,0),StdDraw.PINK);
    Body mars = new Body("Mars",6.41712e23,3390e3,new Vector3(0,0,0),new Vector3(0,0,0),StdDraw.RED);
    Body moon = new Body("Moon", 7.349e22, 1.737e6, new Vector3(0,0,0), new Vector3(0,0,0),
            StdDraw.GRAY);
    Body deimos = new Body("Deimos", 1.8e20, 6e3, new Vector3(0,0,0), new Vector3(0,0,0),
            StdDraw.GRAY);
    Body phobos = new Body("Phobos", 1.08e20, 5e3, new Vector3(0,0,0), new Vector3(0,0,0),
            StdDraw.GRAY);
    Body vesta = new Body("Vesta", 2.5908e20, 5e5, new Vector3(0,0,0), new Vector3(0,0,0),
            StdDraw.GRAY);
    Body pallas = new Body("Pallas", 2.14e20, 5e5, new Vector3(0,0,0), new Vector3(0,0,0),
            StdDraw.GRAY);
    Body hygiea = new Body("Hygiea", 8.32e19, 5e5, new Vector3(0,0,0), new Vector3(0,0,0),
            StdDraw.GRAY);
    Body ceres = new Body("Ceres", 9.394e20, 5e5, new Vector3(0,0,0), new Vector3(0,0,0),
                StdDraw.GRAY);
    Body oumuamua = new Body("Oumuamua",8e6,0.2e3,new Vector3(0,0,0),new Vector3(0,0,0),
                StdDraw.WHITE);
    */

    // Reads the position and velocity vector on the specified 'day' from the file with the
    // specified 'path', and sets position and current velocity of 'b' accordingly. If
    // successful the method returns 'true'. If the specified 'day' was not found in the file,
    // 'b' is unchanged and the method returns 'false'.
    // The file format is validated before reading the state.
    // Lines before the line $$SOE and after the line $$EOE the are ignored. Each line of the
    // file between the line $$SOE and the line $$EOE is required to have the following format:
    // JDTDB, TIME, X, Y, Z, VX, VY, VZ
    // where JDTDB is interpretable as a 'double' value, TIME is a string and X, Y, Z, VX, VY and
    // VZ are interpretable as 'double' values. The character ',' must only be used as field
    // separator. If the file is not found, an exception of the class 'StateFileNotFoundException' is
    // thrown. If it does not comply with the format described above, the method throws an
    // exception of the class 'StateFileFormatException'. Both exceptions are subtypes of 'IOException'.
    public static boolean readConfiguration(Body b, String path, String day) throws IOException {

            //TODO: implement method
            return false;

    }

}

