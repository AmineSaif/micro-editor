package com.editor.command;

public interface Command {
    void execute();
    void undo();
}