package app;

import java.util.Scanner;

import xadrez.XadrezMatch;
import xadrez.XadrezPeca;
import xadrez.XadrezPosicao;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		XadrezMatch xadrezMatch  = new XadrezMatch();
		
		while (true) {	
		UI.printTabuleiro(xadrezMatch.getPecas());
		System.out.println();
		System.out.print("source: ");
		XadrezPosicao source = UI.readXadrezPosicao(sc);
		
		boolean[][] possibleMoves = xadrezMatch.possibleMoves(source);
		
		System.out.println();
		System.out.print("target: ");
		XadrezPosicao target = UI.readXadrezPosicao(sc);

		XadrezPeca capturadaPeca = xadrezMatch.performChessMove(source, target);
	}	
	}
	}
