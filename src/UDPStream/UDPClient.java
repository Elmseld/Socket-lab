/**
 * 
 */
package UDPStream;
import java.net.*;
import java.io.*;
/**
 * @author Emily Elmseld 28 apr 2015
 *
 * Klassens tar emot string-paket och skriver ut detta i terminalfönster ihop 
 * med information om serverns Ip-adress och antalet bytes som 
 * strängen/paketen innehåller.
 *  
 */
	public class UDPClient implements Runnable{
		//Skapar den privata variabeln port av typen int
		private int port;
		
		/**
		 * En konstruktor med en parameter, port som tar emot ett portnummer
		 * @param port
		 */
		public UDPClient (int port) {
			this.port = port;
		}
	   
		/**
		 * Metoden som pratar med servern, skickar en förfrågan och tar emot string-paket och skriver ut innehållet i dem i terminalen
		 */
		public void run () {
	      try {
	    	 // Skapar ett nytt DatagramSocket-objekt socket med en parameter, port
	         DatagramSocket socket = new DatagramSocket( port );
	         // Skapar ett nytt DatagramPacket packet
	         DatagramPacket packet;
	         // Skapar variabeln message av typen String
	         String message;
	         //Skapar en variabel data av typen byte-array med en storlek på 65536 byte
	         byte[] data = new byte[ 65536 ];
	         //While-loop som tar hand om svaren från servern
	         while( true ) {
	        	 // skapar ett nytt DatagramPacket med parametrarna data och data.lenght och knyter dem till variabeln packet
	            packet = new DatagramPacket( data, data.length );
	            //Tar emot paket från servern
	            socket.receive( packet );
	            //Lägger in informationen data och bytestorleken från packet-variabel i message-variabeln
	            message=new String(packet.getData(),0,packet.getLength(),"ISO-8859-1");
	            // Skriver ut meddelandet i terminalen i formatet, ip-adress: medelandet : (medelandets längd i byte) bytes  
	            System.out.println( packet.getAddress().toString() + ": " + message + " : " + message.length() + " bytes"); 
	         }
	         // Fångar och skriver ut problem som kan uppstå i kommunikationen mellan klient-server 
	      } catch(IOException e) {
	         System.out.println( e );
	      }
	   }
		
		/**
		 * Metod med en parammeter, port som skapar ett nytt UDPClient-objekt, reciver och en ny tråd med variabelnamnet thread
		 * @param port tar emot ett portnummer här 4444
		 */
		public static void receive( int port) {
			//Skapar ett nytt UDPClient-objekt, receiver som en referens till UDPClient-konstruktorn med en variabel, port 
			UDPClient receiver = new UDPClient ( port );
			//Skapar en ny tråd med variabelnamnet thread som tar med sig variabeln receiver
			Thread thread = new Thread( receiver);
			//Startar tråden
			thread.start();
		}
	   
		/**
		 * Metod som sätter portnumret, här till 4444
		 * @param args
		 */
	   public static void main( String[] args ) {
		   UDPClient.receive( 4444 );
	   }

	}
