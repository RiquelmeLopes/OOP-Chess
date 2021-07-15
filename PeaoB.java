package xadrez.pecas;

import jogo.Tabuleiro;
import xadrez.Cor;
import xadrez.XadrezPeca;

public class PeaoB extends XadrezPeca {

	public PeaoB(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "p";
	}
}
