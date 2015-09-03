package edu.noctrl.craig.generic;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * Created by craig_000 on 5/9/2015.
 */
public abstract class GameSprite extends GameObject {
    public static final Paint EIGHT_BIT_GOODNESS;
    static {
        EIGHT_BIT_GOODNESS = new Paint();
        EIGHT_BIT_GOODNESS.setAntiAlias(false);
        EIGHT_BIT_GOODNESS.setFilterBitmap(false);
    }
    public static Bitmap SPRITE_SOURCE;

    public abstract Rect getSource();
    public abstract Point3F getScale();

    public RectF bounds;

    public GameSprite(World theWorld){super(theWorld);}
    public float getWidth(){
        Rect src = getSource();
        Point3F scale = getScale();
        return ((src.width() * scale.X) * scale.Z * this.world.worldScale.X);
    }
    public float getHeight(){
        Rect src = getSource();
        Point3F scale = getScale();
        return ((src.height() * scale.Y) * scale.Z * this.world.worldScale.Y);
    }



    @Override
    public void draw(Canvas canvas){
        if(canvas == null)
            return;
        Rect src = getSource();
        Point3F scale = getScale();
        float width = ((src.width() * scale.X) * scale.Z * this.world.worldScale.X)/2F;
        float height = ((src.height() * scale.Y) * scale.Z * this.world.worldScale.Y)/2F;
        //Rect(int left, int top, int right, int bottom)
        RectF dest = new RectF(position.X - width,
                position.Y - height,
                position.X + width,
                position.Y + height);
        bounds = dest;
        if(dest.right<0 || dest.left>canvas.getWidth()
                || dest.bottom <0 || dest.top > canvas.getHeight())
            offScreen = true;
        else
            canvas.drawBitmap(SPRITE_SOURCE, src, dest, EIGHT_BIT_GOODNESS);
    }
}
