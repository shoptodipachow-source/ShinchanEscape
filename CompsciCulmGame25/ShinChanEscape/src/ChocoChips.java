import java.awt.*;

public class ChocoChips extends Sprite{

    private int x, y;

    public ChocoChips(int x, int y) {
        super(x, y);
        initChocoChips();
    }

    private void initChocoChips() {

        loadImage("chocoChips.png");
        getImageDimensions();
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 8, 8);
    }



}
