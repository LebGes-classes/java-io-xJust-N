package com.journal_app.handlers.object;

import com.journal_app.handlers.InputHandler;
import com.journal_app.model.Nameable;
import com.journal_app.model.Printable;
import com.journal_app.ui.Ui;

import java.util.Map;
import java.util.NoSuchElementException;

public abstract class ObjectInputHandler<T extends Printable & Nameable>  extends InputHandler {
    protected Map<String, T> mapping;
    protected Ui ui = new Ui();

    ObjectInputHandler(Map<String, T> mapping) {
        this.mapping = mapping;
        ui = new Ui();
    }

    @Override
    public void handle(String command) {
        switch (command) {
            case "1" -> infoMenu();
            case "2" -> newObjectMenu();
            case "3" -> deleteObjectMenu();
        }
    }

    abstract void newObjectMenu();

    private void deleteObjectMenu(){
        deleteByName(ui.read());
    };

    @Override
    public String getInfo() {
        return "1) Показать информацию\n" +
                "2) Добавить\n" +
                "3) Удалить\n";
    }

    private void infoMenu() {
        ui.clearConsole();
        ui.print("1) Показать обо всех\n" +
                "2) Показать об одном\n"
        );

        switch (ui.read()) {
            case "1" -> mapping.values().forEach(s -> ui.print(s));
            case "2" -> {
                ui.print("Введите имя");
                ui.print(getObjectByName(ui.read()).getInfo());
            }
        }
        ui.waitForInput();
    }

    protected T getObjectByName(String name) throws NoSuchElementException {
        if (!mapping.containsKey(name)) throw new NoSuchElementException(name + " не найден");
        return mapping.get(name);
    }
    protected void deleteByName(String name){
        if (!mapping.containsKey(name)) throw new NoSuchElementException(name + " не найден");
        mapping.remove(name);
    }
    protected void add(T obj){
        mapping.put(obj.getName(), obj);
    }
}
