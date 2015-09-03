package edu.noctrl.craig.generic;

import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

/**
 * Created by Ryan on 5/16/2015.
 */
public class Stage1 extends World {

    public static int backgroundStreamID;
    protected HeroRobot robot;
    protected HeroLazer lazer;
    protected SoundManager sounds;



    public Stage1(StateListener listener, SoundManager sounds) {
        super(listener, sounds);
        this.sounds = sounds;

        backgroundStreamID = sounds.playSoundLooped(SoundManager.BACKGROUND_SOUND);

        //add hero
        robot = new HeroRobot(this, 64, 302);
        this.addObject(robot);

        //add 10 randomly positioned enemies
        for (int i = 0; i < 10; i++) {
            Random rn = new Random();
            int check = rn.nextInt(10) + 1;
            if (check > 5) {
                this.addObject(new EnemyDinosaur1(this, randomRange(Math.round(World.TARGET_HEIGHT - 40), 200),
                        randomRange(Math.round(World.TARGET_WIDTH - 40), 40), sounds));
            } else {
                this.addObject(new EnemyDinosaur2(this, randomRange(Math.round(World.TARGET_HEIGHT - 40), 200),
                        randomRange(Math.round(World.TARGET_WIDTH - 40), 40),sounds));
            }
        }
    }

    public int randomRange(int maximum, int minimum) {
        Random rn = new Random();
        int range = maximum - minimum + 1;
        int randomNum = rn.nextInt(range) + minimum;
        return randomNum;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Point3F touch;
        if(event.getActionMasked() == MotionEvent.ACTION_DOWN){

            //if the cannonball is not on the screen then add a new one
            if(!World.cannonballOnScreen) {
                //add lazer and set start staticPosition of lazer x,y
                lazer = new HeroLazer(this, 150, 302);
                this.addObject(lazer);
                sounds.playSound(SoundManager.FIRE_ID);

                //get touch staticPosition
                touch = new Point3F(event.getX(), event.getY(), 0f);
                //get staticPosition of lazer
                Point3F lazerPos = lazer.position.clone();
                touch.subtract(lazerPos);
                lazer.baseVelocity = touch.normalize();
                lazer.updateVelocity();
                cannonballOnScreen = true;
            }
        }
        return true;
    }

}
