package com.editor.core.commands;

import com.editor.core.figures.Figure;
import com.editor.gui.WhiteBoard;

public class RemoveFigureCommand implements Command {
    private final WhiteBoard board;
    private final Figure figure;

    public RemoveFigureCommand(WhiteBoard board, Figure figure) {
        this.board = board;
        this.figure = figure;
    }

    @Override
    public void execute() {
        board.removeFigure(figure);
    }

    @Override
    public void undo() {
        board.addFigure(figure);
    }
}
