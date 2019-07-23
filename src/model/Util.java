package model;

public class Util {
//	caso haja algum erro de colisão apara o -1
	public static boolean colide(Elemento a, Elemento b) {
		if(!a.isAtivo() || !b.isAtivo())
			return false;
		
		//posição no eixo X + largura do elemento A e B
		final int plA = a.getPx() + a.getLargura() - 1;
		final int plB = b.getPx() + b.getLargura() - 1;
		
		//posição no eixo Y + altura do elemento A e B
		final int paA = a.getPy() + a.getAltura() - 1;
		final int paB = b.getPy() + b.getAltura() - 1;
		
		if(plA > b.getPx() && a.getPx() < plB
				&& paA > b.getPy() && a.getPy() < paB) {
			return true;
		}
		
		return false;
	}
	
	public static boolean colideX(Elemento a, Elemento b) {
		if(!a.isAtivo() || !b.isAtivo())
			return false;
		
		//posição no eixo X + largura do elemento A e B
		final int plA = a.getPx() + a.getLargura() - 1;
		final int plB = b.getPx() + b.getLargura() - 1;
		
		if(plA > b.getPx() && a.getPx() < plB) {
			return true;
		}
		
		return false;
	}
	
	public static boolean colideY(Elemento a, Elemento b) {
		if(!a.isAtivo() || !b.isAtivo())
			return false;

		//posição no eixo Y + altura do elemento A e B
		final int paA = a.getPy() + a.getAltura() - 1;
		final int paB = b.getPy() + b.getAltura() - 1;
		
		if(paA > b.getPy() && a.getPy() < paB) {
			return true;
		}
		
		return false;
	}
	
	public static boolean colideXT(Elemento a, Elemento b) {
		if(!a.isAtivo() || !b.isAtivo())
			return false;
		
		//posição no eixo X + largura do elemento A e B
		final int plA = a.getPx() + a.getLargura() - 1;
		final int plB = b.getPx() + b.getLargura() - 1;
		
		if(plA > b.getPx() && a.getPx() < plB) {
			return true;
		}
		
		return false;
	}
	
	public static boolean colideYT(Elemento a, Elemento b) {
		if(!a.isAtivo() || !b.isAtivo())
			return false;

		//posição no eixo Y + altura do elemento A e B
		final int paA = a.getPy() - a.getAltura() - 1;
		final int paB = b.getPy() + b.getAltura() - 1;
		
		if(paA > b.getPy() && a.getPy() < paB) {
			return true;
		}
		
		return false;
	}
	
}
