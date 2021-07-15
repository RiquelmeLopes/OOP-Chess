package xadrez.pecas;

import jogo.Position;
import jogo.Tabuleiro;
import xadrez.Cor;
import xadrez.XadrezPeca;

public class PeaoB extends XadrezPeca {

	public PeaoB(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "p";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean [getTabuleiro().getRows()][getTabuleiro().getColumns()];
	
		Position p = new Position(0, 0);
		
		if (getCor() == Cor.WHITE)
		p.setValues(position.getRow() - 1, position.getColumn());
		if (getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPeca(p)) {
		}
		
		
		return mat;
	}
}
