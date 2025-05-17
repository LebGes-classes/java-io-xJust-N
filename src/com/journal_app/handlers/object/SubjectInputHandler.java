package com.journal_app.handlers.object;
import com.journal_app.model.Subject;

import java.io.IOException;
import java.util.Map;

public class SubjectInputHandler extends ObjectInputHandler<Subject> {
    public SubjectInputHandler(Map<String, Subject> mapping){
        super(mapping);
    }

    @Override
    public void handle(String command) throws IOException {
        super.handle(command);
    }

    @Override
    void newObjectMenu() {
        String name = ui.read("Введите имя");
        add(new Subject(name));
    }

    @Override
    public String getInfo() {
        return super.getInfo();
    }
}
