package com.journal_app.repository;

import com.journal_app.model.Student;
import com.journal_app.model.Subject;
import com.journal_app.model.Teacher;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class JournalData implements Serializable {
    private static JournalData instance = new JournalData();
    private Map<String, Teacher> teachers;
    private Map<String, Student> students;
    private Map<String, Subject> subjects;


    private JournalData(){
        students = new HashMap<>();
        teachers = new HashMap<>();
        subjects = new HashMap<>();
    }

    public static JournalData getInstance() {
        if(instance == null)
            instance = new JournalData();
        return instance;
    }

    public static void load(DataLoader loadStrategy) throws IOException {
        loadStrategy.load();
    }
    public static void save(DataSaver saveStrategy) throws IOException {
        saveStrategy.save(instance);
    }

    public Map<String, Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Map<String, Teacher> teachers) {
        this.teachers = teachers;
    }

    public Map<String, Student> getStudents() {
        return students;
    }

    public void setStudents(Map<String, Student> students) {
        this.students = students;
    }

    public void setAll(JournalData data){
        setTeachers(data.getTeachers());
        setSubjects(data.getSubjects());
        setStudents(data.getStudents());
    }

    public Map<String, Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Map<String, Subject> subjects) {
        this.subjects = subjects;
    }
}
