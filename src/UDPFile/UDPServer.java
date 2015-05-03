package UDPFile;

import java.io.*;
import java.net.*;

/**
 * @author Emily Elmseld 28 apr 2015
 *
 */
public class UDPServer {

	public static void main(String args[]) {
	
		DatagramSocket socket = null;
		
		try
		{
			//1. Creating a server socket, parameter is local port number
			socket = new DatagramSocket(12000);
			File file = new File("/Users/emilyelmseld/Documents/fil-lista/tidsplan-emily.elmseld.pdf/");
			
			//buffer to receive incoming data
			byte[] buffer = new byte[65536];
			DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
			
			//2. Wait for an incoming data
			echo("Server socket created. Waiting for incoming data...");
			
			//communication loop
			while(true) {
				socket.receive(incoming);
				byte[] data = incoming.getData();
				String s = new String(data, 0, incoming.getLength());
				
				//echo the details of incoming data - client ip : clinet port - client message
				echo(incoming.getAddress().getHostAddress() + " : " + incoming.getPort() + " - " + s);
				
				byte[] mybytearray = new byte[(int) file.length()];
			      BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			      bis.read(mybytearray, 0, mybytearray.length);
				
				DatagramPacket dp = new DatagramPacket(mybytearray, mybytearray.length, incoming.getAddress(), incoming.getPort());
				socket.send(dp);
			}
		}
		catch(IOException e) {
			System.err.println("IOExcetion" + e);
		}
	}
	//Simple function to echo data to terminal
	public static void echo(String msg) {
		System.out.println(msg);
	}
}
