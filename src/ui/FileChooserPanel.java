package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class FileChooserPanel extends JPanel {
    private final JFileChooser fileChooser;
    private final JLabel label;
    private File selectedFile;

    public FileChooserPanel(String defaultFileName) {
        fileChooser = new JFileChooser();
        JButton fileButton = new JButton("Select a file");
        JButton defaultFileButton = new JButton("Select default file");
        label = new JLabel("No file selected");

        fileButton.addActionListener(this::selectFile);
        defaultFileButton.addActionListener(e -> selectDefaultFile(e, defaultFileName));

        this.add(fileButton);
        this.add(defaultFileButton);
        this.add(label);
    }

    private void selectFile(ActionEvent e) {
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File newFile = fileChooser.getSelectedFile();
            setSelectedFile(newFile);
            label.setText(newFile.getName());
        }
    }

    private void selectDefaultFile(ActionEvent e, String fileName) {
        File newFile = new File(fileName);
        setSelectedFile(newFile);
        label.setText("Default file selected");
    }

    public void setSelectedFile(File selectedFile) {
        this.selectedFile = selectedFile;
    }

    public File getSelectedFile() {
        return selectedFile;
    }
}

