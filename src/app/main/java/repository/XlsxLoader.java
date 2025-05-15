package app.main.java.repository;

import app.main.java.model.Nameable;
import app.main.java.model.Student;
import app.main.java.model.Subject;
import app.main.java.model.Teacher;
import app.main.java.repository.util.ExcelParser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static app.main.java.repository.FileStringsEnum.*;

public class XlsxLoader implements DataLoader {
    private final ExcelParser parser;

    XlsxLoader(){
        parser = new ExcelParser(new File(XLSX_FILE_NAME.getValue()));
    }

    @Override
    public void load() throws IOException {
        JournalData instance = JournalData.getInstance();

        instance.setTeachers(loadAndToMap(TEACHERS.getValue(), Teacher.class));
        instance.setSubjects(loadAndToMap(SUBJECTS.getValue(), Subject.class));
        instance.setStudents(loadAndToMap(STUDENTS.getValue(), Student.class));
    }

    private <T extends Nameable> Map<String, T> loadAndToMap(String sheetName, Class<T> tClass) throws IOException {
        Map<String, T> map = new HashMap<>();
        List<T> list = parser.parseObjectsFromSheetName(sheetName, tClass);
        for(T tObject : list){
            map.put(tObject.getName(), tObject);
        }
        return map;
    }
}
