package com.editor.core;

import com.editor.figure.Figure;
import java.util.*;

public class WhiteBoardMemento {
    private List<Figure> state;

    public WhiteBoardMemento(List<Figure> figures) {
        this.state = figures;
    }

    public List<Figure> getState() {
        return state;
    }
}