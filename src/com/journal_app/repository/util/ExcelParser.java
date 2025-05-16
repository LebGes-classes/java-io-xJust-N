package com.journal_app.repository.util;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;


public class ExcelParser {
    private final File file;

    public ExcelParser(File file) {
        this.file = file;
    }

    public <T> List<T> parseObjectsFromSheetName(String sheetName, Class<T> tClass) throws IOException {    //Возвращает список спаршенных объектов, где строчка в xlsx - параметры конструктора
        List<T> listOfObjects = new LinkedList<>();

        try (FileInputStream fis = new FileInputStream(file);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null)
                return null;

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
                            break;
                        case NUMERIC:
                            params[k - startCell] = (int) cell.getNumericCellValue();
                            break;
                        case BOOLEAN:
                            params[k - startCell] = cell.getBooleanCellValue();
                            break;
                        default:
                            throw new IOException("Ячейка " + (char) (k + 65) + (j) + " имеет неизвестный тип");
                    }

                    paramsTypes[k - startCell] = params[k - startCell].getClass();
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
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException |
                 NoSuchMethodException e) {
            throw new IOException("Ошибка вызова конструктора:\n" + e);
        } catch (Exception e) {
            throw new IOException("Непредвиденная ошибка: " + e);
        }
        return listOfObjects;
    }
}