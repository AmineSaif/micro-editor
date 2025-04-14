package com.editor.core.commands;

import com.editor.core.figures.Figure;
import com.editor.core.figures.Group;
import com.editor.gui.WhiteBoard;

import java.util.ArrayList;
import java.util.List;

/**
 * Commande pour dissocier un groupe d’objets en ses composants individuels.
 */
public class DegroupCommand implements Command {

    private final WhiteBoard whiteBoard;
    private final Group group;
    private final List<Figure> decomposed = new ArrayList<>();

    public DegroupCommand(WhiteBoard whiteBoard, Group group) {
        this.whiteBoard = whiteBoard;
        this.group = group;
    }

    @Override
    public void execute() {
        whiteBoard.removeFigure(group);
        decomposed.clear();
        decomposed.addAll(group.getChildren());

        for (Figure f : decomposed) {
            whiteBoard.addFigure(f);
        }

        whiteBoard.repaint();
    }

    @Override
    public void undo() {
        for (Figure f : decomposed) {
            whiteBoard.removeFigure(f);
        }

        whiteBoard.addFigure(group);
        whiteBoard.repaint();
    }
}
