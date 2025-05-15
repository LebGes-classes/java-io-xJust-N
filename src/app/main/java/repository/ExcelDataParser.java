package app.main.java.repository;

import app.main.java.model.Student;
import app.main.java.model.Subject;
import app.main.java.model.Teacher;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class ExcelDataParser implements DataLoader {
    private final Map<String, Teacher> teachers;
    private final Map<String, Student> students;
    private final Map<String, Subject> subjects;

    private static final String FILE_PATH = FileStringsEnum.XLSX_FILE_NAME.getValue();
    private final File file;

    public ExcelDataParser() {
        teachers = new HashMap<>();
        students = new HashMap<>();
        subjects = new HashMap<>();
        file = new File(FILE_PATH);
    }

    @Override
    public JournalData load() {
        if (!file.exists())
            return null;
        loadTeachers();
        loadSubjects();
        loadStudents();
        return new JournalData(teachers, students, subjects);
    }

    private <T> List<T> loadListOfObjectsFromSheetName(Class<T> tClass, String sheetName){
        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            if(sheet == null)
                throw new IOException("Страница в " + FILE_PATH + " с именем " + sheetName + " не найдена");

            for (int j = 1; j < sheet.getLastRowNum(); j++) {
                Row row = sheet.getRow(j);
                String name = row.getCell(0).getStringCellValue().trim().toLowerCase();
                teachers.put(name, new Teacher(name));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Файл не найден!: " + e);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка входного файла: " + e);
        }
        return null;
    }

    private void loadSubjects(){
        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheet(FileStringsEnum.SUBJECTS.getValue());

            for (int j = 1; j < sheet.getLastRowNum(); j++) {
                Row row = sheet.getRow(j);
                String name = row.getCell(0).getStringCellValue().trim().toLowerCase();
                String teacherName = row.getCell(1).getStringCellValue().trim().toLowerCase();
                subjects.put(name, teachers.get(teacherName));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadTeachers(){
        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheet(FileStringsEnum.TEACHERS.getValue());

            for (int j = 1; j < sheet.getLastRowNum(); j++) {
                Row row = sheet.getRow(j);
                String name = row.getCell(0).getStringCellValue().trim().toLowerCase();
                teachers.put(name, new Teacher(name));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadSubjects(){
        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheet(FileStringsEnum.SUBJECTS.getValue());

            for (int j = 1; j < sheet.getLastRowNum(); j++) {
                Row row = sheet.getRow(j);
                String name = row.getCell(0).getStringCellValue().trim().toLowerCase();
                String teacherName = row.getCell(1).getStringCellValue().trim().toLowerCase();
                subjects.put(name, teachers.get(teacherName));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadStudents(){
        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheet(FileStringsEnum.STUDENTS.getValue());
            String subjectName = sheet.getRow(0).getCell(1).getStringCellValue();
            for (int j = 1; j < sheet.getLastRowNum(); j++) {
                List<Integer> marks = new LinkedList<>();
                Row row = sheet.getRow(j);
                String name = row.getCell(0).getStringCellValue().trim().toLowerCase();
                for(int k = 1; k < row.getLastCellNum(); k++) {
                    marks.add((int) row.getCell(k).getNumericCellValue());
                }
                if(!students.containsKey(name)){
                    students.put(name, new Student(name));
                }
                students.get(name).addMark(subjects.get(subjectName), marks);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
//                Cell cell = row.getCell(0); // Get first cell in a row
//                switch (cell.getCellType()) {
//                    case STRING:
//                        System.out.println(cell.getStringCellValue());
//                        break;
//                    case NUMERIC:
//                        if (DateUtil.isCellDateFormatted(cell)) {
//                            System.out.println(cell.getDateCellValue());
//                        } else {
//                            System.out.println(cell.getNumericCellValue());
//                        }
//                        break;
//                    case BOOLEAN:
//                        System.out.println(cell.getBooleanCellValue());
//                        break;
//                    default:
//                        System.out.println("Unknown cell type");
//                }