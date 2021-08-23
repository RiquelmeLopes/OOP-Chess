package app;

import java.util.Scanner;

import xadrez.Cor;
import xadrez.XadrezExcecao;
import xadrez.XadrezMatch;
import xadrez.XadrezPeca;
import xadrez.XadrezPosicao;

public class Main {
	
	// bem vindo ao jogo! atencao : para realizar o Roque, basta movimentar seu rei
	// para o local do Roque, seja ele pequeno ou grande(exemplo = e1:g1) que o roque sera feito, sendo
	// atentida as condicoes. bom jogo!
	
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

		while (!xadrezMatch.getXequeMate()) {

			try {

				UI.printTabuleiro(xadrezMatch.getPecas());
				System.out.println();

				if (xadrezMatch.getXeque()) {
					System.out.println("xeque.");
				}

				if (xadrezMatch.getCurrentPlayer() == Cor.WHITE) {
					System.out.print(jogador1 + " , digite seu movimento : ");
				} else
					System.out.print(jogador2 + " , digite seu movimento :  ");

				String movimento = sc.nextLine();
				
				String s[] = movimento.split(":");
				String movOrigem = s[0];
				String movDestino = s[1];

				XadrezPosicao source = UI.readXadrezPosicao(movOrigem);
				boolean[][] possibleMoves = xadrezMatch.possibleMoves(source);

				XadrezPosicao target = UI.readXadrezPosicao(movDestino);

				XadrezPeca capturadaPeca = xadrezMatch.performChessMove(source, target);
				
				if(xadrezMatch.getPromo() != null) {
					System.out.println("Digite para qual peca voce deseja promover seu peao (B/T/C/Q) : ");
					String peca = sc.nextLine().toUpperCase();
					while(!peca.equals("T") && !peca.equals("C") && !peca.equals("Q") && !peca.equals("B")) {
						System.out.println("Peca invalida, digite alguma das pecas indicadas (B/T/C/Q) : ");
						peca = sc.nextLine().toUpperCase();
					}
					xadrezMatch.replacePromoPeca(peca);
				}
			
			} catch (XadrezExcecao e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("notacao esta na forma errada");
				sc.nextLine();
			}

		}

		UI.printTabuleiro(xadrezMatch.getPecas());
		System.out.println("Xeque-mate!");
		if (xadrezMatch.getCurrentPlayer() == Cor.WHITE) {
			System.out.println("Vencedor : " + jogador1);
		} else
			System.out.println("Vencedor : " + jogador2);

		sc.close();
	}
}
