package com.editor.gui;

import com.editor.core.commands.CommandManager;
import com.editor.core.commands.EditFigureCommand;
import com.editor.core.figures.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Fenêtre d’édition des propriétés d’une figure avec support Undo/Redo.
 */
public class FigureEditDialog extends JDialog {

    private final Figure figure;
    private final CommandManager commandManager;

    private final JTextField xField = new JTextField(5);
    private final JTextField yField = new JTextField(5);
    private final JTextField rotationField = new JTextField(5);
    private final JTextField colorField = new JTextField(7);

    // Pour Rectangle
    private JTextField widthField, heightField, radiusField;

    // Pour Polygone
    private JTextField sideLengthField, sidesField;

    public FigureEditDialog(JFrame parent, Figure figure, CommandManager commandManager) {
        super(parent, "Edit Figure", true);
        this.figure = figure;
        this.commandManager = commandManager;

        setLayout(new BorderLayout());
        JPanel fieldsPanel = new JPanel(new GridLayout(0, 2, 5, 5));

        // Champs communs
        xField.setText(String.valueOf(figure.getX()));
        yField.setText(String.valueOf(figure.getY()));
        rotationField.setText(String.valueOf(figure.getRotation()));
        colorField.setText(colorToHex(figure.getColor()));

        fieldsPanel.add(new JLabel("X:")); fieldsPanel.add(xField);
        fieldsPanel.add(new JLabel("Y:")); fieldsPanel.add(yField);
        fieldsPanel.add(new JLabel("Rotation (°):")); fieldsPanel.add(rotationField);
        fieldsPanel.add(new JLabel("Color (#RRGGBB):")); fieldsPanel.add(colorField);

        // Rectangle
        if (figure instanceof RectangleFigure rect) {
            widthField = new JTextField(String.valueOf(rect.getBounds().width));
            heightField = new JTextField(String.valueOf(rect.getBounds().height));
            radiusField = new JTextField(String.valueOf(rect.getBorderRadius()));

            fieldsPanel.add(new JLabel("Width:")); fieldsPanel.add(widthField);
            fieldsPanel.add(new JLabel("Height:")); fieldsPanel.add(heightField);
            fieldsPanel.add(new JLabel("Border Radius:")); fieldsPanel.add(radiusField);
        }

        // Polygone
        if (figure instanceof PolygonFigure poly) {
            sideLengthField = new JTextField(String.valueOf(poly.getSideLength()));
            sidesField = new JTextField(String.valueOf(poly.getSides()));

            fieldsPanel.add(new JLabel("Side Length:")); fieldsPanel.add(sideLengthField);
            fieldsPanel.add(new JLabel("Sides:")); fieldsPanel.add(sidesField);
        }

        // Groupe
        if (figure instanceof Group) {
            JLabel info = new JLabel("Modifications appliquées à tous les enfants");
            info.setForeground(Color.GRAY);
            fieldsPanel.add(info);
        }

        // Boutons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton applyBtn = new JButton("Appliquer");
        JButton okBtn = new JButton("OK");
        JButton cancelBtn = new JButton("Annuler");

        applyBtn.addActionListener(e -> applyChanges());
        okBtn.addActionListener(e -> {
            applyChanges();
            dispose();
        });
        cancelBtn.addActionListener(e -> dispose());

        btnPanel.add(applyBtn);
        btnPanel.add(okBtn);
        btnPanel.add(cancelBtn);

        add(fieldsPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(parent);
    }

    private void applyChanges() {
        try {
            int x = Integer.parseInt(xField.getText());
            int y = Integer.parseInt(yField.getText());
            double rotation = Double.parseDouble(rotationField.getText());
            Color color = Color.decode(colorField.getText());

            if (figure instanceof Group group) {
                for (Figure f : group.getChildren()) {
                    commandManager.executeCommand(new EditFigureCommand(f, x, y, rotation, color));
                }
            } else {
                commandManager.executeCommand(new EditFigureCommand(figure, x, y, rotation, color));
            }

            if (figure instanceof RectangleFigure rect) {
                int width = Integer.parseInt(widthField.getText());
                int height = Integer.parseInt(heightField.getText());
                int radius = Integer.parseInt(radiusField.getText());

                rect.setBorderRadius(radius);
                // Optionnel : créer EditRectangleCommand pour taille aussi
            }

            if (figure instanceof PolygonFigure poly) {
                int sideLength = Integer.parseInt(sideLengthField.getText());
                int sides = Integer.parseInt(sidesField.getText());

                poly.setSideLength(sideLength);
                poly.setSides(sides);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String colorToHex(Color c) {
        return String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
    }
}
