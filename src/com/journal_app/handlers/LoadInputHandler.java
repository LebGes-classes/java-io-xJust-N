package com.journal_app.handlers;

import com.journal_app.repository.JournalData;
import com.journal_app.repository.loaders.JsonLoader;
import com.journal_app.repository.loaders.XlsxLoader;

import java.io.IOException;

public class LoadInputHandler extends InputHandler {
    public LoadInputHandler() {}

    @Override
    public void handle(String command) throws IOException {
            switch (command) {
                case ("1"):
                    JournalData.load(new XlsxLoader());
                    break;
                case ("2"):
                    JournalData.load(new JsonLoader());
                    break;
            }
    }


    @Override
    public String getInfo() {
        return "Выберете откуда вы хотите загрузить:\n" +
                "1) .xlsx\n" +
                "2) .json";
    }
}
