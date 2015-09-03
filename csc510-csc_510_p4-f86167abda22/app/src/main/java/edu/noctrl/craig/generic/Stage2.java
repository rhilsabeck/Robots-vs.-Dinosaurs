package edu.noctrl.craig.generic;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

/**
 * Created by Ryan on 5/20/2015.
 */
public class Stage2 extends World {

    public static int backgroundStreamID;
    protected HeroRobot robot;
    protected float oldXPosition = 64; //added
    protected float oldYPosition = 302; //added
    protected float movedByX; //added
    protected float movedByY; //added
    protected HeroLazer lazer;
    protected boolean drag;
    protected SoundManager sounds;


    public Stage2(StateListener listener, SoundManager sounds, Context context) {
        super(listener, sounds);
        this.sounds = sounds;

        backgroundStreamID = sounds.playSoundLooped(SoundManager.BACKGROUND_SOUND_STAGE2);
        //add hero
        robot = new HeroRobot(this, 64, 302);

        this.addObject(robot);

        //add 10 randomly positioned enemies
        for (int i = 0; i < 10; i++) {
            Random rn = new Random();
            int check = rn.nextInt(10) + 1;
            if (check > 5) {
                this.addObject(new EnemyDinosaur3(this, randomRange(Math.round(World.TARGET_HEIGHT - 40), 200),
                        randomRange(Math.round(World.TARGET_WIDTH - 40), 40), sounds));
            } else {
                this.addObject(new EnemyDinosaur4(this, randomRange(Math.round(World.TARGET_HEIGHT - 40), 200),
                        randomRange(Math.round(World.TARGET_WIDTH - 40), 40), sounds));
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

        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            drag = true;
            return true;
        }
            if (event.getActionMasked() == MotionEvent.ACTION_MOVE) {
                if (drag == true) {
                    movedByX = event.getX() - oldXPosition;
                    movedByY = event.getY() - oldYPosition;

                    oldXPosition = event.getX();
                    oldYPosition = event.getY();
                    HeroRobot.staticPosition.X += movedByX;
                    HeroRobot.staticPosition.Y += movedByY;
                }

            }
            if (event.getActionMasked() == MotionEvent.ACTION_UP) {
                drag = false;

                //if the cannonball is not on the screen then add a new one
                if(!World.cannonballOnScreen) {
                    //add lazer and set start staticPosition of lazer x,y
                    lazer = new HeroLazer(this, (int) HeroRobot.staticPosition.X, (int) HeroRobot.staticPosition.Y);
                    this.addObject(lazer);
                    sounds.playSound(SoundManager.FIRE_ID);
                    lazer.baseVelocity = new Point3F(1,0,0);
                    lazer.updateVelocity();
                    cannonballOnScreen = true;
                }
                //Log.i("touch", "Up");
            }
            return true;
        }

}
