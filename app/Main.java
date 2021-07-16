package app;

import java.util.Scanner;

import xadrez.XadrezMatch;
import xadrez.XadrezPeca;
import xadrez.XadrezPosicao;

public class Main {

	public static void main(String[] args) {
		
		String jogador1;
		String jogador2
			
		Scanner sc = new Scanner(System.in);
		XadrezMatch xadrezMatch  = new XadrezMatch();
		
		System.out.println("Jogador 1, digite seu nome :");
		jogador1 = sc.nextLine();
		System.out.println("Jogador 2, digite seu nome :");
		jogador2 = sc.nextLine();
		
		while (true) {	
		UI.printTabuleiro(xadrezMatch.getPecas());
		System.out.println();
		System.out.print(jogador1 + " , digite a peça que quer mover : ");
		XadrezPosicao source = UI.readXadrezPosicao(sc);
		
		boolean[][] possibleMoves = xadrezMatch.possibleMoves(source);
		
		System.out.println();
		System.out.print(jogador1 + ", digite para onde quer mover a peça : ");
		XadrezPosicao target = UI.readXadrezPosicao(sc);

		XadrezPeca capturadaPeca = xadrezMatch.performChessMove(source, target);
	}	
	}
	}
