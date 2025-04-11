package com.editor.core;

import com.editor.figure.Figure;

import java.util.ArrayList;
import java.util.List;

public class ToolBar {

    private final List<Figure> templates = new ArrayList<>();

    public void addTemplate(Figure f) {
        templates.add(f);
    }

    public Figure getClone(int index) {
        return templates.get(index).clone();
    }

    public int size() {
        return templates.size();
    }

    public Figure getTemplate(int index) {
        return templates.get(index);
    }

    public List<Figure> getAllTemplates() {
        return new ArrayList<>(templates);
    }

    public void clear() {
        templates.clear();
    }

    // ✅ Suppression d’un template à l’index donné
    public void removeTemplateAt(int index) {
        if (index >= 0 && index < templates.size()) {
            templates.remove(index);
        }
    }
}
