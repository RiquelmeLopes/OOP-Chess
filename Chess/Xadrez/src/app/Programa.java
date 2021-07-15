package app;

import xadrez.XadrezMatch;

public class Programa {

	public static void main(String[] args) {

		XadrezMatch chessMatch  = new XadrezMatch();
		UI.printTabuleiro(chessMatch.getPecas());
		
		
	}
	}
