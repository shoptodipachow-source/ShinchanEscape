import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GameBackground extends JPanel {

    private Color backgroundColor;

    public GameBackground(Color color) {
        this.backgroundColor = color;
        setBackground(backgroundColor);
    }

    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
        setBackground(backgroundColor);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
