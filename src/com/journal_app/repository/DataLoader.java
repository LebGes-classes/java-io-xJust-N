package com.journal_app.repository;


import java.io.IOException;

public interface DataLoader {   //Интерфейс для стратегии загрузки
    void load() throws IOException;
}

