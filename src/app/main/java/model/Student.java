package app.main.java.model;

import java.util.*;

public class Student implements Printable, Nameable{
    private String name;
    private final Map<String, List<Integer>> marks;

    public Student(String name){
        marks = new HashMap<>();
    }
    public Student(String name, String subjectName, Integer... subjectMarks){
        setName(name);
        this.marks = new HashMap<>();
        marks.put(subjectName, Arrays.stream(subjectMarks).toList());
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
