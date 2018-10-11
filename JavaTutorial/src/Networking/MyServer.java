package Networking;

import java.io.*;
import java.net.*;

public class MyServer {
	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(3333);
		Socket socket = server.accept();
		DataInputStream din = new DataInputStream(socket.getInputStream());
		DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = "", str2 = "";
		while(!str.equals("stop")){
			str = din.readUTF();
			System.out.println(str);
			str2 = br.readLine();
			dout.writeUTF(str2);
			dout.flush();
		}
		din.close();
		server.close();
		socket.close();
		
	}
}
