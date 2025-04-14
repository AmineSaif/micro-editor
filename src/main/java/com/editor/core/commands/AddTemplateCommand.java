package com.editor.core.commands;

import com.editor.core.figures.Figure;
import com.editor.gui.Toolbar;

/**
 * Commande pour ajouter un modèle (template) dans la Toolbar avec support Undo/Redo.
 */
public class AddTemplateCommand implements Command {

    private final Toolbar toolbar;
    private final Figure template;

    public AddTemplateCommand(Toolbar toolbar, Figure template) {
        this.toolbar = toolbar;
        this.template = template;
    }

    @Override
    public void execute() {
        toolbar.addTemplate(template);
    }

    @Override
    public void undo() {
        toolbar.removeTemplate(template);
    }
}
