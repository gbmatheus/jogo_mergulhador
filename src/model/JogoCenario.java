package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import model.Jogador.InfoJogador;
import view.JanelaTeste2;

public class JogoCenario extends CenarioPadrao {

	Opcao opcao;// = Opcao.MULTI;
	
	Jogador jogadorUm, jogadorDois;
	Coisa[] coisa = new Coisa[50];
	Obstaculo[] obstaculo = new Obstaculo[50];
	InimigoBot iBot;
	
	Inventario inventario;
	
	BufferedImage background;
	
	Random random = new Random();

	//random instancia do elemento para adicionar premio/obstaculo
	int adiciona = 1; 
	int intervalo = 10;
	int temporizador = 0;
	int dificuldade = 1;
	
	
	public JogoCenario(int altura, int largura) {
		super(altura, largura);
	}

	@Override
	public void carregar() {
		for (int i = 0; i < coisa.length; i++) {
			coisa[i] = new Coisa();
			coisa[i].setLargura(22);
			coisa[i].setAltura(20);			
		}
		
		for (int i = 0; i < obstaculo.length; i++) {
			obstaculo[i] = new Obstaculo();
			obstaculo[i].setLargura(30);
			obstaculo[i].setAltura(20);
		}
		
		jogadorUm = new Jogador(JanelaTeste2.getJanelaLargura()/40, JanelaTeste2.getJanelaAltura()/2, 58, 40, InfoJogador.JOGADOR1);
		jogadorUm.setVel(5);
		jogadorUm.setVida(3);
		jogadorUm.setHp(20);
		jogadorUm.setOxygen(10);
//		jogadorUm.setPlayer(InfoJogador.JOGADOR1);
		jogadorUm.setAtivo(true);
		

		if(opcao == Opcao.MULTI) {
			jogadorDois = new Jogador(JanelaTeste2.getJanelaLargura() - 98, JanelaTeste2.getJanelaAltura()/2, 58, 40, InfoJogador.JOGADOR2);
			jogadorDois.setVel(5);
			jogadorDois.setVida(3);
			jogadorDois.setHp(20);
			jogadorDois.setOxygen(10);
//			jogadorDois.setPlayer(InfoJogador.JOGADOR2);'
			jogadorDois.setAtivo(true);
		}
		
		iBot = new InimigoBot(JanelaTeste2.getJanelaLargura() - (getLargura()/2), JanelaTeste2.getJanelaAltura(), 50, 46);
		iBot.setAtivo(true);
		iBot.setVel(8);
		
		inventario = new Inventario(jogadorUm, jogadorDois); 
		
		
		try {
			background = ImageIO.read(new File("resources/Fundo.png"));
//			background = ImageIO.read(new File("resources/Back.png"));
		} catch (IOException e) {e.printStackTrace();}
		
		
	}
	
	@Override
	public void descarregar() {
		jogadorUm = null;
		jogadorDois = null;
		coisa = null;
		obstaculo = null;
		iBot = null;
		inventario = null;
		background = null;
		
	}
	
