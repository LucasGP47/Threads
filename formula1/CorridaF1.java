package formula1;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CorridaF1 {
    public static void main(String[] args) {
        String[] nomes = {"Hamilton", "Verstappen", "Leclerc", "Norris", "Bottas"};
        
        System.out.println("\u001B[32m" + "\n----- COMEÇA A CORRIDA -----" + "\u001B[0m");
        
        List<Carro> carros = new ArrayList<>();
        for (String nome : nomes) {
            carros.add(new Carro(nome));
        }
        
        SafetyCar safetyCar = new SafetyCar(carros);
        safetyCar.start();
        
        for (Carro carro : carros) {
            carro.start();
        }
        
        for (Carro carro : carros) {
            try {
                carro.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        safetyCar.pararMonitoramento();
        safetyCar.interrupt();
        
        System.out.println("\u001B[34m" + "\n----- RESULTADO FINAL -----" + "\u001B[0m");
        
        List<Carro> finalizados = new ArrayList<>();
        List<Carro> abandonados = new ArrayList<>();
        
        for (Carro carro : carros) {
            if (carro.isProblemasTecnicos()) {
                abandonados.add(carro);
            } else {
                finalizados.add(carro);
            }
        }
        
        finalizados.sort(Comparator.comparingInt(Carro::getColocacao));
        
        for (Carro carro : finalizados) {
            System.out.println(carro.getColocacao() + "º lugar: " + carro.getNome());
        }
        
        if (!abandonados.isEmpty()) {
            System.out.println("\nAbandonaram:");
            for (Carro carro : abandonados) {
                System.out.println("- " + carro.getNome());
            }
        }
    }
}