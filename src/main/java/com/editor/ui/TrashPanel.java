package com.editor.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Zone poubelle à droite. Supprime une figure si elle y est déposée.
 */
public class TrashPanel extends JPanel {

    public TrashPanel() {
        // Prend toute la hauteur pour être facilement atteignable
        setPreferredSize(new Dimension(100, 700));
        setBackground(new Color(200, 50, 50));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.drawString("🗑️ Poubelle", 10, getHeight() / 2);
    }

    /**
     * Vérifie si un point en coordonnées écran est bien à l’intérieur de la poubelle.
     */
    public boolean containsGlobalPoint(Point globalPoint) {
        try {
            // Position de la poubelle dans l'écran
            Point locationOnScreen = this.getLocationOnScreen();

            // Rectangle absolu
            Rectangle screenRect = new Rectangle(locationOnScreen, getSize());

            boolean inside = screenRect.contains(globalPoint);
            System.out.println("Test poubelle (screenRect): " + inside + " / Point: " + globalPoint + " / Rect: " + screenRect);
            return inside;
        } catch (IllegalComponentStateException ex) {
            // Cela arrive si l'UI n'est pas encore visible
            System.out.println("Erreur accès poubelle : UI pas visible ?");
            return false;
        }
    }
}
