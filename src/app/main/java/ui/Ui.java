package app.main.java.ui;

import app.main.java.model.Printable;

import java.util.Collection;
import java.util.Scanner;

public class Ui {
    private final Scanner scanner;
    public Ui(){
        scanner = new Scanner(System.in);
    }

    public void print(String s){
        System.out.println(s);
    }
    public void print(Printable obj){
        print(obj.getInfo());
    }
    public void printMenu(Printable obj){
        clearConsole();
        print(obj);
        printBaseOptions();
    }
    public void print(Collection<? extends Printable> coll){
        coll.forEach(this::print);
    }

    public String read(){
        return scanner.nextLine().trim().toLowerCase();
    }
    public String read(String s){
        print(s);
        return scanner.nextLine().trim().toLowerCase();
    }
    private void printBaseOptions(){
        print(
                "back) Вернуться в главное меню " + "\n" +
                        "exit) Закрыть приложение" + "\n"
        );
    }

    public void printErrorMessage(String message){
        clearConsole();
        print("Ошибка! " + message);
        waitForInput();
    }
    public void waitForInput(){
        print("Введите любую строку чтобы продолжить");
        read();
    }

    public void clearConsole(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
        catch (Exception e) {
            printErrorMessage(e.getMessage());
        }
    }
}
