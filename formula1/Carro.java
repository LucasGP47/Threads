package formula1;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Carro extends Thread {
    private String nome;
    private int velocidade;
    private Random random = new Random();
    private int distanciaCorrida = 0;
    private int distanciaTotal = 1000;
    private boolean problemasTecnicos = false;
    private boolean emBox = false;
    private boolean safetyCar = false;
    private static final AtomicInteger posicaoChegada = new AtomicInteger(0);
    private int colocacao = 0;
    
    public Carro(String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }
    
    public int getColocacao() {
        return colocacao;
    }
    
    public boolean isProblemasTecnicos() {
        return problemasTecnicos;
    }
    
    public void setSafetyCar(boolean safetyCar) {
        this.safetyCar = safetyCar;
    }
    
    @Override
    public void run() {
        System.out.println(nome + " iniciou a corrida!");
        
        while (distanciaCorrida < distanciaTotal && !problemasTecnicos) {
            try {
                if (random.nextInt(100) < 2) {
                    problemasTecnicos = true;
                    System.out.println("\u001B[31m" + nome + " abandonou a corrida por problemas técnicos!" + "\u001B[0m");
                    break;
                }
                
                if (random.nextInt(100) < 10 && !emBox) {
                    emBox = true;
                    System.out.println(nome + " está entrando no box!");
                    Thread.sleep(1000);
                    
                    velocidade = random.nextInt(50);
                    
                    if (velocidade > 40) {
                        System.out.println(nome + " recebeu penalidade por excesso de velocidade no box: " + velocidade);
                        Thread.sleep(1500);
                    }
                    
                    emBox = false;
                }
                
                if (safetyCar) {
                    velocidade = 30 + random.nextInt(20);
                    System.out.println(nome + " sob safety car a " + velocidade);
                } else if (!emBox) {
                    velocidade = random.nextInt(100);
                }
                
                distanciaCorrida += velocidade;
                System.out.println(nome + " está a " + velocidade + " e já correu " + distanciaCorrida);
                System.out.println("/////////////////////////////////////////////////////////////////");
                
                Thread.sleep(500);
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        if (!problemasTecnicos) {
            colocacao = posicaoChegada.incrementAndGet();
            System.out.println("\u001B[32m" + nome + " finalizou a corrida em " + colocacao + "º lugar!" + "\u001B[0m");
        }
    }
}