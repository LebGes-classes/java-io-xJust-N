package com.journal_app;

import com.journal_app.handlers.InputHandler;
import com.journal_app.handlers.LoadInputHandler;
import com.journal_app.handlers.MainMenuInputHandler;
import com.journal_app.handlers.SaveInputHandler;
import com.journal_app.repository.JournalData;
import com.journal_app.repository.savers.JsonSaver;
import com.journal_app.ui.Ui;

import java.io.IOException;

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
        try {
            handler.handle(ui.read());
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить файл:\n" + e + "\n:(");
        }
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

            ui.print("back) Вернуться в главное меню " + "\n" +
                    "save) Сохранить" + "\n" +
                    "exit) Закрыть приложение" + "\n"
            );

            String command = ui.read();
            switch (command){
                case "back" -> handler = new MainMenuInputHandler();
                case "save" -> save();
                case "exit" -> {
                    isRunning = false;
                    exit();
                }
                default -> {
                    try {
                        handler.handle(command);
                    } catch (IOException e) {
                        ui.printErrorMessage("Ошибка ввода: " + e);
                    }
                }
            }
            handler = handler.getNextHandler();
        }
    }

    private void save() {
        handler = new SaveInputHandler();
        ui.printMenu(handler);
        try {
            handler.handle(ui.read());
        } catch (IOException e) {
            throw new RuntimeException("Не удалось сохранить файл:\n" + e + "\n:(");
        }
    }
    private void exit() {
        save();
    }
}
