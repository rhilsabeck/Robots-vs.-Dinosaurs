package edu.noctrl.craig.generic;

import android.graphics.Rect;

import java.util.Random;

/**
 * Created by Ryan on 5/16/2015.
 */
public class EnemyDinosaur3 extends GameSprite {

    protected static final Rect source = new Rect(92, 0, 136, 25); //blue
    protected static final Point3F scale = Point3F.identity();

    protected SoundManager sounds;
    protected Point3F moveTowards;
    protected double timeLeftBeforeEnemyUpdate;
    protected double timeLeftBeforeEnemyFire;
    protected EnemyLazer enemylazer;

    public EnemyDinosaur3(World theWorld, int ranx, int rany, SoundManager sounds) {
        super(theWorld);

        this.world = theWorld;
        this.position = new Point3F(ranx, rany, 0F);
        this.baseVelocity = new Point3F(1F, 1F, 0F);
        this.speed = 60.00F;
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

        timeLeftBeforeEnemyUpdate += interval; //amount of time changed reset > every second;
        timeLeftBeforeEnemyFire += interval; //amount of time changed reset > every second;
        Point3F enemyPosition = position.clone();


        Random rn = new Random();
        if (timeLeftBeforeEnemyFire > rn.nextDouble() + randomRange(4, 1)) {
            //fire randomly
            enemylazer = new EnemyLazer(world, Math.round(enemyPosition.X), Math.round(enemyPosition.Y));
            world.addObject(enemylazer);
            timeLeftBeforeEnemyFire = 0.0;
        }

        if (timeLeftBeforeEnemyUpdate > rn.nextDouble() + 1.0) { //update minimum of every second
            moveTowards = new Point3F(randomRange(Math.round(World.TARGET_HEIGHT - 40), 40), randomRange(Math.round(World.TARGET_WIDTH - 40), 40), 0F);
            moveTowards.subtract(enemyPosition);
            baseVelocity = moveTowards.normalize();
            updateVelocity();
            timeLeftBeforeEnemyUpdate = 0.0;
        }

        position.add(velocity.clone().mult(interval));
    }

    public int randomRange(int maximum, int minimum) {
        Random rn = new Random();
        int range = maximum - minimum + 1;
        int randomNum = rn.nextInt(range) + minimum;
        return randomNum;
    }

    @Override
    public void collision(GameObject other) {
        sounds.playSound(SoundManager.ALIEN_HIT);
        other.kill();
        ++World.kills;
        --World.remaining;
        World.timeLeft += World.HIT_REWARD; // add reward to remaining time
    }

    @Override
    public void cull() {
    }
}
