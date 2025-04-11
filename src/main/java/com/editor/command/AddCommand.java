package com.editor.command;

import com.editor.figure.Figure;

import com.editor.core.WhiteBoard;

public class AddCommand implements Command {
    private WhiteBoard whiteboard;
    private Figure figure;

    public AddCommand(WhiteBoard w, Figure f) {
        this.whiteboard = w;
        this.figure = f;
    }

    public void execute() { whiteboard.addFigure(figure); }
    public void undo() { whiteboard.removeFigure(figure); }
}