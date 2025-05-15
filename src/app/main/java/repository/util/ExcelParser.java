package app.main.java.repository.util;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;


public class ExcelParser {
    private final File file;

    public ExcelParser(File file){
        this.file = file;
    }

    public <T> List<T> parseObjectsFromSheetName(String sheetName, Class<T> tClass) throws IOException {//Возвращает список спаршенных объектов, где строчка в xlsx - параметры конструктора
        List<T> listOfObjects = new LinkedList<>();

        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null)
                throw new IOException("Страница в " + file + " с именем " + sheetName + " не найдена");

            int startRow = sheet.getTopRow() + 1;   // Пропуск верхней строчки с обозначениями
            int lastRow = sheet.getLastRowNum();
            for (int j = startRow; j < lastRow; j++) {
                Row row = sheet.getRow(j);
                
                int startCell = row.getFirstCellNum();
                int lastCell = row.getLastCellNum();
                Class<?>[] paramsTypes = new Class<?>[lastCell - startCell];
                Object[] params = new Object[lastCell - startCell];
                for (int k = startCell; k < lastCell; k++) {
                    Cell cell = row.getCell(k);
                    switch (cell.getCellType()) {
                        case STRING:
                            params[k - startCell] = cell.getStringCellValue();
                            paramsTypes[k - startCell] = String.class;
                            break;
                        case NUMERIC:
                            params[k - startCell] = cell.getNumericCellValue();
                            paramsTypes[k - startCell] = int.class;
                            break;
                        case BOOLEAN:
                            params[k - startCell] = cell.getBooleanCellValue();
                            paramsTypes[k - startCell] = boolean.class;
                            break;
                        default:
                            throw new IOException("Ячейка " + (char) (k + 65) + (j) + " имеет неизвестный тип");
                    }
                }
                try {
                    Constructor<T> constructor = tClass.getConstructor(paramsTypes);
                    listOfObjects.add(constructor.newInstance(params));
                } catch (NoSuchMethodException e) {
                    throw new NoSuchMethodException("Класс не имеет нужного конструктора для типов параметров: " + (Arrays.toString(paramsTypes)));
                }
            }
        } catch (FileNotFoundException e) {
            throw new IOException("Файл не найден: " + e);
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException | NoSuchMethodException e){
            throw new IOException("Ошибка вызова конструктора:\n" + e);
        }
        return listOfObjects;
    }
}