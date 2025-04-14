package com.editor.gui;

import com.editor.core.commands.CommandManager;
import com.editor.core.commands.RemoveFigureCommand;
import com.editor.core.commands.RemoveTemplateCommand;
import com.editor.core.figures.Figure;

import javax.swing.*;
import java.awt.*;

public class TrashZone extends JPanel {

    private final CommandManager commandManager;
    private final WhiteBoard whiteBoard;
    private final Toolbar toolbar;

    public TrashZone(CommandManager commandManager, WhiteBoard whiteBoard, Toolbar toolbar) {
        this.commandManager = commandManager;
        this.whiteBoard = whiteBoard;
        this.toolbar = toolbar;

        setBackground(new Color(255, 100, 100));
        setBorder(BorderFactory.createTitledBorder("🗑 Trash"));
        setPreferredSize(new Dimension(150, 50));
        JLabel label = new JLabel("Drag here to delete");
        label.setFont(new Font("Arial", Font.PLAIN, 10));
        add(label);

        setTransferHandler(new TransferHandler() {
            @Override
            public boolean canImport(TransferSupport support) {
                return support.isDataFlavorSupported(FigureTransfer.FIGURE_FLAVOR);
            }

            @Override
            public boolean importData(TransferSupport support) {
                try {
                    Figure figure = (Figure) support.getTransferable()
                            .getTransferData(FigureTransfer.FIGURE_FLAVOR);

                    // Decide origin of figure
                    if (whiteBoard.getFigures().contains(figure)) {
                        commandManager.executeCommand(new RemoveFigureCommand(whiteBoard, figure));
                    } else if (toolbar.getTemplates().contains(figure)) {
                        commandManager.executeCommand(new RemoveTemplateCommand(toolbar, figure));
                    }

                    return true;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return false;
                }
            }
        });
    }
}
