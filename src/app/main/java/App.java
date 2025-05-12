package app.main.java;

public class App {
    private boolean isRunning;
    App(){}

    public void start(){
        isRunning = true;
    }

    private void run(){
        while(isRunning){}
    }

}
