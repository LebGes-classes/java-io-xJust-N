package app.main.java.repository;

import app.main.java.model.Student;
import app.main.java.model.Subject;
import app.main.java.model.Teacher;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JournalData {
    private final Map<String, Teacher> teachers;
    private final Map<String, Student> students;
    private final Map<String, Subject> subjects;


    private JournalData(){
        students = new HashMap<>();
        teachers = new HashMap<>();
        subjects = new HashMap<>();
    }
    JournalData(Map<String, Teacher> teachers, Map<String, Subject> subjects, Map<String, Student> students){
        this.teachers = teachers;
        this.students = students;
        this.subjects = subjects;
    }

    public JournalData load(DataLoader loadStrategy) throws IOException {
        return loadStrategy.load();
    }
}
