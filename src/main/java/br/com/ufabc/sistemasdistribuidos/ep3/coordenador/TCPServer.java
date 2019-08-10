package br.com.ufabc.sistemasdistribuidos.ep3.coordenador;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

class TCPServer {
	private ServerSocket server;

	public TCPServer(String ipAddress) throws Exception {
		if (ipAddress != null && !ipAddress.isEmpty())
			this.server = new ServerSocket(0, 1, InetAddress.getByName(ipAddress));
		else
			this.server = new ServerSocket(0, 1, InetAddress.getLocalHost());
	}

	private void listen() throws Exception {
		String data = null;
		Socket client = this.server.accept();
		String clientAddress = client.getInetAddress().getHostAddress();
		System.out.println("\r\nNew connection from " + clientAddress);

		BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		while ((data = in.readLine()) != null) {
			System.out.println("\r\nMessage from " + clientAddress + ": " + data);
		}
	}

	public InetAddress getSocketAddress() {
		return this.server.getInetAddress();
	}

	public int getPort() {
		return this.server.getLocalPort();
	}

	public static void main(String[] args) throws Exception {
		TCPServer app;
		
		if(args.length > 0)
			app = new TCPServer(args[0]);
		else
			app = new TCPServer(null);
		
		System.out.println(
				"\r\nRunning Server: " + "Host=" + app.getSocketAddress().getHostAddress() + " Port=" + app.getPort());

		app.listen();
	}
}
