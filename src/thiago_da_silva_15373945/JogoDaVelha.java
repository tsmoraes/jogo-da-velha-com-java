package thiago_da_silva_15373945;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class JogoDaVelha extends JFrame {
	/** 
	 * É preciso aumentar e diminuir a janela para conseguir jogar o jogo.
	 * 
	 * @author Thiago da Silva
	 */
	private static final long serialVersionUID = 1L;
	ImageIcon iconCirculo = new ImageIcon(getClass().getResource("circulo.jpg"));
	ImageIcon iconX = new ImageIcon(getClass().getResource("x.jpg"));

	JPanel pTela = new JPanel(new GridLayout(3, 3, 2, 2));
	
	Bloco[] blocos = new Bloco[9];
	
	int rodadas = 0;
	
	final String JOGADOR_1 = "Tom", JOGADOR_2 = "Jerry";
	
	String jogadorVez = JOGADOR_1;
	
	JLabel lInformacao = new JLabel("Jogador "+JOGADOR_1);
	private final JButton btnReiniciar = new JButton("Reiniciar");
	
	public JogoDaVelha() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(JogoDaVelha.class.getResource("/thiago_da_silva_15373945/tom e jerry.jpg")));
		setBackground(Color.YELLOW);
		setFont(new Font("Malgun Gothic Semilight", Font.ITALIC, 12));
		configurarJanela();
		configurarTela();
	}
	
	//Adiciona componentes na tela
	public void configurarTela() {
		getContentPane().setLayout(null);
		pTela.setBounds(0, 0, 584, 423);
		getContentPane().add(pTela);
		lInformacao.setBounds(0, 427, 584, 66);
		getContentPane().add(lInformacao);
		pTela.setBackground(Color.ORANGE);
		lInformacao.setFont(new Font("Monospaced",Font.BOLD,50));
		lInformacao.setForeground(Color.DARK_GRAY);
		lInformacao.setHorizontalAlignment(SwingConstants.CENTER);
		btnReiniciar.setForeground(new Color(139, 0, 0));
		btnReiniciar.setBackground(new Color(124, 252, 0));
		
		//Responsï¿½vel por reiniciar as partidas
		btnReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<9;i++) {
					 blocos[i].setIcon(null);
					 blocos[i].quem = "";
					 rodadas = 0;
				}
			}
		});
		btnReiniciar.setBounds(10, 504, 502, 46);
		
		getContentPane().add(btnReiniciar);
		
		//Iformaï¿½ï¿½es sobre o aluno, curso e a faculdade
		JButton btnInfo = new JButton("");
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Thiago da Silva \n\n"
						+ "Curso de Análise e Desenvolvimento de Sistemas \n"
						+ "Centro Universitário CESUMAR \n"
						+ "Trabalho de Programação Java");
			}
		});
		btnInfo.setIcon(new ImageIcon(JogoDaVelha.class.getResource("/thiago_da_silva_15373945/info.png")));
		btnInfo.setBounds(522, 504, 52, 46);
		getContentPane().add(btnInfo);
		
		for(int i=0;i<9;i++) {
			Bloco bloco = new Bloco();
			blocos[i] = bloco;
			pTela.add(bloco);
		}
	}
	
	public void mudarVez(){
		if(jogadorVez=="Tom") {
			jogadorVez="Jerry";
			lInformacao.setText("Vez do Jerry");
			lInformacao.setForeground(Color.magenta);
		} else {
			jogadorVez="Tom";
			lInformacao.setText("Vez do Tom");
			lInformacao.setForeground(Color.blue);
		}
	}
	
	public boolean testarVitoria(String quem) {
		if(blocos[0].quem==quem && blocos[1].quem==quem && blocos[2].quem==quem) {
			return true;
		}
		if(blocos[3].quem==quem && blocos[4].quem==quem && blocos[5].quem==quem) {
			return true;
		}
		if(blocos[6].quem==quem && blocos[7].quem==quem && blocos[8].quem==quem) {
			return true;
		}
		if(blocos[0].quem==quem && blocos[3].quem==quem && blocos[6].quem==quem) {
			return true;
		}
		if(blocos[1].quem==quem && blocos[4].quem==quem && blocos[7].quem==quem) {
			return true;
		}
		if(blocos[2].quem==quem && blocos[5].quem==quem && blocos[8].quem==quem) {
			return true;
		}
		if(blocos[0].quem==quem && blocos[4].quem==quem && blocos[8].quem==quem) {
			return true;
		}
		if(blocos[2].quem==quem && blocos[4].quem==quem && blocos[6].quem==quem) {
			return true;
		}
		return false;
	}

	public void configurarJanela() {
		setTitle("MAPA Jogo da Velha | Tom e Jerry");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600,600);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new JogoDaVelha();
	}
	
	public class Bloco extends JButton{
		private static final long serialVersionUID = 1L;
		
		String quem = "";		
		public Bloco() {
			setBackground(Color.CYAN);
			addActionListener(e->{
				if(quem=="") {
					if(jogadorVez==JOGADOR_1) {
						setIcon(iconCirculo);
						quem = JOGADOR_1;
					} else {
						setIcon(iconX);
						quem = JOGADOR_2;
					}
					if(testarVitoria(quem)) {
						JOptionPane.showMessageDialog(null,"Jogador "+quem+" Venceu!");
						System.exit(0);
					}
					rodadas++;
					if(rodadas==9) {
						JOptionPane.showMessageDialog(null,"Deu velha!");
						System.exit(0);
					}
					mudarVez();
				}
			});
		}
	}
}