package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;

import view.JanelaTeste2;

public class Jogador extends Elemento implements Runnable{
	
	private Sprite sprite;
	private int auxSprite;
	
	private int vida;
	private int hp;
	private double oxygen;
	private int pontos = 0;
	
	//variaveis para o controle do oxigenio
	private boolean fimO = false;
	long prx1 = System.currentTimeMillis() + 100,
			prx2 = System.currentTimeMillis() + 30000;
;

	private InfoJogador player, direcao = InfoJogador.DIREITA;
	
	
	public Jogador() {
		try {
			sprite = new Sprite("/Projeto/resources/Nadando.png", 2, 8, 58, 40);
		} catch (IOException e) {e.printStackTrace(); System.err.println("Erro arquivo não encontrado");}
	}

	public Jogador(int px, int py, int largura, int altura) {
		super(px, py, largura, altura);
	
	}
	
	public Jogador(int px, int py, int largura, int altura, InfoJogador player) {
		super(px, py, largura, altura);
		this.player = player;
	
		
		try {
			if(player == InfoJogador.JOGADOR1)
				sprite = new Sprite("resources/NadandoP11.png", 2, 10, 58, 40);
			else if(player == InfoJogador.JOGADOR2)
				sprite = new Sprite("resources/NadandoP21.png", 2, 10, 58, 40);
			
		} catch (IOException e) {e.printStackTrace(); System.err.println("Erro arquivo não encontrado");}
		
	}
	
	public Jogador(int px, int py, int largura, int altura, int vida, int hp, double oxygen, InfoJogador player) {
		super(px, py, largura, altura);
		
		this.vida = vida;
		this.hp = hp;
		this.oxygen = oxygen;
		this.player = player;
	
		
		try {
			if(player == InfoJogador.JOGADOR1)
				sprite = new Sprite("resources/NadandoP11.png", 2, 10, 58, 40);
			else if(player == InfoJogador.JOGADOR2)
				sprite = new Sprite("resources/NadandoP21.png", 2, 10, 58, 40);
			
		} catch (IOException e) {e.printStackTrace(); System.err.println("Erro arquivo não encontrado");}
	
	}

	@Override
	public void desenha(Graphics2D g) {
		if(!isAtivo())
			return;
		
		if(InfoJogador.JOGADOR2 == player) {
//			g.setColor(Color.MAGENTA);
//			g.fillRect(getPx(), getPy(), getLargura(), getAltura());
			
			g.setColor(Color.RED);
			g.drawRect(getPx(), getPy() - 12, 55, 5);
			g.fillRect(getPx(), getPy() - 12,(int) (55 * 0.05 * hp) , 5);
			
			g.setColor(Color.CYAN);
//			g.setColor(new Color(20, 150, 225));
			g.drawRect(getPx(), getPy() - 6, 55, 5);
			g.fillRect(getPx(), getPy() - 6, (int) (55 * 0.1 * oxygen), 5);
			g.drawImage(sprite.getSprites()[auxSprite], getPx(), getPy(), null);

		}else {
//			g.setColor(Color.CYAN);
//			g.fillRect(getPx(), getPy(), getLargura(), getAltura());
			
			g.setColor(Color.RED);
			g.drawRect(getPx(), getPy() - 12, 55, 5);
			g.fillRect(getPx(), getPy() - 12,(int) (55 * 0.05 * hp) , 5);

			g.setColor(Color.CYAN);
//			g.setColor(new Color(20, 150, 225));
			g.drawRect(getPx(), getPy() - 6, 55, 5);
			g.fillRect(getPx(), getPy() - 6, (int) (55 * 0.1 * oxygen), 5);
			
			g.drawImage(sprite.getSprites()[auxSprite], getPx(), getPy(), null);
			
		}
	}
	
	@Override
	public void atualiza() {
		if(!isAtivo())
			return;

		//ALTERAÇOES FEITAS - PARA OXIGENIO E DIMINUIÇÃO DA PONTUAÇÃO E VIDA		
		if(oxygen  > 0 && !fimO)
			oxygen -= 0.05;
		else if(oxygen  < 0)
			oxygen = 0;
		else {
			fimO = true;
		}
		

		if(fimO) {
			if(getPy() + 5 > JanelaTeste2.getJanelaAltura() * 0.2) {

				if(System.currentTimeMillis() >= prx2) {
					if(hp > 0)
						hp -= 1;
					
					prx2 = System.currentTimeMillis() + 2000;

				}
			
			}else if(getPy() < JanelaTeste2.getJanelaAltura() * 0.2) {

				if(System.currentTimeMillis() >= prx1) {
					if(pontos > 0)
						pontos -= 1;
					
					
					if(oxygen < 10 && fimO) {
						oxygen += 1;

					}else
						fimO = false;

					
					System.out.println(fimO + "Passou aki 1");	
					prx1 = System.currentTimeMillis() + 500;
				}
				
			}

		}		

		
		switch (direcao) {
		case ESQUERDA:
			if(auxSprite % 2 == 0 && auxSprite < sprite.getSprites().length - 2)
				auxSprite+=2;
		
			else if(auxSprite % 2 == 1)
				auxSprite = 0;
			else
				auxSprite = 0;
			break;
				
		case DIREITA:
			if(auxSprite % 2 == 1 && auxSprite < sprite.getSprites().length - 1)
				auxSprite+=2;
			
			else if(auxSprite % 2 == 1)
				auxSprite = 1;
			else
				auxSprite = 1;
			break;

		}
		
		
	}

	@Override
	public void run() {
		
		atualiza();
		
	}
	
	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}
	
	public double getOxygen() {
		return oxygen;
	}

	public void setOxygen(double oxygen) {
		this.oxygen = oxygen;
	}

	public boolean isFimO() {
		return fimO;
	}

	public void setFimO(boolean fimO) {
		this.fimO = fimO;
	}

	public InfoJogador getPlayer() {
		return player;
	}

	public void setPlayer(InfoJogador player) {
		this.player = player;
	}
	
	public InfoJogador getDirecao() {
		return direcao;
	}

	public void setDirecao(InfoJogador direcao) {
		this.direcao = direcao;
	}

	public enum InfoJogador{
		JOGADOR1, JOGADOR2, DIREITA, ESQUERDA 
	}
	

}
