package com.editor.gui;

import com.editor.core.figures.Figure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FigurePreviewButton extends JComponent {

    private final Figure figure;

    public FigurePreviewButton(Figure figure, Toolbar toolbar) {
        this.figure = figure;
        setPreferredSize(new Dimension(60, 60)); // Taille uniforme
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Un peu d'espace

        setToolTipText(figure.getClass().getSimpleName());

        setTransferHandler(new FigureTransferHandler.ValueExportTransferHandler(figure));

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                getTransferHandler().exportAsDrag(FigurePreviewButton.this, e, TransferHandler.MOVE);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        // 🔲 Fond blanc et bordure grise
        g2.setColor(Color.WHITE);
        g2.setColor(Color.GRAY); // fond gris clair
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

        // 📐 Dessin centré de la figure clonée
        Figure preview = figure.clone();
        Rectangle b = preview.getBounds();

        int dx = getWidth() / 2 - (b.x + b.width / 2);
        int dy = getHeight() / 2 - (b.y + b.height / 2);
        preview.setX(b.x + dx);
        preview.setY(b.y + dy);

        preview.draw(g2); // ✅ très important

        g2.dispose();
    }
}
