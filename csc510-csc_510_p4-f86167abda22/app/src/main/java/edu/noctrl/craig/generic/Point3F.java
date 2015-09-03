package edu.noctrl.craig.generic;

/**
 * Created by craig_000 on 5/9/2015.
 */
public class Point3F implements Comparable<Point3F>{
    public static Point3F identity(){
        return new Point3F(1F,1F,1F);
    }
    public float X,Y,Z;
    public Point3F(){
        X = Y = Z = 0F;
    }
    public Point3F(float x, float y, float z){
        set(x,y,z);
    }
    public void set(float x, float y, float z){
        X = x;
        Y = y;
        Z = z;
    }
    public float distance(Point3F target){
        return (float)Math.sqrt(squaredDist(target));
    }
    public float distance(float x, float y, float z){
        return (float)Math.sqrt(squaredDist(x,y,z));
    }
    public float squaredDist(Point3F target){
        return squaredDist(target.X, target.Y, target.Z);
    }
    public float squaredDist(float x, float y, float z){
        float dx = x - this.X;
        float dy = y - this.Y;
        float dz = z - this.Z;
        return dx*dx + dy*dy + dz*dz;
    }
    public Point3F set(Point3F value){
        this.X = value.X;
        this.Y = value.Y;
        this.Z = value.Z;
        return this;
    }
    public Point3F add(float value){
        this.X += value;
        this.Y += value;
        this.Z += value;
        return this;
    }
    public Point3F mult(float value){
        this.X *= value;
        this.Y *= value;
        this.Z *= value;
        return this;
    }
    public Point3F subtract(float value){
        this.X -= value;
        this.Y -= value;
        this.Z -= value;
        return this;
    }
    public Point3F divide(float value){
        this.X /= value;
        this.Y /= value;
        this.Z /= value;
        return this;
    }

    public Point3F add(Point3F value){
        this.X += value.X;
        this.Y += value.Y;
        this.Z += value.Z;
        return this;
    }
    public Point3F mult(Point3F value){
        this.X *= value.X;
        this.Y *= value.Y;
        this.Z *= value.Z;
        return this;
    }
    public Point3F subtract(Point3F value){
        this.X -= value.X;
        this.Y -= value.Y;
        this.Z -= value.Z;
        return this;
    }
    public Point3F divide(Point3F value){
        this.X /= value.X;
        this.Y /= value.Y;
        this.Z /= value.Z;
        return this;
    }
    public Point3F difference(Point3F value){
        return new Point3F(this.X - value.X,
                this.Y - value.Y,
                this.Z - value.Z);
    }
    public Point3F normalize(){
        float magnitude = new Point3F().distance(this);
        return magnitude == 0 ? new Point3F() : new Point3F(
            this.X / magnitude,
            this.Y / magnitude,
            this.Z / magnitude
        );
    }
    public Point3F clone(){
        return new Point3F(this.X, this.Y, this.Z);
    }
    @Override
    public String toString(){
        return "(" + X + ", " + Y + ", " + Z + ")";
    }
    @Override
    public boolean equals(Object obj){
        if(obj instanceof  Point3F) {
            Point3F cmp = (Point3F)obj;
            return this.X == cmp.X && this.Y == cmp.Y && this.Z == cmp.Z;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        String hc = this.Z + "" + this.X + "" + this.Y;
        hc = hc.replaceAll(".","");
        return (int)Long.parseLong(hc);
    }

    @Override
    public int compareTo(Point3F point3F) {
        if(this.X < point3F.X) return -1;
        else if(this.X > point3F.X) return 1;
        else if(this.Y < point3F.Y) return -1;
        else if(this.Y > point3F.Y) return 1;
        else if(this.Z < point3F.Z) return -1;
        else if(this.Z > point3F.Z) return 1;
        else return 0;
    }
}
