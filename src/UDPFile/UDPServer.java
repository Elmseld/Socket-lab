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
		File file = new File("/Users/emilyelmseld/Documents/fil-lista/tidsplan-emily.elmseld.pdf/");
		FileInputStream fis = new FileInputStream(file);
		byte[] b = new byte[(int)file.length()];
		fis.read(b,0, b.length);
		DatagramSocket ds = new DatagramSocket(2000);
		DatagramPacket dp = new DatagramPacket(b, b.length, InetAddress.getByName("192.168.1.15"),2000);
		ds.send(dp);
		
	}

}
