package MathLib;

/**
 * Created by Justin on 8/30/2017.
 */
public class Vector2D {
    private final double magX;
    private final double magY;

    public Vector2D() {
        magX = 0;
        magY = 0;
    }

    public Vector2D(double magX, double magY) {
        this.magX = magX;
        this.magY = magY;
    }

    public double getMagX() {
        return magX;
    }

    public double getMagY() {
        return magY;
    }
}
