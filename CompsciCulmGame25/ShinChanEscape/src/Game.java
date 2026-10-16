import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Game extends JFrame {

    public Game()
    {
        initUI();
    }
    private void initUI() {

        add(new Board() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        setResizable(false);
        pack();

        setTitle("Shinchan Escape");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



    public static void main(String[] args) {

         GameBackground background = new GameBackground(Color.BLUE);
         background.setBackgroundColor(new Color(5, 100, 5));

        EventQueue.invokeLater(() -> {
            Game ex = new Game();
            ex.setVisible(true);
        });


    }


}
