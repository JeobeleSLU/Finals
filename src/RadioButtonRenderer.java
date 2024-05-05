import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class RadioButtonRenderer extends JRadioButton implements TableCellRenderer {
    public RadioButtonRenderer() {
        setHorizontalAlignment(JRadioButton.CENTER);
        setEnabled(true); // Radio button should be disabled to prevent interaction
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setSelected((Boolean) value);
        return this;
    }
}
