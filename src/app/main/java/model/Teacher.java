package app.main.java.model;

public class Teacher implements Printable{
    private final String name;

    public Teacher(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }

    @Override
    public String getInfo() {
        return "";
    }
}
