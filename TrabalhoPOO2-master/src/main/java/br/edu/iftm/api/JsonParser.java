package br.edu.iftm.api;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.api.client.http.HttpResponse;

public class JsonParser {
	public static JSONObject parseToObject(HttpResponse resposta) throws IOException {
		String respostaEmJson = resposta.parseAsString();
		JSONObject respostaJsonObject = new JSONObject(respostaEmJson);
		return respostaJsonObject;
	}
	
	public static JSONArray parseToArray(HttpResponse resposta) throws IOException {
		String respostaEmJson = resposta.parseAsString();
		JSONArray respostaJsonObject = new JSONArray(respostaEmJson);
		return respostaJsonObject;
	}
}
