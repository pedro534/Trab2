package Testes;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.json.JSONArray;
import org.json.JSONObject;

import br.edu.iftm.api.Api;
import br.edu.iftm.api.JsonParser;

public class Teladados extends JFrame{
	
	String nomeInvocador;
	String token = "RGAPI-64773628-6e93-45b3-8ce7-b2dc568139d0";
	
	JLabel icone;
	JLabel nickname, lblidchamp, lblnivelMaestria, lblpontosMaestria, lblidchamp1, lblnivelMaestria1, lblpontosMaestria1, lblidchamp2, lblnivelMaestria2, lblpontosMaestria2;
	JButton voltar;
	
	public Teladados(String pnomeInvocador, String ptoken) throws IOException {
		
		nomeInvocador = pnomeInvocador;
		token = ptoken;
		this.setSize(1000, 700);
		this.setTitle(nomeInvocador);
		this.setLayout(null);
		 this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		componentes();
		ação();
	}
	
	private void componentes() throws IOException 
	{

		String url_apiSummonerV4 = String.format("https://br1.api.riotgames.com/lol/summoner/v4/summoners/by-name/%s",nomeInvocador);
		Api apiSummoner = new Api(url_apiSummonerV4);
		Map<String, String> cabecalhos = new HashMap<String, String>();
		cabecalhos.put("X-Riot-Token",token);
		
		JSONObject json = JsonParser.parseToObject(apiSummoner.executar(cabecalhos));
		String  idconta = json.getString("id");
		
		int fotoId = json.getInt("profileIconId");
		
		icone = pegarIcone(fotoId);
		add(icone);
		
		//Api Maestria 
		String url_apiMasteryV4 = String.format("https://br1.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-summoner/%s", idconta);
		Api apiMaestria = new Api(url_apiMasteryV4);
		JSONArray arrayMaestria = JsonParser.parseToArray(apiMaestria.executar(cabecalhos));
		
		
		JSONObject champ = arrayMaestria.getJSONObject(0);
		int idChamp = champ.getInt("championId");
		int nivelMaestria = champ.getInt("championLevel");
		int pontosMaestria = champ.getInt("championPoints");
		System.out.printf("ChampId :%d\nNivel de maestria:%d\nPontos de maestria:%d\n",idChamp,nivelMaestria,pontosMaestria);
		
		lblidchamp = new JLabel("(foto do champ)"+idChamp);
		lblidchamp.setBounds(500, 250, 150, 100);
		add(lblidchamp);
		
		lblpontosMaestria = new JLabel(""+pontosMaestria);
		lblpontosMaestria.setBounds(550,300,50,50);
		add(lblpontosMaestria);
		
		JSONObject champ1 = arrayMaestria.getJSONObject(1);
		int idChamp1 = champ1.getInt("championId");
		int nivelMaestria1 = champ1.getInt("championLevel");
		int pontosMaestria1 = champ1.getInt("championPoints");
		System.out.printf("ChampId :%d\nNivel de maestria:%d\nPontos de maestria:%d\n",idChamp1,nivelMaestria1,pontosMaestria1);
		
		lblidchamp1 = new JLabel("(foto do champ)"+idChamp1);
		lblidchamp1.setBounds(370, 270, 150, 100);
		add(lblidchamp1);
		
		lblpontosMaestria1 = new JLabel(""+pontosMaestria1);
		lblpontosMaestria1.setBounds(400,320,50,50);
		add(lblpontosMaestria1);
		
		JSONObject champ2 = arrayMaestria.getJSONObject(1);
		int idChamp2 = champ2.getInt("championId");
		int nivelMaestria2 = champ2.getInt("championLevel");
		int pontosMaestria2 = champ2.getInt("championPoints");
		System.out.printf("ChampId :%d\nNivel de maestria:%d\nPontos de maestria:%d\n",idChamp2,nivelMaestria2,pontosMaestria2);
		
		lblidchamp2 = new JLabel("(foto do champ)"+idChamp2);
		lblidchamp2.setBounds(630, 270, 150, 100);
		add(lblidchamp2);
		
		lblpontosMaestria2 = new JLabel(""+pontosMaestria2);
		lblpontosMaestria2.setBounds(680,320,50,50);
		add(lblpontosMaestria2);
		
		
		
		//Api ELO
		//Esse for faz com que entre em um JSONObject 
		//Ja esta configurado para pegar os dois elos mostrar nome tier rank e tipo de queue
		String url_apiSummonerElo = String.format("https://br1.api.riotgames.com/lol/league/v4/entries/by-summoner/%s", idconta); 
		Api apiElo = new Api(url_apiSummonerElo);
		JSONArray arrayElo = JsonParser.parseToArray(apiElo.executar(cabecalhos));
		int x = 0;
		for (int indice = 0; indice < arrayElo.length(); indice++) {
			JSONObject elo = arrayElo.getJSONObject(indice);
			String queueType = elo.getString("queueType");
			String tier = elo.getString("tier");
			String rank = elo.getString("rank");
			String nickname = elo.getString("summonerName");
			System.out.printf("Nome invocador :%s\nSeu tier :%s\nSeu rank:%s\nFila:%s\n",nickname,tier,rank,queueType);
			
			JLabel lblnickname = new JLabel(nickname);
			lblnickname.setBounds(350+x, 100, 500, 30);
			add(lblnickname);
			
			JLabel lblrank = new JLabel(tier+" "+rank);
			lblrank.setBounds(350+x, 150, 500, 30);
			add(lblrank);
			
			JLabel lblqueueType = new JLabel(queueType);
			lblqueueType.setBounds(350+x, 50, 500, 30);
			
			add(lblqueueType);
			
			x+=300;
			
		}
		
		voltar = new JButton("Fazer outra pesquisa");
		voltar.setBounds(700, 600, 250, 30);
		add(voltar);
		
	}

	private void ação() 
	{
		voltar.addActionListener( new ActionListener(){
					
					public void actionPerformed(ActionEvent e) {
						
						PegarDados pegar = new PegarDados();
						Teladados.this.dispose();
					}
				});
		
	}
	
	private static JLabel pegarIcone(int fotoId) throws MalformedURLException, IOException {
		
		String fotoUrl = String.format("http://ddragon.leagueoflegends.com/cdn/10.13.1/img/profileicon/%d.png", fotoId);
		JLabel icone = new JLabel("");
		Image imagem = ImageIO.read(new URL(fotoUrl));
		icone.setIcon(new ImageIcon(imagem));
		icone.setBounds(10,50, 300,300);
		return icone;
	}
	
	private static JLabel pegarChamp(int fotoId) throws MalformedURLException, IOException {
		String fotoUrl = String.format("https://ddragon.leagueoflegends.com/cdn/10.13.1/img/champion/%d.png", fotoId);
		JLabel icone = new JLabel("");
		Image imagem = ImageIO.read(new URL(fotoUrl));
		icone.setIcon(new ImageIcon(imagem));
		icone.setBounds(10,10, 300,300);
		return icone;
	}

}
