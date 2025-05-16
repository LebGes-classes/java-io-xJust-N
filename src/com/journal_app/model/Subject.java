package com.journal_app.model;

public class Subject implements Printable, Nameable{
    private String name;
    private String teacherName;

    public Subject(String name){
        this.name = name;
    }

    public Subject(String name, String teacherName){
        this.name = name;
        this.teacherName = teacherName;
    }

    @Override
    public void setName(String s) {
        this.name = s;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }


    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String getInfo() {
        return "Название: " + name + "\n" +
                    "\tПреподаватель: " + teacherName + "\n";
    }
}
