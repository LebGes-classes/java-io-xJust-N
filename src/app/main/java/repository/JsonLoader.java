package app.main.java.repository;

import app.main.java.repository.util.JsonDataHandler;

import java.io.File;
import java.io.IOException;

public class JsonLoader implements DataLoader{
    private final JsonDataHandler parser;

    public JsonLoader() {
        parser = new JsonDataHandler(new File(FileStringsEnum.JSON_FILE_NAME.getValue()));
    }

    @Override
    public JournalData load() throws IOException {
        return parser.loadFromJson(JournalData.class);
    }
}
