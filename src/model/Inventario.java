package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import view.JanelaTeste2;

//Alterar o item a ser mostrado
public class Inventario {
	
	private Jogador jogadorUm, jogadorDois;
	
	private int vida;
	private int pontos;
	private int nivel = 1;
	private Coisa[] coisa = new Coisa[5 * nivel];
	private BufferedImage[] logo = new BufferedImage[5 * nivel];
	private BufferedImage logoPlayer1,logoPlayer2;
	private BufferedImage backInventario;
	
	private int item = 0;
	
	
	
	public Inventario(Jogador jogadorUm) {
		this.jogadorUm = jogadorUm;
		
		carregar();

	}

	public Inventario(Jogador jogadorUm, Jogador jogadorDois) {
		this.jogadorUm = jogadorUm;
		this.jogadorDois = jogadorDois;
		
		carregar();
		
	}

	
	public void carregar() {
		for(int i = 0;i< coisa.length; i++) {
			coisa[i] = new Coisa();
			coisa[i].setTipo(coisa[i].definirTipo(i));
			coisa[i].setPx(JanelaTeste2.getJanelaLargura()/2 - 28);
			coisa[i].setPy(JanelaTeste2.getJanelaAltura() + 30);
			coisa[i].setAtivo(true);
		
		}
		
		try {
//			backInventario= ImageIO.read(new File("resources/Inventario2.png"));
			backInventario = ImageIO.read(new File("resources/Inventario1.png"));
			logoPlayer1 = ImageIO.read(new File("resources/LogoP11.png"));
			logoPlayer2 = ImageIO.read(new File("resources/LogoP21.png"));
		} catch (IOException e) {e.printStackTrace();
		}
	}
	
	public void desenha(Graphics2D g) {
		
		g.setColor(new Color(202020));
		g.fillRect(0, JanelaTeste2.getJanelaAltura(), JanelaTeste2.getJanelaLargura(), JanelaTeste2.getJanelaAltura() + JanelaTeste2.getJanelaInvetario());
//		g.drawImage(backInventario, 0, JanelaTeste2.getJanelaAltura(), null);
		g.drawImage(backInventario, 0, JanelaTeste2.getJanelaAltura() - 28, null);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Consolas", Font.ITALIC + Font.BOLD, 16));
		
		g.drawString("JOGADOR 1 ", 20, JanelaTeste2.getJanelaAltura() + 20);
		for(int i = jogadorUm.getVida();i > 0 ;i--) {
			g.drawImage(logoPlayer1, 80 + (25 * i),  JanelaTeste2.getJanelaAltura() + 3, null);
			
		}
			
		g.drawString("HP ", 20, JanelaTeste2.getJanelaAltura() + 40);
		g.drawRect(100, JanelaTeste2.getJanelaAltura() + 28, 100, 10);
//		g.fillRect(100, JanelaTeste2.getJanelaAltura() + 8, (20 * vida), 10);
		g.fillRect(100, JanelaTeste2.getJanelaAltura() + 28, (5 * jogadorUm.getHp()), 10);
		
		
		g.drawString("OXIGÊNIO ", 20, JanelaTeste2.getJanelaAltura() + 60);
		g.drawRect(100, JanelaTeste2.getJanelaAltura() + 48, 100, 10);
		g.fillRect(100, JanelaTeste2.getJanelaAltura() + 48, (int) (10 * jogadorUm.getOxygen()), 10);
		
		g.drawString("PONTOS "+ jogadorUm.getPontos(), 20, JanelaTeste2.getJanelaAltura() + 80);
		
		g.drawString("COLETE - "+coisa[item].getTipo(), JanelaTeste2.getJanelaLargura()/2 - 55, JanelaTeste2.getJanelaAltura() + 20);
//		g.drawString("COLETE - "+coisa[item].getTipo(), JanelaTeste2.getJanelaLargura()/2 - 55, JanelaTeste2.getJanelaAltura() + 20);
		g.drawImage(coisa[item].getLogo(), JanelaTeste2.getJanelaLargura()/2 - 28, JanelaTeste2.getJanelaAltura() + 30, null);
//		coisa[item].desenha(g);
		
		if(jogadorDois != null) {
//		Jogador 2
			g.setColor(Color.WHITE);
			g.setFont(new Font("Consolas", Font.ITALIC + Font.BOLD, 16));
			g.drawString("JOGADOR 2", JanelaTeste2.getJanelaLargura()/2 + 180, JanelaTeste2.getJanelaAltura() + 20);
			for(int i = jogadorDois.getVida();i > 0 ;i--) {
				g.drawImage(logoPlayer2, JanelaTeste2.getJanelaLargura()/2 + 260 + (25 * i),  JanelaTeste2.getJanelaAltura() + 3, null);
				
			}
			
			g.drawString("HP ", JanelaTeste2.getJanelaLargura()/2 + 180, JanelaTeste2.getJanelaAltura() + 40);
			g.drawRect(JanelaTeste2.getJanelaLargura()/2 + 280, JanelaTeste2.getJanelaAltura() + 28, 100, 10);
			g.fillRect(JanelaTeste2.getJanelaLargura()/2 + 280, JanelaTeste2.getJanelaAltura() + 28, (5 * jogadorDois.getHp()), 10);

			g.drawString("OXIGÊNIO ", JanelaTeste2.getJanelaLargura()/2 + 180, JanelaTeste2.getJanelaAltura() + 60);
			g.drawRect(JanelaTeste2.getJanelaLargura()/2 + 280, JanelaTeste2.getJanelaAltura() + 48, 100, 10);
			g.fillRect(JanelaTeste2.getJanelaLargura()/2 + 280, JanelaTeste2.getJanelaAltura() + 48, (int) (10 * jogadorDois.getOxygen()), 10);

			g.drawString("PONTOS "+ jogadorDois.getPontos(), JanelaTeste2.getJanelaLargura()/2 + 180, JanelaTeste2.getJanelaAltura() + 80);
		
		}
		
	}
	
	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public Coisa[] getCoisa() {
		return coisa;
	}

	public void setCoisa(Coisa[] coisa) {
		this.coisa = coisa;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public Jogador getJogadorUm() {
		return jogadorUm;
	}

	public void setJogadorUm(Jogador jogadorUm) {
		this.jogadorUm = jogadorUm;
	}

	public Jogador getJogadorDois() {
		return jogadorDois;
	}

	public void setJogadorDois(Jogador jogadorDois) {
		this.jogadorDois = jogadorDois;
	}

}
