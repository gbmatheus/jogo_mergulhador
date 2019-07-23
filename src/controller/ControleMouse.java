package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.InicioCenario;
//import model.InicioCenario.Opcao;
import model.Opcao;
import model.Rank;

public class ControleMouse implements MouseListener{

	InicioCenario cenario;
	
	public ControleMouse(InicioCenario cenario) {
		this.cenario = cenario;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (cenario instanceof InicioCenario) {
			if(cenario.getOpcao() == Opcao.MENU) {
			if (e.getX() > 350 && e.getX() < 450 && e.getY() > 262 && e.getY() < 279) {
				System.out.println("Jogar");
				((InicioCenario) cenario).setOpcao(Opcao.JOGAR);
				System.out.println(((InicioCenario) cenario).getOpcao());

			} else if (e.getX() > 350 && e.getX() < 450 && e.getY() > 281 && e.getY() < 298) { 
				System.out.println("Multijogador");
				((InicioCenario) cenario).setOpcao(Opcao.MULTI);
				
				
			}else if (e.getX() > 350 && e.getX() < 450 && e.getY() > 300 && e.getY() < 317) {
				System.out.println("Classificação");
				Rank.rankingExibir();

			}else if (e.getX() > 350 && e.getX() < 450 && e.getY() > 319 && e.getY() < 336) {
				System.out.println("Ajuda");
				
				((InicioCenario) cenario).setOpcao(Opcao.AJUDA);
				System.out.println(((InicioCenario) cenario).getOpcao());
				cenario.atualizar();

			}else if (e.getX() > 350 && e.getX() < 450 && e.getY() > 338 && e.getY() < 355) {
				System.out.println("Sair");
				System.exit(0);

			}else
				System.out.println("Menu Principal");
			}
			
			else if(cenario.getOpcao() == Opcao.AJUDA)
				if(e.getX() > 683 && e.getX() < 787 && e.getY() > 566 && e.getY() < 585){
					System.out.println("Voltar");
					((InicioCenario) cenario).setOpcao(Opcao.MENU);
					System.out.println(((InicioCenario) cenario).getOpcao());
					cenario.atualizar();
					
				}
						
				
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
