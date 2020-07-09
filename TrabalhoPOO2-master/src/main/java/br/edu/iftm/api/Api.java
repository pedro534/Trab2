package br.edu.iftm.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

public class Api {
	private HttpTransport TRANSPORT;
	private GenericUrl url;
	
	public Api(String url) {
		this.url = new GenericUrl(url);
		
	}
	
	public Api(GenericUrl genericUrl) {
		this.url = genericUrl;
	}
	
	/**
	 * Executa a requisição com a URL informada.
	 * @return Retorna uma resposta HTTP
	 * @throws IOException
	 */
	public HttpResponse executar() throws IOException {
		return executar(new HashMap<String, String>());
	}
	
	/**
	 * Executa a requisição com a URL informada, sendo necessário informar os parâmetros do cabeçalho
	 * @param cabecalhos Lista mapeada contendo os parâmetros do cabeçalho
	 * @return Resposta da requisição HTTP
	 * @throws IOException
	 */
	public HttpResponse executar(Map<String, String> cabecalhos) throws IOException {
		HttpRequest requisicao = reqFactory().buildGetRequest(url);
		HttpHeaders cabecalho = new HttpHeaders();
		for(String chave : cabecalhos.keySet()) {
			//System.out.printf("Adicionado a chave %s com o valor %s\n", chave, cabecalhos.get(chave));
			cabecalho.set(chave, cabecalhos.get(chave));
		}
		
		requisicao.setHeaders(cabecalho);
		HttpResponse resposta = requisicao.execute();
		return resposta;
	}
	// Métodos obrigatórios para fazer a requisição com a API
	private HttpTransport transport() {
	    if (null == TRANSPORT) {
	        TRANSPORT = new NetHttpTransport();
	    }
	    return TRANSPORT;
	}
	
	private HttpRequestFactory REQ_FACTORY;
	 
	private HttpRequestFactory reqFactory() {
	    if (null == REQ_FACTORY) {
	        REQ_FACTORY = transport().createRequestFactory();
	    }
	    return REQ_FACTORY;
	}
}
