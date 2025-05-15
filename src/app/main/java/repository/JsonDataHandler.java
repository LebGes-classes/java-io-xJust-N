package app.main.java.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class JsonDataHandler implements DataLoader, DataSaver{
    private final String FILE_NAME = FileStringsEnum.JSON_FILE_NAME.getValue();
    private final Gson gson;
    private final File file;

    JsonDataHandler(){
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        file = new File(FILE_NAME);
    }

    @Override
    public <T> void save(T data) {
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Невозможно создать файл: " + e);
            }
        }
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JournalData load() {
        if(!file.exists())
            return null;

        try (FileReader reader = new FileReader(FILE_NAME)) {
            return gson.fromJson(reader, JournalData.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}