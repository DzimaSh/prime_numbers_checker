import parser.Parser;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Простые числа");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);

            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);

            JButton button = getButton();

            frame.setLayout(new BorderLayout());
            frame.add(button, BorderLayout.NORTH);
            frame.add(new JScrollPane(textArea), BorderLayout.CENTER);

            frame.setVisible(true);
        });
    }

    private static JButton getButton() {
        JButton button = new JButton("Выбрать файл");
        button.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            try {
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    List<Long> numbers = Parser.readFileAndParseArray(selectedFile);
                    System.out.println(numbers);
                }
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        return button;
    }

    public static boolean isPrime(int num) {
        if (num <= 1) return false;
        if (num <= 3) return true;
        if (num % 2 == 0 || num % 3 == 0) return false;
        for (int i = 5; i * i <= num; i = i + 6)
            if (num % i == 0 || num % (i + 2) == 0)
                return false;
        return true;
    }
}