package edu.noctrl.craig.generic;

import android.graphics.Rect;

/**
 * Created by ttkoch on 5/17/2015.
 */
public class HeroLazer extends GameSprite {

    public static final Rect source = new Rect(0, 30, 16, 46); //lazer bullet
    public static final Point3F scale = Point3F.identity();

    public HeroLazer(World theWorld, int posx, int posy) {
        super(theWorld);
        this.position = new Point3F(posx, posy,0F);
        this.speed = 400;
        this.baseVelocity = new Point3F(1, 0, 0); //x y z direction
        this.updateVelocity();
        this.substance = Collision.None;
        this.collidesWith = Collision.SolidAI;


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
        other.kill();
    }

    @Override
    public void cull() {
        World.cannonballOnScreen = false;
        ++world.shotsFired;
        world.timeLeft -= world.MISS_PENALTY;
    }
}
