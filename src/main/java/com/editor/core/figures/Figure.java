package com.editor.core.figures;

import java.awt.*;

public interface Figure extends Cloneable {

    void draw(Graphics g);

    Rectangle getBounds();

    void setX(int x);
    void setY(int y);
    int getX();
    int getY();

    void translate(int dx, int dy);

    void setRotation(double angle);
    double getRotation();

    void setRotationCenter(int x, int y);
    int getRotationCenterX();
    int getRotationCenterY();

    Color getColor();
    void setColor(Color color);

    Figure clone();
}
