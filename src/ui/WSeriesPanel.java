package ui;

import parser.Parser;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.util.List;

public class WSeriesPanel extends SeriesPanel {

    private final static String DEFAULT_FILE = "testdata/zadPriz1_TaranchukV0w.txt";
    private final BoundInputPanel boundPanel;

    public WSeriesPanel() {
        super(DEFAULT_FILE);
        boundPanel = new BoundInputPanel();

        this.add(boundPanel, BorderLayout.NORTH);
    }

    @Override
    protected void proceed() {
        try {
            List<Long> numbers = Parser.readFileAndParseArray(getSelectedFile());
            printNumbers(numbers.subList(boundPanel.getLowerBoundValue(), boundPanel.getUpperBoundValue()));
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
}
