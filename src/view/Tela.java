package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Tela extends JFrame{
	
	BufferedImage telaInicio;
	
	public Tela() {
		
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		carregar();

		setVisible(true);
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, Janela.getJanelaLargura(), Janela.getJanelaAltura()+Janela.getJanelaInvetario());
		
		g.drawImage(telaInicio, 0, 0, null);
		
		
	}

	public void carregar() {
		try {
			telaInicio = ImageIO.read(new File("resources/Inicio2.png"));
		} catch (IOException e) {e.printStackTrace();}
	}

	public BufferedImage getTelaInicio() {
		return telaInicio;
	}

	public void setTelaInicio(BufferedImage telaInicio) {
		this.telaInicio = telaInicio;
	}
	
	public static void main(String[] args) {
		new Tela();
	}
	
}
