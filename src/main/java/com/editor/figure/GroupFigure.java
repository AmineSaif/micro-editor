package com.editor.figure;

import java.awt.*;
import java.util.*;
import java.util.List;

public class GroupFigure implements Figure {
    List<Figure> children = new ArrayList<>();

    public void add(Figure f) { children.add(f); }
    public void remove(Figure f) { children.remove(f); }

    public void draw(Graphics g) {
        for (Figure f : children) f.draw(g);
    }
    public void translate(int dx, int dy) {
        for (Figure f : children) f.translate(dx, dy);
    }
    public void resize(double factor) {
        for (Figure f : children) f.resize(factor);
    }
    public Figure clone() {
        GroupFigure copy = new GroupFigure();
        for (Figure f : children) copy.add(f.clone());
        return copy;
    }
    public boolean contains(Point p) {
        for (Figure f : children) if (f.contains(p)) return true;
        return false;
    }
}