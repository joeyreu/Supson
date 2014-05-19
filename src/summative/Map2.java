/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package summative;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.*;

public class Map2 extends BasicGameState {

    private TiledMap map2;
    private float x = 15 * 40f, y = 6 * 40f;
    private boolean[][] blocked;
    private boolean[][] house;
    public static final int size = 40;
    //MAIN CHARACTER VARIABLE INITIALIZATION
    Animation supson, moveup, movedown, moveleft, moveright, slashup, slashdown, slashright, slashleft;
    Image swords, swordup, sworddown, swordleft, swordright;
    boolean animate = false;
    int[] duration = {125, 125};
    int[] duration2 = {300, 300};
    boolean sword = false;
    int swordx = 0;
    int swordy = 0;
    Animation enup, endown, enleft, enright, enemy, enemy2, death;
    int enx = 40 * 3, eny = 40 * 6;
    int enx2 = 40 * 14, eny2 = 40 * 1;
    boolean endied, en2died, erase1, erase2;

    public Map2(int State) {
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        map2 = new TiledMap("res/map2.tmx");
        //setting values to character animation variables
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
        supson = moveleft;
        swordup = new Image("res/link/Sword (up 2).png");
        sworddown = new Image("res/link/Sword (down 2).png");
        swordleft = new Image("res/link/Sword (left 2).png");
        swordright = new Image("res/link/Sword (right 2).png");
        swords = swordup;

        erase1 = false;
        erase2 = false;
        endied = false;
        en2died = false;
        //create enemy object to import values
        Enemy enem = new Enemy();
        enup = enem.enup;
        endown = enem.endown;
        enright = enem.enright;
        enleft = enem.enleft;
        death = enem.died;
        enemy = enright;
        enemy2 = enleft;
        //2D array that declares which coordinates are blocked (where you can't pass through)
        blocked = new boolean[map2.getWidth()][map2.getHeight()];
        //this code section sorts out which tiles can be passed through
        for (int xAxis = 0; xAxis < map2.getWidth(); xAxis++) {
            for (int yAxis = 0; yAxis < map2.getHeight(); yAxis++) {
                int tileID = map2.getTileId(xAxis, yAxis, 0);
                String value = map2.getTileProperty(tileID, "Blocked", "false");
                if ("true".equals(value)) {
                    blocked[xAxis][yAxis] = true;
                }
            }
        }

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        map2.render(0, 0);
        supson.draw((int) x, (int) y);
        if (!erase1) {
            enemy.draw(enx, eny);
        }
        if (!erase2) {
            enemy2.draw(enx2, eny2);
        }
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
                if (y <= 9) {
                    sbg.enterState(5);
                } else if (!isBlocked(x, y - delta * 0.1f)) {
                    y -= delta * 0.1f;
                }



            } else if (input.isKeyDown(Input.KEY_DOWN)) {//Moving Down
                supson = movedown;
                supson.start();

                if (!isBlocked(x, y + size + delta * 0.1f)) {
                    y += delta * 0.1f;
                }
            } else if (input.isKeyDown(Input.KEY_LEFT)) {//Moving Left
                supson = moveleft;
                supson.start();
                if (!isBlocked(x - delta * 0.1f, y)) {
                    x -= delta * 0.1f;
                }
            } else if (input.isKeyDown(Input.KEY_RIGHT)) {//Moving Right
                supson = moveright;
                supson.start();
                if (x >= 595) {
                    sbg.enterState(2);
                } else if (!isBlocked(x + size + delta * 0.1f, y)) {
                    x += delta * 0.1f;
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

        if (sword == true) {
            if (eny > swordy - 30 && eny < swordy + 30) {
                if (enx > swordx - 30 && enx < swordx + 30) {
                    enemy = death;
                    if (death.getFrame() == 3) {
                        death.restart();
                    }
                    endied = true;
                }
            }
        }

        //enemy movement
        if (endied == false) {

            if (enemy == enright && enx < 553) {
                enx += 5;
            } else if (enx >= 553) {
                enemy = enleft;
                enx -= 5;
            } else if (enemy == enleft && enx > 44) {
                enx -= 5;
            } else if (enx <= 44) {
                enemy = enright;
                enx += 5;
            }

            //collision with enemy 1
            if (y > eny - 30 && y < eny + 30) {
                if (x > enx - 30 && x < enx + 30) {
                    sbg.enterState(6);
                }
            }

        }
        if (endied == true) {
            if (enemy.getFrameCount() - 1 == enemy.getFrame()) {
                enemy.stop();
                erase1 = true;
            }
        }

        if (en2died == true) {
            if (enemy2.getFrameCount() - 1 == enemy2.getFrame()) {
                enemy2.stop();
                erase2 = true;
            }
        }

        if (sword == true) {
            if (eny2 > swordy - 30 && eny2 < swordy + 30) {
                if (enx2 > swordx - 30 && enx2 < swordx + 30) {
                    enemy2 = death;
                    if (death.getFrame() == 3) {
                        death.restart();
                    }
                    en2died = true;
                }
            }
        }
        //enemy2 movement
        if (en2died == false) {
            if (enemy2 == enright && enx2 < 558) {
                enx2 += 5;
            } else if (enx2 >= 558) {
                enemy2 = enleft;
                enx2 -= 5;
            } else if (enemy2 == enleft && enx2 > 402) {
                enx2 -= 5;
            } else if (enx2 < 402) {
                enemy2 = enright;
                enx2 += 5;
            }
            //collision with enemy 2
            if (y > eny2 - 30 && y < eny2 + 30) {
                if (x > enx2 - 30 && x < enx2 + 30) {
                    sbg.enterState(6);
                }
            }
        }

        //sword collision with enemy

    }

    public int getID() {
        return 4;
    }

    private boolean isBlocked(float x, float y) {
        int xBlock = (int) x / size;
        int yBlock = (int) y / size;
        return blocked[xBlock][yBlock];
    }
}