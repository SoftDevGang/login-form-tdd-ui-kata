package samples;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Source code for Tutorial 2.
 * 
 * @see "http://abbot.sourceforge.net/doc/Tutorial-2.shtml"
 */
public class AbbotLabeledList extends JPanel {

    private final JList<String> list;
    private final JLabel label;

    public AbbotLabeledList(String[] initialContents) {
        setLayout(new BorderLayout());
        list = new JList<>(initialContents);
        add(list, BorderLayout.CENTER);
        label = new JLabel("Selected: ");
        add(label, BorderLayout.SOUTH);
        // Update the label whenever the list selection changes
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(@SuppressWarnings("unused") ListSelectionEvent lse) {
                label.setText("Selected: " + list.getSelectedValue());
            }
        });
    }

    /**
     * Main to see the component for manual inspection.
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Manual Inspection");
        frame.getContentPane().add(new AbbotLabeledList(new String[] { "Abigail", "Alexandra", "Alison", "Amanda" }));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
