package br.com.ufabc.sistemasdistribuidos.ep3.coordenador.bo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.ufabc.sistemasdistribuidos.ep3.coordenador.tcp.TCPClient;

public class ListaBO {
	private int nmappers;

	public ListaBO(int nmappers) {
		this.nmappers = nmappers;
	}

	public void distribuiLista(String lista) {
		List<String> urls = new ArrayList<String>(Arrays.asList(lista.split(",")));
		
		int inicio = 0;
		int fim = Math.floorDiv(urls.size(), nmappers);
		
		for (int i = 0; i < nmappers; i++) {
			String mensagem = String.join(",", urls.subList(inicio, fim));
			
			try {
				TCPClient client = new TCPClient(null, 8081);
				client.enviaMensagem(mensagem);
				client.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
