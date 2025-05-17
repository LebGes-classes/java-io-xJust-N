package com.journal_app.handlers.object;

import com.journal_app.model.Student;

import java.io.IOException;
import java.util.Map;

public class StudentInputHandler extends ObjectInputHandler<Student> {
    public StudentInputHandler(Map<String, Student> mapping){
        super(mapping);
    }

    @Override
    public void handle(String command) throws IOException {
        super.handle(command);

        switch (command){
            case "4" -> changeMarkMenu();
        }
    }

    @Override
    void newObjectMenu() {
        String name = ui.read("Введите имя");
        add(new Student(name));
    }

    private void changeMarkMenu() throws IOException {
        ui.print("Введите имя");
        Student student = getObjectByName(ui.read());
        ui.print("Введите оценку");
        int mark = parseInt(ui.read());
        if(mark < 0 || mark > 100)
            throw new IOException("Неверный формат ввода числа");
        student.setAverageMark(mark);
    }

    @Override
    public String getInfo() {
        return super.getInfo() +
                "4) Поменять оценку\n";
    }
}
