package app.main.java.repository;

public interface DataSaver {
    <T> void save(T data);
}
