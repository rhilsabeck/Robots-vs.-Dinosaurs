package edu.noctrl.craig.generic;

import org.openintents.sensorsimulator.hardware.Sensor;
import org.openintents.sensorsimulator.hardware.SensorEvent;

import java.util.Random;

/**
 * Created by Ryan on 5/20/2015.
 */
public class Stage3 extends World {

    public static int backgroundStreamID;
    public HeroRobot robot;
    protected SoundManager sounds;
    float x;
    float y;

    public Stage3(StateListener listener, SoundManager sounds) {
        super(listener, sounds);
        this.sounds = sounds;

        backgroundStreamID = sounds.playSoundLooped(SoundManager.BACKGROUND_SOUND_STAGE2);
        //add hero
        robot = new HeroRobot(this, 64, 302);
        robot.speed = 100.0F;
        robot.baseVelocity.X = 0;
        robot.baseVelocity.Y = 0;
        robot.updateVelocity();

        this.addObject(robot);

        //add 10 randomly positioned enemies
        for (int i = 0; i < 10; i++) {
            this.addObject(new EnemyDinosaur5(this, randomRange(Math.round(World.TARGET_HEIGHT)-50, 500),
                    randomRange(Math.round(World.TARGET_WIDTH)-50, 50), sounds));
        }
    }

    public int randomRange(int maximum, int minimum) {
        Random rn = new Random();
        int range = maximum - minimum + 1;
        int randomNum = rn.nextInt(range) + minimum;
        return randomNum;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    //float z;

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        x = sensorEvent.values[0];
        y = sensorEvent.values[1];
        //z = sensorEvent.values[2];

        robot.baseVelocity.X = x;
        robot.baseVelocity.Y = y;
        robot.updateVelocity();
    }
}