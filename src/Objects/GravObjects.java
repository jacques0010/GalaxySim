package Objects;

import MathLib.Vector2D;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Created by Justin on 8/30/2017.
 */
public class GravObjects {
    private double x;
    private double y;

    private Vector2D velVector;
    private Vector2D accelVector;

    private final Circle circle;
   // private final DropShadow shadow;
    //private final Glow glow;

    protected boolean isUpdated;

    GravObjects(double x, double y, Vector2D velVector, int size) {
        this.x = x;
        this.y = y;
        accelVector = new Vector2D();
        this.velVector = velVector;
        //glow= new Glow(0d);
       // shadow = new DropShadow(20 ,Color.VIOLET);
        circle = new Circle(x, y, size, Color.WHITE);
        //circle.setEffect(glow);
    }

    public synchronized double getX() {
        return x;
    }

    public synchronized void setX(double x) {
        this.x = x;
    }

    public synchronized double getY() {
        return y;
    }

    public synchronized void setY(double y) {
        this.y = y;
    }

    public synchronized Vector2D getVelVector() {
        return velVector;
    }

    public synchronized void setVelVector(Vector2D velVector) {
        this.velVector = velVector;
    }

    public synchronized Vector2D getAccelVector() {
        return accelVector;
    }

    public synchronized void setAccelVector(Vector2D accelVector) {
        this.accelVector = accelVector;
    }

    public Circle getCircle() {
        return circle;
    }

    public void update() {
        circle.setCenterX(x);
        circle.setCenterY(y);
    }
//   public synchronized void update(GalaxyCore[] coreArray){
//        while(isUpdated){
//            try {
//                wait();
//            }catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            Physics.calcPos(coreArray, this);
//            isUpdated = true;
//            notify();
//        }
//    }
//    public synchronized void isUpdated(){
//        while (!isUpdated){
//            try{
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            notify();
//        }
//    }
}
