package app.main.java.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Student extends Profile implements Printable{
    private String group;
    private final Map<Subject, List<Integer>> marks;

    Student(String name, int age, boolean sex, String group){
        super(name, age, sex);
        setGroup(group);
        marks = new HashMap<>();
    }

    public void addMark(Subject sub, int mark){
        if(!marks.containsKey(sub))
            marks.put(sub, new LinkedList<>());

        marks.get(sub).add(mark);
    }

    public void replaceLastMark(Subject sub, int mark){
        List<Integer> markList = marks.get(sub);
        markList.removeLast();
        markList.add(mark);
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String getInfo(){
        String info = super.getInfo() + "\n" +
                "Группа: " + getGroup() + "\n" +
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
