package view;

import javax.swing.JOptionPane;

public class Mensagem {
	
	public static String entradaNome() {
		return JOptionPane.showInputDialog("Digite seu nome");
		
	}
	
	public static void exibirRank(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

}
