package UDPFile;

import java.io.*;
import java.net.*;

/**
 * @author Emily Elmseld 28 apr 2015
 *
 */
public class UDPServer {

	public static void main(String args[]) throws IOException, SocketException {
		DataInputStream dis = new DataInputStream(System.in);
		File file = new File("/Users/emilyelmseld/Documents/fil-lista/");
		FileInputStream fis = new FileInputStream(file);
		byte[] b = new byte[(int)file.length()];
		fis.read(b,0, b.length);
		DatagramSocket ds = new DatagramSocket(1000);
		DatagramPacket dp = new DatagramPacket(b, b.length, InetAddress.getLocalHost(),2000);
		ds.send(dp);
		
	}

}
