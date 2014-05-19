/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package summative;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;
/**
 *
 * @author Administrator
 */
public class Instructions extends BasicGameState {
    
    public Image Instructions;
    public Animation move, slash, baddy;
    
    public Instructions(int State) {
    }
    
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        Instructions = new Image("res/Instructions.png");
        Image[] movepics = {new Image("res/Link/Walk (down 1).png"), new Image("res/Link/Walk (down 2).png"), new Image("res/Link/Walk (left 1).png"),new Image("res/Link/Walk (left 2).png"), new Image("res/Link/Walk (up 1).png"), new Image("res/Link/Walk (up 2).png"), new Image("res/Link/Walk (right 1).png"), new Image("res/Link/Walk (right 2).png")};
        int []duration = {200,200,200,200,200,200,200,200};
        move = new Animation(movepics, duration,true);
        Image[]slashpics = {new Image("res/Link/Walk (right 2).png"), new Image("res/Link/Slash (right 2).png")};
        int[]duration1 = {200, 200};
        slash = new Animation(slashpics, duration1, true);
        Image[] bad = {new Image("res/Enemy/Left 1.png"), new Image("res/Enemy/Left 2.png")};
        baddy = new Animation(bad, duration1, true);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        Instructions.draw(0,0);
        move.draw(450, 120);
        slash.draw(450, 200);
        baddy.draw(450, 300);
        if(slash.getFrame() == 1){
            g.drawImage(new Image("res/Link/Sword (right 2).png"), 490, 200);
        }
            
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        if (Mouse.getX() > 89 && Mouse.getX() < 276 && Mouse.getY() > 15 && Mouse.getY() < 69) {
            Instructions = new Image("res/InstructionsMenu.png");
            if(input.isMouseButtonDown(0)){
                sbg.enterState(0);
            }
        }else{
            Instructions = new Image("res/Instructions.png");
        }
    }

    public int getID() {
        return 1;
    }
}


