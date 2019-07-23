package model;

import java.awt.Graphics2D;

public abstract class CenarioPadrao {
	
	private int altura, largura;

	public CenarioPadrao(int altura, int largura) {
		this.altura = altura;
		this.largura = largura;
	}
	
	public abstract void carregar();
	
	public abstract void descarregar();
	
	public abstract void atualizar();
	
	public abstract void desenhar(Graphics2D g);

	
	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getLargura() {
		return largura;
	}

	public void setLargura(int largura) {
		this.largura = largura;
	}

	
	
}
