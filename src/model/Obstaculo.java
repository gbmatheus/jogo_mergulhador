package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;

import view.JanelaTeste2;

public class Obstaculo extends Elemento{

	private Sprite sprite;
	private int auxSprite = 0;
	
	public Obstaculo() {
		try {
			sprite = new Sprite("resources/Peixe.png", 1, 4, 30, 20);
		} catch (IOException e) {e.printStackTrace();System.exit(0);}
	}

	public Obstaculo(int px, int py, int largura, int altura) {
		super(px, py, largura, altura);
		
		try {
			sprite = new Sprite("resources/Peixe.png", 1, 4, 30, 20);
		} catch (IOException e) {e.printStackTrace();System.exit(0);}
	}
	
	@Override
	public void desenha(Graphics2D g) {

		if(!isAtivo())
			return;
		
		g.setColor(Color.RED);
//		g.fillOval(getPx(), getPy(), getLargura(), getAltura());
		
		g.drawImage(sprite.getSprites()[auxSprite], getPx(), getPy(), null);
		
	}
	
	@Override
	public void atualiza() {
		if(getPy() < JanelaTeste2.getJanelaAltura())
			if(auxSprite < sprite.getSprites().length - 1)
				auxSprite++;
			else
				auxSprite = 0;
			incPy(getVel());
					
	}
	
}
