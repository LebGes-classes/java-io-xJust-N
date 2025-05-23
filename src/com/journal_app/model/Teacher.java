package com.journal_app.model;

import java.io.Serializable;

public class Teacher implements Printable, Nameable, Serializable {
    private String name;

    public Teacher(String name){
        this.name = name;
    }

    @Override
    public void setName(String s) {
        this.name = s;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getInfo() {
        return "Имя: " + name + "\n";
    }
}
