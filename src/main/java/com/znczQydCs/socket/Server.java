package com.znczQydCs.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private int serverPort = 8000;
	private ServerSocket serverSocket;

	public Server() {
		this.initServer();
	}

	private void initServer() {
		try {
			serverSocket = new ServerSocket(this.serverPort);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void waitClientConnect() {
		if (this.serverSocket == null)
			return;
		try {
			while (true) {
				System.out.println("等待客户端连接...");
				Socket socket = serverSocket.accept();
				System.out.println(((InetSocketAddress) socket
						.getRemoteSocketAddress()).getAddress());
				
				try {
					SocketProxy proxy = new SocketProxy(socket);
					ProxySet.addSocketProxy(proxy);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Server server = new Server();
		server.waitClientConnect();

	}

}
