import ui.MSeriesPanel;
import ui.WSeriesPanel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Dimension;

/**
 * A class that creates a graphical user interface with two tabs, one for M series and one for W series.
 * The M series tab displays a panel that can generate and display prime numbers of the form 6k - 1.
 * The W series tab displays a panel that can generate and display prime numbers of the form 6k + 1.
 * The user can input the number of primes to generate and the range of k values to use.
 * The class uses Swing components such as JFrame, JTabbedPane, JPanel, JLabel, JTextField, and JButton.
 * The class also implements the ActionListener interface to handle the button click events.
 *
 * @author dzmitry.shuhskevich@gmail.com
 * @version 1.0
 * @since 21.02.2024 22.00
 */
public class Main {

    public static void main(String[] args) {
        new Main().buildUI();
    }

    private void buildUI() {
        SwingUtilities.invokeLater(this::prepareFrame);
    }

    private void prepareFrame() {
        JFrame frame = new JFrame("Простые числа");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);

        frame.setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(400, 600));

        JPanel mCenterPanel = new MSeriesPanel();
        tabbedPane.addTab("M series", null, mCenterPanel, "This is M series tab");

        JPanel wCenterPanel = new WSeriesPanel();
        tabbedPane.addTab("W series", null, wCenterPanel, "This is W series tab");

        frame.add(tabbedPane, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }
}