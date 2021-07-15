package xadrez;

import jogo.Peca;
import jogo.Tabuleiro;

public class XadrezPeca extends Peca {

	private Cor cor;

	public XadrezPeca(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
}
