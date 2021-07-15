package xadrez.pecas;

import jogo.Position;
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
	
		Position p = new Position(0, 0);
		
		if (getCor() == Cor.BLACK) {
		p.setValues(position.getRow() + 1, position.getColumn());
		if (getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPeca(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		p.setValues(position.getRow() + 2, position.getColumn());
		Position p2 = new Position(position.getRow() - 1, position.getColumn());
		if (getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPeca(p) && getTabuleiro().positionExists(p2) && !getTabuleiro().thereIsAPeca(p2) &&getMoveCount() == 0) {
			mat[p.getRow()][p.getColumn()] = true;
		
		}
		
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		if (getTabuleiro().positionExists(p) && isThereOpponentPeca(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		if (getTabuleiro().positionExists(p) && isThereOpponentPeca(p)) {
			mat[p.getRow()][p.getColumn()] = true;

		
		}
		}
		return mat;
	}
	
	}

