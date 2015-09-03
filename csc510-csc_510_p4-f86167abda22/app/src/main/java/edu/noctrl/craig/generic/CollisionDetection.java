package edu.noctrl.craig.generic;

import java.util.ArrayList;

/**
 * Created by craig_000 on 5/7/2015.
 */
public class CollisionDetection {

    public static void checkCollisions(ArrayList<GameObject> objects){
        int osize = objects.size();
        GameObject go1,go2;
        for(int i = 0;i<osize;i++){
            go1 = objects.get(i);
            if(!go1.isDead()) {
                for (int j = 0; j < osize; j++) {
                    go2 = objects.get(j);
                    if (i != j
                            && !go2.isDead()
                            && (go1.collidesWith & go2.substance) > 0) {
                        if (collision(go1, go2)) {
                            go1.collision(go2);
                            go2.collision(go1);
                            if(go1.isDead()) break;
                        }
                    }
                }
            }
        }
    }
    public static boolean collision(GameObject test, GameObject hit){
        if(hit instanceof  GameSprite){
            return ((GameSprite)hit).bounds.contains(test.position.X, test.position.Y);
        }
        return false;
    }
}
