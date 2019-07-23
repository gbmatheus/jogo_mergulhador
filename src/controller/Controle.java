package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.CenarioPadrao;
import model.InicioCenario;
import model.Jogador;
import model.Jogador.InfoJogador;
import view.JanelaTeste2;
import model.JogoCenario;
import model.Opcao;

//Controle Definitivo
public class Controle implements KeyListener, MouseListener {

//	Jogador jogador;
	CenarioPadrao cenario;

	private enum Tecla {
		CIMA, BAIXO, ESQUERDA, DIREITA, TW, TA, TS, TD
	}

	public static boolean[] controleTecla = new boolean[Tecla.values().length];

//	public Controle() {}

	public Controle(CenarioPadrao cenario) {
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
//		if (InfoJogador.JOGADOR1 == ((JogoCenario) cenario).getJogador().getPlayer()) {
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
//		}

			if (InfoJogador.JOGADOR2 == ((JogoCenario) cenario).getJogador().getPlayer()) {
				switch (tecla) {
				case KeyEvent.VK_W:
					controleTecla[Tecla.TW.ordinal()] = pressionada;
					System.out.println("Cima W - " + pressionada);
					break;
				case KeyEvent.VK_S:
					controleTecla[Tecla.TS.ordinal()] = pressionada;
					System.out.println("Baixo S - " + pressionada);
					break;
				case KeyEvent.VK_A:
					controleTecla[Tecla.TA.ordinal()] = pressionada;
					System.out.println("Esquerda A - " + pressionada);
					break;
				case KeyEvent.VK_D:
					controleTecla[Tecla.TD.ordinal()] = pressionada;
					System.out.println("Direita D - " + pressionada);
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
		if (cenario instanceof JogoCenario) {
			if (Controle.controleTecla[Controle.Tecla.ESQUERDA.ordinal()] && ((JogoCenario) cenario).getJogador().getPx() > 0)
				((JogoCenario) cenario).getJogador().incPx(-((JogoCenario) cenario).getJogador().getVel());
//			jogador.incPxCos(jogador.getVel(), 180);
//			System.out.println("Esquerda Pressionada");

			else if (Controle.controleTecla[Controle.Tecla.DIREITA.ordinal()] && ((JogoCenario) cenario).getJogador().getPx() + ((JogoCenario) cenario).getJogador().getLargura() < JanelaTeste2.getJanelaLargura())
				((JogoCenario) cenario).getJogador().incPx(((JogoCenario) cenario).getJogador().getVel());
			// jogador.incPxCos(jogador.getVel(), 0);
//			System.out.println("Direita Pressionada");		

			if (Controle.controleTecla[Controle.Tecla.CIMA.ordinal()] && ((JogoCenario) cenario).getJogador().getPy() > JanelaTeste2.getJanelaAltura() * 0.2) {
//			System.out.println("Cima pressionada");
				if (Controle.controleTecla[Controle.Tecla.ESQUERDA.ordinal()] && ((JogoCenario) cenario).getJogador().getPx() > 0) {
//				System.out.println("Esquerda pressionada");
					((JogoCenario) cenario).getJogador().incPxCos(((JogoCenario) cenario).getJogador().getVel(), 225);
					((JogoCenario) cenario).getJogador().incPySin(((JogoCenario) cenario).getJogador().getVel(), 225);

				} else if (Controle.controleTecla[Controle.Tecla.DIREITA.ordinal()] && ((JogoCenario) cenario).getJogador().getPx() + ((JogoCenario) cenario).getJogador().getLargura() < JanelaTeste2.getJanelaLargura()) {
//				System.out.println("Direita pressionada");
					((JogoCenario) cenario).getJogador().incPxCos(((JogoCenario) cenario).getJogador().getVel(), 315);
					((JogoCenario) cenario).getJogador().incPySin(((JogoCenario) cenario).getJogador().getVel(), 315);

				} else
					((JogoCenario) cenario).getJogador().incPy(-((JogoCenario) cenario).getJogador().getVel());
//				jogador.incPySin(jogador.getVel(), 270);
			}

			else if (Controle.controleTecla[Controle.Tecla.BAIXO.ordinal()] && ((JogoCenario) cenario).getJogador().getPy() + ((JogoCenario) cenario).getJogador().getAltura() < JanelaTeste2.getJanelaAltura()) {
//			System.out.println("Baixo pressionado");
				if (Controle.controleTecla[Controle.Tecla.ESQUERDA.ordinal()] && ((JogoCenario) cenario).getJogador().getPx() > 0) {
//				System.out.println("Esquerda pressionada");
					((JogoCenario) cenario).getJogador().incPxCos(((JogoCenario) cenario).getJogador().getVel(), 135);
					((JogoCenario) cenario).getJogador().incPySin(((JogoCenario) cenario).getJogador().getVel(), 135);

				} else if (Controle.controleTecla[Controle.Tecla.DIREITA.ordinal()] && ((JogoCenario) cenario).getJogador().getPx() + ((JogoCenario) cenario).getJogador().getLargura() < JanelaTeste2.getJanelaLargura()) {
//				System.out.println("Direita pressionada");
					((JogoCenario) cenario).getJogador().incPxCos(((JogoCenario) cenario).getJogador().getVel(), 45);
					((JogoCenario) cenario).getJogador().incPySin(((JogoCenario) cenario).getJogador().getVel(), 45);

				} else
					((JogoCenario) cenario).getJogador().incPy(((JogoCenario) cenario).getJogador().getVel());
//			jogador.incPySin(jogador.getVel(), 90);
			}
		}
	}

	public void controlaJogadorDois() {
		if (InfoJogador.JOGADOR2 == ((JogoCenario) cenario).getJogador().getPlayer()
				&& ((JogoCenario) cenario).getJogador().isAtivo()) {
			if (Controle.controleTecla[Controle.Tecla.TA.ordinal()])
//				jogador.incPxCos(jogador.getVel(), 180);
				System.out.println("A Pressionada");

			else if (Controle.controleTecla[Controle.Tecla.TD.ordinal()])
//				jogador.incPxCos(jogador.getVel(), 0);
				System.out.println("D Pressionada");

			if (Controle.controleTecla[Controle.Tecla.TW.ordinal()]) {
				System.out.println("W pressionada");
				if (Controle.controleTecla[Controle.Tecla.TA.ordinal()]) {
					System.out.println("A pressionada");
					((JogoCenario) cenario).getJogador().incPxCos(((JogoCenario) cenario).getJogador().getVel() - 1,
							225);
					((JogoCenario) cenario).getJogador().incPySin(((JogoCenario) cenario).getJogador().getVel() - 1,
							225);

				} else if (Controle.controleTecla[Controle.Tecla.TD.ordinal()]) {
					System.out.println("D pressionada");
					((JogoCenario) cenario).getJogador().incPxCos(((JogoCenario) cenario).getJogador().getVel() - 1,
							315);
					((JogoCenario) cenario).getJogador().incPySin(((JogoCenario) cenario).getJogador().getVel() - 1,
							315);

				} else
					((JogoCenario) cenario).getJogador().incPySin(((JogoCenario) cenario).getJogador().getVel(), 270);
			}

			else if (Controle.controleTecla[Controle.Tecla.TS.ordinal()]) {
				System.out.println("S pressionado");
				if (Controle.controleTecla[Controle.Tecla.TA.ordinal()]) {
					System.out.println("A pressionada");
					((JogoCenario) cenario).getJogador().incPxCos(((JogoCenario) cenario).getJogador().getVel() - 1,
							135);
					((JogoCenario) cenario).getJogador().incPySin(((JogoCenario) cenario).getJogador().getVel() - 1,
							135);

				} else if (Controle.controleTecla[Controle.Tecla.TD.ordinal()]) {
					System.out.println("D pressionada");
					((JogoCenario) cenario).getJogador().incPxCos(((JogoCenario) cenario).getJogador().getVel() - 1,
							45);
					((JogoCenario) cenario).getJogador().incPySin(((JogoCenario) cenario).getJogador().getVel() - 1,
							45);

				} else
					((JogoCenario) cenario).getJogador().incPySin(((JogoCenario) cenario).getJogador().getVel(), 90);
			}
		}
	}

	public boolean[] getControleTecla() {
		return controleTecla;
	}

	public void setControleTecla(boolean[] controleTecla) {
		this.controleTecla = controleTecla;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (cenario instanceof InicioCenario) {
			if (e.getX() > 350 && e.getX() < 450 && e.getY() > 262 && e.getY() < 279) {
				System.out.println("Jogar");
				((InicioCenario) cenario).setOpcao(Opcao.JOGAR);
				System.out.println(((InicioCenario) cenario).getOpcao());

			} else if (e.getX() > 350 && e.getX() < 450 && e.getY() > 281 && e.getY() < 298)
				System.out.println("Opção");

			else if (e.getX() > 350 && e.getX() < 450 && e.getY() > 300 && e.getY() < 317)
				System.out.println("Classificação");

			else if (e.getX() > 350 && e.getX() < 450 && e.getY() > 319 && e.getY() < 336)
				System.out.println("Ajuda");

			else if (e.getX() > 350 && e.getX() < 450 && e.getY() > 338 && e.getY() < 355) {
				System.out.println("Sair");
				System.exit(0);

			}else
				System.out.println("Menu Principal");
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
