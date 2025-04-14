package com.editor.core.figures;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Groupe composite de figures (Composite Pattern)
 */
public class Group implements Figure, Serializable {

    private final List<Figure> children = new ArrayList<>();

    public void add(Figure f) {
        children.add(f);
    }

    public void remove(Figure f) {
        children.remove(f);
    }

    public List<Figure> getChildren() {
        return children;
    }

    @Override
    public void draw(Graphics g) {
        for (Figure f : children) {
            f.draw(g);
        }
    }

    @Override
    public void translate(int dx, int dy) {
        for (Figure f : children) {
            f.translate(dx, dy);
        }
    }

    @Override
public void setRotation(double angle) {
    for (Figure f : children) {
        f.setRotation(angle);
        Rectangle b = f.getBounds();
        f.setRotationCenter(b.x + b.width / 2, b.y + b.height / 2); // 🔁 centrage local
    }
}


    @Override
    public double getRotation() {
        return children.isEmpty() ? 0 : children.get(0).getRotation();
    }

    @Override
    public void setRotationCenter(int x, int y) {
        for (Figure f : children) {
            f.setRotationCenter(x, y);
        }
    }

    @Override
    public int getRotationCenterX() {
        return children.isEmpty() ? 0 : children.get(0).getRotationCenterX();
    }

    @Override
    public int getRotationCenterY() {
        return children.isEmpty() ? 0 : children.get(0).getRotationCenterY();
    }

    @Override public void setX(int x) {
        int dx = x - getX();
        translate(dx, 0);
    }

    @Override public void setY(int y) {
        int dy = y - getY();
        translate(0, dy);
    }

    @Override public int getX() {
        return getBounds().x;
    }

    @Override public int getY() {
        return getBounds().y;
    }

    @Override
    public Color getColor() {
        return children.isEmpty() ? Color.BLACK : children.get(0).getColor();
    }

    @Override
    public void setColor(Color color) {
        for (Figure f : children) {
            f.setColor(color);
        }
    }

    @Override
    public Rectangle getBounds() {
        if (children.isEmpty()) return new Rectangle(0, 0, 0, 0);
        Rectangle bounds = children.get(0).getBounds();
        for (int i = 1; i < children.size(); i++) {
            bounds = bounds.union(children.get(i).getBounds());
        }
        return bounds;
    }

    @Override
    public Figure clone() {
        Group clone = new Group();
        for (Figure f : children) {
            clone.add(f.clone());
        }
        return clone;
    }
}
