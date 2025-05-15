package app.main.java.model;

public class Teacher implements Printable, Nameable{
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
        return "";
    }
}
