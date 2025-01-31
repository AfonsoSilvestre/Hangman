import javax.swing.*;
import java.awt.*;

/**
 * This class is simply the frame in which we will be displaying all of our content.
 * More commonly known as the "window" of the app. Upon each interaction the window will
 * change its components and load new subsequent panels ( new "menus" ).
 * It's good practice to use panels rather than new frames to display content.
 * ------------
 * Here we initialize our frame and set its name, icon, size, characteristics, etc.
 * After that we add our panels to aggregate panel, the "container". That panel will store
 * other panels, and due to the layout we use (card layour) we can simply just flip panels easily.
 */
public class Window extends JFrame {
    private CardLayout layout = new CardLayout();
    private JPanel container = new JPanel(layout);
    ImageIcon hangmanIcon = new ImageIcon(getClass().getResource("ImageAssets/StickMan.png"));

    public Window() {
        setTitle("Hangman");
        setIconImage(hangmanIcon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        container.add(new MainMenu(layout, container).getPanel(), "Main");
        container.add(new GameWindow(layout, container).getPanel(), "Game");

        layout.show(container, "Main");
        add(container);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
