package com.editor.core.figures;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.Serializable;

public class RectangleFigure implements Figure, Serializable {

    private int x, y, width, height;
    private int borderRadius = 0;
    private double rotation = 0.0;
    private Color color;

    public RectangleFigure(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        Shape shape = new RoundRectangle2D.Float(x, y, width, height, borderRadius, borderRadius);
        g2.rotate(Math.toRadians(rotation), x + width / 2.0, y + height / 2.0);

        g2.setColor(color);
        g2.fill(shape);
        g2.setColor(Color.BLACK);
        g2.draw(shape);
        g2.dispose();
    }

    @Override public void setX(int x) { this.x = x; }
    @Override public void setY(int y) { this.y = y; }
    @Override public int getX() { return x; }
    @Override public int getY() { return y; }

    @Override public void translate(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    @Override public void setRotation(double angle) { this.rotation = angle; }
    @Override public double getRotation() { return rotation; }

    @Override public void setRotationCenter(int x, int y) {} // inutile
    @Override public int getRotationCenterX() { return x + width / 2; }
    @Override public int getRotationCenterY() { return y + height / 2; }

    @Override public Color getColor() { return color; }
    @Override public void setColor(Color color) { this.color = color; }

    public void setBorderRadius(int r) { this.borderRadius = r; }
    public int getBorderRadius() { return borderRadius; }

    @Override public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    @Override public Figure clone() {
        RectangleFigure clone = new RectangleFigure(x, y, width, height, color);
        clone.setBorderRadius(borderRadius);
        clone.setRotation(rotation);
        return clone;
    }
}
