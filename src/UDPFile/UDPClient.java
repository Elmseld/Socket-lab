package UDPFile;

import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author Emily Elmseld 28 apr 2015
 *
 */
public class UDPClient {
	
	  public static void main(String[] args) {
		  DatagramSocket socket = null;
		  int port = 12000;
		  String s;
		  byte[] b = new byte[256];

		  
//		  BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
		  
		  try {
			  socket = new DatagramSocket();
			  
			  InetAddress host = InetAddress.getByName("192.168.1.9");
			  
			  while(true) {
				  DatagramPacket dp = new DatagramPacket(b, b.length, host, port);
				  socket.send(dp);
				  
				  //now receive reply
				  //buffer to receive incoming data
				  byte[] buffer = new byte[65536];
				  DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
				  socket.receive(reply);
				  
				  byte[] data = reply.getData();
				  s = new String(data, 0 , reply.getLength());
				  
				  	int p = randInt(0, 500);
				    FileOutputStream fos = new FileOutputStream("bixie" + p + ".pdf");
				    BufferedOutputStream bos = new BufferedOutputStream(fos);
				    bos.write(data, 0, reply.getLength());
				    bos.close();

				    
				  //echo the details of incoming data - client ip : client port - client message
				  System.out.println(reply.getAddress().getHostAddress() + " : " + reply.getPort() + " - färdig");
				  //För att avbryta mottagningen av filer.
				  socket.close();

			  }
		  }
		  catch(IOException e) {
			  System.err.println("IOException " + e);
		  }
		  
	}


public static int randInt(int min, int max) {

    // NOTE: Usually this should be a field rather than a method
    // variable so that it is not re-seeded every call.
    Random rand = new Random();

    // nextInt is normally exclusive of the top value,
    // so add 1 to make it inclusive
    int randomNum = rand.nextInt((max - min) + 1) + min;

    return randomNum;
}
}
