package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import xadrez.XadrezExcecao;
import xadrez.XadrezMatch;
import xadrez.XadrezPosicao;
import xadrez.Cor;

public class MinhaJanela extends JFrame implements ActionListener{
	
	JButton botaoJogar; 
	JButton botaoDesistir;
	JTextField jogadas;
	JTextArea tabuleiro;
	JLabel jogadorB;
	JLabel jogadorP;
	XadrezMatch xadrezMatch = new XadrezMatch();

	public MinhaJanela(String nomeJogador1, String nomeJogador2){
	
		
		//labels
		
		JLabel titulo = new JLabel("Bem vindo ao jogo!");
		titulo.setHorizontalAlignment(JLabel.CENTER);
		titulo.setVerticalAlignment(JLabel.TOP);
		titulo.setForeground(Color.green);
		titulo.setFont(new Font("Helvetica", Font.PLAIN, 20));
		titulo.setBounds(240, 10, 300, 300);
		
		ImageIcon iconep1 = new ImageIcon("iconp1.png");
		jogadorB = new JLabel(nomeJogador1.toUpperCase() + " (TURNO ATUAL)");
		jogadorB.setIcon(iconep1);
		jogadorB.setIconTextGap(5);
		jogadorB.setHorizontalAlignment(JLabel.LEFT);
		jogadorB.setVerticalAlignment(JLabel.TOP);
		jogadorB.setForeground(Color.white);
		jogadorB.setFont(new Font("Helvetica", Font.BOLD, 14));
		jogadorB.setBounds(110, 510, 300, 300);
		jogadorB.setVisible(true);
		
		ImageIcon iconep2 = new ImageIcon("iconp2.png");
		jogadorP = new JLabel(nomeJogador2.toUpperCase() + " (TURNO ATUAL)");
		jogadorP.setIcon(iconep2);
		jogadorP.setIconTextGap(5);
		jogadorP.setHorizontalAlignment(JLabel.LEFT);
		jogadorP.setVerticalAlignment(JLabel.TOP);
		jogadorP.setForeground(Color.yellow);
		jogadorP.setFont(new Font("Helvetica", Font.BOLD, 14));
		jogadorP.setBounds(110, 70, 300, 300);
		jogadorP.setVisible(false);

		
		//botoes
		
		ImageIcon iconeBotao = new ImageIcon("iconeBotao.png");
		botaoJogar = new JButton();
		botaoJogar.setBounds(600, 70, 150, 75);
		botaoJogar.addActionListener(this);
		botaoJogar.setFocusable(false);
		botaoJogar.setText("Jogar");
		botaoJogar.setIcon(iconeBotao);
		botaoJogar.setHorizontalTextPosition(JButton.LEFT);
		botaoJogar.setFont(new Font("Helvetica", Font.PLAIN, 20));
		botaoJogar.setBackground(Color.LIGHT_GRAY);
		botaoJogar.setBorder(BorderFactory.createLineBorder(Color.white));
		
		ImageIcon iconeDesistir = new ImageIcon("iconeDesistir.png");
		botaoDesistir = new JButton();
		botaoDesistir.setBounds(600, 400, 150, 75);
		botaoDesistir.addActionListener(this);
		botaoDesistir.setFocusable(false);
		botaoDesistir.setText("Desistir");
		botaoDesistir.setIcon(iconeDesistir);
		botaoDesistir.setIconTextGap(5);
		botaoDesistir.setHorizontalTextPosition(JButton.LEFT);
		botaoDesistir.setFont(new Font("Helvetica", Font.PLAIN, 20));
		botaoDesistir.setBackground(Color.LIGHT_GRAY);
		botaoDesistir.setBorder(BorderFactory.createLineBorder(Color.white));
		
		//Textfields / TextArea
		
		jogadas = new JTextField();
		jogadas.addActionListener(this);
		jogadas.setBounds(600, 252, 150, 40);
		jogadas.setFont(new Font("Helvetica", Font.PLAIN, 20));
		jogadas.setForeground(Color.black);
		jogadas.setBackground(Color.white);
		
		
		tabuleiro = new JTextArea();
		tabuleiro.append(UI.printTabuleiro(XadrezMatch.getPecas()));
		tabuleiro.setBounds(110, 150, 440, 400);
		tabuleiro.setBackground(Color.black);
		tabuleiro.setLineWrap(true);
		tabuleiro.setForeground(Color.white);
		tabuleiro.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));
		tabuleiro.setEditable(false);
		
		//frame
		
		ImageIcon imagem = new ImageIcon("icone.png");
		
		this.setTitle("Xadrez");
		this.setSize(800,600);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.black);
		this.setLayout(null);
		this.setIconImage(imagem.getImage());		
		this.add(titulo);
		this.add(jogadorB);
		this.add(jogadorP);
		this.add(botaoJogar);
		this.add(botaoDesistir);
		this.add(jogadas);
		this.add(tabuleiro);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == botaoJogar) {
			
			try {

					if (xadrezMatch.getXeque()) {
						JOptionPane.showMessageDialog(null, "Seu rei esta em xeque", "Erro de movimento", JOptionPane.WARNING_MESSAGE);
					}


					String movimento = jogadas.getText().toLowerCase();
					
					String s[] = movimento.split(":");
					String movOrigem = s[0];
					String movDestino = s[1]; 

					XadrezPosicao source = UI.readXadrezPosicao(movOrigem);
					XadrezPosicao target = UI.readXadrezPosicao(movDestino);

					xadrezMatch.performChessMove(source, target);
					tabuleiro.setText(UI.printTabuleiro(XadrezMatch.getPecas()));
					
					if(xadrezMatch.getXequeMate()) {
						JOptionPane.showMessageDialog(null, "XEQUE-MATE!", "fim de jogo", JOptionPane.WARNING_MESSAGE);
						System.exit(0);
					}
					
					if(xadrezMatch.getPromo() != null) {
						String peca = JOptionPane.showInputDialog("Digite para qual peca voce deseja promover seu peao (B/T/C/Q) : ");
						if(!peca.equals("T") && !peca.equals("C") && !peca.equals("Q") && !peca.equals("B")) {
							JOptionPane.showMessageDialog(null, "Peca invalida, digite alguma das pecas indicadas (B/T/C/Q) : ", "Peca invalida", JOptionPane.ERROR_MESSAGE);
						}
						xadrezMatch.replacePromoPeca(peca);
					}
					
					if (xadrezMatch.getCurrentPlayer() == Cor.WHITE) {
						jogadorP.setVisible(false);
					} else
						jogadorP.setVisible(true);

					if (xadrezMatch.getCurrentPlayer() == Cor.BLACK) {
						jogadorB.setVisible(false);
					} else
						jogadorB.setVisible(true);

					
			} catch (XadrezExcecao excecao) {
				JOptionPane.showMessageDialog(null, excecao.getMessage(), "Erro de movimento", JOptionPane.ERROR_MESSAGE);
			} catch (ArrayIndexOutOfBoundsException excecao2) {
				JOptionPane.showMessageDialog(null, "Notacao esta na forma errada", "Erro de movimento", JOptionPane.ERROR_MESSAGE);
			} catch (InputMismatchException excecao3) {
				JOptionPane.showMessageDialog(null, excecao3.getMessage(), "Erro de movimento", JOptionPane.ERROR_MESSAGE);
			}
			jogadas.setText("");
		}
		
		if(e.getSource() == botaoDesistir) {
			int answer = JOptionPane.showConfirmDialog(null, "Tem certeza que quer desistir?", "Desistencia", JOptionPane.YES_NO_OPTION);
				if(answer == 0) {
					if(xadrezMatch.getCurrentPlayer() == Cor.WHITE) {
					JOptionPane.showMessageDialog(null, "Voce desistiu.", "Desistencia", JOptionPane.ERROR_MESSAGE);
					System.exit(0);
					}
				}
		}
	}

	private static final long serialVersionUID = 1L;

}
