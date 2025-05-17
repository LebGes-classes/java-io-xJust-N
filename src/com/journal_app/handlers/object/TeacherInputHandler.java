package com.journal_app.handlers.object;
import com.journal_app.model.Teacher;

import java.io.IOException;
import java.util.Map;

public class TeacherInputHandler extends ObjectInputHandler<Teacher> {
    public TeacherInputHandler(Map<String, Teacher> mapping){
        super(mapping);
    }

    @Override
    public void handle(String command) throws IOException {
        super.handle(command);
    }

    @Override
    void newObjectMenu() {
        String name = ui.read("Введите имя");
        add(new Teacher(name));
    }

    @Override
    public String getInfo() {
        return super.getInfo();
    }
}
