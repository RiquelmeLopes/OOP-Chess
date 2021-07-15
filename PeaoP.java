package xadrez.pecas;

import jogo.Tabuleiro;
import xadrez.Cor;
import xadrez.XadrezPeca;

public class PeaoP extends XadrezPeca {

	public PeaoP(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "P";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean [getTabuleiro().getRows()][getTabuleiro().getColumns()];
		return mat;
	}
}
