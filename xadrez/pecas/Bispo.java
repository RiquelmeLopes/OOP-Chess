package xadrez.pecas;

import jogo.Position;
import jogo.Tabuleiro;
import xadrez.Cor;
import xadrez.XadrezPeca;

public class Bispo extends XadrezPeca {

	public Bispo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "B";
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getTabuleiro().getRows()][getTabuleiro().getColumns()];
		
		Position p = new Position(0, 0);
		
		// nw
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		while (getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPeca(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() - 1);
		}
		if (getTabuleiro().positionExists(p) && isThereOpponentPeca(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// ne
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		while (getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPeca(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() + 1);
		}
		if (getTabuleiro().positionExists(p) && isThereOpponentPeca(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// se
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		while (getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPeca(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() + 1);
		}
		if (getTabuleiro().positionExists(p) && isThereOpponentPeca(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// sw
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		while (getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPeca(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() - 1);
		}
		if (getTabuleiro().positionExists(p) && isThereOpponentPeca(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		return mat;
	}
}