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
import org.lwjgl.input.Mouse;

public class GameOver extends BasicGameState {

    public GameOver(int State) {
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {    
        
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawString("You have been slain. Game Over", 100, 200);
        g.drawString("Thanks for Playing. Press the Escape Button to exit", 100, 250);
        g.drawImage(new Image("res/Boss/Boss 2.png"), 200, 50);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        if(input.isKeyPressed(input.KEY_ESCAPE)){
            System.exit(0);
        }
    }

    public int getID() {
        return 6;
    }
}
