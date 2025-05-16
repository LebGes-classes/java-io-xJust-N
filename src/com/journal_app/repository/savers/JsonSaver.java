package com.journal_app.repository.savers;

import com.journal_app.repository.DataSaver;
import com.journal_app.repository.JournalData;
import com.journal_app.repository.util.JsonDataHandler;

import java.io.File;
import java.io.IOException;

import static com.journal_app.repository.FileStringsEnum.JSON_FILE_NAME;

public class JsonSaver implements DataSaver {
    @Override
    public void save(JournalData data) throws IOException {
        JsonDataHandler handler = new JsonDataHandler(new File(JSON_FILE_NAME.getValue()));
        handler.saveToJson(data);
    }
}
