package edu.noctrl.craig.generic;

import android.graphics.Canvas;

/**
 * Created by craig_000 on 5/7/2015.
 */
public abstract class GameObject {
    public enum ObjectType{
        Box,
        Circle,
        Point,
        Triangle,
        Line
    }
    public enum DrawOrder{
        Main,
        Background,
        Foreground
    }
    public static class Collision{
        public static final int None = 0;
        public static final int Ethereal = 1;
        public static final int SolidPlayer = 2;
        public static final int SolidAI = 4;
        public static final int Solid = 6;
    }
    protected World world;
    public GameObject(World theWorld){
        world = theWorld;
    }
    public boolean offScreen = false;
    public DrawOrder drawOrder = DrawOrder.Foreground;
    public ObjectType type = ObjectType.Point;
    public int substance = Collision.None;
    public int collidesWith = Collision.None;


    public volatile Point3F position = new Point3F(0F,0F,0F);
    public volatile Point3F baseVelocity = new Point3F(0F,0F,0F);
    public volatile Point3F velocity = new Point3F(0F,0F,0F);
    public float speed;
    public void updateVelocity(){
        velocity = baseVelocity.clone();
        velocity = velocity.mult(speed);
    }
    public void update(float interval){
        position.add(velocity.clone().mult(interval));
    }
    public abstract void draw(Canvas canvas);
    public abstract void cull();
    public void message(int message){}
    public void collision(GameObject other){}
    public boolean isDead(){
        return this.offScreen;
    }
    public void kill(){
        this.offScreen = true;
    }
}
