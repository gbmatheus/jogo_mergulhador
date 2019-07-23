package app;

import java.util.Random;

public class Teste {

	public static void main(String[] args) throws InterruptedException {
//		int prx = 0;
//		long prxAtualizacao = 0;
//		
//		for(int i = 0; i < 10; i++) {
////			Thread.sleep(1000);
//			
//			if(System.currentTimeMillis() >= prxAtualizacao) {
//				System.out.println(System.currentTimeMillis());
//					
//			}
//			else
//				prxAtualizacao = System.currentTimeMillis() + 100000;
//			
//		}
//		
//		int vida = 3;
//		int hp = 10;
//
//		for (int i = vida * hp; i > 0; i--) {
//
//			if (i % 10 == 0)
//				System.out.println(i + " True");
//			else
//				System.out.println(i + " False");
//			Thread.sleep(1000);
//		}
		
		System.out.println("Plastico"+(new Random().nextInt(3)+1)+".png");
	}

}
