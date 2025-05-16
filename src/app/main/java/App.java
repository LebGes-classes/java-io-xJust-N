package app.main.java;

import app.main.java.handlers.InputHandler;
import app.main.java.handlers.LoadInputHandler;
import app.main.java.handlers.MainMenuInputHandler;
import app.main.java.ui.Ui;

public class App {
    private boolean isRunning;
    private InputHandler handler;
    private final Ui ui;
    App(){
        ui = new Ui();
    }

    private void load(){
        handler = new LoadInputHandler();
        ui.printMenu(handler);
        handler.handle(ui.read());
    }

    public void start(){
        load();
        isRunning = true;
        run();
    }

    private void run(){
        handler = new MainMenuInputHandler();
        while(isRunning){
            ui.printMenu(handler);
            String command = ui.read();
            switch (command){
                case "back" -> handler = new MainMenuInputHandler();
                case "exit" -> {
                    isRunning = false;
                    exit();
                }
                default -> handler.handle(command);
            }
            handler = handler.getNextHandler();
        }
    }
    private void exit(){}

}
