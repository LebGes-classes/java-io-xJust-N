package app.main.java.model;

import app.main.java.repository.JournalData;

import java.util.*;

public class Student implements Printable, Nameable{
    private String name;
    private final Map<Subject, List<Integer>> marks;

    public Student(String name){
        marks = new HashMap<>();
    }
    public Student(String name, Map<Subject, List<Integer>> marks){
        this.marks = marks;
    }
    public Student(String name, String nameSubject, Integer... subjectMarks){
        setName(name);
        this.marks = new HashMap<>();
        Subject subject = JournalData.getInstance().getSubjects().get(nameSubject);
        marks.put(subject, Arrays.stream(subjectMarks).toList());
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


    @Override
    public void setName(String s) {
        this.name = s;
    }

    @Override
    public String getName() {
        return name;
    }
}