	@Override
	public void atualizar() {
//		inventario.setVida(jogadorUm.getVida());
//		inventario.setPontos(jogadorUm.getPontos());
//		inventario.atualiza();
		
		if(jogadorUm.getVida() <= 0 && jogadorUm.getHp() <= 0)
			jogadorUm.setAtivo(false);
		
		if(jogadorUm.getVida() > 0 && jogadorUm.getHp() <= 0) {
			morreu(jogadorUm);
		}
		
		jogadorUm.run();
//		jogadorUm.atualiza();

		if(Util.colide(iBot, jogadorUm))
			morreu(jogadorUm);
					
		
		if(jogadorDois != null) {
			if(jogadorDois.getVida() <= 0 && jogadorDois.getHp() <= 0)
				jogadorDois.setAtivo(false);
			
			if(jogadorDois.getVida() > 0 && jogadorDois.getHp() <= 0) {
				morreu(jogadorDois);
			}
			
			jogadorDois.run();

			if(Util.colide(iBot, jogadorDois))
				morreu(jogadorDois);

		}
		
		iBot.atualiza();

		//Teste Seguir Jogador
//		iBot.setJ(jogador); 
				
		if(iBot.getPx() + iBot.getLargura() < 0 || iBot.getPx() < 0) {
			iBot.setPx(5 + iBot.getLargura());
//			System.out.println("Inimigo menor que Largura");
		}
		else if(iBot.getPx() + iBot.getLargura() > JanelaTeste2.getJanelaLargura() ||iBot.getPx() > JanelaTeste2.getJanelaLargura()) { 
			iBot.setPx(JanelaTeste2.getJanelaLargura() - 5 - iBot.getLargura());
//			System.out.println("Inimigo maior que Largura");
		}
		
		
		if(temporizador == intervalo) {
			temporizador = 0;
			adicionarObjeto();
		
		}else { 
			temporizador++;
		}
		
//		teste dificuldade
//		if(jogadorUm.getPontos() > 50 * dificuldade) {
//			 dificuldade++;
//			 System.out.println(dificuldade +" "+intervalo+" "+ adiciona);
//			 if(intervalo > 40) {
//				 intervalo -= 10;
//				 adiciona = 2;
//			 }else {
//				 intervalo = 30;
//				 adiciona = 3;			 	
//			 }
//		}
			
		
		for (Coisa obj : coisa) {
			
			if(!obj.isAtivo())
				continue;
			
			if(obj.getPy() < JanelaTeste2.getJanelaAltura() * 0.1 || obj.getPy() > obj.getAltura() + JanelaTeste2.getJanelaAltura()) {
				obj.setAtivo(false);
				continue;
			}
			
			if(obj.getPx() + obj.getLargura() <  0 || obj.getPx() + obj.getLargura() > JanelaTeste2.getJanelaLargura()) {
				obj.setAtivo(false);
				continue;
			}
			
			if(Util.colide(jogadorUm, obj)) {
				if(inventario.getCoisa()[inventario.getItem()].getTipo() == obj.getTipo()) {
						System.out.println("Tipos iguais");
						inventario.setItem(inventario.getItem() + 1);
						
						jogadorUm.setPontos(jogadorUm.getPontos() + 10);
								
						if(inventario.getCoisa().length == inventario.getItem())
							inventario.setItem(0);
				}else {
					jogadorUm.setPontos(jogadorUm.getPontos() + 5);
					System.out.println("Tipos diferentes");
				
				}
				obj.setAtivo(false);
				continue;
			}
			
//			Jogador Dois
			if(jogadorDois != null && Util.colide(jogadorDois, obj)) {
				if(inventario.getCoisa()[inventario.getItem()].getTipo() == obj.getTipo()) {
						System.out.println("Tipos iguais");
						inventario.setItem(inventario.getItem() + 1);
						
						jogadorDois.setPontos(jogadorDois.getPontos() + 10);
								
						if(inventario.getCoisa().length == inventario.getItem())
							inventario.setItem(0);
				}else {
					jogadorDois.setPontos(jogadorDois.getPontos() + 5);
					System.out.println("Tipos diferentes");
				
				}
				obj.setAtivo(false);
				continue;
			}
			
			if(Util.colide(iBot, obj)) {
				obj.setAtivo(false);
				
				if(jogadorUm.getPontos() > 0)
					jogadorUm.setPontos(jogadorUm.getPontos() -5);
				else
					jogadorUm.setPontos(0);
				
				if(jogadorDois != null){
					if(jogadorUm.getPontos() > 0)
						jogadorDois.setPontos(jogadorUm.getPontos() -5);
					else
						jogadorDois.setPontos(0);
				}
				continue;
			}
			
			obj.atualiza();
		}
		
		for(Obstaculo ob : obstaculo) {
			
			if(!ob.isAtivo())
				continue;
			
			if(ob.getPy() < JanelaTeste2.getJanelaAltura() * 0.1 || ob.getPy() > ob.getAltura() + JanelaTeste2.getJanelaAltura()) {
				ob.setAtivo(false);
				continue;
			}

			if(ob.getPx() + ob.getLargura() < 0 || ob.getPx() + ob.getLargura() > JanelaTeste2.getJanelaLargura()) {
				ob.setAtivo(false);
				continue;
			}
			
			if(Util.colide(jogadorUm, ob)) {
				ob.setAtivo(false);
				jogadorUm.setHp(jogadorUm.getHp() - 4);
				continue;
			}
			
//			Jogador Dois
			if(jogadorDois != null && Util.colide(jogadorDois, ob)) {
				ob.setAtivo(false);
				jogadorDois.setHp(jogadorDois.getHp() - 4);
				continue;
			}
			
			if(Util.colide(iBot, ob)) {
				ob.setAtivo(false);
				continue;
			}
			
			ob.atualiza();
		}
	
	}
	
