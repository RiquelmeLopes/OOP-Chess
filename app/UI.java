package app;

import java.util.InputMismatchException;
import java.util.Scanner;

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
	
	public static void printTabuleiro(XadrezPeca[][] pecas) {
		for (int i = 0; i<pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j<pecas.length; j++) {
				printPeca(pecas[i][j]);
			}
			System.out.println(); 
		}
		System.out.println("  a b c d e f g h");
	}
	public static void printTabuleiro(XadrezPeca[][] pecas, boolean[][] possibleMoves) {
		for (int i = 0; i<pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j<pecas.length; j++) {
				printPeca(pecas[i][j]);
			}
			System.out.println(); 
		}
		System.out.println("  a b c d e f g h");
	}

	private static void printPeca(XadrezPeca peca) {
    	if (peca == null) {
            System.out.print("-");
        }
        else {
                System.out.print(peca);
            }
        System.out.print(" ");
	}
	
}
