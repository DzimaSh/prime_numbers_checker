package ui;

import parser.Parser;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class MSeriesPanel extends SeriesPanel {

    private final static String DEFAULT_FILE = "testdata/zadPriz1_TaranchukV0m.txt";

    public MSeriesPanel() {
        super(DEFAULT_FILE);
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
}
