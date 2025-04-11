package com.editor.command;

import com.editor.figure.Figure;

public class MoveCommand implements Command {
    private Figure figure;
    private int dx, dy;

    public MoveCommand(Figure f, int dx, int dy) {
        this.figure = f;
        this.dx = dx;
        this.dy = dy;
    }

    public void execute() { figure.translate(dx, dy); }
    public void undo() { figure.translate(-dx, -dy); }
}