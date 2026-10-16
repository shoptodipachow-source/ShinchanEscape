import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class ShinchanMom extends Sprite{

    public ShinchanMom(int x, int y) {
        super(x, y);
        initMom();
    }

    private void initMom() {
        loadImage("shinchanMom.png");
        getImageDimensions();
    }

    public void move() {
        y += 1;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 15, 15);
    }

}
