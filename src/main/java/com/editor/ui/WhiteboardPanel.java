package com.editor.ui;

import com.editor.figure.Figure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class WhiteboardPanel extends JPanel {

    private final List<Figure> figures = new ArrayList<>();
    private TrashPanel trashPanel;
    private ToolBarPanel toolBarPanel;

    public WhiteboardPanel() {
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (toolBarPanel != null && toolBarPanel.getDraggingTemplate() != null) {
                    Point global = SwingUtilities.convertPoint(WhiteboardPanel.this, e.getPoint(), null);
                    if (trashPanel != null && trashPanel.containsGlobalPoint(global)) {
                        int index = toolBarPanel.getLastDraggedIndex();
                        if (index != -1) {
                            toolBarPanel.getToolBar().removeTemplateAt(index);
                            System.out.println("SUPPRIMÉ !");
                        }
                    } else {
                        // ➕ Ajouter la figure si pas dans la poubelle
                        Figure dropped = toolBarPanel.getDraggingTemplate().clone();
                        dropped.translate(e.getX(), e.getY());
                        addFigure(dropped);
                        System.out.println("Figure ajoutée au whiteboard");
                    }
            
                    toolBarPanel.clearDraggingTemplate();
                    toolBarPanel.revalidate();
                    toolBarPanel.repaint();
                }
            }
            
        });
    }

    public void setToolBarPanel(ToolBarPanel panel) {
        this.toolBarPanel = panel;
    }

    public void setTrashPanel(TrashPanel panel) {
        this.trashPanel = panel;
    }

    public void addFigure(Figure f) {
        figures.add(f);
        repaint();
    }

    public List<Figure> getFigures() {
        return new ArrayList<>(figures);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Figure f : figures) {
            f.draw(g);
        }
    }
}
