package com.journal_app.repository;

import java.io.IOException;

public interface DataSaver {    // интерфейс для стратегии сохранения
    void save(JournalData data) throws IOException;
}
