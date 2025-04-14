package com.editor.core.commands;

import com.editor.core.figures.Figure;

import java.awt.*;

/**
 * Commande permettant de modifier une figure (position, rotation, couleur)
 * avec support Undo/Redo.
 */
public class EditFigureCommand implements Command {

    private final Figure target;
    private final int oldX, oldY;
    private final double oldRotation;
    private final Color oldColor;

    private final int newX, newY;
    private final double newRotation;
    private final Color newColor;

    public EditFigureCommand(Figure target, int newX, int newY, double newRotation, Color newColor) {
        this.target = target;

        this.oldX = target.getX();
        this.oldY = target.getY();
        this.oldRotation = target.getRotation();
        this.oldColor = target.getColor();

        this.newX = newX;
        this.newY = newY;
        this.newRotation = newRotation;
        this.newColor = newColor;
    }

    @Override
    public void execute() {
        apply(newX, newY, newRotation, newColor);
    }

    @Override
    public void undo() {
        apply(oldX, oldY, oldRotation, oldColor);
    }

    private void apply(int x, int y, double rotation, Color color) {
        target.setX(x);
        target.setY(y);
        target.setRotation(rotation);
        target.setColor(color);
    }
}
