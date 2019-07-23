package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import view.JanelaTeste2;

public class Coisa extends Elemento implements Runnable{
	
	private Tipo tipo;
	private Sprite sprite;
	private int auxSprite = 0, spriteFim;
	private boolean fim = false;
	
	private BufferedImage logo, lixo;
	
	public Coisa() {}

	public Coisa(int px, int py, int largura, int altura) {
		super(px, py, largura, altura);
	}
	

	@Override
	public void desenha(Graphics2D g) {
		
		if(!isAtivo())
			return;
		
		switch (tipo) {
		case VERDE:
			g.setColor(Color.GREEN);
			try {
				sprite = new Sprite("resources/A.png", 1, 4, 22, 20);
			} catch (IOException e) {e.printStackTrace();System.exit(0);}
			spriteFim = sprite.getSprites().length - 2;
			break;
		case AMARELO:
			g.setColor(Color.YELLOW);
			try {
				sprite = new Sprite("resources/B.png", 1, 4, 22, 20);
			} catch (IOException e) {e.printStackTrace();}
			spriteFim = sprite.getSprites().length - 2;
			break;
		case AZUL:
			g.setColor(Color.BLUE);
			try {
				sprite = new Sprite("resources/C.png", 1, 4, 22, 20);
			} catch (IOException e) {e.printStackTrace();}
			spriteFim = sprite.getSprites().length - 2;
			break;
		case VERMELHO:
			g.setColor(Color.RED);	
			try {
				sprite = new Sprite("resources/D.png", 1, 4, 22, 20);
			} catch (IOException e) {e.printStackTrace();}
			spriteFim = sprite.getSprites().length - 2;
			break;
		case CINZA:
			g.setColor(Color.GRAY);
			try {
				sprite = new Sprite("resources/E.png", 1, 4, 22, 20);
			} catch (IOException e) {e.printStackTrace();}
			spriteFim = sprite.getSprites().length - 2;
			break;
		}
		
//		g.drawImage(sprite.getSprites()[auxSprite], getPx(), getPy(), null);
		g.drawImage(lixo, getPx(), getPy(),null);
		
	}
	
	@Override
	public void atualiza() {
		if(getPy() < JanelaTeste2.getJanelaAltura()) {
			incPy(getVel());
			
			if(auxSprite < spriteFim && fim == false) 
				auxSprite++;
			else if(auxSprite > 0 && fim == true)
				auxSprite--;
			else if(auxSprite == spriteFim)
				fim = true;
			else { 
				auxSprite = 0;
				fim = false;
			}
		}
		
	}
	
	public Tipo definirTipo(int numero) {
//		System.out.println("Numero - "+numero);
		if(numero == 0) {
			try {
				logo = ImageIO.read(new File("resources/logo/Verde.png"));
				lixo = ImageIO.read(new File("resources/lixo/Vidro"+ (new Random().nextInt(2) + 1) +".png"));
			} catch (IOException e) {e.printStackTrace();
			}
			return Tipo.VERDE;
		
		}else if(numero == 1) {
			try {
				logo = ImageIO.read(new File("resources/logo/Amarelo.png"));
				lixo = ImageIO.read(new File("resources/lixo/Metal"+ (new Random().nextInt(2) + 1) +".png"));
			} catch (IOException e) {e.printStackTrace();
			}
			return Tipo.AMARELO;
		}else if(numero == 2) {
			try {
				logo = ImageIO.read(new File("resources/logo/Azul.png"));
				lixo = ImageIO.read(new File("resources/lixo/Papel"+ (new Random().nextInt(2) + 1) +".png"));
			} catch (IOException e) {e.printStackTrace();
			}
			return Tipo.AZUL;
		}else if(numero == 3) {
			try {
				logo = ImageIO.read(new File("resources/logo/Vermelho.png"));
				lixo = ImageIO.read(new File("resources/lixo/Plastico"+ (new Random().nextInt(2) + 1) +".png"));
			} catch (IOException e) {e.printStackTrace();
			}
			return Tipo.VERMELHO;
		}else {
			try {
				logo = ImageIO.read(new File("resources/logo/Cinza.png"));
				lixo = ImageIO.read(new File("resources/lixo/Organico"+ (new Random().nextInt(3) + 1) +".png"));
			} catch (IOException e) {e.printStackTrace();
			}
			return Tipo.CINZA;
		}
				
		
	}
	
	@Override
	public void run() {}
	
	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
//	public Sprite getSprite() {
//		return sprite;
//	}
//
//	public void setSprite(Sprite sprite) {
//		this.sprite = sprite;
//	}
	

	public BufferedImage getLogo() {
		return logo;
	}



	public enum Tipo {
		VERDE, AMARELO, AZUL, VERMELHO, CINZA;
	}

	

}
