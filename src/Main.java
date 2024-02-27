import ui.MSeriesPanel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class Main {
    private JFrame frame;
    private JTabbedPane tabbedPane;

    public static void main(String[] args) {
        new Main().buildUI();
    }

    private void buildUI() {
        SwingUtilities.invokeLater(this::prepareFrame);
    }

    private void prepareFrame() {
        frame = new JFrame("Простые числа");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);

        frame.setLayout(new BorderLayout());

        tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(400, 600));

        JPanel mCenterPanel = new MSeriesPanel();

        tabbedPane.addTab("M series", null, mCenterPanel, "This is M series tab");

        frame.add(tabbedPane, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }



}