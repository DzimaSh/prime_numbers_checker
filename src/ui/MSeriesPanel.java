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

public class MSeriesPanel extends SeriesPanel {

    private final static String DEFAULT_FILE = "testdata/zadPriz1_TaranchukV0m.txt";

    public MSeriesPanel() {
        init();

        JPanel mCenterPanel = new JPanel(new GridLayout(1, 3));
        mCenterPanel.add(new JScrollPane(textPane1));
        mCenterPanel.add(new JScrollPane(textPane2));
        mCenterPanel.add(new JScrollPane(textPane3));

        JPanel mBottomPanel = new JPanel(new GridLayout(1, 2));
        mBottomPanel.add(fileButton);
        mBottomPanel.add(defaultFileButton);

        this.add(mCenterPanel, BorderLayout.CENTER);
        this.add(mBottomPanel, BorderLayout.NORTH);
    }

    @Override
    protected void prepareDefaultFileButton() {
        defaultFileButton = new JButton("Proceed default data");
        defaultFileButton.addActionListener(e -> {
            try {
                File selectedFile = new File(DEFAULT_FILE);
                List<Long> numbers = Parser.readFileAndParseArray(selectedFile);
                printNumbers(numbers);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void init() {
        prepareDefaultFileButton();
    }
}
