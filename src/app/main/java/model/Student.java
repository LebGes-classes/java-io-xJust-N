package app.main.java.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Student implements Printable{
    private String name;
    private final Map<Subject, List<Integer>> marks;

    public Student(String name){
        marks = new HashMap<>();
    }
    public Student(String name, Map<Subject, List<Integer>> marks){
        this.marks = marks;
    }

    public void addMark(Subject sub, List<Integer> listOfMarks){
        if(!marks.containsKey(sub))
            marks.put(sub, new LinkedList<>());

        marks.get(sub).addAll(listOfMarks);
    }

    public void replaceLastMark(Subject sub, int mark){
        List<Integer> markList = marks.get(sub);
        markList.removeLast();
        markList.add(mark);
    }

    @Override
    public String getInfo(){
        String info = "Имя: " + name + "\n" +
                "Оценки: \n";

        for(Subject sub : marks.keySet()){
           info += sub.getName() + ": ";
           for(int i : marks.get(sub)){
               info += i + " ";
           }
           info += "\n";
        }

        return info;
    }




}
