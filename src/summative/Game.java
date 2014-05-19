/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package summative;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame{
    public static final String gamename = "Sup Son! 2.0";
    public static final int menu = 0;
    public static final int instructions = 1;
    public static final int map1 = 2;
    public static final int house = 3;
    public static final int map2 = 4;
    public static final int map3 = 5;
    public static final int gameover = 6;
    public static final int wingame = 7;
   
    
    public Game(String gamename){
        super(gamename);
        this.addState(new Menu(menu));
        this.addState(new Map1(map1));
        this.addState(new Instructions(instructions));
        this.addState(new House(house));
        this.addState(new Map2(map2));
        this.addState(new Map3(map3));
        this.addState(new GameOver(gameover));
        this.addState(new WinGame(wingame));
    }
    
    public void initStatesList(GameContainer gc) throws SlickException{
        this.getState(menu).init(gc, this);
        this.getState(instructions).init(gc, this);
        this.getState(map1).init(gc, this);
        this.getState(house).init(gc, this);
        this.getState(map2).init(gc, this);
        this.getState(map3).init(gc, this);
        this.getState(gameover).init(gc, this);
        
        this.enterState(menu);
    }
    
   
    public static void main(String[] args) {
        AppGameContainer appgc;
        try{
            appgc = new AppGameContainer(new Game(gamename));
            appgc.setDisplayMode(640, 480, false);
            appgc.setTargetFrameRate(30);            
            appgc.start();            
        }catch(SlickException e){
            e.printStackTrace();
        }
    }
}
