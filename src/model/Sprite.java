package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {

	private String localSprite; // diretorio da sprite

	private BufferedImage spriteSheet;// = ImageIO.read(new File(localSprite));

	private int linhas; // linhas
	private int colunas; // colunas
	private BufferedImage[] sprites;

	private int largura; // largura
	private int altura; // altura
	
//	private int auxSprite = 0;

	public Sprite() throws IOException {

		// this.width = spriteSheet.getWidth()/columns;
		// this.height = spriteSheet.getHeight()/rows;
		this.localSprite = "";
		spriteSheet = ImageIO.read(new File(localSprite));
		
		sprites = new BufferedImage[this.colunas * this.linhas];
		for (int i = 0; i < this.colunas; i++) {
			for (int j = 0; j < this.linhas; j++) {
				sprites[(i * this.linhas) + j] = spriteSheet.getSubimage(i * largura, j * altura, largura, altura);
			}
		}

	}

	public Sprite(String localSprite, int linhas, int colunas, 
			int largura, int altura) throws IOException {

		this.localSprite = localSprite;
		this.linhas = linhas;
		this.colunas = colunas;
		this.largura = largura;
		this.altura = altura;
		
		spriteSheet = ImageIO.read(new File(localSprite));

		// this.width = spriteSheet.getWidth()/columns;
		// this.height = spriteSheet.getHeight()/rows;

		sprites = new BufferedImage[this.colunas * this.linhas];
		for (int i = 0; i < this.colunas; i++) {
			for (int j = 0; j < this.linhas; j++) {
				sprites[(i * this.linhas) + j] = spriteSheet.getSubimage(i * largura, j * altura, largura, altura);
			}
		}

	}

	public String getLocalSprite() {
		return localSprite;
	}

	public void setLocalSprite(String localSprite) {
		this.localSprite = localSprite;
	}

	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

	public void setSpriteSheet(BufferedImage spriteSheet) {
		this.spriteSheet = spriteSheet;
	}

	public int getLinhas() {
		return linhas;
	}

	public void setLinhas(int linhas) {
		this.linhas = linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public void setColunas(int colunas) {
		this.colunas = colunas;
	}

	public BufferedImage[] getSprites() {
		return sprites;
	}

	public void setSprites(BufferedImage[] sprites) {
		this.sprites = sprites;
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
	
	

//	public int getAuxSprite() {
//		return auxSprite;
//	}
//
//	public void setAuxSprite(int auxSprite) {
//		this.auxSprite = auxSprite;
//	}

}
