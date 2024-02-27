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
import java.util.Objects;

public abstract class SeriesPanel extends JPanel {
    protected JTextPane textPane1, textPane2, textPane3;
    protected JButton fileButton, defaultFileButton, proceedButton;
    protected File selectedFile = null;
    private FileChooserPanel fileSelector;


    public SeriesPanel(String defaultFileName) {
        super(new BorderLayout());
        prepareTextPanes();
        prepareDefaultFileButton();
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

    private void prepareTextPanes() {
        textPane1 = new JTextPane();
        textPane2 = new JTextPane();
        textPane3 = new JTextPane();
    }

    private void prepareProceedButton() {
        proceedButton = new JButton("Proceed");
        proceedButton.addActionListener(e -> {
            if (Objects.nonNull(getSelectedFile())) {
                try {
                    List<Long> numbers = Parser.readFileAndParseArray(getSelectedFile());
                    printNumbers(numbers);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
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

    protected File getSelectedFile() {
        return fileSelector.getSelectedFile();
    }
}
