package com.editor.figure;

import java.awt.*;

public class RegularPolygon implements Figure {

    int sides;
    int sideLength;
    int x, y;
    double rotation; // in radians
    Point center;
    Color color;

    public RegularPolygon(int x, int y, int sides, int sideLength, Color color) {
        this.x = x;
        this.y = y;
        this.sides = sides;
        this.sideLength = sideLength;
        this.rotation = 0;
        this.color = color;
        this.center = new Point(x, y);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(color);

        Polygon poly = generatePolygon();
        g2.fillPolygon(poly);

        g2.dispose();
    }

    private Polygon generatePolygon() {
        Polygon polygon = new Polygon();
        double angle = 2 * Math.PI / sides;
        int radius = (int)(sideLength / (2 * Math.sin(Math.PI / sides)));
        for (int i = 0; i < sides; i++) {
            double theta = rotation + i * angle;
            int px = x + (int)(radius * Math.cos(theta));
            int py = y + (int)(radius * Math.sin(theta));
            polygon.addPoint(px, py);
        }
        return polygon;
    }

    @Override
    public void translate(int dx, int dy) {
        x += dx;
        y += dy;
        if (center != null)
            center.translate(dx, dy);
    }

    @Override
    public void resize(double factor) {
        sideLength = (int) (sideLength * factor);
    }

    @Override
    public Figure clone() {
        RegularPolygon copy = new RegularPolygon(x, y, sides, sideLength, color);
        copy.rotation = this.rotation;
        if (this.center != null)
            copy.center = new Point(this.center);
        return copy;
    }

    public boolean contains(Point p) {
        return generatePolygon().contains(p);
    }
}
