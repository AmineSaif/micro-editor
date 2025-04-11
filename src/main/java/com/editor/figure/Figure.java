package com.editor.figure;

import java.awt.*;

public interface Figure {
    void draw(Graphics g);
    void translate(int dx, int dy);
    void resize(double factor);
    boolean contains(Point p);
    Figure clone();
}

