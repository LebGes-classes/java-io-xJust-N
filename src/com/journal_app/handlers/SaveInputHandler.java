package com.journal_app.handlers;

import com.journal_app.repository.JournalData;
import com.journal_app.repository.savers.BinarySaver;
import com.journal_app.repository.savers.JsonSaver;

import java.io.IOException;

public class SaveInputHandler extends InputHandler{
    public SaveInputHandler() {}

    @Override
    public void handle(String command) throws IOException {
        switch (command) {
            case ("1"):
                JournalData.save(new JsonSaver());
                break;
            case("2"):
                JournalData.save(new BinarySaver());
        }
    }


    @Override
    public String getInfo() {
        return "Выберете куда вы хотите сохранить:\n" +
                "1) .json\n" +
                "2) .bin";
    }
}
