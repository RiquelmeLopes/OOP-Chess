package jogo;

public class Tabuleiro {
	
	private int rows;
	private int columns;
	private Peca [][] pecas;
	
	public Tabuleiro(int rows, int columns) {
		if (rows < 1 || columns < 1) {
			throw new TabuleiroExcecao("Erro ao criar tabuleiro, e preciso ao menos 1 linha e 1 coluna");
		}
		this.rows = rows;
		this.columns = columns;
		pecas = new Peca[rows][columns];
				
}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
	public Peca peca(int row, int column) {
		if (!positionExists(row, column)) {
			throw new TabuleiroExcecao("Posicao nao esta no tabuleiro");
		}
		return pecas[row][column];
	}
	public Peca peca(Position position) {
		if (!positionExists(position)) {
			throw new TabuleiroExcecao("Posicao nao esta no tabuleiro");
		}
		return pecas[position.getRow()][position.getColumn()];
	}
	
	public void placePeca(Peca peca, Position position) {
		if (thereIsAPeca(position)) {
			throw new TabuleiroExcecao("Ja tem uma peca nessa posicao" + position);
		}
		pecas[position.getRow()][position.getColumn()] = peca;
		peca.position = position;
	}
	
	public Peca removePeca(Position position) {
		if (!positionExists(position)) {
			throw new TabuleiroExcecao("Posicao nao esta no tabuleiro");
		}
		if (peca(position) == null) {
			return null;
		}
		Peca aux = peca(position);
		aux.position = null;
		pecas[position.getRow()][position.getColumn()] = null;
		return aux;
	}
	
	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}
	
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}
	
	public boolean thereIsAPeca(Position position) {
		if (!positionExists(position)) {
			throw new TabuleiroExcecao("Posicao nao esta no tabuleiro");
		}

		return peca(position) != null;
		
	}
}
