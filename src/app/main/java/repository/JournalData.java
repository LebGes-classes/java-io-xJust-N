package app.main.java.repository;

import app.main.java.model.Student;
import app.main.java.model.Subject;
import app.main.java.model.Teacher;

import java.util.HashMap;
import java.util.Map;

public class JournalData {
    private static JournalData instance;
    private final Map<String, Teacher> teachers;
    private final Map<String, Student> students;
    private final Map<String, Subject> subjects;


    JournalData(){
        instance = this;
        students = new HashMap<>();
        teachers = new HashMap<>();
        subjects = new HashMap<>();
    }
    JournalData(Map<String, Teacher> teachers, Map<String, Student> students, Map<String, Subject> subjects){
        this.teachers = teachers;
        this.students = students;
        this.subjects = subjects;
    }

    public static JournalData getInstance(){
        if(instance == null){
            new JournalData(loadTeachers());
        }
        return instance;
    }

    public JournalData load(DataLoader loadStrategy){
        return loadStrategy.load(this.getClass());
    }
}
