package com.editor.core.memento;

import com.editor.core.figures.Figure;
import com.editor.gui.WhiteBoard;

import java.util.ArrayList;
import java.util.List;

/**
 * Originator qui encapsule l’état du WhiteBoard.
 * Permet de créer et restaurer des Memento.
 */
public class Originator {

    private List<Figure> state = new ArrayList<>();
    private final WhiteBoard whiteBoard;

    public Originator(WhiteBoard whiteBoard) {
        this.whiteBoard = whiteBoard;
    }

    public void setState(List<Figure> figures) {
        this.state = new ArrayList<>(figures); // shallow copy
    }

    public List<Figure> getState() {
        return state;
    }

    public Memento saveToMemento() {
        return new Memento(state);
    }

    public void restoreFromMemento(Memento memento) {
        this.state = memento.getSavedState();
    }
}
