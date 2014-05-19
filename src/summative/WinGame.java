package summative;

/**
 *
 * @author Administrator
 */
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;

public class WinGame extends BasicGameState {

    //Variable for display
    String words;
    //Animation variables
    Animation explode;
    int duration[] = {120, 120, 120, 120};
    //boolean for animation
    boolean n = false;

    public WinGame(int State) {
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        words = "You have slain the dragon! Press escape to continue";
        Image exploding[] = {new Image("res/Enemy/Death 1.png"), new Image("res/Enemy/Death 2.png"), new Image("res/Enemy/Death 3.png"), new Image("res/Enemy/Death 4.png")};
        explode = new Animation(exploding, duration, true);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawString(words, 50, 30);
        if (!n) {
            g.drawImage(new Image("res/Boss/Boss 1.png"), 300, 200);
        } else {
            explode.draw(300, 200);
            explode.draw(340, 200);
            explode.draw(300, 240);
            explode.draw(340, 240);
            explode.draw(300, 280);
            explode.draw(340, 280);
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        if (input.isKeyPressed(input.KEY_ESCAPE)) {
            if (words == "You have slain the dragon! Press escape to continue") {
                words = "YOU WIN! Thank you for playing. Press escape to exit.";
                n = true;
            } else {
                System.exit(0);//exits the program user desires
            }
        }



    }

    public int getID() {
        return 7;
    }
}
