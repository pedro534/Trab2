package Testes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class PegarDados extends JFrame{
	
	private JLabel nickname, token;
	private JTextField campoNickname, campoToken;
	private JButton botao;
	
	public PegarDados()
	{
		this.setSize(500, 510);
		this.setLayout(null);
		 this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		componentes();
		ação();
		
	}
	
	private void componentes() 
	{
		nickname = new JLabel("Digite seu nickname: ");
		nickname.setBounds(50, 20, 200, 30);
		this.add(nickname);

		campoNickname = new JTextField("Luizão Do DORJÃO");
		campoNickname.setBounds(50, 50, 200, 30);
		this.add(campoNickname);
		
		token = new JLabel("Digite o token: ");
		token.setBounds(50, 100, 200, 30);
		this.add(token);

		campoToken = new JTextField("RGAPI-64773628-6e93-45b3-8ce7-b2dc568139d0");
		campoToken.setBounds(50, 140, 200, 30);
		this.add(campoToken);

		botao = new JButton("Pesquisar");
		botao.setBounds(50, 200, 100, 30);
		this.add(botao);

	}
	
	private void ação() 
	{
		
		botao.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				String nick = campoNickname.getText();
				String token = campoToken.getText();
				System.out.println(nick);
				System.out.println(token);
				try {
					Teladados dados = new Teladados(nick, token);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PegarDados.this.dispose();
			}
		});
	}

}
