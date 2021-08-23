package xadrez.pecas;

import jogo.Position;
import jogo.Tabuleiro;
import xadrez.Cor;
import xadrez.XadrezMatch;
import xadrez.XadrezPeca;

public class Rei extends XadrezPeca {

	private XadrezMatch xadrezMatch;

	public Rei(Tabuleiro tabuleiro, Cor cor, XadrezMatch xadrezMatch) {
		super(tabuleiro, cor);
		this.xadrezMatch = xadrezMatch;
	}

	@Override
	public String toString() {
		return "R";

	}

	private boolean canMove(Position position) {
		XadrezPeca p = (XadrezPeca) getTabuleiro().peca(position);
		return p == null || p.getCor() != getCor();

	}

	private boolean testeRoque(Position position) {
		XadrezPeca p = (XadrezPeca) getTabuleiro().peca(position);
		return p != null && p instanceof Torre && p.getCor() == getCor() && p.getMoveCount() == 0;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getTabuleiro().getRows()][getTabuleiro().getColumns()];

		Position p = new Position(0, 0);
		
		//movimentos
		
		p.setValues(position.getRow() - 1, position.getColumn());
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		p.setValues(position.getRow() + 1, position.getColumn());
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		p.setValues(position.getRow(), position.getColumn() - 1);
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		p.setValues(position.getRow(), position.getColumn() + 1);
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// roque

		if (getMoveCount() == 0 && !xadrezMatch.getXeque()) {
			Position posT1 = new Position(position.getRow(), position.getColumn() + 3);
			if (testeRoque(posT1)) {
				Position p1 = new Position(position.getRow(), position.getColumn() + 1);
				Position p2 = new Position(position.getRow(), position.getColumn() + 2);

				if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null) {
					mat[position.getRow()][position.getColumn() + 2] = true;
				}

			}

			Position posT2 = new Position(position.getRow(), position.getColumn() - 4);
			if (testeRoque(posT2)) {
				Position p1 = new Position(position.getRow(), position.getColumn() - 1);
				Position p2 = new Position(position.getRow(), position.getColumn() - 2);
				Position p3 = new Position(position.getRow(), position.getColumn() - 3);

				if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null && getTabuleiro().peca(p3) == null) {
					mat[position.getRow()][position.getColumn() - 2] = true;
				}

			}
		}

		return mat;

	}

}
