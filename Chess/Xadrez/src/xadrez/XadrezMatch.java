package xadrez;

import jogo.Tabuleiro;

public class XadrezMatch {
	
	private Tabuleiro tabuleiro;
	
	public XadrezMatch() {
		tabuleiro = new Tabuleiro(8, 8); 
		
	}

	public XadrezPeca[][] getPecas(){
		XadrezPeca[][] mat = new XadrezPeca[tabuleiro.getRows()][tabuleiro.getColumns()];
		for (int i=0; i<tabuleiro.getRows(); i++) {
			for (int j=0; j<tabuleiro.getColumns(); j++) {
				mat[i][j] = (XadrezPeca) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}
}
