package UDPFile;

import java.io.*;
import java.net.*;

/**
 * 
 */

/**
 * @author Emily Elmseld 28 apr 2015
 *
 */
public class UDPClient {
	
	  public static void main(String[] args) throws IOException, SocketException {
		  byte[] b = new byte[3000];
		  DatagramSocket ds = new DatagramSocket(2000);
		  DatagramPacket dp = new DatagramPacket(b, b.length);

		  FileOutputStream fos = new FileOutputStream(new File("/Users/emilyelmseld/Downloads/"));
		  ds.receive(dp);
		  byte[] b1 = new byte[dp.getLength()];
		  fos.write(b, 0, b1.length);
		  
		  }

}
