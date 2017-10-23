package Objects.ObjectCreator;

import MathLib.Trig;
import Objects.GalaxyCore;
import Objects.Star;
import Physics.Physics;

import java.util.LinkedList;


/**
 * Created by Justin on 9/3/2017.
 */
public class GalaxyCreator {
    private static final int DISTANCE_INCREMENT = 7;
    //public static GalaxyCore createGalaxies(){

    //}
    public static Star[] populateGalaxy(GalaxyCore[] coreArray, int... stars) {
        LinkedList<Star> starList = new LinkedList<>();
        for (GalaxyCore core : coreArray) {
            int dist = 7;
            for (int star : stars) {
                double angle = Math.PI * 2 / star;
                for (int i = 0; i < star; i++) {
                    double x = Math.cos(angle * i) * dist + core.getX();
                    double y = Math.sin(angle * i) * dist + core.getY();
                    double velocity = Math.sqrt(Physics.G * core.getMASS() / (dist * Physics.SCALE));
                    System.out.println("Velocity: " + velocity);
                    double velocityAngle = (angle * i) - (Math.PI / 2);
                    starList.add(starList.size(), new Star(x, y, Trig.addVector(core.getVelVector(), Trig.decomposeVector(velocity, velocityAngle)), 1));
                }
                dist += DISTANCE_INCREMENT;
            }
        }
        return starList.toArray(new Star[starList.size()]);
    }
}

