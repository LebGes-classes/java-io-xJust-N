package app.main.java.handlers;

import app.main.java.handlers.ObjectHandlers.StudentInputHandler;
import app.main.java.repository.JournalData;

public class MainMenuInputHandler extends InputHandler{
    public MainMenuInputHandler(){}

    @Override
    public void handle(String command) {
        switch (command){
            case "1" -> setNextHandler(new StudentInputHandler(JournalData.getInstance().getStudents()));
            case "2" -> setNextHandler(new TeacherInputHandler(JournalData.getInstance().getTeachers()));
            case "3" -> setNextHandler(new SubjectInputHandler(JournalData.getInstance().getSubjects()));
        }
    }

    @Override
    public String getInfo() {
        return "1) Ученики\n" +
                "2) Учителя\n" +
                "3) Предметы\n";
    }
}
