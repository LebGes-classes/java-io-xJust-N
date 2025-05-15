package app.main.java.repository;


import java.io.IOException;

public interface DataLoader {
    JournalData load() throws IOException;
}
