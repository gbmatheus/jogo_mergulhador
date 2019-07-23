package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.ControleJogadorTeste;
import model.CenarioPadrao;
import model.JogoCenario;

public class Janela extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	private static final int JANELA_LARGURA = 800;
	private static final int JANELA_ALTURA = 500;
	private static final int JANELA_INVETARIO = 100;

	CenarioPadrao cenario;
	ControleJogadorTeste controleJogador;
	
//	public static BufferedImage tela; evitar da tela piscar

	public Janela(String titulo, int x, int y) {
		super(titulo);
		
		cenario = new JogoCenario(getWidth(), getHeight());
		cenario.carregar();
//		
//		inventarioMenu = new InventarioTela();
//		inventarioMenu.repaint();
//		
		controleJogador = new ControleJogadorTeste(((JogoCenario) cenario).getJogador());
		
		addKeyListener(controleJogador);
	
		
		setSize(x, y);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setUndecorated(true);
		setVisible(true);

		repaint();

	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		
		if(((JogoCenario) cenario).getJogador().isAtivo()) {
			cenario.desenhar(g2d);
//			g2d.drawImage(tela, 0, 0, null);
			
			
		}else {
			try {
				cenario.desenhar(g2d);//gambiarra
				Thread.sleep(500);//gambiarra
				
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, JANELA_LARGURA, JANELA_LARGURA);


				g.setColor(Color.WHITE);
				g.drawLine(0, JANELA_ALTURA/2, JANELA_LARGURA, JANELA_ALTURA/2);
				g.drawLine(JANELA_LARGURA/2, 0, JANELA_LARGURA/2, JANELA_ALTURA);
				g.setFont(new Font("Consolas", Font.BOLD, 40));
				g.drawString("FIM DO JOGO", JANELA_LARGURA/2 - 120, JANELA_ALTURA/2 + 10);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
	
	
	private void carregarJogo() {
		
	}

	@Override
	public void update(Graphics g) {
		super.update(g);
	}

	@Override
	public void run() {
		while (((JogoCenario) cenario).getJogador().isAtivo()) {
			try {
//				tela = new BufferedImage(640, 640, BufferedImage.TYPE_4BYTE_ABGR);
				cenario.atualizar();
				Thread.sleep(100);
				repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	public static int getJanelaLargura() {
		return JANELA_LARGURA;
	}

	public static int getJanelaAltura() {
		return JANELA_ALTURA;
	}
	
	public static int getJanelaInvetario() {
		return JANELA_INVETARIO;
	}


	public static void main(String[] args) {
		Janela janela = new Janela("janela", JANELA_LARGURA, JANELA_ALTURA + JANELA_INVETARIO);
		janela.run();
	}

}
