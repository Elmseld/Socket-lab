package UDPFile;

import java.io.*;
import java.net.*;

/**
 * @author Emily Elmseld 28 apr 2015
 * 
 * En UDP-Server-klass som skickar en specifik fil till en klientklass över en UDP-socket.
 *
 */
public class UDPServer {

	public static void main(String args[]) {
		//Skapar en variabel socket av typen DatagramSocket med värdet null.
		DatagramSocket socket = null;
		
		try
		{
			// Skapar en server-socket, parametern är portnr som används här 12000
			socket = new DatagramSocket(12000);
			// Skapar variabeln file med en referens till en specifik fil
			File file = new File("/Users/emilyelmseld/Documents/fil-lista/tidsplan-emily.elmseld.pdf/");
			
			//buffer till mottagen data med storlek 65536 byte
			byte[] buffer = new byte[65536];
			// Skapar ett nytt datagrampaket med variabelnamnet incoming med en referens till mottagen data
			DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
			
			// Skriver ut ett meddelande i terminalen, Server socket created. Waiting for incoming data... 
			System.out.println("Server socket created. Waiting for incoming data...");
			
			//while-loop som tar hand om kommunikationen mellan klient och server
			while(true) {
				// Tar emot data från servern
				socket.receive(incoming);
				// Skapa buffert-variabeln data med referens till inkommen data
				byte[] data = incoming.getData();
				// Skapar en ny String s med referenser till inkommen data och datastorleken
				String s = new String(data, 0, incoming.getLength());
				
				//Skriver ut informationen om inkommen data i terminalen i formatet - Klient ip-nummer : klientporten - Klient meddelandet
				System.out.println(incoming.getAddress().getHostAddress() + " : " + incoming.getPort() + " - " + s);
				
				// Skapar buffert-variabeln mybytearray med referens till filen storlek
				byte[] mybytearray = new byte[(int) file.length()];
					// Skapar en ny BufferInputStream med variabelnamnet bis som refererar till en ny FileInputStream med filen som ska skickas till klienten 
			      	BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			      	//Läser in filens data och storlek
			      	bis.read(mybytearray, 0, mybytearray.length);
				// skapar ett nytt datagramPaket med variabelnamnet dp med parametrarna mybytearray, mybytearray.length, incoming.getAddress(), incoming.getPort()
				DatagramPacket dp = new DatagramPacket(mybytearray, mybytearray.length, incoming.getAddress(), incoming.getPort());
				// Skickar DatagramPaketet dp till klienten via socketen
				socket.send(dp);
			}
		}
		//Kastar exception vid problem i kommunikationen mellan klient och server 
		catch(IOException e) {
			System.err.println("IOExcetion" + e);
		}
	}
}
