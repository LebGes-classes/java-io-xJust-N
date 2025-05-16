package app.main.java.handlers;

import app.main.java.model.Printable;

public abstract class InputHandler implements Printable {
    private InputHandler nextHandler = this;

    public abstract void handle(String command);

    public InputHandler getNextHandler() {
        return nextHandler;
    }

    protected void setNextHandler(InputHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

}
