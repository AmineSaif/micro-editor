package com.editor.gui;

import com.editor.core.commands.*;
import com.editor.core.figures.Figure;
import com.editor.core.figures.Group;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class WhiteBoard extends JPanel {

    private final List<Figure> figures = new ArrayList<>();
    private final List<Figure> selection = new ArrayList<>();
    private final CommandManager commandManager;

    private Point selectionStart = null;
    private Figure dragged = null;

    public WhiteBoard(CommandManager commandManager) {
        this.commandManager = commandManager;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(800, 600));

        setTransferHandler(new TransferHandler() {
            @Override
            public boolean canImport(TransferSupport support) {
                return support.isDataFlavorSupported(FigureTransfer.FIGURE_FLAVOR);
            }

            @Override
            public boolean importData(TransferSupport support) {
                try {
                    Figure fig = (Figure) support.getTransferable()
                            .getTransferData(FigureTransfer.FIGURE_FLAVOR);

                    Point drop = support.getDropLocation().getDropPoint();
                    Rectangle b = fig.getBounds();

                    Figure copy = fig.clone();
                    copy.setX(drop.x - b.width / 2);
                    copy.setY(drop.y - b.height / 2);

                    commandManager.executeCommand(new AddFigureCommand(WhiteBoard.this, copy));
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }

            @Override
            protected Transferable createTransferable(JComponent c) {
                if (!selection.isEmpty()) {
                    dragged = selection.get(0);
                    return new FigureTransfer(dragged); // ✅ on envoie l’original
                }
                return null;
            }

            @Override
            public int getSourceActions(JComponent c) {
                return MOVE;
            }

            @Override
            protected void exportDone(JComponent c, Transferable data, int action) {
                if (action == MOVE && dragged != null) {
                    removeFigure(dragged);
                    dragged = null;
                }
            }
        });

        // Souris
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectionStart = e.getPoint();
                Figure clicked = findFigureAt(selectionStart);

                if (!e.isControlDown()) {
                    selection.clear();
                }

                if (clicked != null) {
                    if (selection.contains(clicked)) {
                        selection.remove(clicked);
                    } else {
                        selection.add(clicked);
                    }

                    if (SwingUtilities.isRightMouseButton(e)) {
                        showContextMenu(WhiteBoard.this, e.getX(), e.getY(), clicked);
                    }
                }

                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                selectionStart = null;
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (!selection.isEmpty()) {
                    getTransferHandler().exportAsDrag(WhiteBoard.this, e, TransferHandler.MOVE);
                }

                if (selectionStart != null) {
                    Rectangle box = new Rectangle(
                            Math.min(selectionStart.x, e.getX()),
                            Math.min(selectionStart.y, e.getY()),
                            Math.abs(e.getX() - selectionStart.x),
                            Math.abs(e.getY() - selectionStart.y));

                    selection.clear();
                    for (Figure f : figures) {
                        if (box.contains(f.getBounds())) {
                            selection.add(f);
                        }
                    }
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Figure f : figures) {
            f.draw(g);
        }

        g.setColor(Color.RED);
        for (Figure f : selection) {
            Rectangle r = f.getBounds();
            g.drawRect(r.x - 2, r.y - 2, r.width + 4, r.height + 4);
        }
    }

    public void addFigure(Figure f) {
        figures.add(f);
        repaint();
    }

    public void removeFigure(Figure f) {
        figures.remove(f);
        selection.remove(f);
        repaint();
    }

    public List<Figure> getSelection() {
        return selection;
    }

    public void clearSelection() {
        selection.clear();
        repaint();
    }

    // Removed duplicate method definition

    private Figure findFigureAt(Point p) {
        for (int i = figures.size() - 1; i >= 0; i--) {
            if (figures.get(i).getBounds().contains(p)) {
                return figures.get(i);
            }
        }
        return null;
    }

    private void showContextMenu(Component c, int x, int y, Figure clicked) {
        JPopupMenu menu = new JPopupMenu();

        JMenuItem edit = new JMenuItem("✏️ Edit");
        edit.addActionListener(e -> {
            new FigureEditDialog((JFrame) SwingUtilities.getWindowAncestor(this), clicked, commandManager)
                    .setVisible(true);
            repaint();
        });

        JMenuItem delete = new JMenuItem("❌ Delete");
        delete.addActionListener(e -> {
            commandManager.executeCommand(new RemoveFigureCommand(this, clicked));
        });

        if (selection.size() >= 2) {
            JMenuItem group = new JMenuItem("🧩 Group");
            group.addActionListener(e -> {
                commandManager.executeCommand(new GroupCommand(this, new ArrayList<>(selection)));
                clearSelection();
            });
            menu.add(group);
        }

        if (clicked instanceof Group) {
            JMenuItem degroup = new JMenuItem("🧷 Degroup");
            degroup.addActionListener(e -> {
                commandManager.executeCommand(new DegroupCommand(this, (Group) clicked));
            });
            menu.add(degroup);
        }

        menu.add(edit);
        menu.add(delete);
        menu.show(c, x, y);
    }

    public void setFigures(List<Figure> figures) {
        this.figures.clear();
        this.figures.addAll(figures);
        this.selection.clear();
        repaint();
    }
    
    public List<Figure> getFigures() {
        return figures;
    }
    
}
