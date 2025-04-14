package com.editor.core.commands;

import com.editor.core.figures.Figure;
import com.editor.core.figures.Group;
import com.editor.gui.WhiteBoard;

import java.util.ArrayList;
import java.util.List;

/**
 * Commande pour regrouper plusieurs figures dans un groupe composite.
 */
public class GroupCommand implements Command {

    private final WhiteBoard whiteBoard;
    private final List<Figure> toGroup;
    private Group group;

    public GroupCommand(WhiteBoard whiteBoard, List<Figure> toGroup) {
        this.whiteBoard = whiteBoard;
        this.toGroup = new ArrayList<>(toGroup); // 🔐 copie pour éviter conflits
    }

    @Override
    public void execute() {
        // 🔐 Crée une copie locale pour ne pas modifier pendant itération
        List<Figure> toRemove = new ArrayList<>(toGroup);
        for (Figure f : toRemove) {
            whiteBoard.removeFigure(f);
        }

        group = new Group();
        for (Figure f : toGroup) {
            group.add(f);
        }

        whiteBoard.addFigure(group);
        whiteBoard.repaint();
    }

    @Override
    public void undo() {
        whiteBoard.removeFigure(group);
        for (Figure f : toGroup) {
            whiteBoard.addFigure(f);
        }
        whiteBoard.repaint();
    }
}
