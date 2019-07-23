package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.JogoCenario;
import model.Jogador.InfoJogador;
import view.JanelaTeste2;

//Controle Definitivo
public class ControleJogador implements KeyListener {

	JogoCenario cenario;

	private enum Tecla {
		CIMA, BAIXO, ESQUERDA, DIREITA, TW, TA, TS, TD
	}

	public static boolean[] controleTecla = new boolean[Tecla.values().length];

	public ControleJogador(JogoCenario cenario) {
		this.cenario = cenario;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		setaTecla(e.getKeyCode(), true);
	}


	@Override
	public void keyReleased(KeyEvent e) {
		setaTecla(e.getKeyCode(), false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public static void liberaTecla(Tecla tecla) {
		controleTecla[tecla.ordinal()] = false;
	}

	public static void liberaTeclas() {
		for (int i = 0; i < controleTecla.length; i++) {
			controleTecla[i] = false;
		}
	}

	public void setaTecla(int tecla, boolean pressionada) {
		if (cenario instanceof JogoCenario) {
		if (InfoJogador.JOGADOR1 == ((JogoCenario) cenario).getJogador().getPlayer()) {
			switch (tecla) {
			case KeyEvent.VK_UP:
				controleTecla[Tecla.CIMA.ordinal()] = pressionada;
//				System.out.println("Cima - " + pressionada);
				break;
			case KeyEvent.VK_DOWN:
				controleTecla[Tecla.BAIXO.ordinal()] = pressionada;
//				System.out.println("Baixo - " + pressionada);
				break;
			case KeyEvent.VK_LEFT:
				liberaTecla(Tecla.DIREITA);
				((JogoCenario) cenario).getJogador().setDirecao(InfoJogador.ESQUERDA);
				controleTecla[Tecla.ESQUERDA.ordinal()] = pressionada;
//				System.out.println("Esquerda - " + pressionada);
				break;
			case KeyEvent.VK_RIGHT:
				liberaTecla(Tecla.ESQUERDA);
				((JogoCenario) cenario).getJogador().setDirecao(InfoJogador.DIREITA);
				controleTecla[Tecla.DIREITA.ordinal()] = pressionada;
//				System.out.println("Direita - " + pressionada);
				break;
			}
		}
			
			if (((JogoCenario) cenario).getJogadorDois() != null && InfoJogador.JOGADOR2 == ((JogoCenario) cenario).getJogadorDois().getPlayer()) {
				switch (tecla) {
				case KeyEvent.VK_W:
					controleTecla[Tecla.TW.ordinal()] = pressionada;
//					System.out.println("Cima W - " + pressionada);
					break;
				case KeyEvent.VK_S:
					controleTecla[Tecla.TS.ordinal()] = pressionada;
//					System.out.println("Baixo S - " + pressionada);
					break;
				case KeyEvent.VK_A:
					liberaTecla(Tecla.TD);
					((JogoCenario) cenario).getJogadorDois().setDirecao(InfoJogador.ESQUERDA);
					controleTecla[Tecla.TA.ordinal()] = pressionada;
//					System.out.println("Esquerda A - " + pressionada);
					break;
				case KeyEvent.VK_D:
					liberaTecla(Tecla.TA);
					((JogoCenario) cenario).getJogadorDois().setDirecao(InfoJogador.DIREITA);
					controleTecla[Tecla.TD.ordinal()] = pressionada;
//					System.out.println("Direita D - " + pressionada);
					break;
				}
			}

			if (tecla == KeyEvent.VK_ESCAPE) {
				System.out.println("ESC ou sair do jogo");
				System.exit(0);
			}

			if (tecla == KeyEvent.VK_SPACE) 
				System.out.println("Tiro ou Pause");
				
			
		}
	}

	public void controlaJogadorUm() {
		if (cenario instanceof JogoCenario && InfoJogador.JOGADOR1 == ((JogoCenario) cenario).getJogador().getPlayer()) {
			
			if (ControleJogador.controleTecla[ControleJogador.Tecla.ESQUERDA.ordinal()] && ((JogoCenario) cenario).getJogador().getPx() > 0)
				((JogoCenario) cenario).getJogador().incPx(-((JogoCenario) cenario).getJogador().getVel());
//			System.out.println("Esquerda Pressionada");

			else if (ControleJogador.controleTecla[ControleJogador.Tecla.DIREITA.ordinal()] && ((JogoCenario) cenario).getJogador().getPx() + ((JogoCenario) cenario).getJogador().getLargura() < JanelaTeste2.getJanelaLargura())
				((JogoCenario) cenario).getJogador().incPx(((JogoCenario) cenario).getJogador().getVel());
//			System.out.println("Direita Pressionada");		

			if (ControleJogador.controleTecla[ControleJogador.Tecla.CIMA.ordinal()]) {//&& ((JogoCenario) cenario).getJogador().getPy() > JanelaTeste2.getJanelaAltura() * 0.2) {
				//			System.out.println("Cima pressionada");

				if(((JogoCenario) cenario).getJogador().getOxygen() > 0 && ((JogoCenario) cenario).getJogador().getPy() > JanelaTeste2.getJanelaAltura() * 0.2) {

					if (ControleJogador.controleTecla[ControleJogador.Tecla.ESQUERDA.ordinal()] && ((JogoCenario) cenario).getJogador().getPx() > 0) {
						//				System.out.println("Esquerda pressionada");
						((JogoCenario) cenario).getJogador().incPxCos(((JogoCenario) cenario).getJogador().getVel(), 225);
						((JogoCenario) cenario).getJogador().incPySin(((JogoCenario) cenario).getJogador().getVel(), 225);

					} else if (ControleJogador.controleTecla[ControleJogador.Tecla.DIREITA.ordinal()] && ((JogoCenario) cenario).getJogador().getPx() + ((JogoCenario) cenario).getJogador().getLargura() < JanelaTeste2.getJanelaLargura()) {
						//				System.out.println("Direita pressionada");
						((JogoCenario) cenario).getJogador().incPxCos(((JogoCenario) cenario).getJogador().getVel(), 315);
						((JogoCenario) cenario).getJogador().incPySin(((JogoCenario) cenario).getJogador().getVel(), 315);

					} else
						((JogoCenario) cenario).getJogador().incPy(-((JogoCenario) cenario).getJogador().getVel());

				}else if(((JogoCenario) cenario).getJogador().isFimO() && ((JogoCenario) cenario).getJogador().getPy() > JanelaTeste2.getJanelaAltura() * 0.1) {

					((JogoCenario) cenario).getJogador().incPy(-((JogoCenario) cenario).getJogador().getVel());

				}
					
			}

			else if (ControleJogador.controleTecla[ControleJogador.Tecla.BAIXO.ordinal()] && ((JogoCenario) cenario).getJogador().getPy() + ((JogoCenario) cenario).getJogador().getAltura() < JanelaTeste2.getJanelaAltura()) {
//			System.out.println("Baixo pressionado");
			
				if (ControleJogador.controleTecla[ControleJogador.Tecla.ESQUERDA.ordinal()] && ((JogoCenario) cenario).getJogador().getPx() > 0) {
//				System.out.println("Esquerda pressionada");
					((JogoCenario) cenario).getJogador().incPxCos(((JogoCenario) cenario).getJogador().getVel(), 135);
					((JogoCenario) cenario).getJogador().incPySin(((JogoCenario) cenario).getJogador().getVel(), 135);

				} else if (ControleJogador.controleTecla[ControleJogador.Tecla.DIREITA.ordinal()] && ((JogoCenario) cenario).getJogador().getPx() + ((JogoCenario) cenario).getJogador().getLargura() < JanelaTeste2.getJanelaLargura()) {
//				System.out.println("Direita pressionada");
					((JogoCenario) cenario).getJogador().incPxCos(((JogoCenario) cenario).getJogador().getVel(), 45);
					((JogoCenario) cenario).getJogador().incPySin(((JogoCenario) cenario).getJogador().getVel(), 45);

				} else
					((JogoCenario) cenario).getJogador().incPy(((JogoCenario) cenario).getJogador().getVel());
//			
			}
		}
	}

	public void controlaJogadorDois() {
		if (cenario instanceof JogoCenario && InfoJogador.JOGADOR2 == ((JogoCenario) cenario).getJogadorDois().getPlayer()) {
			
			if (ControleJogador.controleTecla[ControleJogador.Tecla.TA.ordinal()] && ((JogoCenario) cenario).getJogadorDois().getPx() > 0)
				((JogoCenario) cenario).getJogadorDois().incPx(-((JogoCenario) cenario).getJogadorDois().getVel());
//			System.out.println("Esquerda Pressionada");

			else if (ControleJogador.controleTecla[ControleJogador.Tecla.TD.ordinal()] && ((JogoCenario) cenario).getJogadorDois().getPx() + ((JogoCenario) cenario).getJogadorDois().getLargura() < JanelaTeste2.getJanelaLargura())
				((JogoCenario) cenario).getJogadorDois().incPx(((JogoCenario) cenario).getJogadorDois().getVel());
//			System.out.println("Direita Pressionada");		

			if (ControleJogador.controleTecla[ControleJogador.Tecla.TW.ordinal()]) {// && ((JogoCenario) cenario).getJogadorDois().getPy() > JanelaTeste2.getJanelaAltura() * 0.2) {
				//			System.out.println("Cima pressionada");
				if(((JogoCenario) cenario).getJogadorDois().getOxygen() > 0 && ((JogoCenario) cenario).getJogadorDois().getPy() > JanelaTeste2.getJanelaAltura() * 0.2) {

					if (ControleJogador.controleTecla[ControleJogador.Tecla.TA.ordinal()] && ((JogoCenario) cenario).getJogadorDois().getPx() > 0) {
						//				System.out.println("Esquerda pressionada");
						((JogoCenario) cenario).getJogadorDois().incPxCos(((JogoCenario) cenario).getJogadorDois().getVel(), 225);
						((JogoCenario) cenario).getJogadorDois().incPySin(((JogoCenario) cenario).getJogadorDois().getVel(), 225);

					} else if (ControleJogador.controleTecla[ControleJogador.Tecla.TD.ordinal()] && ((JogoCenario) cenario).getJogadorDois().getPx() + ((JogoCenario) cenario).getJogadorDois().getLargura() < JanelaTeste2.getJanelaLargura()) {
						//				System.out.println("Direita pressionada");
						((JogoCenario) cenario).getJogadorDois().incPxCos(((JogoCenario) cenario).getJogadorDois().getVel(), 315);
						((JogoCenario) cenario).getJogadorDois().incPySin(((JogoCenario) cenario).getJogadorDois().getVel(), 315);

					} else
						((JogoCenario) cenario).getJogadorDois().incPy(-((JogoCenario) cenario).getJogadorDois().getVel());

				}else if(((JogoCenario) cenario).getJogadorDois().isFimO() && ((JogoCenario) cenario).getJogadorDois().getPy() > JanelaTeste2.getJanelaAltura() * 0.1) {

					((JogoCenario) cenario).getJogadorDois().incPy(-((JogoCenario) cenario).getJogadorDois().getVel());

				}
			}

			else if (ControleJogador.controleTecla[ControleJogador.Tecla.TS.ordinal()] && ((JogoCenario) cenario).getJogadorDois().getPy() + ((JogoCenario) cenario).getJogadorDois().getAltura() < JanelaTeste2.getJanelaAltura()) {
//			System.out.println("Baixo pressionado");
			
				if (ControleJogador.controleTecla[ControleJogador.Tecla.TA.ordinal()] && ((JogoCenario) cenario).getJogadorDois().getPx() > 0) {
//				System.out.println("Esquerda pressionada");
					((JogoCenario) cenario).getJogadorDois().incPxCos(((JogoCenario) cenario).getJogadorDois().getVel(), 135);
					((JogoCenario) cenario).getJogadorDois().incPySin(((JogoCenario) cenario).getJogadorDois().getVel(), 135);

				} else if (ControleJogador.controleTecla[ControleJogador.Tecla.TD.ordinal()] && ((JogoCenario) cenario).getJogadorDois().getPx() + ((JogoCenario) cenario).getJogadorDois().getLargura() < JanelaTeste2.getJanelaLargura()) {
//				System.out.println("Direita pressionada");
					((JogoCenario) cenario).getJogadorDois().incPxCos(((JogoCenario) cenario).getJogadorDois().getVel(), 45);
					((JogoCenario) cenario).getJogadorDois().incPySin(((JogoCenario) cenario).getJogadorDois().getVel(), 45);

				} else
					((JogoCenario) cenario).getJogadorDois().incPy(((JogoCenario) cenario).getJogadorDois().getVel());
//			
			}
		}
	}


}
