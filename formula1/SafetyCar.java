package formula1;
import java.util.List;
import java.util.Random;

public class SafetyCar extends Thread {
    private List<Carro> carros;
    private Random random = new Random();
    private boolean corridaEmAndamento = true;
    
    public SafetyCar(List<Carro> carros) {
        this.carros = carros;
    }
    
    public void pararMonitoramento() {
        corridaEmAndamento = false;
    }
    
    @Override
    public void run() {
        while (corridaEmAndamento) {
            try {
                Thread.sleep(5000);
                
                if (random.nextInt(100) < 50) {
                    System.out.println("\u001B[33m" + "SAFETY CAR ACIONADO!" + "\u001B[0m");
                    
                    for (Carro carro : carros) {
                        carro.setSafetyCar(true);
                    }
                    
                    Thread.sleep(3000);
                    
                    System.out.println("\u001B[33m" + "SAFETY CAR RECOLHIDO!" + "\u001B[0m");
                    
                    for (Carro carro : carros) {
                        carro.setSafetyCar(false);
                    }
                }
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}