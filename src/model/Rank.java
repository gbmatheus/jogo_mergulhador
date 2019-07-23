package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import view.Mensagem;

public class Rank {

	public static void rankingTXT(String nome, int pontuacao) {
		String txt = "\nNome "+nome + "- Pontuação "+ String.valueOf(pontuacao);

		try {
			OutputStream os = new FileOutputStream("arquivo.txt", true);
			OutputStreamWriter ows = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(ows);

			bw.write(txt);
			bw.newLine();

			bw.close();

			PrintWriter ps = new PrintWriter("rank.txt");
			ps.write(txt + "\n");
			ps.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void rankingXML(String nome, int pontuacao) {
		
		
	}
	
	public static void rankingExibir() {
		String s = "Classificação\n";
		try {
			InputStream  is = new FileInputStream("arquivo.txt");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			while(br.readLine() != null) {
				s += br.readLine() + "\n";	
			}
			
			br.close();
			
			Mensagem.exibirRank(s);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
