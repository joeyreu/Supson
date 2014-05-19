/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package summative;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.*;

public class Map1 extends BasicGameState {
    
    private TiledMap map1;
    private float x = 8 * 40f, y = 3 * 40f;
    private boolean[][] blocked;
    private boolean[][] house;
    public static final int size = 40;
    Animation supson, moveup, movedown, moveleft, moveright, slashup, slashdown, slashright, slashleft;
    Image swords, swordup, sworddown, swordleft, swordright;
    boolean animate = false;
    int[] duration = {125, 125};
    int[] duration2 = {300, 300};
    boolean sword = false;
    int swordx = 0;
    int swordy = 0;
    
    public Map1(int State) {
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        map1 = new TiledMap("res/map1.tmx");
        //Initializing Character Animation Variables
        Image[] up = {new Image("res/link/walk (up 1).png"), new Image("res/link/walk (up 2).png")};
        Image[] down = {new Image("res/link/walk (down 1).png"), new Image("res/link/walk (down 2).png")};
        Image[] left = {new Image("res/link/walk (left 1).png"), new Image("res/link/walk (left 2).png")};
        Image[] right = {new Image("res/link/walk (right 1).png"), new Image("res/link/walk (right 2).png")};
        Image[] sup = {new Image("res/link/slash (up 2).png"), new Image("res/link/slash (up 2).png")};
        Image[] sdown = {new Image("res/link/slash (down 2).png"), new Image("res/link/slash (down 2).png")};
        Image[] sleft = {new Image("res/link/slash (left 2).png"), new Image("res/link/slash (left 2).png")};
        Image[] sright = {new Image("res/link/slash (right 2).png"), new Image("res/link/slash (right 2).png")};
        moveup = new Animation(up, duration, true);
        slashup = new Animation(sup, duration2, true);
        movedown = new Animation(down, duration, true);
        slashdown = new Animation(sdown, duration2, true);
        moveleft = new Animation(left, duration, true);
        slashleft = new Animation(sleft, duration2, true);
        moveright = new Animation(right, duration, true);
        slashright = new Animation(sright, duration2, true);

        supson = movedown;

        swordup = new Image("res/link/Sword (up 2).png");
        sworddown = new Image("res/link/Sword (down 2).png");
        swordleft = new Image("res/link/Sword (left 2).png");
        swordright = new Image("res/link/Sword (right 2).png");
        swords = swordup;
        //2D array that declares which coordinates are blocked (where you can't pass through)
        blocked = new boolean[map1.getWidth()][map1.getHeight()];
        //this code section sorts out which tiles can be passed through
        for (int xAxis = 0; xAxis < map1.getWidth(); xAxis++) {
            for (int yAxis = 0; yAxis < map1.getHeight(); yAxis++) {
                int tileID = map1.getTileId(xAxis, yAxis, 0);
                String value = map1.getTileProperty(tileID, "Blocked", "false");
                if ("true".equals(value)) {
                    blocked[xAxis][yAxis] = true;
                }
            }
        }

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        map1.render(0, 0);
        supson.draw((int) x, (int) y);
        if (sword == true) {
            swords.draw(swordx, swordy);
        }

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();

        //stopping the animation if no moving button is pressed
        if (!input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_RIGHT)) {
            supson.stop();
            sword = false;
        }
        //main character control (movemen and sword activation)
        if (input.isKeyDown(Input.KEY_SPACE)) {
            sword = true;
            supson.start();
            if (supson == movedown) {
                supson = slashdown;
                swords = sworddown;
                swordx = (int) x;
                swordy = (int) y + 40;
            } else if (supson == moveup) {
                supson = slashup;
                swords = swordup;
                swordx = (int) x;
                swordy = (int) y - 40;
            } else if (supson == moveleft) {
                supson = slashleft;
                swords = swordleft;
                swordx = (int) x - 40;
                swordy = (int) y;
            } else if (supson == moveright) {
                supson = slashright;
                swords = swordright;
                swordx = (int) x + 40;
                swordy = (int) y;
            }

        } else {
            sword = false;
            //movement statements
            if (input.isKeyDown(Input.KEY_UP)) {//Moving Up
                supson = moveup;
                supson.start();
                if (x > 250 && x < 297 && y == 120) {
                } else if (x > 331 && x < 372 && y == 120) {
                } else if (!isBlocked(x, y - delta * 0.1f)) {
                    y -= 3;
                }
                if (x > 318 && x < 330 && y < 95) {
                    sbg.enterState(3);
                }


            } else if (input.isKeyDown(Input.KEY_DOWN)) {//Moving Down
                supson = movedown;
                supson.start();

                if (!isBlocked(x, y + size + delta * 0.1f)) {
                    y += 3;
                }
            } else if (input.isKeyDown(Input.KEY_LEFT)) {//Moving Left
                supson = moveleft;
                supson.start();
                if (y < 203 && y > 164 && x == 80) {
                } else if(y > 245 && y< 290 && x ==41){            
                }
                else if (!isBlocked(x - delta * 0.1f, y)) {
                    x -= 3;
                }
                if(x <= 11){
                    sbg.enterState(4);
                }
            } else if (input.isKeyDown(Input.KEY_RIGHT)) {//Moving Right
                supson = moveright;
                supson.start();
                if (!isBlocked(x + size + delta * 0.1f, y)) {
                    x += 3;
                }
            } else if (supson == slashup) {
                supson = moveup;
            } else if (supson == slashdown) {
                supson = movedown;
            } else if (supson == slashright) {
                supson = moveright;
            } else if (supson == slashleft) {
                supson = moveleft;
            }
        }
    }

    public int getID() {
        return 2;
    }

    private boolean isBlocked(float x, float y) {
        int xBlock = (int) x / size;
        int yBlock = (int) y / size;
        return blocked[xBlock][yBlock];
    }
}