package Main;

import MathLib.Trig;
import Objects.GalaxyCore;
import Objects.Star;
import Physics.Physics;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static Objects.ObjectCreator.GalaxyCreator.populateGalaxy;

/**
 * Created by Justin on 8/15/2017.
 */
public class Main extends Application {
    private static int width = 1200;
    private static int height = 720;

    @Override
    public void start(Stage PrimaryStage) throws Exception {
        Group simulation = new Group();
        Scene sim = new Scene(simulation);
        Canvas canvas = new Canvas(width, height);

        GalaxyCore[] galaxies = {
                new GalaxyCore(300, 300, Trig.decomposeVector(30000, 3 * Math.PI / 4), 2E42, 5),
                new GalaxyCore(300, 600, Trig.decomposeVector(30000, -Math.PI/4), 2E42, 5),
                new GalaxyCore(300 + (300 * Math.sqrt(3)/2), 450, Trig.decomposeVector(30000, -3 * Math.PI/4), 2E42, 5),
               //new GalaxyCore(400, 400, Trig.decomposeVector(30000, 0), 2E42, 5),
                //new GalaxyCore(800, 200, Trig.decomposeVector(30000, -Math.PI), 2E42, 5),


        };
        Star[] starArray = populateGalaxy(galaxies, 16, 32, 40, 70, 82, 90);
        simulation.getChildren().add(canvas);
        GraphicsContext graphics = canvas.getGraphicsContext2D();

        for (GalaxyCore core : galaxies) {
            simulation.getChildren().add(core.getCircle());
        }
        for (Star star : starArray) {
            simulation.getChildren().add(star.getCircle());
        }


        PrimaryStage.setTitle("Galaxy Sim");
        PrimaryStage.setScene(sim);

        graphics.setFill(Color.BLACK);
        graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        new AnimationTimer() {
            final long startTime = System.currentTimeMillis();
            double lastTime = 0;
            double deltaTime = 0;
            double accumulatedTime = 0;
            final double timeStep = .0166;

            @Override
            public void handle(long currentTime) {
                if (lastTime == 0) {
                    lastTime = currentTime;
                    return;
                }
                deltaTime = (currentTime - lastTime) / 1e9;
                double deltaTimeCapped = Math.min(deltaTime, timeStep);
                accumulatedTime += deltaTimeCapped;
                lastTime = currentTime;
                while (accumulatedTime >= timeStep) {
                    graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                    Physics.calcPos(galaxies, galaxies);
                    Physics.calcPos(galaxies, starArray);
                    for (GalaxyCore core : galaxies) {
                        core.update();
                        for (Star star : starArray) {
                            star.update();
                        }
                    }
                    //System.out.println(accumulatedTime);
                    accumulatedTime -= timeStep;
                }
            }
        }.start();


        PrimaryStage.show();
    }

    public static int getHeight() {
        return height;
    }

    public static int getWidth() {
        return width;
    }
}
