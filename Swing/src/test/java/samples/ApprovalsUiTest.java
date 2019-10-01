package samples;

import javax.swing.JButton;
import javax.swing.JComponent;

import org.approvaltests.Approvals;
import org.approvaltests.reporters.ClipboardReporter;
import org.approvaltests.reporters.ImageWebReporter;
import org.approvaltests.reporters.QuietReporter;
import org.approvaltests.reporters.UseReporter;
import org.junit.Test;

public class ApprovalsUiTest {

    @Test
    @UseReporter({ ImageWebReporter.class, ClipboardReporter.class, QuietReporter.class })
    public void shouldApproveUi() {
        JComponent button = new JButton("Click me!");
        Approvals.verify(button);
    }

}
