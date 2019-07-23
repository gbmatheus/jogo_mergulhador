package model;

import java.awt.Color;
import java.awt.Graphics2D;

public class Elemento{
	
	private int px;
	private int py;
	private int largura;
	private int altura;
	
	private int vel;
	private boolean ativo;
	
	private Color color;
	private Sprite sprite;
	
	public Elemento() {}

	public Elemento(int px, int py, int largura, int altura) {
		this.px = px;
		this.py = py;
		this.largura = largura;
		this.altura = altura;
	}
	
	public void atualiza() {}
	
	public void desenha(Graphics2D g) {}
	
	public void incPx(double x) { px = px+(int)x;}
	public void incPy(double y) { py = py+(int)y;}

	public void incPxCos(double x, int angulo) { 
	
		px = (int) (px + ( (x) * Math.cos(Math.toRadians(angulo)))) ;
		
	}
	public void incPySin(double y, int angulo) { 
		py = (int) (py + ( (y) * Math.sin(Math.toRadians(angulo)))) ;
	}

	public int getPx() {
		return px;
	}

	public int getPy() {
		return py;
	}

	public void setPy(int py) {
		this.py = py;
	}

	public int getLargura() {
		return largura;
	}

	public void setLargura(int largura) {
		this.largura = largura;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getVel() {
		return vel;
	}

	public void setVel(int vel) {
		this.vel = vel;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public void setPx(int px) {
		this.px = px;
	}
	
}
