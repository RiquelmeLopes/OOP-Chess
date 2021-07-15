package xadrez;

import jogo.Peca;
import jogo.Position;
import jogo.Tabuleiro;

public abstract class XadrezPeca extends Peca {
	
	private Cor cor;
	
	public XadrezPeca(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}
	
	public Cor getCor() {
		return cor;
	}

	protected boolean isThereOpponentPeca(Position position) {
		XadrezPeca p = (XadrezPeca)getTabuleiro().peca(position);
		return p != null && p.getCor() != cor;
	}
}
