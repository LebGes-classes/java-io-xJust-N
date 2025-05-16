package com.journal_app.handlers.object;

import com.journal_app.model.Student;

import java.util.Map;

public class StudentInputHandler extends ObjectInputHandler<Student> {
    public StudentInputHandler(Map<String, Student> mapping){
        super(mapping);
    }

    @Override
    public void handle(String command) {
        super.handle(command);
    }

    @Override
    void newObjectMenu() {
        String name = ui.read("Введите имя");
        mapping.put(name, new Student(name));
    }

    @Override
    public String getInfo() {
        return super.getInfo();
    }
}
