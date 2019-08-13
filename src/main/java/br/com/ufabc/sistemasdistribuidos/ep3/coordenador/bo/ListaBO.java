package br.com.ufabc.sistemasdistribuidos.ep3.coordenador.bo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ufabc.sistemasdistribuidos.ep3.coordenador.dto.Dto;
import br.com.ufabc.sistemasdistribuidos.ep3.coordenador.tcp.TCPClient;

public class ListaBO {
	private int nmappers;

	public ListaBO(int nmappers) {
		this.nmappers = nmappers;
	}

	public void distribuiLista(String lista) {
		
		//traduz o dto que o cliente mandou
		Dto dto = new Dto();
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			dto = mapper.readValue(lista, Dto.class);
			lista = dto.getUrls();
			
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		List<String> urls = new ArrayList<String>(Arrays.asList(lista.split(",")));
		
		int inicio = 0;
		int fim = Math.floorDiv(urls.size(), nmappers);
		
		for (int i = 0; i < nmappers; i++) {
			String mensagem = String.join(",", urls.subList(inicio, fim));
			
			dto.setUrls(mensagem);
			try {
				TCPClient client = new TCPClient(null, 8081);
				client.enviaMensagem(mapper.writeValueAsString(dto));
				client.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
