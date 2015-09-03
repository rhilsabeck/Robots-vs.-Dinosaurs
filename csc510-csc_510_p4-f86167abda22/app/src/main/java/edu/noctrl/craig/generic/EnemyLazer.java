package edu.noctrl.craig.generic;

import android.graphics.Rect;

/**
 * Created by ttkoch on 5/17/2015.
 */
public class EnemyLazer extends GameSprite {

    public static final Rect source = new Rect(176, 0, 187, 11);
    public static final Point3F scale = Point3F.identity();

    public EnemyLazer(World theWorld, int posx, int posy) {
        super(theWorld);
        this.position = new Point3F(posx, posy, 0F);
        this.speed = 200;
        this.baseVelocity = new Point3F(-1, 0, 0); //x y z direction //aim for hero robot
        this.updateVelocity();
        this.substance = Collision.None;
        this.collidesWith = Collision.SolidPlayer;


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
    public void collision(GameObject other) {
        other.kill();
    }

    @Override
    public void cull() {
        World.cannonballOnScreen = false;
    }
}
