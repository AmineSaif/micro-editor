package com.editor.core.commands;

import com.editor.gui.Toolbar;
import com.editor.core.figures.Figure;

/**
 * Command that removes a template from the toolbar and supports undo.
 */
public class RemoveTemplateCommand implements Command {

    private final Toolbar toolbar;
    private final Figure template;
    private int index = -1;

    public RemoveTemplateCommand(Toolbar toolbar, Figure template) {
        this.toolbar = toolbar;
        this.template = template;
    }

    @Override
    public void execute() {
        index = toolbar.removeTemplate(template); // returns previous index if needed for undo
    }

    @Override
    public void undo() {
        if (index >= 0) {
            toolbar.addTemplateAt(template, index);
        } else {
            toolbar.addTemplate(template);
        }
    }
}
