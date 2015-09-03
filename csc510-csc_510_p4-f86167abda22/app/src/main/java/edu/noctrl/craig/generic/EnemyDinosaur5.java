package edu.noctrl.craig.generic;

import android.graphics.Rect;

import java.util.Random;

/**
 * Created by Ryan on 5/16/2015.
 */
public class EnemyDinosaur5 extends GameSprite {

    protected static final Rect source = new Rect(136, 0, 176, 25);
    protected static final Point3F scale = Point3F.identity();

    protected SoundManager sounds;
    protected double timeLeftBeforeEnemyUpdate;

    public EnemyDinosaur5(World theWorld, int ranx, int rany, SoundManager sounds) {
        super(theWorld);

        this.world = theWorld;
        this.position = new Point3F(ranx, rany, 0F);
        this.baseVelocity = new Point3F(-1F, 0F, 0F);
        this.speed = 20.00F;
        this.substance = Collision.SolidAI;
        this.collidesWith = Collision.SolidPlayer;
        this.sounds = sounds;
    }


    @Override
    public Rect getSource() {
        return source;
    }

    @Override
    public Point3F getScale() {
        return scale;
    }

    @Override
    public void update(float interval) {
        Point3F touch;

        timeLeftBeforeEnemyUpdate += interval; //amount of time changed reset > every second;
        Random ran1 = new Random();
        Point3F enemyPosition = new Point3F(position.X + ran1.nextFloat() * 10,position.Y + ran1.nextFloat() * 10,0);

        Random rn = new Random();
        if (timeLeftBeforeEnemyUpdate > rn.nextDouble() + 1.0) { //update minimum of every second
            touch = new Point3F(HeroRobot.staticPosition.X,HeroRobot.staticPosition.Y , 0f);
            touch.subtract(enemyPosition);
            baseVelocity = touch.normalize();
            updateVelocity();
            timeLeftBeforeEnemyUpdate = 0.0;
            speed += 10;

        }

        position.add(velocity.clone().mult(interval));
    }

/*    public int randomRange(int maximum, int minimum) {
        Random rn = new Random();
        int range = maximum - minimum + 1;
        int randomNum = rn.nextInt(range) + minimum;
        return randomNum;
    }*/

    @Override
    public void collision(GameObject other) {
        sounds.playSound(SoundManager.ALIEN_HIT);
        other.kill();
        //Ryan Addition
        ++World.kills;
        --World.remaining;
        World.timeLeft += World.HIT_REWARD; // add reward to remaining time
    }

    @Override
    public void cull() {
    }
}
