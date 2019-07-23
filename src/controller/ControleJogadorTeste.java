package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Jogador;
import model.Jogador.InfoJogador;

public class ControleJogadorTeste implements KeyListener {

	Jogador jogador;
	boolean[] controleTecla = new boolean[10];
	int angulo = 0;
	int[] angulos = new int[4];

	public ControleJogadorTeste(Jogador jogador) {
		this.jogador = jogador;
		movimentar();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
//		setaTecla(e.getKeyCode(), true);
//		if(Player.JOGADOR1 == jogador.getPlayer()) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				System.out.println(e.getKeyCode());
				angulo = 270;
				jogador.incPy(jogador.getVel() * Math.sin(Math.toRadians(angulo)));
				jogador.incPx(jogador.getVel() * Math.cos(Math.toRadians(angulo)));
				break;
			case KeyEvent.VK_DOWN:
				angulo = 90;
				System.out.println(e.getKeyCode());
				jogador.incPy(jogador.getVel() * Math.sin(Math.toRadians(angulo)));
				jogador.incPx(jogador.getVel() * Math.cos(Math.toRadians(angulo)));
				break;
			case KeyEvent.VK_LEFT:
				angulo = 180;
				System.out.println(e.getKeyCode());
				jogador.setDirecao(InfoJogador.ESQUERDA);
				jogador.incPy(jogador.getVel() * Math.sin(Math.toRadians(angulo)));
				jogador.incPx(jogador.getVel() * Math.cos(Math.toRadians(angulo)));
				break;
			case KeyEvent.VK_RIGHT:
				angulo = 0;
				System.out.println(e.getKeyCode());
				jogador.setDirecao(InfoJogador.DIREITA);
				jogador.incPy(jogador.getVel() * Math.sin(Math.toRadians(angulo)));
				jogador.incPx(jogador.getVel() * Math.cos(Math.toRadians(angulo)));
			break;
		}
			
//		}
//		if(InfoJogador.JOGADOR2 == jogador.getPlayer()) {
//		switch (e.getKeyCode()) {
//		case KeyEvent.VK_W:
//			jogador.incPy(-2);
//			System.out.println("Cima W");
//			break;
//		case KeyEvent.VK_S:
//			jogador.incPy(2);
//			System.out.println("Baixo S");
//			break;
//		case KeyEvent.VK_A:
//			jogador.incPx(-3);
//			System.out.println("Esquerda A");
//			break;
//		case KeyEvent.VK_D:
//			jogador.incPx(3);
//			System.out.println("Direita D");
//			break;
//		}
//		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		setaTecla(e.getKeyCode(), false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public void setaTecla(int tecla, boolean pressionada) {
//		if (InfoJogador.JOGADOR1 == jogador.getPlayer()) {
			switch (tecla) {
			case KeyEvent.VK_UP:
				controleTecla[0] = pressionada;
				angulos[0] = 90;
				angulo = 90;
				System.out.println("Cima - " + pressionada);
				break;
			case KeyEvent.VK_DOWN:
				controleTecla[1] = pressionada;
				angulos[1] = 270;
				angulo = 270;
				System.out.println("Baixo - " + pressionada);
				break;
			case KeyEvent.VK_LEFT:
				controleTecla[2] = pressionada;
				angulos[2] = 0;
				angulo = 0;
				System.out.println("Esquerda - " + pressionada);
				break;
			case KeyEvent.VK_RIGHT:
				controleTecla[3] = pressionada;
				angulos[3] = 180;
				angulo = 180;
				System.out.println("Direita - " + pressionada);
				break;
//			}
		}

		if (InfoJogador.JOGADOR2 == jogador.getPlayer()) {
			switch (tecla) {
			case KeyEvent.VK_W:
				controleTecla[4] = pressionada;
				System.out.println("Cima W - " + pressionada);
				break;
			case KeyEvent.VK_S:
				controleTecla[5] = pressionada;
				System.out.println("Baixo S - " + pressionada);
				break;
			case KeyEvent.VK_A:
				controleTecla[6] = pressionada;
				System.out.println("Esquerda A - " + pressionada);
				break;
			case KeyEvent.VK_D:
				controleTecla[7] = pressionada;
				System.out.println("Direita D - " + pressionada);
				break;
			}
		}

		if (tecla == KeyEvent.VK_ESCAPE)
			System.out.println("ESC ou sair do jogo");

		if (tecla == KeyEvent.VK_SPACE)
			System.out.println("Tiro ou Pause");
	}
	
	public void movimentar() {
//		Controle Jogador teclas
		if(controleTecla[0]) {
			jogador.incPy(jogador.getVel() * Math.sin(angulos[0]));
			jogador.incPx(jogador.getVel() * Math.cos(angulos[0]));
		}else if(controleTecla[1]) {
			jogador.incPy(jogador.getVel() * Math.sin(angulos[1]));
			jogador.incPx(jogador.getVel() * Math.cos(angulos[1]));
		}
		if(controleTecla[2]) {
			jogador.incPy(jogador.getVel() * Math.sin(angulo));
			jogador.incPx(jogador.getVel() * Math.cos(angulo));
		}			else if(controleTecla[3]) {
			jogador.incPy(jogador.getVel() * Math.sin(angulo));
			jogador.incPx(jogador.getVel() * Math.cos(angulo));
		}
	}

	public boolean[] getControleTecla() {
		return controleTecla;
	}

	public void setControleTecla(boolean[] controleTecla) {
		this.controleTecla = controleTecla;
	}

}
