package Physics;

import MathLib.Trig;
import MathLib.Vector2D;
import Objects.GalaxyCore;
import Objects.GravObjects;

/**
 * Created by Justin on 8/30/2017.
 */
public class Physics {
    public static final double G = 6.674 * Math.pow(10, -11);
    private static final double LIGHT_YEAR = 9.46 * Math.pow(10, 15);
    private static final double DT = 3.1536E12; // 100,000 years in seconds
    public static final double SCALE = LIGHT_YEAR * 1000;


    private Physics() {
    }

    private static <GravOb extends GravObjects> void calcAcceleration(GalaxyCore[] coreArray, GravOb[] obArray) {
        for (GravOb ob : obArray) {
            ob.setAccelVector(new Vector2D());
            Vector2D bufferVector;
            for (GalaxyCore core : coreArray) {
                double dist = Trig.calcDist(ob.getX(), ob.getY(), core.getX(), core.getY()) * SCALE;
                double angle = Trig.calcAngle(ob.getX(), ob.getY(), core.getX(), core.getY());
                double acceleration = dist * SCALE > 5 ? (G * core.getMASS()) / Math.pow(dist, 2) : 0;

                bufferVector = new Vector2D(Math.cos(angle) * acceleration, Math.sin(angle) * acceleration);
                ob.setAccelVector(Trig.addVector(ob.getAccelVector(), bufferVector));
            }
        }
    }

    private static <GravOb extends GravObjects> void calcVelocity(GravOb[] obArray) {
        for (GravOb ob : obArray) {
            Vector2D bufferVector = new Vector2D(ob.getAccelVector().getMagX() * DT, ob.getAccelVector().getMagY() * DT);
            ob.setVelVector(Trig.addVector(ob.getVelVector(), bufferVector));
        }
    }

    public static <GravOb extends GravObjects> void calcPos(GalaxyCore[] coreArray, GravOb[] obArray) {
        for (GravOb ob : obArray) {
            ob.setX(ob.getX() + ob.getVelVector().getMagX() * DT / SCALE);
            ob.setY(ob.getY() + ob.getVelVector().getMagY() * DT / SCALE);
        }
        calcAcceleration(coreArray, obArray);
        calcVelocity(obArray);
    }
}