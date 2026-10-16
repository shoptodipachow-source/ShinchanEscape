import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Board extends JPanel implements ActionListener {
    private Timer timer;
    private Shinchan shinchan;
    private ShinchanMom[] moms;
    private ArrayList<ChocoChips> chocoChipsList;
    private boolean inGame;
    private int score;

    private final int B_WIDTH = 400;
    private final int B_HEIGHT = 300;
    private final int DELAY = 15;
    private final int MAX_MOMS = 5;

    public Board() {
        initBoard();
        initGame();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        inGame = true;

        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void initGame() {
        shinchan = new Shinchan(200, 200);

        moms = new ShinchanMom[MAX_MOMS];
        for (int i = 0; i < MAX_MOMS; i++) {
            moms[i] = new ShinchanMom(50 * i, 50);
        }

        chocoChipsList = new ArrayList<>();
        makeChocoChips();
        score = 0;
    }

    private void makeChocoChips() {
        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            chocoChipsList.add(new ChocoChips(rand.nextInt(B_WIDTH), rand.nextInt(B_HEIGHT))); 
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame) {
            drawObjects(g);
        } else {
            drawGameOver(g);
        }
    }

    private void drawObjects(Graphics g) {
        if (shinchan.isVisible()) {
            g.drawImage(shinchan.getImage(), shinchan.getX(), shinchan.getY(), this); //'pacman'
        }

        for (ShinchanMom mom : moms) { //'ghost'
            if (mom.isVisible()) {
                g.drawImage(mom.getImage(), mom.getX(), mom.getY(), this);
            }
        }

        for (ChocoChips chip : chocoChipsList) { // 'pacman food'
            g.drawImage(chip.getImage(), chip.getX(), chip.getY(), this);
        }

        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 10, 20);
    }

    private void drawGameOver(Graphics g) {
        String msg = "Game Over!!!!! Score: " + score;
        Font font = new Font("Helvetica", Font.BOLD, 14);
        g.setFont(font);
        g.setColor(Color.WHITE);
        FontMetrics fm = g.getFontMetrics(font);
        int x = (B_WIDTH - fm.stringWidth(msg)) / 2;
        int y = B_HEIGHT / 2;
        g.drawString(msg, x, y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            updateGame();
        }
        repaint();
    }

    private void updateGame() {
        shinchan.move();
        for (ShinchanMom mom : moms) {
            mom.move();
        }
        checkCollisions();
    }

    //Check if anything intersects
    private void checkCollisions() {
        Rectangle rShinchan = shinchan.getBounds();

        for (ShinchanMom mom : moms) {
            if (rShinchan.intersects(mom.getBounds())) {
                inGame = false;
            }
        }

        Iterator<ChocoChips> iterator = chocoChipsList.iterator();
        while (iterator.hasNext()) {
            ChocoChips chip = iterator.next();
            if (rShinchan.intersects(chip.getBounds())) {
                iterator.remove();
                score += 10;
            }
        }
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            shinchan.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            shinchan.keyReleased(e);
        }
    }
}