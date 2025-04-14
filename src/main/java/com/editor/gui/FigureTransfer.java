package com.editor.gui;

import com.editor.core.figures.Figure;

import java.awt.datatransfer.*;

public class FigureTransfer implements Transferable {
    public static final DataFlavor FIGURE_FLAVOR = new DataFlavor(Figure.class, "Figure");
    private final Figure figure;

    public FigureTransfer(Figure figure) {
        this.figure = figure;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{FIGURE_FLAVOR};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return FIGURE_FLAVOR.equals(flavor);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) {
        return figure;
    }
}
