import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class RadioButtonRenderer extends JRadioButton implements TableCellRenderer {
    public RadioButtonRenderer() {
        setHorizontalAlignment(JRadioButton.CENTER);
        setEnabled(true); // Radio button should be disabled to prevent interaction
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof Boolean) {
            setSelected((Boolean) value);
        } else if (value instanceof Float) {
            // Handle float values
            float floatValue = (Float) value;
            // Example condition: consider any value greater than 75 as true
            setSelected(floatValue > 75);
        }
        return this;
    }
}
