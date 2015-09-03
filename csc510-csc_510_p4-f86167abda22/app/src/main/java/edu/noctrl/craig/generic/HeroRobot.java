package edu.noctrl.craig.generic;

import android.graphics.Rect;

/**
 * Created by Ryan on 5/16/2015.
 */
public class HeroRobot extends GameSprite {

    protected static final Rect source = new Rect(73, 0, 93, 30);
    protected static final Point3F scale = Point3F.identity();
    public static Point3F staticPosition;
    private static World world = null;

    public HeroRobot(World theWorld, int posx, int posy) {
        super(theWorld);
        world = theWorld;
        staticPosition = new Point3F(posx, posy, 0F);
        this.position = staticPosition;
        this.baseVelocity = new Point3F(1F, 0F, 0F);
        this.updateVelocity();
        this.substance = Collision.SolidPlayer;
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
    public void collision(GameObject other) {

        other.kill();
        World.score = ((int) World.totalElapsedTime * 50) + (World.kills * 100) - (World.remaining * 100);
        world.listener.onGameOver(true);
    }
    @Override
    public void updateVelocity(){
        velocity = baseVelocity.clone();
        velocity = velocity.mult(speed);
    }
    @Override
    public void update(float interval){
        position.add(velocity.clone().mult(interval));
        staticPosition = position;
    }


    @Override
    public void cull() {

    }
}
