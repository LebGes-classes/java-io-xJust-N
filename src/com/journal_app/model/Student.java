package com.journal_app.model;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Student implements Printable, Nameable{
    private String name;
    private final Map<String, List<Integer>> marks;

    public Student(String name){
        this.name = name;
        marks = new HashMap<>();
    }

    public void addMark(String subName, List<Integer> listOfMarks){
        if(!marks.containsKey(subName))
            marks.put(subName, new LinkedList<>());

        marks.get(subName).addAll(listOfMarks);
    }

    public void replaceLastMark(String subName, int mark){
        List<Integer> markList = marks.get(subName);
        markList.removeLast();
        markList.add(mark);
    }

    @Override
    public String getInfo(){
        String info =
                "Имя: " + name + "\n" +
                "Оценки: \n";

        for(String subName : marks.keySet()){
           info += subName + ": ";
           for(int i : marks.get(subName)){
               info += i + " ";
           }
           info += "\n";
        }

        return info;
    }


    @Override
    public void setName(String s) {
        this.name = s;
    }

    @Override
    public String getName() {
        return name;
    }
}
