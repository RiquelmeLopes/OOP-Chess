package xadrez;

import jogo.Peca;
import jogo.Position;
import jogo.Tabuleiro;
import xadrez.pecas.PeaoB;
import xadrez.pecas.PeaoP;

public class XadrezMatch {
	
	private int turn;
	private Cor currentPlayer;
	private static Tabuleiro tabuleiro;
	
	public XadrezMatch() {
		tabuleiro = new Tabuleiro(8, 8); 
		turn = 1;
		currentPlayer = Cor.WHITE;		
		initialSetup();
	}
	
	public int getTurn() {
		return turn;
	}

	public Cor getCurrentPlayer() {
		return currentPlayer;
	}
	
	public static XadrezPeca[][] getPecas(){
		XadrezPeca[][] mat = new XadrezPeca[tabuleiro.getRows()][tabuleiro.getColumns()];
		for (int i=0; i<tabuleiro.getRows(); i++) {
			for (int j=0; j<tabuleiro.getColumns(); j++) {
				mat[i][j] = (XadrezPeca) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}
	
	public boolean[][] possibleMoves(XadrezPosicao sourcePosition) {
		Position position = sourcePosition.toPosition(); 
		validateSourcePosition(position);
		return tabuleiro.peca(position).possibleMoves();
	}
	
	public  XadrezPeca performChessMove(XadrezPosicao sourcePosition, XadrezPosicao targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Peca capturaPeca = makeMove(source, target);
		nextTurn();
		return (XadrezPeca)capturaPeca;
		
	}
	private Peca makeMove(Position source, Position target) {
		XadrezPeca p = (XadrezPeca)tabuleiro.removePeca(source);
		p.increaseMoveCount();
		Peca capturadaPeca = tabuleiro.removePeca(target);
		tabuleiro.placePeca(p, target);
		return capturadaPeca;
	}
	
	private void validateSourcePosition(Position position) {
		if (!tabuleiro.thereIsAPeca(position)) {
			throw new XadrezExcecao("Nao ha peca na posicao de origem");
		}
		if (currentPlayer != ((XadrezPeca)tabuleiro.peca(position)).getCor()) {
			throw new XadrezExcecao("A peca escolhida nao e sua");
		}
		if (!tabuleiro.peca(position).isThereAnyPossibleMove()) {
			throw new XadrezExcecao("Nao ha movimentos possiveis para peca escolhida");
		}
	}
	private void validateTargetPosition(Position source, Position target) {
		if (!tabuleiro.peca(source).possibleMove(target)) {
			throw new XadrezExcecao("A peca escolhida nao pode se mover para posicao de destino");
		}
	}
	
	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
	}
	
	private void placeNewPeca(char column, int row, XadrezPeca peca) {
		tabuleiro.placePeca(peca, new XadrezPosicao(column, row).toPosition());
	}
	
	private void initialSetup() {
		
		placeNewPeca('a', 2, new PeaoB(tabuleiro, Cor.WHITE));
		placeNewPeca('b', 2, new PeaoB(tabuleiro, Cor.WHITE));
		placeNewPeca('c', 2, new PeaoB(tabuleiro, Cor.WHITE));
		placeNewPeca('d', 2, new PeaoB(tabuleiro, Cor.WHITE));
		placeNewPeca('e', 2, new PeaoB(tabuleiro, Cor.WHITE));
		placeNewPeca('f', 2, new PeaoB(tabuleiro, Cor.WHITE));
		placeNewPeca('g', 2, new PeaoB(tabuleiro, Cor.WHITE));
		placeNewPeca('h', 2, new PeaoB(tabuleiro, Cor.WHITE));
		
		placeNewPeca('a', 7, new PeaoP(tabuleiro, Cor.BLACK));
		placeNewPeca('b', 7, new PeaoP(tabuleiro, Cor.BLACK));
		placeNewPeca('c', 7, new PeaoP(tabuleiro, Cor.BLACK));
		placeNewPeca('d', 7, new PeaoP(tabuleiro, Cor.BLACK));
		placeNewPeca('e', 7, new PeaoP(tabuleiro, Cor.BLACK));
		placeNewPeca('f', 7, new PeaoP(tabuleiro, Cor.BLACK));
		placeNewPeca('g', 7, new PeaoP(tabuleiro, Cor.BLACK));
		placeNewPeca('h', 7, new PeaoP(tabuleiro, Cor.BLACK));

	}
}
