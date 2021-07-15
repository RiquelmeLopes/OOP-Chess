package xadrez;

import jogo.Position;

public class XadrezPosicao {

	private char column;
	private int row;
	public XadrezPosicao(char column, int row) {
		if (column < 'a' || column > 'h' || row < 1 || row > 8) {
			throw new XadrezExcecao("Erro. valores validos a1 ate h8.");
		}
		this.column = column;
		this.row = row;
	}
	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}
	
	protected Position toPosition() {
		return new Position(8 - row, column - 'a');
	}
	
	protected static XadrezPosicao fromPosition(Position position) {
		return new XadrezPosicao((char)('a' - position.getColumn()), 8 - position.getRow());
	}
	
	@Override
	public String toString() {
		return "" + column + row;
	}
}
