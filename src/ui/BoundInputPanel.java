package ui;

import javax.swing.*;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

public class BoundInputPanel extends JPanel implements PropertyChangeListener {
    // Declare the components
    private final JFormattedTextField lowerBoundField;
    private final JFormattedTextField upperBoundField;
    private final JLabel label;

    public BoundInputPanel() {
        super(new GridLayout(2, 1));
        // Initialize the components
        NumberFormat integerFormat = NumberFormat.getIntegerInstance(); // create a format for integers
        integerFormat.setGroupingUsed(false); // disable grouping (e.g. 1,000)
        lowerBoundField = new JFormattedTextField(integerFormat); // create a formatted text field for the first input
        lowerBoundField.setValue(0); // set the initial value to 0
        lowerBoundField.setColumns(10); // set the preferred column size
        lowerBoundField.addPropertyChangeListener("value", this); // add a property change listener to get the value when it changes
        upperBoundField = new JFormattedTextField(integerFormat); // create a formatted text field for the second input
        upperBoundField.setValue(0); // set the initial value to 0
        upperBoundField.setColumns(10); // set the preferred column size
        upperBoundField.addPropertyChangeListener("value", this); // add a property change listener to get the value when it changes
        label = new JLabel("No input yet"); // create a label to show the input values

        // Add the components to the panel
        JPanel mainPanel = new JPanel();
        mainPanel.add(new JLabel("Enter two integers:"));
        mainPanel.add(lowerBoundField);
        mainPanel.add(upperBoundField);

        this.add(mainPanel);
        this.add(label);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        // Get the input values as integers
        int lowerBoundValue = getLowerBoundValue();
        int upperBoundValue = getUpperBoundValue();

        // Update the label with the input values
        label.setText("Lower bound: " + lowerBoundValue + ". Upper bound: " + upperBoundValue);
    }

    public int getLowerBoundValue() {
        return ((Number) lowerBoundField.getValue()).intValue();
    }

    public int getUpperBoundValue() {
        return ((Number) upperBoundField.getValue()).intValue();
    }
}

