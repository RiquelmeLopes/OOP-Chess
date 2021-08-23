package xadrez;

import jogo.Peca;
import jogo.Position;
import jogo.Tabuleiro;

public abstract class XadrezPeca extends Peca {
	
	private Cor cor;
	private int moveCount;
	
	public XadrezPeca(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}
	
	public int getMoveCount() {
		return moveCount;
	}
	
	public void increaseMoveCount() {
		moveCount++;
	}
	
	public void decreaseMoveCount() {
		moveCount--;
	}
	
	public Cor getCor() {
		return cor;
	}
	
	public XadrezPosicao getXadrezPosicao() {
		return XadrezPosicao.fromPosition(position);
	}

	protected boolean isThereOpponentPeca(Position position) {
		XadrezPeca p = (XadrezPeca)getTabuleiro().peca(position);
		return p != null && p.getCor() != cor;
	}
}
