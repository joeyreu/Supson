/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package summative;

/**
 *
 * @author Administrator
 */
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.*;

public class Map3 extends BasicGameState {

    private TiledMap map3;
    private float x = 10 * 40f, y = 10 * 40f;
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
    Animation boss, bleft, bright;
    int bossx = 40 * 4, bossy = 0;
    Animation enup, endown, enleft, enright, enemy, enemy2,enemy3, enemy4, death;
    int enx = 40 * 5, eny = 40 * 6;
    int enx2 = 40 * 14, eny2 = 40 * 3;
    int enx3 = 40* 9, eny3 = 40*7;
    int enx4 = 40*4, eny4 = 40*10;
    boolean endied, en2died, en3died, en4died, erase1, erase2, erase3, erase4;

    public Map3(int State) {
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        map3 = new TiledMap("res/map3.tmx");
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

        supson = moveup;

        swordup = new Image("res/link/Sword (up 2).png");
        sworddown = new Image("res/link/Sword (down 2).png");
        swordleft = new Image("res/link/Sword (left 2).png");
        swordright = new Image("res/link/Sword (right 2).png");
        swords = swordup;

        Image[] bossleft = {new Image("res/Boss/Boss 1.png"), new Image("res/Boss/Boss 2.png")};
        Image[] bossright = {new Image("res/Boss/Right 1.png"), new Image("res/Boss/Right 2.png")};

        bleft = new Animation(bossleft, duration, true);
        bright = new Animation(bossright, duration, true);
        boss = bright;

        erase1 = false;
        erase2 = false;
        erase3 = false;
        erase4 = false;
        endied = false;
        en2died = false;
        en3died = false;
        en4died = false;
        //create enemy object to import values
        Enemy enem = new Enemy();
        enup = enem.enup;
        endown = enem.endown;
        enright = enem.enright;
        enleft = enem.enleft;
        death = enem.died;
        enemy = enright;
        enemy2 = enleft;
        enemy3 = endown;
        enemy4 = enright;

        //2D array that declares which coordinates are blocked (where you can't pass through)
        blocked = new boolean[map3.getWidth()][map3.getHeight()];
        //this code section sorts out which tiles can be passed through
        for (int xAxis = 0; xAxis < map3.getWidth(); xAxis++) {
            for (int yAxis = 0; yAxis < map3.getHeight(); yAxis++) {
                int tileID = map3.getTileId(xAxis, yAxis, 0);
                String value = map3.getTileProperty(tileID, "Blocked", "false");
                if ("true".equals(value)) {
                    blocked[xAxis][yAxis] = true;
                }
            }
        }

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        map3.render(0, 0);
        supson.draw((int) x, (int) y);
        boss.draw(bossx, bossy);
        if (sword == true) {
            swords.draw(swordx, swordy);
        }
        if (!erase1) {
            enemy.draw(enx, eny);
        }
        if (!erase2) {
            enemy2.draw(enx2, eny2);
        }
        if (!erase3) {
            enemy3.draw(enx3, eny3);
        }
        if (!erase4) {
            enemy4.draw(enx4, eny4);
        }

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        //CHARACTER CONTROL
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
                if (!isBlocked(x, y - delta * 0.1f)) {
                    y -= delta * 0.1f;
                }


            } else if (input.isKeyDown(Input.KEY_DOWN)) {//Moving Down
                supson = movedown;
                supson.start();

                if (y > 416) {
                    sbg.enterState(4);
                } else if (!isBlocked(x, y + size + delta * 0.1f)) {
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
                if (!isBlocked(x + size + delta * 0.1f, y)) {
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
        if (boss == bright && bossx < 524) {
            bossx += 3;
        } else if (bossx > 160) {
            boss = bleft;
            bossx -= 3;
        } else if (boss == bleft && bossx > 160) {
            bossx -= 3;
        } else if (bossx < 559) {
            boss = bright;
            bossx += 3;
        }
        if (y > bossy - 30 && y < bossy + 120) {
            if (x > bossx - 30 && x < bossx + 80) {
                sbg.enterState(6);
            }
        }
        if (sword == true) {
            if (bossy > swordy - 100 && bossy < swordy + 100) {
                if (bossx > swordx - 100 && bossx < swordx + 100) {
                    sbg.enterState(7);
                }
            }
        }

       
        
        //ENEMY 1 CODING
        //CHECKS IF SWORD IS HITTING ENEMY
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
        if (endied == true) {
            if (enemy.getFrameCount() - 1 == enemy.getFrame()) {
                enemy.stop();
                erase1 = true;
            }
        }
        if (endied == false) {

            if (enemy == enright && enx < 559) {
                enx += 5;
            } else if (enx >= 559) {
                enemy = enleft;
                enx -= 5;
            } else if (enemy == enleft && enx > 236) {
                enx -= 5;
            } else if (enx <= 236) {
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
        
        //ENEMY 2 CODING
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
        if (en2died == true) {
            if (enemy2.getFrameCount() - 1 == enemy2.getFrame()) {
                enemy2.stop();
                erase2 = true;
            }
        }
        if (en2died == false) {
            if (enemy2 == enright && enx2 < 558) {
                enx2 += 5;
            } else if (enx2 >= 558) {
                enemy2 = enleft;
                enx2 -= 5;
            } else if (enemy2 == enleft && enx2 > 276) {
                enx2 -= 5;
            } else if (enx2 < 276) {
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
        //ENEMY 3 CODING
        if (sword == true) {
            if (eny3 > swordy - 30 && eny3 < swordy + 30) {
                if (enx3 > swordx - 30 && enx3 < swordx + 30) {
                    enemy3 = death;
                    if (death.getFrame() == 3) {
                        death.restart();
                    }
                    en3died = true;
                }
            }
        }
        if (en3died == true) {
            if (enemy3.getFrameCount() - 1 == enemy3.getFrame()) {
                enemy3.stop();
                erase3 = true;
            }
        }
        if (en3died == false) {

            if (enemy3 == endown && eny3 < 397) {
                eny3 += 5;
            } else if (eny3 >= 397) {
                enemy3 = enup;
                eny3 -= 5;
            } else if (enemy3 == enup && eny3 > 237) {
                eny3 -= 5;
            } else if (eny3 <= 237) {
                enemy3 = endown;
                eny3 += 5;
            }

            //collision with enemy 3
            if (y > eny3 - 30 && y < eny3 + 30) {
                if (x > enx3 - 30 && x < enx3 + 30) {
                    sbg.enterState(6);
                }
            }

        }
        //ENEMY 4 CODING
        if (sword == true) {
            if (eny4 > swordy - 30 && eny4 < swordy + 30) {
                if (enx4 > swordx - 30 && enx4 < swordx + 30) {
                    enemy4 = death;
                    if (death.getFrame() == 3) {
                        death.restart();
                    }
                    en4died = true;
                }
            }
        }
        if (en4died == true) {
            if (enemy4.getFrameCount() - 1 == enemy4.getFrame()) {
                enemy4.stop();
                erase4 = true;
            }
        }
        if (en4died == false) {
            if (enemy4 == enright && enx4 < 479) {
                enx4 += 5;
            } else if (enx4 >= 479) {
                enemy4 = enleft;
                enx4 -= 5;
            } else if (enemy4 == enleft && enx4 > 276) {
                enx4 -= 5;
            } else if (enx4 < 276) {
                enemy4 = enright;
                enx4 += 5;
            }
            //collision with enemy 2
            if (y > eny4 - 30 && y < eny4 + 30) {
                if (x > enx4 - 30 && x < enx4 + 30) {
                    sbg.enterState(6);
                }
            }
        }
    }

    public int getID() {
        return 5;
    }

    private boolean isBlocked(float x, float y) {
        int xBlock = (int) x / size;
        int yBlock = (int) y / size;
        return blocked[xBlock][yBlock];
    }
}
