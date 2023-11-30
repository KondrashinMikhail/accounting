package ru.ulstu.accounting.form.customGraphics.table;

import ru.ulstu.accounting.utils.DesignUtils;

import javax.swing.*;
import java.awt.*;

public class ScrollBarCustom extends JScrollBar {
    public ScrollBarCustom() {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(8, 8));
        setForeground(DesignUtils.MAIN_COLOR);
        setBackground(Color.WHITE);
    }
}
