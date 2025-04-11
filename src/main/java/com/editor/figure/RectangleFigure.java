package com.editor.figure;

import java.awt.*;

public class RectangleFigure implements Figure {
    int width, height, borderRadius;
    int x, y;
    double rotation;
    Point center;
    Color color;

    public RectangleFigure(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.borderRadius = 0; // Default border radius
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRoundRect(x, y, width, height, borderRadius, borderRadius);
    }

    @Override
    public void translate(int dx, int dy) {
        this.x += dx;
        this.y += dy;
        if (center != null) {
            center.translate(dx, dy);
        }
    }

    @Override
    public void resize(double factor) {
        width = (int) (width * factor);
        height = (int) (height * factor);
        if (center != null) {
            center.x = (int) (center.x * factor);
            center.y = (int) (center.y * factor);
        }
    }

    @Override
    public Figure clone() {
        RectangleFigure copy = new RectangleFigure(x, y, width, height, color);
        copy.rotation = this.rotation;
        copy.borderRadius = this.borderRadius;
        if (this.center != null)
            copy.center = new Point(this.center); // Copie défensive
        return copy;
    }

    @Override
    public boolean contains(Point p) {
        Rectangle bounds = new Rectangle(x, y, width, height);
        return bounds.contains(p);
    }

}