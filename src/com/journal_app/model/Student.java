package com.journal_app.model;

public class Student implements Printable, Nameable{
    private String name;
    private int averageMark;

    public Student(String name){
        this(name, 0);
    }

    public Student(String name, Integer averageMark){
        this.name = name;
        this.averageMark = averageMark;

    }

    @Override
    public String getInfo(){
        return ("Имя: " + name + "\n" +
                    "\tСредний балл: " + averageMark) + "\t\n";
    }


    @Override
    public void setName(String s) {
        this.name = s;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setAverageMark(int averageMark) {
        this.averageMark = averageMark;
    }


}
