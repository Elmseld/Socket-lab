package UDPFile;

import java.io.*;
import java.net.*;
import java.util.Random;

/**
 * 
 */

/**
 * @author Emily Elmseld 28 apr 2015
 * Klass som skickar en request till en serverklass och tar 
 * emot en fil från serverklassen och sparar ner denna och 
 * ger den ett namn
 */
public class UDPClient {
	
	  public static void main(String[] args) {
		  //Skapar variabeln socket av typen DatagramSocket med värdet null
		  DatagramSocket socket = null;
		  //Sätter portnummret till 12000
		  int port = 12000;
		  //Skapar variabeln bytes av typen byte[] med värdet 256 bytes
		  byte[] bytes = new byte[256];

		  		  
		  try {
			  //Sätter variabeln socket till en ny datagramSocket;
			  socket = new DatagramSocket();
			  //Skapar en lokal variabel host av typen  InetAddress och sätter den till servern ip-nummer
			  InetAddress host = InetAddress.getByName("192.168.1.9");
			  
			  //while-loop som tar hand om kommunikationen mellan klient och server 
			  while(true) {
				  //Skapar variabeln requestDP av typer DatagramPacket med fyra parametrar, bytes, bytes.length, host och port
				  DatagramPacket requestDP = new DatagramPacket(bytes, bytes.length, host, port);
				  //Skickar requesten till servern
				  socket.send(requestDP);
				  
				  
			      // Skapar en variabel buffer av typen byte-array med en storlek på 65536 byte
				  byte[] buffer = new byte[65536];
				  // Skapar ett nytt DatagramPacket med parametrarna data och data.lenght och knyter dem till variabeln packet
				  DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
		          // Tar emot paket från servern
				  socket.receive(reply);
				  
			      // Skapar en variabel buffer av typen byte-array och lägger in datan från serverpacket
				  byte[] data = reply.getData();
				  // Skapar en variabel diff av typen int som tar emot ett tal från metoden randInt(min, max) 
				  int diff = randInt(0, 500);
				  // Skapar en ny fil med namnet bixie + diff-nummret + .pdf
				  FileOutputStream fos = new FileOutputStream("bixie" + diff + ".pdf");
				  // Skapar en ny variabel bos av typen BufferedOutputStream med en parameter till den nya filen som skapades i förra raden
				  BufferedOutputStream bos = new BufferedOutputStream(fos);
				  // Lägger in datan från server i den nya filen och anger längden
				  bos.write(data, 0, reply.getLength());
				  // Stänger BufferedOutputStream
				  bos.close();

				    
				  //Skriver ut meddelandet i terminalen i formatet,  Klientens ip-adress : Klienten portnr - namnet på filen som sparas - färdig
				  System.out.println(reply.getAddress().getHostAddress() + " : " + reply.getPort() + " bixie" + diff + ".pdf - färdig");
				  //För att avbryta mottagningen av filer.
				  socket.close();

			  }
		  }
	      // Fångar och skriver ut problem som kan uppstå i kommunikationen mellan klient-server 
		  catch(IOException e) {
			  System.err.println("IOException " + e);
		  }
		  
	}

		/**
		 * Metod som skapar ett random nummer mellan variablarna min och max för att skapa olika namn på de nedsparade filerna.
		 * @param min det minsta värdet
		 * @param max det högsta värdet
		 * @return returnerar randomNum, de talet som slumpats fram
		 */
		public static int randInt(int min, int max) {
		    Random rand = new Random();
		    int randomNum = rand.nextInt((max - min) + 1) + min;
		    return randomNum;
		}
}
