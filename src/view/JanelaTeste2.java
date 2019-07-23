package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

import controller.ControleJogador;
import controller.ControleMouse;
import model.CenarioPadrao;
import model.InicioCenario;
//import model.InicioCenario.Opcao;

import model.JogoCenario;
import model.Opcao;
import model.Rank;

public class JanelaTeste2 extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	
	private static final int JANELA_LARGURA = 800;
	private static final int JANELA_ALTURA = 500;
	private static final int JANELA_INVETARIO = 100;
	
	private static final int FPS = 1000/20;
	

	CenarioPadrao cenario;
	ControleJogador controleJogador;
	ControleMouse controleMouse;
	
	JogoTela jogoTela;
	
	public JanelaTeste2() {
		super("Coleta de Lixo - Oceano");
		
		jogoTela = new JogoTela();
		
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
		
		if(cenario != null)
			cenario.descarregar();
		
		cenario = null;
		
		cenario = new InicioCenario(JANELA_LARGURA, JANELA_ALTURA + JANELA_INVETARIO);
		cenario.carregar();
		controleMouse = new ControleMouse((InicioCenario) cenario);
		addMouseListener(controleMouse);

	}

	@Override
	public void paint(Graphics g) {}
	
	@Override
	public void run() {
		
	}
	
	
//	Alterar para Thread/ Run  
	public void iniciarJogo() {
		long prxAtualizacao = 0;
		
		while(true) {
			if(System.currentTimeMillis() >= prxAtualizacao) {

				cenario.desenhar(jogoTela.getG2d());
				
				if(cenario instanceof InicioCenario) {
					if(((InicioCenario) cenario).getOpcao() == Opcao.JOGAR) {
						
						cenario.descarregar();
						cenario = null;
						removeMouseListener(controleMouse);
//						controleMouse = null;
										
						cenario = new JogoCenario(JANELA_LARGURA, JANELA_ALTURA + JANELA_INVETARIO);
						cenario.carregar();
						
						controleJogador = new ControleJogador(((JogoCenario) cenario));
						addKeyListener(controleJogador);
						
						
						jogoTela.getG2d().setColor(Color.WHITE);
						jogoTela.getG2d().drawString("Carregando...", 20, 20);
						
						jogoTela.repaint();
						
					}else if(((InicioCenario) cenario).getOpcao() == Opcao.MULTI) {
						
						cenario.descarregar();
						cenario = null;
						removeMouseListener(controleMouse);
//						controleMouse = null;
										
						cenario = new JogoCenario(JANELA_LARGURA, JANELA_ALTURA + JANELA_INVETARIO);
						((JogoCenario) cenario).setOpcao(Opcao.MULTI);
						cenario.carregar();
						
						controleJogador = new ControleJogador(((JogoCenario) cenario));
						addKeyListener(controleJogador);
						
						
						jogoTela.getG2d().setColor(Color.WHITE);
						jogoTela.getG2d().drawString("Carregando...", 20, 20);
						
						jogoTela.repaint();
						
					
					}else if (((InicioCenario) cenario).getOpcao() == Opcao.AJUDA) {
						cenario.desenhar(jogoTela.getG2d());
						jogoTela.repaint();
					}
						
				}
				else if(cenario instanceof JogoCenario) { 
					if(((JogoCenario) cenario).getJogador().isAtivo()) {// && ((JogoCenario) cenario).getJogadorDois().isAtivo()){						
						
						controleJogador.controlaJogadorUm();
						
						if(((JogoCenario) cenario).getJogadorDois() != null)
							controleJogador.controlaJogadorDois();
						
						cenario.atualizar();
						cenario.desenhar(jogoTela.getG2d());

					}
					
					else { 
						fimDeJogo(jogoTela.getG2d());
						jogoTela.repaint();
						
						try {
							Thread.sleep(2000);
							if(((JogoCenario) cenario).getJogadorDois() == null) {
								String nome = Mensagem.entradaNome();
								Rank.rankingTXT(nome, ((JogoCenario)cenario).getJogador().getPontos());
							}
							
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						
						cenario.descarregar();
						cenario = null;
						removeKeyListener(controleJogador);
//						controleJogador = null;
						
//						cenario = new InicioCenario(JANELA_LARGURA, JANELA_ALTURA + JANELA_INVETARIO);
//						cenario.carregar();
						carregarJogo();

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

	public void fimDeJogo(Graphics2D g) {
		
			String fim = "FIM DE JOGO";
			
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, JanelaTeste2.getJanelaLargura(), JanelaTeste2.getJanelaAltura() + JanelaTeste2.getJanelaInvetario());


			g.setColor(Color.WHITE);
			g.setFont(new Font("Consolas", Font.BOLD + Font.ITALIC, 60));
			
			g.drawString(fim, JanelaTeste2.getJanelaLargura()/2 - 175, 
					JanelaTeste2.getJanelaAltura()/2 + JanelaTeste2.getJanelaInvetario()/2);
			
	}
	
	public static void main(String[] args) {
		JanelaTeste2 janela = new JanelaTeste2();
		janela.carregarJogo();
		janela.iniciarJogo();
	}

}
