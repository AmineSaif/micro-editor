package com.editor.core.commands;

/**
 * Interface pour toute commande Undo/Redo.
 */
public interface Command {
    void execute();
    void undo();
}
