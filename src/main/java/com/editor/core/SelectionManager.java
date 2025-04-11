package com.editor.core;

import com.editor.figure.Figure;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Manages the selection of figures on the whiteboard.
 * Allows single and multiple selection, toggle, and clearing.
 */
public class SelectionManager {

    private final Set<Figure> selectedFigures = new HashSet<>();

    /**
     * Select a single figure, replacing any previous selection.
     */
    public void select(Figure figure) {
        selectedFigures.clear();
        if (figure != null)
            selectedFigures.add(figure);
    }

    /**
     * Adds or removes a figure from the current selection.
     * Useful for Ctrl+Click toggle.
     */
    public void toggle(Figure figure) {
        if (figure == null) return;
        if (selectedFigures.contains(figure))
            selectedFigures.remove(figure);
        else
            selectedFigures.add(figure);
    }

    /**
     * Clear the current selection.
     */
    public void clear() {
        selectedFigures.clear();
    }

    /**
     * Returns an unmodifiable view of selected figures.
     */
    public Set<Figure> getSelected() {
        return Collections.unmodifiableSet(selectedFigures);
    }

    /**
     * Check if a specific figure is selected.
     */
    public boolean isSelected(Figure figure) {
        return selectedFigures.contains(figure);
    }

    /**
     * Select a group of figures (e.g. via selection rectangle).
     */
    public void selectAll(Set<Figure> figures) {
        selectedFigures.clear();
        selectedFigures.addAll(figures);
    }
} 
