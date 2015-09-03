package edu.noctrl.craig.generic;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.deitel.cannongame.R;

import org.openintents.sensorsimulator.hardware.Sensor;
import org.openintents.sensorsimulator.hardware.SensorEvent;
import org.openintents.sensorsimulator.hardware.SensorEventListener;

import java.util.ArrayList;

/**
 * Created by craig_000 on 5/9/2015.
 */
public abstract class World implements View.OnTouchListener, SensorEventListener {
    public static final int ENEMIES = 10; // sections in the target
    public static final int MISS_PENALTY = 2; // seconds deducted on a miss
    public static final int HIT_REWARD = 3; // seconds added on a hit
    protected static final float TARGET_WIDTH = 540;
    protected static final float TARGET_HEIGHT = 960;
    protected static final float TARGET_PIXELS_PER_METER = 64F / 30F;//ship len = 30m  ship base = 64px
    public static Object GUI_LOCKER = new Object();
    public static Resources resources;
    public static float PIXELS_PER_METER = 64F / 30F;//ship len = 30m  ship base = 64px
    public static double totalElapsedTime = 0;
    public static int kills;
    public static int remaining;
    public static double timeLeft; // time remaining in seconds
    public static int shotsFired; // shots the user has fired
    public static int score;
    public static boolean cannonballOnScreen = false;
    public static int stage = 1; //starting stage
    public static SensorEventListener sel;
    public int width;
    public int height;
    public StateListener listener;
    public Point3F worldScale = Point3F.identity();
    protected ArrayList<GameObject> objects = new ArrayList<>(1000);
    protected ArrayList<GameObject> newObjects = new ArrayList<>(1000);
    private SoundManager soundManager;
    private Paint textPaint; // Paint used to draw text


    public World(StateListener listener, SoundManager sounds) {
        this.listener = listener;
        this.soundManager = sounds;
        textPaint = new Paint();
        sel = this;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        return true;
    }


    public void addObject(GameObject obj) {
        synchronized (newObjects) {
            newObjects.add(obj);
        }
    }

    protected void initialize() {

    }

    public void updateSize(float width, float height) {
        this.width = (int) width;
        this.height = (int) height;
        worldScale = Point3F.identity();
        worldScale.X = width / TARGET_WIDTH;
        worldScale.Y = width / TARGET_WIDTH;
        textPaint.setTextSize(width / 20); // text size 1/20 of screen width
        PIXELS_PER_METER = worldScale.Y * TARGET_PIXELS_PER_METER;
        if (objects.size() == 0) {
            initialize();
        }
    }

    public void update(float elapsedTimeMS) {
        float interval = elapsedTimeMS / 1000.0F; // convert to seconds
        timeLeft -= interval; // subtract from time left

        // if the timer reached zero
        if (timeLeft <= 0.0) {
            score = ((int) totalElapsedTime * 50) + (kills * 100) - (remaining * 100);
            soundManager.stopSoundLooped(Stage1.backgroundStreamID);
            listener.onGameOver(true);
        }
        // if killed 10 enemies in first level, move onto second stage
        if (remaining == 0 && stage != 2) {
            soundManager.stopSoundLooped(Stage1.backgroundStreamID);
            ++stage;
            listener.nextStage();
        } else if (remaining == 0 && stage == 2) { //change 2 to last stage
            score = ((int) totalElapsedTime * 50) + (kills * 100) - (remaining * 100);
            soundManager.stopSoundLooped(Stage2.backgroundStreamID);
            ++stage;
            listener.nextStage();
        }

        for (GameObject obj : objects) {
            obj.update(interval);
        }

    } // end method updatePositions


    public void draw(Canvas canvas) {
        if (canvas != null) {
            canvas.drawColor(Color.parseColor("#33B5E5"));
            // display time remaining
            canvas.drawText(resources.getString(
                    R.string.time_remaining_format, timeLeft), 30, 50, textPaint);
            for (GameObject obj : objects) {
                obj.draw(canvas);
            }
        }
    }

    public void cullAndAdd() {
        CollisionDetection.checkCollisions(objects);
        GameObject go;
        ArrayList<GameObject> removed = new ArrayList<>(objects.size());
        synchronized (newObjects) {
            for (int i = objects.size() - 1; i >= 0; i--) {
                go = objects.get(i);
                if (go.offScreen) {
                    objects.remove(i);
                    removed.add(go);
                }
            }
            for (GameObject obj : newObjects) {
                if (obj.drawOrder == GameObject.DrawOrder.Foreground)
                    objects.add(obj);
                else
                    objects.add(0, obj);
            }
            newObjects.clear();
        }
        for (GameObject obj : removed) {
            obj.cull();
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //Log.i("Ryan Debug", "We are in world");
    }

    public interface StateListener {
        void onGameOver(boolean lost);

        void nextStage();
    }
}
