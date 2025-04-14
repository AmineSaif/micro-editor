package com.editor.gui;

import com.editor.core.figures.Figure;

import javax.swing.*;
import java.awt.datatransfer.Transferable;

/**
 * Handler pour lancer un drag & drop depuis une FigurePreviewButton.
 */
public class FigureTransferHandler {

    public static class ValueExportTransferHandler extends TransferHandler {
        private final Figure figure;

        public ValueExportTransferHandler(Figure figure) {
            this.figure = figure;
        }

        @Override
        protected Transferable createTransferable(JComponent c) {
            return new FigureTransfer(figure); // contenu transféré
        }

        @Override
        public int getSourceActions(JComponent c) {
            return COPY_OR_MOVE;
        }
    }
}
