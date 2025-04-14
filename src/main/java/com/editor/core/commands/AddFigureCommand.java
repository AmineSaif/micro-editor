package com.editor.core.commands;

import com.editor.core.figures.Figure;
import com.editor.gui.WhiteBoard;

public class AddFigureCommand implements Command {
    private final WhiteBoard board;
    private final Figure figure;

    public AddFigureCommand(WhiteBoard board, Figure figure) {
        this.board = board;
        this.figure = figure;
    }

    @Override
    public void execute() {
        board.addFigure(figure);
    }

    @Override
    public void undo() {
        board.removeFigure(figure);
    }
}
