package edu.noctrl.craig.generic;

import android.graphics.Rect;

/**
 * Created by Ryan on 5/16/2015.
 */
public class EnemyDinosaur2 extends GameSprite {

    protected static final Rect source = new Rect(43, 0, 73, 25);
    protected static final Point3F scale = Point3F.identity();
    protected SoundManager sounds;

    public EnemyDinosaur2(World theWorld, int ranx, int rany, SoundManager sounds) {
        super(theWorld);

        this.position = new Point3F(ranx, rany, 0F);
        this.substance = Collision.SolidAI;
        this.collidesWith = Collision.None;
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
    public void collision(GameObject other){
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
