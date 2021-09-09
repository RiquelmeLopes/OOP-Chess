package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import jogo.Peca;
import jogo.Position;
import jogo.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Dama;
import xadrez.pecas.PeaoB;
import xadrez.pecas.PeaoP;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class XadrezMatch {
	
	private int turn;
	private Cor currentPlayer;
	private static Tabuleiro tabuleiro;
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();
	private boolean xeque;
	private boolean xequeMate;
	private XadrezPeca promo;
	
	public XadrezMatch() {
		tabuleiro = new Tabuleiro(8, 8); 
		turn = 1;
		currentPlayer = Cor.WHITE;
		xeque = false;
		initialSetup();
	}
	
	public int getTurn() {
		return turn;
	}

	public Cor getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean getXeque() {
		return xeque;
	}
	
	public boolean getXequeMate() {
		return xequeMate;
	}
	
	public XadrezPeca getPromo() {
		return promo;
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
		
		if(taEmXeque(currentPlayer)) {
			undoMove(source, target, capturaPeca);
			throw new XadrezExcecao("voce nao pode se colocar em xeque!");
		}
		
		XadrezPeca movedPiece = (XadrezPeca)tabuleiro.peca(target);
		
		promo = null;
		if(movedPiece instanceof PeaoB && target.getRow() == 0) {
			promo = (XadrezPeca)tabuleiro.peca(target);
			promo = replacePromoPeca("Q");
		}
		if(movedPiece instanceof PeaoP && target.getRow() == 7) {
			
		}
		
		xeque = (taEmXeque(oponente(currentPlayer))) ? true : false;
		
		if(taEmXequeMate(oponente(currentPlayer))) {
			xequeMate = true;
		}else {
		nextTurn();
		}
		return (XadrezPeca)capturaPeca;
		
	}
	
	public XadrezPeca replacePromoPeca(String peca) {
		if(promo == null) {
			throw new IllegalStateException("Nao tem nenhuma peca para ser promovida");
		}
		if(!peca.equals("T") && !peca.equals("C") && !peca.equals("Q") && !peca.equals("B")) {
			return promo;
		}
		
		Position position = promo.getXadrezPosicao().toPosition();
		Peca p = tabuleiro.removePeca(position);
		pecasNoTabuleiro.remove(p);
		
		XadrezPeca novaPeca = novaPeca(peca, promo.getCor());
		tabuleiro.placePeca(novaPeca, position);
		pecasNoTabuleiro.add(novaPeca);
		
		return novaPeca;
		
	}
	
	private XadrezPeca novaPeca(String peca, Cor cor) {
		if(peca.equals("T")) return new Torre(tabuleiro, cor);
		if(peca.equals("C")) return new Cavalo(tabuleiro, cor);
		if(peca.equals("Q")) return new Dama(tabuleiro, cor);
		return new Bispo(tabuleiro, cor);
		
	}
	
	private Peca makeMove(Position source, Position target) {
		XadrezPeca p = (XadrezPeca)tabuleiro.removePeca(source);
		p.increaseMoveCount();
		Peca capturadaPeca = tabuleiro.removePeca(target);
		tabuleiro.placePeca(p, target);
		
		if(capturadaPeca != null) {
			pecasNoTabuleiro.remove(capturadaPeca);
			pecasCapturadas.add(capturadaPeca);
		}
		
		//roque
		try {
		if(p instanceof Rei && target.getColumn() == source.getColumn() + 2) {
			Position sourceT = new Position(source.getRow(), source.getColumn() + 3);
			Position targetT = new Position(source.getRow(), source.getColumn() + 1);
			XadrezPeca torre = (XadrezPeca)tabuleiro.removePeca(sourceT);
			tabuleiro.placePeca(torre, targetT);
			torre.increaseMoveCount();
		}
		
		if(p instanceof Rei && target.getColumn() == source.getColumn() - 2) {
			Position sourceT = new Position(source.getRow(), source.getColumn() - 4 );
			Position targetT = new Position(source.getRow(), source.getColumn() - 1);
			XadrezPeca torre = (XadrezPeca)tabuleiro.removePeca(sourceT);
			tabuleiro.placePeca(torre, targetT);
			torre.increaseMoveCount();
		}
			
	}catch (XadrezExcecao e) {
		JOptionPane.showMessageDialog(null, "Nao e possivel fazer o roque", "Erro no roque", JOptionPane.ERROR_MESSAGE);
	}
		return capturadaPeca;
	}
	
	private void undoMove(Position source, Position target, Peca capturedPiece) {
		XadrezPeca p = (XadrezPeca)tabuleiro.removePeca(target);
		p.decreaseMoveCount();
		tabuleiro.placePeca(p, source);
		
		if(capturedPiece != null) {
			tabuleiro.placePeca(capturedPiece, target);
		}
		
		//desfazer roque
		
		if(p instanceof Rei && target.getColumn() == source.getColumn() + 2) {
			Position sourceT = new Position(source.getRow(), source.getColumn() + 3);
			Position targetT = new Position(source.getRow(), source.getColumn() + 1);
			XadrezPeca torre = (XadrezPeca)tabuleiro.removePeca(targetT);
			tabuleiro.placePeca(torre, sourceT);
			torre.decreaseMoveCount();
		}
		
		if(p instanceof Rei && target.getColumn() == source.getColumn() - 2) {
			Position sourceT = new Position(source.getRow(), source.getColumn() - 4 );
			Position targetT = new Position(source.getRow(), source.getColumn() - 1);
			XadrezPeca torre = (XadrezPeca)tabuleiro.removePeca(targetT);
			tabuleiro.placePeca(torre, sourceT);
			torre.decreaseMoveCount();
		}
		
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
	
	private Cor oponente (Cor cor) {
		 return (cor == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
	}
	
	private XadrezPeca rei (Cor cor) {
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((XadrezPeca)x).getCor() == cor).collect(Collectors.toList());
		for(Peca p : list) {
			if(p instanceof Rei) {
				return (XadrezPeca)p;
			}
		}
		throw new IllegalStateException("nao tem rei " + cor + "no tabuleiro");
	}
	
	private boolean taEmXeque(Cor cor) {
		Position reiPosition = rei(cor).getXadrezPosicao().toPosition();
		List<Peca> pecasOponentes = pecasNoTabuleiro.stream().filter(x -> ((XadrezPeca)x).getCor() == oponente(cor)).collect(Collectors.toList());
		for(Peca p : pecasOponentes) {
			boolean[][] mat = p.possibleMoves();
			if(mat[reiPosition.getRow()][reiPosition.getColumn()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean taEmXequeMate(Cor cor) {
		if(!taEmXeque(cor)) {
			return false;
		}
		
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((XadrezPeca)x).getCor() == cor).collect(Collectors.toList());
		for(Peca p : list) {
			boolean[][] mat = p.possibleMoves();
			for(int i=0; i<tabuleiro.getRows(); i++) {
				for(int j=0; j<tabuleiro.getColumns(); j++) {
					if(mat[i][j]) {
						Position source = ((XadrezPeca)p).getXadrezPosicao().toPosition();
						Position target = new Position(i,j);
						Peca capturedPiece = makeMove(source,target);
						boolean taEmXeque = taEmXeque(cor);
						undoMove(source, target, capturedPiece);
						if(!taEmXeque) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
	}
	
	private void placeNewPeca(char column, int row, XadrezPeca peca) {
		tabuleiro.placePeca(peca, new XadrezPosicao(column, row).toPosition());
		pecasNoTabuleiro.add(peca);
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
		placeNewPeca('a', 1, new Torre(tabuleiro, Cor.WHITE));
		placeNewPeca('h', 1, new Torre(tabuleiro, Cor.WHITE));
		placeNewPeca('e', 1, new Rei(tabuleiro, Cor.WHITE, this));
		placeNewPeca('c', 1, new Bispo(tabuleiro, Cor.WHITE));
		placeNewPeca('f', 1, new Bispo(tabuleiro, Cor.WHITE));
		placeNewPeca('b', 1, new Cavalo(tabuleiro, Cor.WHITE));
		placeNewPeca('g', 1, new Cavalo(tabuleiro, Cor.WHITE));
		placeNewPeca('d', 1, new Dama(tabuleiro, Cor.WHITE));

		
		
		
		placeNewPeca('a', 7, new PeaoP(tabuleiro, Cor.BLACK));
		placeNewPeca('b', 7, new PeaoP(tabuleiro, Cor.BLACK));
		placeNewPeca('c', 7, new PeaoP(tabuleiro, Cor.BLACK));
		placeNewPeca('d', 7, new PeaoP(tabuleiro, Cor.BLACK));
		placeNewPeca('e', 7, new PeaoP(tabuleiro, Cor.BLACK));
		placeNewPeca('f', 7, new PeaoP(tabuleiro, Cor.BLACK));
		placeNewPeca('g', 7, new PeaoP(tabuleiro, Cor.BLACK));
		placeNewPeca('h', 7, new PeaoP(tabuleiro, Cor.BLACK));
		placeNewPeca('a', 8, new Torre(tabuleiro, Cor.BLACK));
		placeNewPeca('h', 8, new Torre(tabuleiro, Cor.BLACK));
		placeNewPeca('e', 8, new Rei(tabuleiro, Cor.BLACK, this));
		placeNewPeca('c', 8, new Bispo(tabuleiro, Cor.BLACK));
		placeNewPeca('f', 8, new Bispo(tabuleiro, Cor.BLACK));
		placeNewPeca('b', 8, new Cavalo(tabuleiro, Cor.BLACK));
		placeNewPeca('g', 8, new Cavalo(tabuleiro, Cor.BLACK));
		placeNewPeca('d', 8, new Dama(tabuleiro, Cor.BLACK));

		
		
	}
}