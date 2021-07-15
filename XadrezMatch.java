package xadrez;

import jogo.Peca;
import jogo.Position;
import jogo.Tabuleiro;
import xadrez.pecas.Peao;

public class XadrezMatch {
	
	private Tabuleiro tabuleiro;
	
	public XadrezMatch() {
		tabuleiro = new Tabuleiro(8, 8); 
		initialSetup();
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
	public  XadrezPeca performChessMove(XadrezPosicao sourcePosition, XadrezPosicao targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		Peca capturaPeca = makeMove(source, target);
		return (XadrezPeca)capturaPeca;
		
	}
	private Peca makeMove(Position source, Position target) {
		Peca p = tabuleiro.removePeca(source);
		Peca capturadaPeca = tabuleiro.removePeca(target);
		tabuleiro.placePeca(p, target);
		return capturadaPeca;
	}
	
	private void validateSourcePosition(Position position) {
		if (!tabuleiro.thereIsAPeca(position)) {
			throw new XadrezExcecao("Nao ha peca na posicao de origem");
		}
	}
	
	private void placeNewPeca(char column, int row, XadrezPeca peca) {
		tabuleiro.placePeca(peca, new XadrezPosicao(column, row).toPosition());
	}
	
	private void initialSetup() {
		
		placeNewPeca('a', 2, new Peao(tabuleiro, Cor.WHITE));
		placeNewPeca('b', 2, new Peao(tabuleiro, Cor.WHITE));
		placeNewPeca('c', 2, new Peao(tabuleiro, Cor.WHITE));
		placeNewPeca('d', 2, new Peao(tabuleiro, Cor.WHITE));
		placeNewPeca('e', 2, new Peao(tabuleiro, Cor.WHITE));
		placeNewPeca('f', 2, new Peao(tabuleiro, Cor.WHITE));
		placeNewPeca('g', 2, new Peao(tabuleiro, Cor.WHITE));
		placeNewPeca('h', 2, new Peao(tabuleiro, Cor.WHITE));
		
		placeNewPeca('a', 7, new Peao(tabuleiro, Cor.BLACK));
		placeNewPeca('b', 7, new Peao(tabuleiro, Cor.BLACK));
		placeNewPeca('c', 7, new Peao(tabuleiro, Cor.BLACK));
		placeNewPeca('d', 7, new Peao(tabuleiro, Cor.BLACK));
		placeNewPeca('e', 7, new Peao(tabuleiro, Cor.BLACK));
		placeNewPeca('f', 7, new Peao(tabuleiro, Cor.BLACK));
		placeNewPeca('g', 7, new Peao(tabuleiro, Cor.BLACK));
		placeNewPeca('h', 7, new Peao(tabuleiro, Cor.BLACK));

	}
}
