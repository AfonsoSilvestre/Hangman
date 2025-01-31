import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This class will be our game panel. Where we'll be playing and interacting with the application.
 * We create multiple functions that automate the creation of this panel as well as to make our code readable.
 * For more specifications read the description of each function.
 */
public class GameWindow implements Words {
    private JPanel mainPanel;
    private JButton guessButton;
    private JPanel guessHolderPanel;
    private JTextField input;
    private JPanel inputPanel;
    private JLabel guessHolder;
    private JLabel guessCount;
    private JPanel emptyPanelRight;
    private JPanel emptyPanelLeft;
    private JButton newWordButton;
    private JButton exitButton;
    private JLabel guessedLettersHolder;
    private JLabel emptyLeftLabel;

    private String word;
    private StringBuilder guess;
    private int guessesRemaining = 6;
    private ArrayList<Character> guessedLetters = new ArrayList<>();
    private ArrayList<Character> wrongLetters = new ArrayList<>();
    private Image[] HangmanStages = new Image[7];

    public GameWindow(CardLayout layout, JPanel container) {
        createNewWord();
        createEmptyGuess();
        downloadImages();
        loadMainPanel();
        loadComponents(layout, container);
    }

    /**
     * As said in the Window class file, we'll be working with panels to display our components.
     * However the Java GUI form system works with JFrames, therefore this function's only purpose is to get
     * the JFrames content panel to other files.
     * @return the game panel
     */
    public JPanel getPanel() {
        return this.mainPanel;
    }

    /**
     * This function is executed upon calling the class, and it simply defines some small characteristics of the game,
     */
    private void loadMainPanel() {
        this.mainPanel.setOpaque(true);
        this.mainPanel.setBackground(new Color(255, 254, 254, 255));
    }

    /**
     * This class implements the Words interface, and this function is simply generating a random word from that interface.
     */
    private void createNewWord() {
        this.word = words[(int) (Math.random() * words.length)];
    }