	public void adicionarObjeto() {
		
		boolean premio = random.nextBoolean();
		int contador = 0;
		
		if(premio) {
			for (int i = 0; i < coisa.length; i++) {
				if(contador == adiciona)
					break;
				
				Coisa obj = coisa[i];
				
				if(obj.isAtivo())
					continue;
				
				contador++;
				obj.setAtivo(true);
				obj.setTipo(obj.definirTipo(random.nextInt(5)));//definir o tipo do lixo
				
				obj.setLargura(20);
				obj.setAltura(20);
				
				
				obj.setPx(random.nextInt(JanelaTeste2.getJanelaLargura()));
//				obj.setPy(-obj.getAltura());
				obj.setPy(JanelaTeste2.getJanelaAltura() * 15 / 100);
				obj.setVel(random.nextInt(2)+ 2);

			}
			
		
		}else {
			
			for (int i = 0; i < obstaculo.length; i++) {
				if(contador == adiciona)
					break;
				
				Obstaculo ob = obstaculo[i];
				
				if(ob.isAtivo())
					continue;
				
				contador++;
				ob.setAtivo(true);
				
				ob.setLargura(30);
				ob.setAltura(20);

				ob.setPx(random.nextInt(JanelaTeste2.getJanelaLargura()));
//				ob.setPy(-ob.getAltura());
				ob.setPy(JanelaTeste2.getJanelaAltura() * 15 / 100);
				ob.setVel(random.nextInt(2)+2);
				
				
			}
			System.out.println("Premio "+premio);
			
		}
		
	}
	
	@Override
	public void desenhar(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, JanelaTeste2.getJanelaLargura(), JanelaTeste2.getJanelaAltura()+JanelaTeste2.getJanelaInvetario());

		g.drawImage(background, 0, 0, null);
		
		for (int i = 0; i < coisa.length; i++) {
			coisa[i].desenha(g);
		}
		
		for (int i = 0; i < obstaculo.length; i++) {
			obstaculo[i].desenha(g);
		}
		jogadorUm.desenha(g);
		
		if(jogadorDois != null)
			jogadorDois.desenha(g);
		
		iBot.desenha(g);
		
		inventario.desenha(g);
	}
	
	
	public void morreu(Jogador jogador) {
		jogador.setVida(jogador.getVida() - 1);
		jogador.setHp(20);
		jogador.setOxygen(10);
		jogador.setPy(JanelaTeste2.getJanelaAltura()/10);
		System.out.println("Vida "+jogador.getVida());
		
		if(jogadorUm.getVida() <= 0)
			jogadorUm.setAtivo(false);
	
	}

	public Jogador getJogador() {
		return jogadorUm;
	}

	public void setJogador(Jogador jogador) {
		this.jogadorUm = jogador;
	}

	public Jogador getJogadorDois() {
		return jogadorDois;
	}

	public void setJogadorDois(Jogador jogadorDois) {
		this.jogadorDois = jogadorDois;
	}

	public Opcao getOpcao() {
		return opcao;
	}

	public void setOpcao(Opcao opcao) {
		this.opcao = opcao;
	}
	

}
