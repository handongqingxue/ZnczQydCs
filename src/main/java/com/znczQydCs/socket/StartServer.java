package com.znczQydCs.socket;

public class StartServer extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("��ʼ���������빤�ػ���ͨѶ");
		Server server = new Server();
		server.waitClientConnect();
	}

}
