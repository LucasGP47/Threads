package formula1;

import java.util.ArrayList;
import java.util.List;

public class CorridaF1 {
    public static void main(String[] args) {
        String[] nomes = {"Corredor1", "Corredor2"};
        List<Thread> threads = new ArrayList<>();

        for (String nome : nomes) {
            Thread t = new Thread(new Carro(nome));
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
