package model;

import java.awt.Color;
import java.awt.Graphics2D;

import view.JanelaTeste2;

public class InimigoTop extends Elemento{
	
	boolean comeco = false;
	
	public InimigoTop() {}

	public InimigoTop(int px, int py, int largura, int altura) {
		super(px, py, largura, altura);
	}
	
	@Override
	public void desenha(Graphics2D g) {
		if(!isAtivo())
			return;
		
		g.setColor(Color.GREEN);
		g.fillRect(getPx(), getPy(), getLargura(), getAltura());
	}
	
	@Override
	public void atualiza() {
		System.out.println("X " + getPx() + " Começo "+comeco);
		
		if(getPx() > JanelaTeste2.getJanelaLargura() * 0 && comeco == false)
			incPx(-getVel());			
			
		else if(getPx() + getLargura() < JanelaTeste2.getJanelaLargura() && comeco == true)
			incPx(getVel());
		
		else if(getPx() == JanelaTeste2.getJanelaLargura() * 0)
			comeco = true;
		
		else
			comeco = false;	
		
	}

	public boolean isFim() {
		return comeco;
	}

	public void setFim(boolean fim) {
		this.comeco = fim;
	}

}
