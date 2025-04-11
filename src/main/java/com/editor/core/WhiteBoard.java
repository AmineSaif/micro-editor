package com.editor.core;

import com.editor.figure.Figure;
import java.util.*;

public class WhiteBoard {
    private List<Figure> figures = new ArrayList<>();

    public void addFigure(Figure f) { figures.add(f); }
    public void removeFigure(Figure f) { figures.remove(f); }

    public WhiteBoardMemento save() {
        return new WhiteBoardMemento(new ArrayList<>(figures));
    }

    public void restore(WhiteBoardMemento m) {
        figures = new ArrayList<>(m.getState());
    }
}