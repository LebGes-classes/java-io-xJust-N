package app.main.java.repository;

public enum FileStringsEnum {
    TEACHERS("преподаватели"),
    SUBJECTS("предметы"),
    STUDENTS("студенты"),
    XLSX_FILE_NAME("resources/journal.xlsx"),
    JSON_FILE_NAME("resources/journal.json");

    private final String value;

    FileStringsEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static FileStringsEnum fromString(String value) {
        for (FileStringsEnum e : values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Неизвестное значение: " + value);
    }
}