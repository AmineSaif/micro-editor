package com.editor.ui;

import com.editor.core.ToolBar;
import com.editor.figure.Figure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ToolBarPanel extends JPanel {

    private final ToolBar toolBar;
    private Figure draggingTemplate = null;
    private int lastDraggedIndex = -1;

    public ToolBarPanel(ToolBar toolBar) {
        this.toolBar = toolBar;
        setPreferredSize(new Dimension(150, 600));
        setBackground(Color.LIGHT_GRAY);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastDraggedIndex = e.getY() / 80;
                if (lastDraggedIndex >= 0 && lastDraggedIndex < toolBar.size()) {
                    draggingTemplate = toolBar.getClone(lastDraggedIndex);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // rien ici
            }
        });
    }

    public Figure getDraggingTemplate() {
        return draggingTemplate;
    }

    public void clearDraggingTemplate() {
        draggingTemplate = null;
    }

    public int getLastDraggedIndex() {
        return lastDraggedIndex;
    }

    public ToolBar getToolBar() {
        return toolBar;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < toolBar.size(); i++) {
            Figure template = toolBar.getTemplate(i);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.translate(10, i * 80 + 10);
            template.draw(g2);
            g2.dispose();
        }
    }
}
