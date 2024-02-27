import ui.MSeriesPanel;
import ui.WSeriesPanel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Dimension;

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