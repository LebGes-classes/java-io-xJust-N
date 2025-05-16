package com.journal_app.handlers;

import com.journal_app.model.Printable;

import java.io.IOException;

public abstract class InputHandler implements Printable {
    private InputHandler nextHandler = this;

    public abstract void handle(String command) throws IOException;

    public InputHandler getNextHandler() {
        return nextHandler;
    }

    protected void setNextHandler(InputHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

}