    /**
     * When we start the game our guess must be empty. Therefore, we simply create a guess with just dashes,
     * just like how it would be when you play on a blackboard.
     */
    private void createEmptyGuess() {
        StringBuilder guess = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            guess.append("_");
        }
        this.guess = guess;
    }

    /**
     * Here we format our guess in a way that each character is spaces out from another
     * and printed out properly. To imitate the feeling of replacing dashes for words when playing.
     * We could and SHOULD use concatenation here, but since this is meant to be a beginner friendly
     * project, we'll do it in a more primitive way
     * @param guess: a certain string about to be formatted accordingly
     * @return the formatted word
     */
    private String formatGuess(String guess) {
        String newGuess = "";
        for (int i = 0; i < guess.length(); i++) {
            if (i == guess.length() - 1) {
                newGuess += String.format("%c", guess.charAt(i));
            } else {
                newGuess += String.format("%c ", guess.charAt(i));
            }
        }
        return newGuess;
    }

    /**
     * Simple method to check if a character is in our mystery word.
     * We use break here to not iterate through unnecessary elements. We only need to find
     * one exception.
     * @param letter: a guessed letter from the player
     * @return a boolean value
     */
    private boolean isInWord(char letter) {
        boolean isInWord = false;
        for (char c : word.toCharArray()) {
            if (c == letter) {
                isInWord = true;
                break;
            }
        }
        return isInWord;
    }

    /**
     * Returns how many times a letter is seen in our mystery word
     * @param letter: a guessed letter from the player
     * @return the frequency of the letter in the mystery word
     */
    private int getLetterFrequency(char letter) {
        int count = 0;
        for (char c : word.toCharArray()) {
            if (c == letter) {
                count++;
            }
        }
        return count;
    }

    /**
     * This method saves onto an array which are the positions of a letter in the mystery word, so
     * that we can replace it in our guess accordingly.
     * @param letter: a guessed letter from the user
     * @return that letter's positions in the mystery word
     */
    private int[] getLetterPositions(char letter) {
        int[] positions = new int[getLetterFrequency(letter)];
        int index = 0;

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                positions[index] = i;
                index++;
            }
        }
        return positions;
    }

    /**
     * The following method loads all the hangman stages onto an Image array. Good
     * to de-clutter code along the way.
     */
    private void downloadImages(){
        for(int i = 0; i < HangmanStages.length; i++){
            Image stage = new ImageIcon(getClass().getResource(String.format("ImageAssets/HangManPics/stage%d.jpg", i))).getImage().getScaledInstance( 90, 140, Image.SCALE_SMOOTH);
            HangmanStages[i] = stage;
        }
    }

    /**
     * Replace the dashes in the players guess by the guessed letter. Uses methods defined
     * above to make the process more streamlined.
     * @param letter
     */
    private void updateGuess(char letter) {
        int[] positions = getLetterPositions(letter);
        for (int pos : positions) {
            guess.setCharAt(pos, letter);
        }

    }

    /**
     * The method gives us the players guess so far.
     * @return the players guess
     */
    private String getGuess() {
        return guess.toString();
    }

    /**
     * This method returns all the wrong guessed letters in a readable format as described in
     * the formatGuess method.
     * We can and SHOULD use concatenation, however this is meant to be beginner friendly so
     * we'll use something more primitive.
     * @return all the wrong guessed letters already spaced out
     */
    private String getWrongLetters(){
        String letters = "";
        for(int i = 0; i < wrongLetters.size(); i++){
            if(i == guessedLetters.size() - 1){
                letters += String.format("%c", wrongLetters.get(i));
            } else {
                letters += String.format("%c ", wrongLetters.get(i));
            }
        }

        return letters;
    }

    /**
     * Tells us if a word was guessed already or not.
     * @param letter: the letter guessed by the player
     * @return whether it was guessed already
     */
    private boolean wasGuessedAlready(char letter) {
        boolean guessed = false;
        for (char c : guessedLetters) {
            if (c == letter) {
                guessed = true;
                break;
            }
        }

        return guessed;
    }

    /**
     * Simply checks if a character is a latin character.
     * @param c: the letter guessed by the user
     * @return whether its a letter
     */
    private boolean isLetter(char c) {
        return Character.isLetter(c);
    }

    /**
     * This method clears the users input in the text box.
     */
    private void clearInputText(){
        this.input.setText("");
    }

    /**
     * Updates the hangman drawing on how many letters have been guessed wrongly.
     */
    private void updateHangman(){
        switch(wrongLetters.size()){
            case 1: emptyLeftLabel.setIcon(new ImageIcon(HangmanStages[1])); break;
            case 2: emptyLeftLabel.setIcon(new ImageIcon(HangmanStages[2])); break;
            case 3: emptyLeftLabel.setIcon(new ImageIcon(HangmanStages[3])); break;
            case 4: emptyLeftLabel.setIcon(new ImageIcon(HangmanStages[4])); break;
            case 5: emptyLeftLabel.setIcon(new ImageIcon(HangmanStages[5])); break;
            case 6: emptyLeftLabel.setIcon(new ImageIcon(HangmanStages[6])); break;
            default: emptyLeftLabel.setIcon(new ImageIcon(HangmanStages[0])); break;

        }
    }

    /**
     * Simply updates the guess holder to the latest guess.
     */
    private void updateGuessHolder() {
        this.guessHolder.setText(formatGuess(getGuess()));
    }

    /**
     * Simply updates the wrong letters panel to the latest wrongly guessed letters.
     */
    private void updateWrongLettersHolder(){
        this.guessedLettersHolder.setText(getWrongLetters());
    }

    /**
     * Updates the guesses remaining counter to the latest amount.
     */
    private void updateGuessCounter(){
        this.guessCount.setText(guessesRemaining + " guesses remaining");
    }

    /**
     * Erases all guessed letters.
     */
    private void resetGuessedLetters() {
        guessedLetters.clear();
    }

    /**
     * Erases all wrongly guesses letters.
     */
    private void resetWrongLetters() {
        wrongLetters.clear();
    }

    /**
     * Decreases how many guesses remaining the player has.
     */
    private void decrementGuessCount(){
        guessesRemaining--;
    }

    /**
     * Sets the guesses remaining to its original value.
     */
    private void resetGuessesRemaining(){
        guessesRemaining = 6;
    }

    /**
     * The following method properly encapsulates the methods above, so that it streamlines the
     * process of resetting the game by clearing values, updating panels and holders.
     */
    private void resetGame(){

        // erase all words from the input box and create a new word
        clearInputText();
        createNewWord();

        // reset the current guess
        createEmptyGuess();
        updateGuessHolder();

        // erase all guessed letters and reset the hangman drawing
        resetGuessedLetters();
        resetWrongLetters();
        updateWrongLettersHolder();
        updateHangman();

        // reset the guesses remaining to 6
        resetGuessesRemaining();
        updateGuessCounter();
    }

    /**
     * Checks if the player has fully guessed the word, or if they have no remaining guesses.
     * In either case, the game is over and the player must click the "New Word" button.
     */
    private void checkGameStatus(){
            if( guess.toString().equals(word) ){
                JOptionPane.showMessageDialog(null, "YOU WON!", "Congrats!", JOptionPane.INFORMATION_MESSAGE);

            } else if( guessesRemaining == 0 ){
                JOptionPane.showMessageDialog(null, String.format("YOU LOST!\nThe correct word was %s", word), "Oh no...", JOptionPane.ERROR_MESSAGE);
            }
    }

    /**
     * Since java swing doesnt offer good methods to easily place components anywhere on the
     * window, many empty panels were created with the purpose of spacing out components.
     * "emptyPanelLeft" is a panel to the left of our current guess that holds the hangman drawing.
     * "emptyPanelRight" is a panel between our current guess and the guesses remaining counter.
     * "emptyLeftLabel" is a label within "emptyPanelLeft" that holds the hangman drawing.
     */
    private void loadEmptyPanels() {
        this.emptyPanelLeft.setOpaque(false);
        this.emptyPanelLeft.setPreferredSize(new Dimension(200, 50));

        this.emptyPanelRight.setOpaque(false);
        this.emptyPanelRight.setPreferredSize(new Dimension(100, 50));

        this.emptyLeftLabel.setOpaque(false);
        this.emptyLeftLabel.setIcon(new ImageIcon(HangmanStages[0]));
    }

    /**
     * This is where we'll be displaying the players current guess. At start is should only
     * display dashes, representing each letter.
     */
    private void loadGuessHolder() {
        // we dont set a size for the guess holder panel since we want it to appropriate the guessHolder. it will resize upon the guessHolders size
        this.guessHolderPanel.setOpaque(false);
        this.guessHolder.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        updateGuessHolder();
    }

    /**
     * This method loads some basic settings of the words that were wrongly guessed.
     * At start is should be empty, and then display letters below the players input box.
     */
    private void loadWrongLettersHolder(){
        this.guessedLettersHolder.setOpaque(false);
        this.guessedLettersHolder.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        updateWrongLettersHolder();
    }

    /**
     * This method creates and sets the guesses remaining counter at start.
     */
    private void loadGuessesRemaining() {
        this.guessCount.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        this.guessCount.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        // we added some padding in the GUI form for this guessCount Label!!!
        updateGuessCounter();
    }

    /**
     * Here we'll create the input box for the player to type their word in.
     */
    private void loadInputHolder() {
        this.inputPanel.setFocusable(false);
        this.inputPanel.setOpaque(false);
        this.inputPanel.setSize(new Dimension(750, 35));
        this.input.setPreferredSize(new Dimension(50, 27));
    }

    /**
     * Here we create the "Guess" button.
     */
    private void loadGuessButton() {
        this.guessButton.setFocusable(false);
        this.guessButton.setPreferredSize(new Dimension(70, 27));
    }

    /**
     * Here we create the "New Word" button.
     */
    private void loadNewWordButton() {
        this.newWordButton.setFocusable(false);
        this.newWordButton.setPreferredSize(new Dimension(70, 27));
    }

    /**
     * Here we create the "Exit" button.
     */
    private void loadExitButton() {
        this.exitButton.setFocusable(false);
        this.exitButton.setPreferredSize(new Dimension(70, 27));
    }

    /**
     * Here we load all of the buttons accordinly.
     */
    private void loadButtons(){
        loadGuessButton();
        loadNewWordButton();
        loadExitButton();
    }

    /**
     * Simple method that streamlines the process of displaying an error message.
     * @param title: Type of message we want to display
     * @param message: What text we want to display
     */
    private void showErrorMessage(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * And this is the method where we at last encapsulate all the methods above defined.
     * We will be creating our components, loading them onto the main panel, and giving them functionality.
     * We must start by using our load methods and then setting the rules for each button.
     * @param layout
     * @param container
     */
    private void loadComponents(CardLayout layout, JPanel container) {
        System.out.println(word);
        loadEmptyPanels();
        loadGuessHolder();
        loadWrongLettersHolder();
        loadGuessesRemaining();
        loadInputHolder();
        loadButtons();

        guessButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String text = input.getText();
                clearInputText();

                if (guessesRemaining == 0 || guess.toString().equals(word)) {
                    showErrorMessage("DENIED!", "Game is over!");

                } else if (text.isEmpty()) {
                    showErrorMessage("DENIED", "Please enter a letter.");

                } else if (text.length() >= 2) {
                    showErrorMessage("DENIED", "Just one letter");

                } else {

                    char letter = Character.toLowerCase(text.charAt(0));

                    if (!isLetter(letter)) {

                        showErrorMessage("DENIED", "Not a letter");

                    } else if (wasGuessedAlready(letter)) {

                        showErrorMessage("DENIED", "Letter already guessed!");

                    } else {

                        guessedLetters.add(letter);

                        if (isInWord(letter)) {
                            updateGuess(letter);
                            updateGuessHolder();

                        } else {
                            wrongLetters.add(letter);
                            updateHangman();
                            updateWrongLettersHolder();
                            decrementGuessCount();
                            updateGuessCounter();

                        }

                        checkGameStatus();
                    }
                }

            }
        });

        newWordButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                resetGame(); System.out.println(word);
            }

        });

        exitButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                resetGame(); System.out.println(word);
                layout.show(container, "Main");
            }

        });

    }

}
