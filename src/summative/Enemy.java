/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package summative;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.*;

/**
 *
 * @author Administrator
 */
public class Enemy {

    public int xen = 2 * 40;
    public int yen = 7 * 40;
    Animation enup, endown, enright, enleft, enemy, died;
    int duration[] = {125, 125};
    int duration2[] = {120, 120, 120, 120};

    public Enemy() throws SlickException {
        Image up[] = {new Image("res/Enemy/Up 1.png"), new Image("res/Enemy/Up 2.png")};
        Image down[] = {new Image("res/Enemy/Down 1.png"), new Image("res/Enemy/Down 2.png")};
        Image right[] = {new Image("res/Enemy/Right 1.png"), new Image("res/Enemy/Right 2.png")};
        Image left[] = {new Image("res/Enemy/Left 1.png"), new Image("res/Enemy/Left 2.png")};
        Image dieing[] = {new Image("res/Enemy/Death 1.png"), new Image("res/Enemy/Death 2.png"), new Image("res/Enemy/Death 3.png"), new Image("res/Enemy/Death 4.png")};
        
        died = new Animation(dieing, duration2, true);
        enup = new Animation(up, duration, true);
        endown = new Animation(down, duration, true);
        enright = new Animation(right, duration, true);
        enleft = new Animation(left, duration, true);
    }

    //get and set methods
    public void setXen(int n) {
        xen = n;
    }

    public void setYen(int n) {
        yen = n;
    }

    public int getXen() {
        return xen;
    }

    public int getYen() {
        return yen;
    }
}
