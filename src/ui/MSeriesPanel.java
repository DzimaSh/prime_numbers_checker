package ui;

import parser.Parser;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;

public class MSeriesPanel extends SeriesPanel {

    private final static String DEFAULT_FILE = "testdata/zadPriz1_TaranchukV0m.txt";

    public MSeriesPanel() {
        super(DEFAULT_FILE);
    }

    @Override
    protected void proceed() {
        try {
            List<Long> numbers = Parser.readFileAndParseArray(getSelectedFile());
            printNumbers(numbers);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
}
