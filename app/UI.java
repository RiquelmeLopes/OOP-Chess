package app;

import java.util.InputMismatchException;


import xadrez.XadrezMatch;
import xadrez.XadrezPeca;
import xadrez.XadrezPosicao;

public class UI {
	
	public static XadrezPosicao readXadrezPosicao(String s) {
		try {
		char column = s.charAt(0);
		int row = Integer.parseInt(s.substring(1));
		return new XadrezPosicao(column, row);
		
		}
		catch (RuntimeException e) {
			throw new InputMismatchException("Erro, valores validos de a1 ate h8.");
		}
	}
	
	public static void printMatch(XadrezMatch xadrezMatch) {
		printTabuleiro(XadrezMatch.getPecas());
		System.out.println();
		System.out.println("Turn:" + xadrezMatch.getTurn());
		System.out.println("Esperando jogador: " + xadrezMatch.getCurrentPlayer());

	}
	
	public static String printTabuleiro(XadrezPeca[][] pecas) {
		
		String tabuleiro = "";
		
		for (int i = 0; i<pecas.length; i++) {
			tabuleiro += " " + (8 - i);
			for (int j = 0; j<pecas.length; j++) {
				tabuleiro += " " + printPeca(pecas[i][j]);
			}
			tabuleiro += "\n";
		}
		tabuleiro += "     A B C D E F G H";
		
		return tabuleiro;
	}
	
	public static String printTabuleiro(XadrezPeca[][] pecas, boolean[][] possibleMoves) {
		
		String tabuleiro = " ";
		
		for (int i = 0; i<pecas.length; i++) {
			tabuleiro += (8 - i) + " ";
			for (int j = 0; j<pecas.length; j++) {
				tabuleiro += printPeca(pecas[i][j]);
			}
			tabuleiro += " "; 
		}
		tabuleiro += "  A B C D E F G H";
		
		return tabuleiro;
	}

	private static String printPeca(XadrezPeca peca) {

		String tabPeca;

		if (peca == null) {
			tabPeca = " -";
		} else {
			tabPeca = peca.toString();
		}
		tabPeca += " ";
		return tabPeca;
	}
	
	
}
