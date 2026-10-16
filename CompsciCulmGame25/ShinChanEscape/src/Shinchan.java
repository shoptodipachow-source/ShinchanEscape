import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class Shinchan extends Sprite {

    private int dx;
    private int dy;
    private final int SPEED = 2;

    public Shinchan(int x, int y) {
        super(x, y);
        initShinchan();
    }

    private void initShinchan() {

        loadImage("ShinChan.png");
        getImageDimensions();
    }

    public void move() { //Shows how to move

        x += dx;
        y += dy;
    }

    public void keyPressed(KeyEvent e) { //Moves shinchan

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
        {
            dx = -SPEED;
        }
        else if (key == KeyEvent.VK_RIGHT) {
            dx = SPEED;
        }
        else if (key == KeyEvent.VK_UP) {
            dy = -SPEED;
        }
        else if (key == KeyEvent.VK_DOWN) {
            dy = SPEED;
        }
    }

    public void keyReleased(KeyEvent e) { //Stops shinchan when released

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }
        else if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
        else if (key == KeyEvent.VK_UP) {
            dy = 0;
        }
        else if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 10, 10);
    }






}
