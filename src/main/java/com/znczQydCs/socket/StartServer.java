package com.znczQydCs.socket;

public class StartServer extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("初始化服务器与工控机间通讯");
		Server server = new Server();
		server.waitClientConnect();
	}

}
