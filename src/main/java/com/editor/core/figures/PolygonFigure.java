package com.editor.core.figures;

import java.awt.*;
import java.io.Serializable;

public class PolygonFigure implements Figure, Serializable {

    private int x, y; // centre
    private int sides;
    private int sideLength;
    private double rotation = 0.0;
    private Color color;

    public PolygonFigure(int x, int y, int sideLength, int sides, Color color) {
        this.x = x;
        this.y = y;
        this.sideLength = sideLength;
        this.sides = sides;
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        double radius = sideLength / (2 * Math.sin(Math.PI / sides));
        Polygon poly = new Polygon();

        for (int i = 0; i < sides; i++) {
            double angle = 2 * Math.PI * i / sides - Math.PI / 2;
            int px = x + (int)(radius * Math.cos(angle));
            int py = y + (int)(radius * Math.sin(angle));
            poly.addPoint(px, py);
        }

        Graphics2D g2 = (Graphics2D) g.create();
        g2.rotate(Math.toRadians(rotation), x, y);
        g2.setColor(color);
        g2.fillPolygon(poly);
        g2.setColor(Color.BLACK);
        g2.drawPolygon(poly);
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

    @Override public void setRotationCenter(int x, int y) {} // inutile ici
    @Override public int getRotationCenterX() { return x; }
    @Override public int getRotationCenterY() { return y; }

    @Override public Color getColor() { return color; }
    @Override public void setColor(Color color) { this.color = color; }

    @Override public Rectangle getBounds() {
        double radius = sideLength / (2 * Math.sin(Math.PI / sides));
        return new Rectangle((int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius));
    }

    @Override public Figure clone() {
        PolygonFigure clone = new PolygonFigure(x, y, sideLength, sides, color);
        clone.setRotation(rotation);
        return clone;
    }

    public int getSideLength() { return sideLength; }
    public void setSideLength(int s) { this.sideLength = s; }

    public int getSides() { return sides; }
    public void setSides(int s) { this.sides = s; }
}
