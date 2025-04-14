package com.editor;

import com.editor.core.commands.*;
import com.editor.core.figures.*;
import com.editor.core.memento.Memento;
import com.editor.core.memento.Originator;
import com.editor.core.persistence.SaveManager;
import com.editor.gui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowUI);
    }

    private static void createAndShowUI() {
        JFrame frame = new JFrame("Micro Éditeur de Figures");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        CommandManager commandManager = new CommandManager();
        WhiteBoard whiteBoard = new WhiteBoard(commandManager);
        Originator originator = new Originator(whiteBoard);

        // === Toolbar avec chargement automatique ===
        Toolbar toolbar = new Toolbar(commandManager);
        toolbar.setWhiteBoard(whiteBoard);

        // Juste après : Toolbar toolbar = new Toolbar(commandManager);
        toolbar.setWhiteBoard(whiteBoard);
        // Ajout simple des deux figures par défaut
        toolbar.addTemplate(new RectangleFigure(0, 0, 50, 50, Color.BLUE));
        toolbar.addTemplate(new PolygonFigure(0, 0, 30, 5, Color.GREEN));

        // Trash zone
        TrashZone trashZone = new TrashZone(commandManager, whiteBoard, toolbar);

        // === Barre supérieure (Undo/Redo/Group) ===
        JPanel topMenu = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton undoBtn = new JButton("Undo");
        JButton redoBtn = new JButton("Redo");
        JButton groupBtn = new JButton("Group");
        JButton ungroupBtn = new JButton("Ungroup");
        JButton saveBtn = new JButton("Save");
        JButton loadBtn = new JButton("Load");

        undoBtn.addActionListener(e -> {
            if (commandManager.canUndo()) {
                commandManager.undo();
                whiteBoard.repaint();
                toolbar.repaint(); // 🔁 n'oublie pas ce repaint !
            }
        });

        redoBtn.addActionListener(e -> {
            if (commandManager.canRedo()) {
                commandManager.redo();
                whiteBoard.repaint();
            }
        });

        groupBtn.addActionListener(e -> {
            if (whiteBoard.getSelection().size() >= 2) {
                commandManager.executeCommand(new GroupCommand(whiteBoard, whiteBoard.getSelection()));
                whiteBoard.clearSelection();
                whiteBoard.repaint();
            }
        });

        ungroupBtn.addActionListener(e -> {
            if (whiteBoard.getSelection().size() == 1 &&
                    whiteBoard.getSelection().get(0) instanceof Group group) {
                commandManager.executeCommand(new DegroupCommand(whiteBoard, group));
                whiteBoard.repaint();
            }
        });

        saveBtn.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
                originator.setState(whiteBoard.getFigures());
                try {
                    SaveManager.save(chooser.getSelectedFile().getAbsolutePath(), originator.saveToMemento());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Erreur lors de la sauvegarde");
                }
            }
        });

        loadBtn.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                try {
                    Memento memento = (Memento) SaveManager.load(chooser.getSelectedFile().getAbsolutePath());
                    originator.restoreFromMemento(memento);
                    whiteBoard.setFigures(originator.getState());
                } catch (IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(frame, "Erreur lors du chargement");
                }
            }
        });

        topMenu.add(undoBtn);
        topMenu.add(redoBtn);
        topMenu.add(groupBtn);
        topMenu.add(ungroupBtn);
        topMenu.add(saveBtn);
        topMenu.add(loadBtn);

        // === Panel latéral (Toolbar + Trash) ===
        JPanel leftPanel = new JPanel(new BorderLayout());
        JScrollPane scroll = new JScrollPane(toolbar);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setPreferredSize(new Dimension(100, 500));
        leftPanel.add(scroll, BorderLayout.CENTER);
        leftPanel.add(trashZone, BorderLayout.SOUTH);

        frame.add(topMenu, BorderLayout.NORTH);
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(new JScrollPane(whiteBoard), BorderLayout.CENTER);

        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}
