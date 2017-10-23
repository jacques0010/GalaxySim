package Objects;

import MathLib.Vector2D;

/**
 * Created by Justin on 8/30/2017.
 */
public class GalaxyCore extends GravObjects {

    private final double MASS;

    public GalaxyCore(double x, double y, Vector2D velocity, double mass, int size) {
        super(x, y, velocity, size);
        this.MASS = mass;
    }

    public double getMASS() {
        return MASS;
    }
}
