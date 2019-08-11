package br.com.ufabc.sistemasdistribuidos.ep3.coordenador.tcp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
	private Socket socket;
	private Scanner scanner;

	public TCPClient(String host, int serverPort) throws Exception {
		if (host == null)
			this.socket = new Socket(InetAddress.getLocalHost(), serverPort);
		else
			this.socket = new Socket(InetAddress.getByName(host), serverPort);
		this.scanner = new Scanner(System.in);
	}

	public void close() throws IOException {
		socket.close();
	}

	public void enviaMensagem(String msg) throws Exception {

		System.out.println("Enviando: " + msg);

		// envia as urls pro coordenador
		PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
		out.println(msg);
		out.flush();
	}
}
