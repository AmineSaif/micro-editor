package com.editor.ui;

import com.editor.core.ToolBar;
import com.editor.figure.RectangleFigure;

import javax.swing.*;
import java.awt.*;

/**
 * Fenêtre principale de l'application Micro Éditeur.
 */
public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Micro Éditeur de Figures Géométriques");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        // ToolBar logique
        ToolBar toolBar = new ToolBar();
        toolBar.addTemplate(new RectangleFigure(0, 0, 60, 40, Color.RED));
        toolBar.addTemplate(new RectangleFigure(0, 0, 80, 50, Color.BLUE));

        

        // Panels d'affichage
        ToolBarPanel toolBarPanel = new ToolBarPanel(toolBar);
        WhiteboardPanel whiteboardPanel = new WhiteboardPanel();

        // Connexion entre les deux pour le drag & drop
        whiteboardPanel.setToolBarPanel(toolBarPanel);

        // Poubelle
        TrashPanel trashPanel = new TrashPanel();
        whiteboardPanel.setTrashPanel(trashPanel);


        // Layout
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(toolBarPanel, BorderLayout.WEST);
        getContentPane().add(whiteboardPanel, BorderLayout.CENTER);
        getContentPane().add(trashPanel, BorderLayout.EAST);


        setVisible(true);
    }
}
