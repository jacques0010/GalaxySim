package MathLib;

import static java.lang.Math.*;

/**
 * Created by Justin on 8/30/2017.
 */
public class Trig {
    public static double calcDist(double x1, double y1, double x2, double y2) {
        return sqrt(pow(x2 - x1, 2) + pow(y2 - y1, 2));
    }

    public static double calcAngle(double x1, double y1, double x2, double y2) {
        return atan2(y2 - y1, x2 - x1);
    }

    public static Vector2D addVector(Vector2D v1, Vector2D v2) {
        return new Vector2D(v1.getMagX() + v2.getMagX(), v1.getMagY() + v2.getMagY());
    }

    public static Vector2D decomposeVector(double magnitude, double angle) {
        return new Vector2D(cos(angle) * magnitude, sin(angle) * magnitude);
    }
}
