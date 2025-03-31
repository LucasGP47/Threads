package formula1;

import java.util.Random;

public class Carro extends Thread {
	private String nome;
	private int velocidade;
	private Random random = new Random();
	private int distanciaCorrida = 0;
	private int distanciaTotal = 1000;
	
	public Carro (String nome) {
		this.nome = nome;
	}
	
	@Override
	public void run() {
		System.out.println( nome + " iniciou a corrida!");
		while (distanciaCorrida <= distanciaTotal) {
			velocidade = random.nextInt(100);
			distanciaCorrida += velocidade;
			System.out.println(nome + " está a " + velocidade + " e ja correu " + distanciaCorrida);
			System.out.println("/////////////////////////////////////////////////////////////////");
		}
		
		System.out.println(nome + " finalizou a corrida!");
		
		
	}
	
}
