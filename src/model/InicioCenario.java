package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import view.JanelaTeste2;

public class InicioCenario extends CenarioPadrao{

	private BufferedImage telaInicio, telaAjuda;
	private Opcao opcao = Opcao.MENU;
	
	private BufferedImage[] tela = new BufferedImage[2];
	private int auxTela = 0;
	
	
	public InicioCenario(int altura, int largura) {
		super(altura, largura);
	}


	@Override
	public void carregar() {
		try {
			telaInicio = ImageIO.read(new File("resources/Inicio2.png"));
			telaAjuda = ImageIO.read(new File("resources/AjudaTela.png"));
		} catch (IOException e) {e.printStackTrace();}
		
		tela[0] = telaInicio;
		tela[1] = telaAjuda;
		
	}

	@Override
	public void descarregar() {
		opcao = null;
	}

	@Override
	public void atualizar() {
		if (getOpcao() == Opcao.MENU)
			auxTela = 0;
		else if(getOpcao() == Opcao.AJUDA)
			auxTela = 1;
		else
			auxTela = 0;
	}

	@Override
	public void desenhar(Graphics2D g) {
			g.drawImage(tela[auxTela], 0, 0, null);
				
	}
	
	
	public Opcao getOpcao() {
		return opcao;
	}


	public void setOpcao(Opcao opcao) {
		this.opcao = opcao;
	}


	public BufferedImage getTelaInicio() {
		return telaInicio;
	}


//	public enum Opcao{
//		MENU, JOGAR, OPCAO, RANK, AJUDA, SAIR;
//	}

}
