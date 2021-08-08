package app;

import java.util.Scanner;

import xadrez.Cor;
import xadrez.XadrezMatch;
import xadrez.XadrezPeca;
import xadrez.XadrezPosicao;

public class Main {

	public static void main(String[] args) {

		boolean jogando = true;
		String jogador1;
		String jogador2;

		Scanner sc = new Scanner(System.in);
		XadrezMatch xadrezMatch = new XadrezMatch();

		System.out.println("Jogador 1, digite seu nome :");
		jogador1 = sc.nextLine();
		System.out.println("Jogador 2, digite seu nome :");
		jogador2 = sc.nextLine();

		while (jogando) {

			UI.printTabuleiro(xadrezMatch.getPecas());
			System.out.println();
			if (xadrezMatch.getCurrentPlayer() == Cor.WHITE) {
				System.out.print(jogador1 + " , digite seu movimento : ");
			} else
				System.out.print(jogador2 + " , digite seu movimento : ");

			String movimento = sc.nextLine();
			String s[] = movimento.split(":");
			String movOrigem = s[0];
			String movDestino = s[1];

			XadrezPosicao source = UI.readXadrezPosicao(movOrigem);
			boolean[][] possibleMoves = xadrezMatch.possibleMoves(source);

			XadrezPosicao target = UI.readXadrezPosicao(movDestino);

			XadrezPeca capturadaPeca = xadrezMatch.performChessMove(source, target);
		}
		sc.close();
	}
}
