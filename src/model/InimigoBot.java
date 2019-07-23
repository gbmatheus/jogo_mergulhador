package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Random;

import view.JanelaTeste2;

public class InimigoBot extends Elemento implements Runnable{
	
	private Sprite sprite;
	private int auxSprite = 0;
	
	private boolean fim = false; 
	
//	private Jogador j; //Utilizado para o inimigo seguir o jogador
	
	public InimigoBot() {}

	public InimigoBot(int px, int py, int largura, int altura) {
		super(px, py, largura, altura);
		try {
			sprite = new Sprite("resources/AguaViva.png", 1, 9, 50, 46);

//			sprite = new Sprite("resources/AguaViva3.png", 1, 9, 75, 69);

		} catch (IOException e) {e.printStackTrace();System.exit(0);}
	}
	
	@Override
	public void desenha(Graphics2D g) {
		if(!isAtivo())
			return;
		
		g.drawImage(sprite.getSprites()[auxSprite], getPx(), getPy(), null);
	}
	
	@Override
	public void atualiza() {
		
		if(auxSprite < sprite.getSprites().length - 1)
			auxSprite++;
		else
			auxSprite = 0;
		
		if(getPy() > JanelaTeste2.getJanelaAltura() * 0.2 && fim == false) {
			incPy(-getVel());
		}
		else if(getPy() < JanelaTeste2.getJanelaAltura() && fim == true) {
			incPy(getVel());
		} 
		else if(getPy() == JanelaTeste2.getJanelaAltura() * 0.2 )
			fim = true;
		else {
//			if(!Util.colideX(this, j)) //inimigo seguir o jogador
//				setPx(j.getPx());
			setPx(new Random().nextInt(JanelaTeste2.getJanelaLargura()));
			fim = false;
			auxSprite=0;
		}
	}

	
	@Override
	public void run() {
		try {
			atualiza();
			Thread.sleep(50);
		} catch (InterruptedException e) {e.printStackTrace();}
		
	}

	public boolean isFim() {
		return fim;
	}

	public void setFim(boolean fim) {
		this.fim = fim;
	}

	public int getAuxSprite() {
		return auxSprite;
	}

	public void setAuxSprite(int auxSprite) {
		this.auxSprite = auxSprite;
	}

//	public void setJ(Jogador j) {
//		this.j = j;
//	}

}
