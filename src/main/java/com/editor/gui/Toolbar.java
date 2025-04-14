package com.editor.gui;

import com.editor.core.commands.AddTemplateCommand;
import com.editor.core.commands.CommandManager;
import com.editor.core.figures.Figure;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.util.ArrayList;
import java.util.List;

public class Toolbar extends JPanel {
    private final List<Figure> templates = new ArrayList<>();
    private final JPanel content;
    private final CommandManager commandManager;
    private WhiteBoard whiteBoard;

    public Toolbar(CommandManager commandManager) {
        this.commandManager = commandManager;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(100, 0));

        content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JScrollPane scroll = new JScrollPane(content);
        add(scroll, BorderLayout.CENTER);

        // ✅ Cas 5 : drop vers toolbar = ajout de template avec Command
        setTransferHandler(new TransferHandler() {
            @Override
            public boolean canImport(TransferSupport support) {
                return support.isDrop() && support.isDataFlavorSupported(FigureTransfer.FIGURE_FLAVOR);
            }

            @Override
            public boolean importData(TransferSupport support) {
                try {
                    Figure figure = (Figure) support.getTransferable()
                            .getTransferData(FigureTransfer.FIGURE_FLAVOR);

                    Figure clone = figure.clone();
                    clone.setX(0);
                    clone.setY(0);

                    commandManager.executeCommand(new AddTemplateCommand(Toolbar.this, clone));
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
    }

    public void addTemplate(Figure f) {
        templates.add(f);
        FigurePreviewButton button = new FigurePreviewButton(f, this);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(button);
        content.add(Box.createVerticalStrut(10));
        revalidate();
        repaint();
    }

    public void addTemplateAt(Figure f, int index) {
        templates.add(index, f);
        FigurePreviewButton button = new FigurePreviewButton(f, this);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(button, index * 2);
        content.add(Box.createVerticalStrut(10), index * 2 + 1);
        revalidate();
        repaint();
    }

    public int removeTemplate(Figure f) {
        int index = templates.indexOf(f);
        if (index >= 0) {
            templates.remove(index);
            content.remove(index * 2); // bouton
            content.remove(index * 2); // espace
            revalidate();
            repaint();
        }
        return index;
    }

    public List<Figure> getTemplates() {
        return templates;
    }

    public void clearTemplates() {
        templates.clear();
        content.removeAll();
        revalidate();
        repaint();
    }

    public void setWhiteBoard(WhiteBoard whiteBoard) {
        this.whiteBoard = whiteBoard;
    }

}
