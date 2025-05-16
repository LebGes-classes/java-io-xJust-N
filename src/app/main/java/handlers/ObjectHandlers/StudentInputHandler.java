package app.main.java.handlers.ObjectHandlers;

import app.main.java.handlers.InputHandler;
import app.main.java.model.Student;

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
    void deleteObjectMenu() {
        String name = ui.read("Введите имя");
        mapping.remove(st.getName());
    }

    @Override
    public String getInfo() {
        return super.getInfo();
    }
}
