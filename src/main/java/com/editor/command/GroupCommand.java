package com.editor.command;

import com.editor.core.WhiteBoard;
import com.editor.figure.*;
import java.util.*;

public class GroupCommand implements Command {
    private WhiteBoard whiteboard;
    private List<Figure> figures;
    private GroupFigure group;

    public GroupCommand(WhiteBoard w, List<Figure> figs) {
        this.whiteboard = w;
        this.figures = figs;
        this.group = new GroupFigure();
    }

    public void execute() {
        for (Figure f : figures) {
            group.add(f);
            whiteboard.removeFigure(f);
        }
        whiteboard.addFigure(group);
    }

    public void undo() {
        whiteboard.removeFigure(group);
        for (Figure f : figures) {
            whiteboard.addFigure(f);
        }
    }
}