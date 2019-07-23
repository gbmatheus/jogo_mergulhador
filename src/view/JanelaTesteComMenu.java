package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

import controller.Controle;
import model.CenarioPadrao;
import model.InicioCenario;
import model.Opcao;
import model.JogoCenario;

public class JanelaTesteComMenu extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	
	private static final int JANELA_LARGURA = 800;
	private static final int JANELA_ALTURA = 500;
	private static final int JANELA_INVETARIO = 100;
	
	private static final int FPS = 1000/20;

	CenarioPadrao cenario;
	Controle controleJogador;
//	ControleMouse controleMouse;
	
	JogoTela jogoTela;
	
	public JanelaTesteComMenu() {
		super("Coleta de Lixo - Oceano");
		
		jogoTela = new JogoTela();
		
//		cenario = new JogoCenario(JANELA_LARGURA, JANELA_ALTURA + JANELA_INVETARIO);
//		cenario.carregar();

//		controleMouse = new ControleMouse(this);
		
		cenario = new InicioCenario(JANELA_LARGURA, JANELA_ALTURA + JANELA_INVETARIO);
		cenario.carregar();
		
		controleJogador = new Controle(cenario);
//		addKeyListener(controleJogador);
		addMouseListener(controleJogador);

//		addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				if(e.getX() > 350 && e.getX() < 450 && e.getY() > 262 && e.getY() < 279) {
//					System.out.println("Jogar");
//					((InicioCenario)cenario).setOpcao(Opcao.JOGAR);
//					System.out.println(((InicioCenario)cenario).getOpcao());
//				
//				}else if(e.getX() > 350 && e.getX() < 450 && e.getY() > 281 && e.getY() < 298)
//					System.out.println("Opção");
//				
//				else if(e.getX() > 350 && e.getX() < 450 && e.getY() > 300 && e.getY() < 317)
//					System.out.println("Classificação");
//				
//				else if(e.getX() > 350 && e.getX() < 450 && e.getY() > 319 && e.getY() < 336)
//					System.out.println("Ajuda");
//				
//				else if(e.getX() > 350 && e.getX() < 450 && e.getY() > 338 && e.getY() < 355)
//					System.out.println("Sair");
//				
//				else
//					System.out.println("Menu Principal");
//			}
//
//		});
		

		getContentPane().add(jogoTela);

		
		setSize(JANELA_LARGURA, JANELA_ALTURA + JANELA_INVETARIO);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		jogoTela.repaint();

	}
	
	public void carregarJogo() {
		cenario = new InicioCenario(JANELA_LARGURA, JANELA_ALTURA + JANELA_INVETARIO);
		cenario.carregar();
	}

	@Override
	public void paint(Graphics g) {}
	
	@Override
	public void run() {
		
	}
	
	public void iniciarJogo() {
		long prxAtualizacao = 0;
		
		while(true) {
			if(System.currentTimeMillis() >= prxAtualizacao) {

				cenario.desenhar(jogoTela.getG2d());
//				
				if(cenario instanceof InicioCenario) {
					if(((InicioCenario) cenario).getOpcao() == Opcao.JOGAR) {
						cenario.descarregar();
						cenario = null;
						controleJogador = null;
						
						cenario = new JogoCenario(getWidth(), getHeight());
						cenario.carregar();
						controleJogador = new Controle(cenario);
						addKeyListener(controleJogador);
//						controleJogador = new ControleJogador(((JogoCenario) cenario).getJogador());
						
						
						jogoTela.getG2d().setColor(Color.WHITE);
						jogoTela.getG2d().drawString("Carregando...", 20, 20);
						
						jogoTela.repaint();
						
					}
				}
				else if(cenario instanceof JogoCenario) { 
					if(((JogoCenario) cenario).getJogador().isAtivo()){
						
//						controleJogador = new ControleJogador(((JogoCenario) cenario).getJogador());
						
						
						jogoTela.getG2d().setColor(Color.CYAN);
						jogoTela.getG2d().fillRect(0, 0, 1000, 1000);

//						controleJogador.setJogador(((JogoCenario) cenario).getJogador());
						controleJogador.controlaJogadorUm();
						cenario.atualizar();
						cenario.desenhar(jogoTela.getG2d());

//						Retirar caso não haja erro
//						jogoTela.repaint();
//						prxAtualizacao = System.currentTimeMillis() + FPS;
					}
				}
				jogoTela.repaint();
				prxAtualizacao = System.currentTimeMillis() + FPS;
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
		JanelaTesteComMenu janela = new JanelaTesteComMenu();
//		janela.carregarJogo();
		janela.iniciarJogo();
	}

}
