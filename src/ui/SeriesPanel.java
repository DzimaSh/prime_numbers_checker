package ui;

import parser.Parser;

import javax.swing.JButton;
import javax.swing.JFileChooser;
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
import java.io.FileNotFoundException;
import java.util.List;

public abstract class SeriesPanel extends JPanel {
    protected JTextPane textPane1, textPane2, textPane3;
    protected JButton fileButton, defaultFileButton;


    public SeriesPanel() {
        super(new BorderLayout());
        prepareFileButton();
        prepareTextPanes();
    }

    private void prepareTextPanes() {
        textPane1 = new JTextPane();
        textPane2 = new JTextPane();
        textPane3 = new JTextPane();
    }

    private void prepareFileButton() {
        fileButton = new JButton("Выбрать файл");
        fileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            try {
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    List<Long> numbers = Parser.readFileAndParseArray(selectedFile);
                    printNumbers(numbers);
                }
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    protected abstract void prepareDefaultFileButton();

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

    private void appendToPane(JTextPane textPane, String text, Color color) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet printAttrSet = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);

        int len = textPane.getDocument().getLength();
        textPane.setCaretPosition(len);
        textPane.setCharacterAttributes(printAttrSet, false);
        textPane.replaceSelection(text);
    }
}
