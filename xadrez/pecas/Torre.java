package xadrez.pecas;

import jogo.Position;
import jogo.Tabuleiro;
import xadrez.Cor;
import xadrez.XadrezPeca;

public class Torre extends XadrezPeca {
	
	public Torre(Tabuleiro tabuleiro, Cor cor){
		super(tabuleiro, cor);
		
	}

	@Override 	
	public String toString() {
		if(getCor() == Cor.WHITE) {
		return "t";
		} else return "T";
	
	}
	
	@Override public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean [getTabuleiro().getRows()][getTabuleiro().getColumns()]; 
		
		Position p = new Position(0, 0);
		
		p.setValues(position.getRow() - 1, position.getColumn());
		while (getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPeca(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() - 1);
			
			
		}
		if (getTabuleiro().positionExists(p) && isThereOpponentPeca(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		p.setValues(position.getRow() , position.getColumn() - 1);
		while (getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPeca(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() - 1);
			
			
		}
		if (getTabuleiro().positionExists(p) && isThereOpponentPeca(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() , position.getColumn() + 1);
		while (getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPeca(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() + 1);
			
			
		}
		if (getTabuleiro().positionExists(p) && isThereOpponentPeca(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() + 1, position.getColumn());
		while (getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPeca(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() + 1);
			
			
		}
		if (getTabuleiro().positionExists(p) && isThereOpponentPeca(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		
		return mat;
		
	}
	
	
}