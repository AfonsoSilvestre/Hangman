import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents that tiny confirmation that we see on our screen before exiting the program.
 * Here we set all of its features such as an icon, text, buttons etc. And we define their functionalities.
 */

public class ExitWindow extends JFrame {
    private JPanel panel;
    private JButton cancelButton;
    private JButton exitButton;

    public ExitWindow() {
        cancelButton.setFocusable(false);
        exitButton.setFocusable(false);

        setIconImage(new ImageIcon(getClass().getResource("ImageAssets/StickMan.png")).getImage());
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                dispose();
                System.exit(0);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                dispose();
            }
        });

        setSize(275, 100);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
