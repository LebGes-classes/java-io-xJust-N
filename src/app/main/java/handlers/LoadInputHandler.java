package app.main.java.handlers;

import app.main.java.repository.JournalData;
import app.main.java.repository.JsonLoader;
import app.main.java.repository.XlsxLoader;

import java.io.IOException;

public class LoadInputHandler extends InputHandler {
    public LoadInputHandler() {}

    @Override
    public void handle(String command) {
        try {
            switch (command) {
                case ("1"):
                    JournalData.load(new XlsxLoader());
                    break;
                case ("2"):
                    JournalData.load(new JsonLoader());
                    break;
            }
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить файл:\n" + e);
        }
    }


    @Override
    public String getInfo() {
        return "Выберете откуда вы хотите загрузить:\n" +
                "1) .xlsx\n" +
                "2) .json";
    }
}
