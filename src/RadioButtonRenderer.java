import java.awt.Component;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class RadioButtonRenderer extends JRadioButton implements TableCellRenderer {
    public RadioButtonRenderer() {
        this.setHorizontalAlignment(0);
        this.setEnabled(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof Boolean) {
            this.setSelected((Boolean)value);
        } else if (value instanceof Float) {
            float floatValue = (Float)value;
            this.setSelected(floatValue > 75.0F);
        } else {
            this.setSelected(false); // Default to false if value is not boolean or float
        }
        return this;
    }
}
