package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class JogoTela extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private Graphics2D g2d;
	private BufferedImage buffer;
	
	public JogoTela() {
		
		buffer = new BufferedImage(JanelaTeste2.getJanelaLargura(), JanelaTeste2.getJanelaAltura()+JanelaTeste2.getJanelaInvetario(), BufferedImage.TYPE_INT_BGR);
		
		g2d = buffer.createGraphics();
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(buffer, 0, 0, null);
	}

	public Graphics2D getG2d() {
		return g2d;
	}

	public BufferedImage getBuffer() {
		return buffer;
	}
	
}
