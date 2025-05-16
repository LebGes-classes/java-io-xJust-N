package app.main.java.model;

public class Subject implements Printable, Nameable{
    private String name;
    private String teacherName;

    Subject(String name){
        this.name = name;
    }
    Subject(String name, String teacherName){
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

    public String getTeacherName() {
        return teacherName;
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
        return "";
    }
}
