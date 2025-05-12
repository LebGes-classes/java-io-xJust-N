package app.main.java.model;

public class Subject implements Printable{
    private final String name;
    private Teacher teacher;

    Subject(String name){
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getName() {
        return name;
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
