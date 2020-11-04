package Packages;

import java.awt.EventQueue;

public class App {
    public static void main(String[] args) throws Exception {
        Cronometro cronos = new Cronometro();
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                cronos.init();
            }
        });
    };
}
