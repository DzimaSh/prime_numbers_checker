import parser.Parser;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
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

public class Main {
    private JFrame frame;
    private JTextPane textPaneM1, textPaneM2, textPaneM3;
    private JButton fileButton, defaultFileButton;

    public static void main(String[] args) {
        new Main().buildUI();
    }

    private void buildUI() {
        SwingUtilities.invokeLater(() -> {
            prepareTextPanes();
            prepareFileButton();
            prepareDefaultFileButton();
            prepareFrame();
        });
    }

    private void prepareFrame() {
        frame = new JFrame("Простые числа");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        frame.setLayout(new BorderLayout());
        frame.add(fileButton, BorderLayout.NORTH);
        frame.add(defaultFileButton, BorderLayout.SOUTH);

        JPanel centerPanel = new JPanel(new GridLayout(1, 3));
        centerPanel.add(new JScrollPane(textPaneM1));
        centerPanel.add(new JScrollPane(textPaneM2));
        centerPanel.add(new JScrollPane(textPaneM3));

        frame.add(centerPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void prepareTextPanes() {
        textPaneM1 = new JTextPane();
        textPaneM2 = new JTextPane();
        textPaneM3 = new JTextPane();
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

    private void prepareDefaultFileButton() {
        defaultFileButton = new JButton("Proceed default data");
        defaultFileButton.addActionListener(e -> {
            try {
                File selectedFile = new File("testdata/zadPriz1_TaranchukV0w.txt");
                List<Long> numbers = Parser.readFileAndParseArray(selectedFile);
                printNumbers(numbers);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void printNumbers(List<Long> numbers) {
        textPaneM1.setText("");
        textPaneM2.setText("");
        textPaneM3.setText("");
        long index = 1;
        for (Long number : numbers) {
            if (Parser.isPrime(number)) {
                appendToPane(textPaneM1, number + "\n", Color.BLUE);
                appendToPane(textPaneM2, number + ", " + index + "\n", Color.BLACK);
                appendToPane(textPaneM3, number * index + "\n", Color.RED);
            } else {
                appendToPane(textPaneM1, number + "\n", Color.BLACK);
                appendToPane(textPaneM3, number + "\n", Color.BLACK);
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