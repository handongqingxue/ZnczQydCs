package com.znczQydCs.socket;

import java.util.Vector;

public class ProxySet {
	
	private static Vector<SocketProxy> proxys = new Vector<SocketProxy>();
	
	public static void addSocketProxy(SocketProxy proxy){
		proxys.add(proxy);
		System.out.println("proxysSize="+proxys.size());
	}
	
	public static void removeSocketProxy(SocketProxy proxy){
		proxys.remove(proxy);
		System.out.println(proxy.getBfNoFlag()+" 离开了");
		//sayToAllProxy(proxy.getBfNoFlag()+" 离开了", proxy);
		
	}
	
	public static void sayToAllProxy(String mes,SocketProxy sender){
		for(int i=0;i<proxys.size();i++){
			if(proxys.get(i)!=sender){
				proxys.get(i).sayToMe(sender.getBfNoFlag()+":"+mes);
			}
			
		}
	}
	
	public static void sayToClient(String mes,int bfNoFlag){
		System.out.println("proxys.size()==="+proxys.size());
		for(int i=0;i<proxys.size();i++){
			System.out.println("getBfNoFlag==="+proxys.get(i).getBfNoFlag());
			System.out.println("bfNoFlag==="+bfNoFlag);
			if(proxys.get(i).getBfNoFlag()==bfNoFlag){
				System.out.println("sayToMemes==="+mes);
				proxys.get(i).sayToMe(mes);
			}
			
		}
	}
}