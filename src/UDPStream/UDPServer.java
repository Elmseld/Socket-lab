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
public class UDPServer {
	    public static void send( String ip, int port, String[] messages ) {
	        try {
	            InetAddress receiver = InetAddress.getByName( ip );
	            DatagramSocket socket = new DatagramSocket();
	            DatagramPacket packet;
	            byte[] data;
	            
	            for( int i = 0; i < messages.length; i++ ) { // Skicka sträng för sträng
	                data = messages[ i ].getBytes("ISO-8859-1");
	                packet = new DatagramPacket( data, data.length, receiver, port );
	                socket.send( packet );
	                Thread.sleep( 3000 );
	            }
	        } catch( IOException e1 ) {
	            System.out.println( e1 );
	        } catch( InterruptedException e2 ) {
	            System.out.println( e2 );
	        }
	    }

	    public static void main( String[] args ) {
	        String[] meddelanden = { "Veni, vidi, vici", 
	                                 "Jag kom, jag såg, jag segrade",
	                                 "Alea iacta est", 
	                                 "Tärningen är kastad",
	                                 "Et tu Brute", 
	                                 "Även du, min käre Brutus"
	                                 };
	        try {
	            Thread.sleep( 5000 ); // vänta 5 sek med att starta sändningen
	        } catch ( InterruptedException e ) {}
	        UDPServer.send( "192.168.1.7", 4444, meddelanden );
	    }
	}