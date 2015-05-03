/**
 * 
 */
package UDPStream;
import java.net.*;
import java.io.*;
/**
 * @author Emily Elmseld 28 apr 2015
 *
 */
	public class UDPClient implements Runnable{
		private int port;
		
		public UDPClient (int port) {
			this.port = port;
		}
	   
		
		public void run () {
	      try {
	         DatagramSocket socket = new DatagramSocket( port );
	         DatagramPacket packet;
	         String message;
	         byte[] data = new byte[ 256 ];
	         while( true ) {
	            packet = new DatagramPacket( data, data.length );
	            socket.receive( packet );
	            message=new String(packet.getData(),0,packet.getLength(),"ISO-8859-1");
	            System.out.println( packet.getAddress().toString() + ": " + message + " : byte " + message.length()); // Skriver ut meddelandet
	         }
	      } catch(IOException e) {
	         System.out.println( e );
	      }
	   }
		
		public static void receive( int port) {
			UDPClient receiver = new UDPClient ( port );
			Thread thread = new Thread( receiver);
			thread.start();
		}
	   
	   public static void main( String[] args ) {
		   UDPClient.receive( 4444 );
	   }

	}
