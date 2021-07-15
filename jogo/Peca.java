package jogo;


public abstract class Peca {

	protected Position position;
	private Tabuleiro tabuleiro;
	
	public Peca(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
	public abstract boolean [][] possibleMoves();
	
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
		
	}
	
	public boolean isThereAnyPossibleMove() {
		boolean[][] mat = possibleMoves();
		for (int i=0; i<mat.length; i++) {
			for (int j=0; j<mat.length; j++) {
				if (mat[i][j]) {
					return true;
					
					
					
				}
			}
		}
		
		return false;
		
	}
	
}




