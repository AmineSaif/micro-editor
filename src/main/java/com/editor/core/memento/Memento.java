package com.editor.core.memento;

import com.editor.core.figures.Figure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Memento représentant un état sauvegardé de la liste des figures.
 * Implémente le pattern Memento.
 */
public class Memento implements Serializable {

    private final List<Figure> savedState;

    public Memento(List<Figure> currentState) {
        this.savedState = new ArrayList<>();
        for (Figure f : currentState) {
            this.savedState.add(f.clone()); // copie profonde
        }
    }

    public List<Figure> getSavedState() {
        List<Figure> copy = new ArrayList<>();
        for (Figure f : savedState) {
            copy.add(f.clone());
        }
        return copy;
    }
}
