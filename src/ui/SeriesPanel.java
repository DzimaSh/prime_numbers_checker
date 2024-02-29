package ui;

import parser.Parser;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.util.List;
import java.util.Objects;

/**
 * An abstract class that represents a panel with three text panes, a file chooser, and a proceed button.
 * The text panes are used to display numbers of a certain series, such as M series or W series.
 * The file chooser allows the user to select a file that contains the parameters for generating the numbers.
 * The proceed button triggers the generation and display of the numbers, using the abstract proceed method.
 * The subclass should implement the proceed method according to the specific series logic.
 * The class uses Swing components such as JPanel, JTextPane, JButton, JScrollPane, and FileChooserPanel.
 * The class also uses the Parser class to check if a number is prime or not.
 *
 * @author dzmitry.shuhskevich@gmail.com
 * @version 1.0
 * @since 22.02.2024 00.00
 */
public abstract class SeriesPanel extends JPanel {
    protected JTextPane textPane1, textPane2, textPane3;
    protected JButton proceedButton;
    private final FileChooserPanel fileSelector;


    /**
     * Constructs a SeriesPanel with a given default file name for the file chooser.
     * Initializes the components and adds them to the panel with a border layout.
     *
     * @param defaultFileName the default file name for the file chooser
     */
    public SeriesPanel(String defaultFileName) {
        super(new BorderLayout());
        prepareTextPanes();
        prepareProceedButton();

        JPanel mCenterPanel = new JPanel(new GridLayout(1, 3));
        mCenterPanel.add(new JScrollPane(textPane1));
        mCenterPanel.add(new JScrollPane(textPane2));
        mCenterPanel.add(new JScrollPane(textPane3));

        JPanel mBottomPanel = new JPanel(new GridLayout(2, 1));
        fileSelector = new FileChooserPanel(defaultFileName);
        mBottomPanel.add(fileSelector);
        mBottomPanel.add(proceedButton);

        this.add(mCenterPanel, BorderLayout.CENTER);
        this.add(mBottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Prepares the text panes by creating and initializing them.
     */
    private void prepareTextPanes() {
        textPane1 = new JTextPane();
        textPane2 = new JTextPane();
        textPane3 = new JTextPane();
    }

    /**
     * Prepares the proceed button by creating and initializing it.
     * Adds an action listener that calls the proceed method if a file is selected.
     */
    private void prepareProceedButton() {
        proceedButton = new JButton("Proceed");
        proceedButton.addActionListener(e -> {
            if (Objects.nonNull(getSelectedFile())) {
                proceed();
            }
        });
    }

    /**
     * An abstract method that generates and displays the numbers of a specific series.
     * The subclass should implement this method according to the series logic.
     * The method should use the file selected by the file chooser as the input source.
     * The method should use the printNumbers method to display the numbers in the text panes.
     */
    protected abstract void proceed();

    /**
     * Prints a list of numbers in the text panes with different colors and formats.
     * The first text pane shows the numbers in blue if they are prime, and black otherwise.
     * The second text pane shows the numbers along with their index in the list.
     * The third text pane shows the product of the numbers and their index.
     * The method uses the appendToPane method to add the text to the text panes.
     *
     * @param numbers the list of numbers to print
     */
    protected void printNumbers(List<Long> numbers) {
        textPane1.setText("");
        textPane2.setText("");
        textPane3.setText("");
        long index = 1;
        for (Long number : numbers) {
            if (Parser.isPrime(number)) {
                appendToPane(textPane1, number + "\n", Color.BLUE);
                appendToPane(textPane2, number + ", " + index + "\n", Color.BLACK);
                appendToPane(textPane3, number * index + "\n", Color.RED);
            } else {
                appendToPane(textPane1, number + "\n", Color.BLACK);
                appendToPane(textPane3, number + "\n", Color.BLACK);
            }
            index++;
        }
    }

    /**
     * Appends a text to a text pane with a given color.
     * The method uses the StyleContext and AttributeSet classes to set the text color.
     * The method also sets the caret position and the character attributes of the text pane.
     *
     * @param textPane the text pane to append the text to
     * @param text the text to append
     * @param color the color of the text
     */
    private void appendToPane(JTextPane textPane, String text, Color color) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet printAttrSet = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);

        int len = textPane.getDocument().getLength();
        textPane.setCaretPosition(len);
        textPane.setCharacterAttributes(printAttrSet, false);
        textPane.replaceSelection(text);
    }

    /**
     * Returns the file selected by the file chooser panel.
     *
     * @return the selected file, or null if no file is selected
     */
    protected File getSelectedFile() {
        return fileSelector.getSelectedFile();
    }
}
