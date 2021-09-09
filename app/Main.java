package app;

import javax.swing.JOptionPane;

public class Main {
	
	// bem vindo ao jogo! atencao : para realizar o Roque, basta movimentar seu rei
	// para o local do Roque, seja ele pequeno ou grande(exemplo = e1:g1) que o roque sera feito, sendo
	// atentida as condicoes. bom jogo!
	
	public static void main(String[] args) {

		String jogador1 = JOptionPane.showInputDialog("Qual o nome do jogador 1? : ");
		String jogador2 = JOptionPane.showInputDialog("Qual o nome do jogador 2? : ");
	
		new MinhaJanela(jogador1, jogador2);
		

	}
}
