package summative;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;

public class Menu extends BasicGameState {

    public Image menu;
    Animation supson;
    Music hover;
    //time array that defines how long each frame of animation will be visible for
    int[] duration = {100, 100};
    int x = 50;
    int y = 200;

    public Menu(int State) {
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        menu = new Image("res/Menu.png");
        Image[] right = {new Image("res/link/walk (right 1).png"), new Image("res/link/walk (right 2).png")};
        supson = new Animation(right, duration, true);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        menu.draw(0, 0);
        supson.draw(x, y);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

        Input input = gc.getInput();
        if (Mouse.getX() > 116 && Mouse.getX() < 507 && Mouse.getY() > 237 && Mouse.getY() < 330) {
            x = 50;
            y = 200;
            menu = new Image("res/MenuPlay.png");
            if (input.isMouseButtonDown(0)) {
                sbg.enterState(3);
            }
        } else if (Mouse.getX() > 152 && Mouse.getX() < 456 && Mouse.getY() > 83 && Mouse.getY() < 126) {
            x = 90;
            y = 360;
            menu = new Image("res/MenuQuit.png");
            if (input.isMouseButtonDown(0)) {
                System.exit(0);
            }
        } else if (Mouse.getX() > 140 && Mouse.getX() < 478 && Mouse.getY() > 157 && Mouse.getY() < 220) {
            x = 70;
            y = 285;
            menu = new Image("res/MenuInstructions.png");
            if (input.isMouseButtonDown(0)) {
                sbg.enterState(1);
            }
        } else {
            menu = new Image("res/Menu.png");
        }
    }

    public int getID() {
        return 0;
    }
}
