import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Lastly this class is the menu we see as soon as we open the game.
 * Yet again we created this class using forms and all we are doing is placing components on a main panel
 * and then extracting said panel to use it in our card layout.
 */
public class MainMenu {
    private JPanel mainPanel;

    private JPanel emptyTopPanel;
    private JLabel authorMark;
    private JLabel githubMark;
    private JLabel linkedInMark;

    private JLabel gameTitle;

    private JPanel buttonPanel;
    private JButton playButton;
    private JButton exitButton;

    private JPanel bottomPanel;

    public MainMenu(CardLayout layout, JPanel container) {
        loadMainPanel();
        loadGameTitle();
        loadEmptyTopPanel();
        loadAuthorMark();
        loadGithubMark();
        loadLinkedInMark();
        loadButtons(layout, container);
        loadBottomPanel();
    }

    /**
     * This method allows for the main panel of our main menu to be called whenever it's needed from wherever it's needed.
     * @return the main panel where all our components are
     */
    public JPanel getPanel() {
        return this.mainPanel;
    }

    /**
     * Lets properly customize our panel by setting a color and making it opaque so that it blends with the hangman logo.
     */
    private void loadMainPanel() {
        this.mainPanel.setOpaque(true);
        this.mainPanel.setBackground(new Color(255, 254, 254, 255));
    }

    /**
     * This method loads the game title that we see in the middle of the screen. The hangman logo, "Hangman" title, and both of their alignments
     * accross the screen.
     */
    private void loadGameTitle() {
        ImageIcon hangmanIcon = new ImageIcon(getClass().getResource("ImageAssets/HangmanGameIcon.png"));
        gameTitle.setFont(new Font(Font.DIALOG, Font.PLAIN, 50));
        gameTitle.setOpaque(false);
        gameTitle.setIcon(new ImageIcon(hangmanIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH))); // good practice to load images with custom resolutions to scale them properly

        //text on top of the picture, centered within the frame
        gameTitle.setVerticalTextPosition(JLabel.TOP);
        gameTitle.setHorizontalTextPosition(JLabel.CENTER);

        // pic + text in the top center of the box
        gameTitle.setHorizontalAlignment(JLabel.CENTER);
        gameTitle.setVerticalAlignment(JLabel.TOP);
        gameTitle.setPreferredSize(new Dimension(750, 400));
    }

    /**
     * This method loads an empty panel above the game title, between the author mark and github/linkedIn marks to space everything out/
     */
    private void loadEmptyTopPanel() {
        emptyTopPanel.setOpaque(false);
        emptyTopPanel.setPreferredSize(new Dimension(750, 27));
    }

    /**
     * Loads the mark on the top right that gives credit to this projects creator. Me.
     */
    private void loadAuthorMark() {
        authorMark.setText("Made by Afonso Silvestre 2024");
        authorMark.setFont(new Font(Font.DIALOG, Font.PLAIN, 11));
        authorMark.setOpaque(false);
        authorMark.setHorizontalAlignment(JLabel.CENTER);
        authorMark.setVerticalAlignment(JLabel.CENTER);
        authorMark.setPreferredSize(new Dimension(170, 27));
    }

    /**
     * Loads this project's author Github link onto the top right of the frame. Mine.
     */
    private void loadGithubMark() {
        ImageIcon GitHubIcon = new ImageIcon(getClass().getResource("ImageAssets/GitHubIcon.png"));

        githubMark.setText("AfonsoSilvestre");
        githubMark.setFont(new Font(Font.DIALOG, Font.PLAIN, 11));
        githubMark.setOpaque(false);
        githubMark.setIcon(new ImageIcon(GitHubIcon.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH)));
        githubMark.setHorizontalAlignment(JLabel.CENTER);
        githubMark.setVerticalAlignment(JLabel.CENTER);
        githubMark.setPreferredSize(new Dimension(130, 27));

        githubMark.addMouseListener(new MouseAdapter() { // and here we make sure that whenever you click that text, it opens up your browser with my github profile

            @Override
            public void mouseClicked(MouseEvent event) {
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/AfonsoSilvestre"));
                } catch (IOException | URISyntaxException err) {
                    System.out.println("Unable to open link.");
                }
            }

            @Override
            public void mouseEntered(MouseEvent event) { // and here we simply set that if your mouse hover over the mark, it changes it to the "hand" icon
                githubMark.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });

    }

    /**
     * Loads this project's author LinkedIn link onto the top right of the frame. Mine.
     */
    private void loadLinkedInMark() {
        ImageIcon LinkedInIcon = new ImageIcon(getClass().getResource("ImageAssets/LinkedInIcon.png"));

        linkedInMark.setText("AfonsoSilvestre");
        linkedInMark.setFont(new Font(Font.DIALOG, Font.PLAIN, 11));
        linkedInMark.setOpaque(false);
        linkedInMark.setIcon(new ImageIcon(LinkedInIcon.getImage().getScaledInstance(12, 12, Image.SCALE_SMOOTH)));
        linkedInMark.setHorizontalAlignment(JLabel.CENTER);
        linkedInMark.setVerticalAlignment(JLabel.CENTER);
        linkedInMark.setPreferredSize(new Dimension(130, 27));

        linkedInMark.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent event) { // and here we make sure that whenever you click that text, it opens up your browser with my LinkedIn profile
                try {
                    Desktop.getDesktop().browse(new URI("https://www.linkedin.com/in/afonsosilvestrecontact/"));
                } catch (IOException | URISyntaxException err) {
                    System.out.println("Unable to open link.");
                }
            }

            public void mouseEntered(MouseEvent event) { // and here we simply set that if your mouse hover over the mark, it changes it to the "hand" icon
                linkedInMark.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });

    }

    /**
     * Since we are going to add all of our buttons onto a panel, and then said panel onto a certain cell in the grid,
     * we must first define the size of said panel, and load the buttons onto it.
     * @param layout our card layour previously created
     * @param container where we keep each "window" aka the main menu and game panel
     */
    private void loadButtons(CardLayout layout, JPanel container) {
        buttonPanel.setOpaque(false);
        buttonPanel.setPreferredSize(new Dimension(750, 35));

        playButton.setFocusable(false);
        playButton.setOpaque(false);
        playButton.setPreferredSize(new Dimension(70, 27));

        playButton.addActionListener(new ActionListener() { // if we click to play, we tell the layout to show the game panel that is saved under the "container"
            @Override
            public void actionPerformed(ActionEvent event) {
                layout.show(container, "Game");
            }

        });

        exitButton.setFocusable(false);
        exitButton.setOpaque(false);
        exitButton.setPreferredSize(new Dimension(60, 27));

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                new ExitWindow(); // opens up a confirmation to check if we want to exit the app
            }
        });
    }

    /**
     * Yet again another empty panel meant to space out components properly placed below the button panel, nothing more than a preference.
     */
    private void loadBottomPanel() {
        bottomPanel.setOpaque(false);
        bottomPanel.setPreferredSize(new Dimension(750, 27));
    }

}
